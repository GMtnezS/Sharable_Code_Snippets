window.onload = () => {
  const canvas = document.getElementById("gl-canvas");
  const gl = canvas.getContext("webgl");
  if (!gl) return alert("WebGL not supported");

  // Resize & viewport
  function resize() {
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;
    gl.viewport(0, 0, canvas.width, canvas.height);
  }
  window.addEventListener("resize", resize);
  resize();

  // Enable depth test (textures + painter’s is overkill)
  gl.enable(gl.DEPTH_TEST);

  // Face normals + placeholder image URLs
  const faceConfigs = [
    { normal: [0, 0, 1], url: "imgs/6.png" },
    { normal: [0, 0, -1], url: "imgs/1.png" },
    { normal: [0, 1, 0], url: "imgs/2.png" },
    { normal: [0, -1, 0], url: "imgs/3.png" },
    { normal: [1, 0, 0], url: "imgs/4.png" },
    { normal: [-1, 0, 0], url: "imgs/5.png" },
  ];

  // Create textures array
  const textures = faceConfigs.map(fc => {
    const tex = gl.createTexture();
    gl.bindTexture(gl.TEXTURE_2D, tex);
    // placeholder 1×1 pixel while loading
    gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, 1, 1, 0, gl.RGBA, gl.UNSIGNED_BYTE,
      new Uint8Array([200, 200, 200, 255]));
    // load image
    const img = new Image();
    img.src = fc.url;
    img.onload = () => {
      gl.bindTexture(gl.TEXTURE_2D, tex);
      gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, img);
      gl.generateMipmap(gl.TEXTURE_2D);
    };
    return tex;
  });

  // Shaders with texcoord
  const vs = `
      attribute vec3 aPosition;
      attribute vec2 aTexCoord;
      uniform mat4 uMatrix;
      varying vec2 vTexCoord;
      void main() {
        gl_Position = uMatrix * vec4(aPosition,1.0);
        vTexCoord = aTexCoord;
      }
    `;
  const fs = `
      precision mediump float;
      varying vec2 vTexCoord;
      uniform sampler2D uTexture;
      void main(){
        gl_FragColor = texture2D(uTexture, vTexCoord);
      }
    `;
  function compile(src, type) {
    const s = gl.createShader(type);
    gl.shaderSource(s, src);
    gl.compileShader(s);
    if (!gl.getShaderParameter(s, gl.COMPILE_STATUS))
      throw gl.getShaderInfoLog(s);
    return s;
  }
  const prog = gl.createProgram();
  gl.attachShader(prog, compile(vs, gl.VERTEX_SHADER));
  gl.attachShader(prog, compile(fs, gl.FRAGMENT_SHADER));
  gl.linkProgram(prog);
  gl.useProgram(prog);

  // locations
  const aPosition = gl.getAttribLocation(prog, "aPosition");
  const aTexCoord = gl.getAttribLocation(prog, "aTexCoord");
  const uMatrix = gl.getUniformLocation(prog, "uMatrix");
  const uTexture = gl.getUniformLocation(prog, "uTexture");

  // Buffers
  const posBuf = gl.createBuffer();
  const texBuf = gl.createBuffer();
  const idxBuf = gl.createBuffer();

  // Helpers
  function makePerspective(fov, aspect, near, far) {
    const f = 1 / Math.tan(fov / 2), r = 1 / (near - far);
    return new Float32Array([
      f / aspect, 0, 0, 0,
      0, f, 0, 0,
      0, 0, (near + far) * r, -1,
      0, 0, near * far * 2 * r, 0
    ]);
  }
  function mul(a, b) {
    const o = new Float32Array(16);
    for (let i = 0; i < 4; i++)for (let j = 0; j < 4; j++) {
      o[i * 4 + j] =
        a[0 * 4 + j] * b[i * 4 + 0] +
        a[1 * 4 + j] * b[i * 4 + 1] +
        a[2 * 4 + j] * b[i * 4 + 2] +
        a[3 * 4 + j] * b[i * 4 + 3];
    }
    return o;
  }

  // Rotation state
  let drag = false, x0 = 0, y0 = 0, rx = 0, ry = 0;
  canvas.addEventListener("mousedown", e => { drag = true; x0 = e.clientX; y0 = e.clientY; });
  canvas.addEventListener("mouseup", () => drag = false);
  canvas.addEventListener("mousemove", e => {
    if (!drag) return;
    ry += (e.clientX - x0) * 0.01;
    rx += (e.clientY - y0) * 0.01;
    x0 = e.clientX; y0 = e.clientY;
  });

  function draw() {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    // Projection × view-model
    const P = makePerspective(Math.PI / 4, canvas.width / canvas.height, 0.1, 100);
    const cx = Math.cos(rx), sx = Math.sin(rx);
    const cy = Math.cos(ry), sy = Math.sin(ry);
    const RX = new Float32Array([1, 0, 0, 0, 0, cx, -sx, 0, 0, sx, cx, 0, 0, 0, 0, 1]);
    const RY = new Float32Array([cy, 0, sy, 0, 0, 1, 0, 0, -sy, 0, cy, 0, 0, 0, 0, 1]);
    const T = new Float32Array([1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, -6, 1]);
    const VM = mul(T, mul(RY, RX));
    const M = mul(P, VM);

    gl.uniformMatrix4fv(uMatrix, false, M);

    // For each face: compute corners, texcoords, indices, then draw
    faceConfigs.forEach((cfg, i) => {
      // compute corners
      const [nx, ny, nz] = cfg.normal;
      const tangent = ny ? [1, 0, 0] : [0, 1, 0];
      const bitangent = [
        ny * tangent[2] - nz * tangent[1],
        nz * tangent[0] - nx * tangent[2],
        nx * tangent[1] - ny * tangent[0]
      ];
      const n = cfg.normal, t = tangent, b = bitangent;
      const corners = [
        [-t[0] - b[0], -t[1] - b[1], -t[2] - b[2]],
        [t[0] - b[0], t[1] - b[1], t[2] - b[2]],
        [t[0] + b[0], t[1] + b[1], t[2] + b[2]],
        [-t[0] + b[0], -t[1] + b[1], -t[2] + b[2]]
      ].map(o => o.map((v, j) => v + n[j]));

      // upload positions
      const posData = [].concat(...corners);
      gl.bindBuffer(gl.ARRAY_BUFFER, posBuf);
      gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(posData), gl.STATIC_DRAW);
      gl.vertexAttribPointer(aPosition, 3, gl.FLOAT, false, 0, 0);

      // upload texcoords (we map entire image to each face)
      const texData = [
        0, 0, 1, 0, 1, 1, 0, 1
      ];
      gl.bindBuffer(gl.ARRAY_BUFFER, texBuf);
      gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(texData), gl.STATIC_DRAW);
      gl.vertexAttribPointer(aTexCoord, 2, gl.FLOAT, false, 0, 0);

      // upload indices
      gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, idxBuf);
      gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, new Uint16Array([
        0, 1, 2, 0, 2, 3
      ]), gl.STATIC_DRAW);

      // bind this face’s texture
      gl.activeTexture(gl.TEXTURE0);
      gl.bindTexture(gl.TEXTURE_2D, textures[i]);
      gl.uniform1i(uTexture, 0);

      // draw
      gl.drawElements(gl.TRIANGLES, 6, gl.UNSIGNED_SHORT, 0);
    });

    requestAnimationFrame(draw);
  }
  draw();
};
