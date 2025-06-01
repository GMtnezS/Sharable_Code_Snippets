window.onload = () => {

  // ------------------------------ //
  //        Boilerplate setup       //
  // ------------------------------ //
  const canvas = document.getElementById("canvas");
  const gl = canvas.getContext("webgl");
  if (!gl) return alert("WebGL not supported");

  canvas.width = canvas.clientWidth;
  canvas.height = canvas.clientHeight;

  gl.viewport(0, 0, canvas.width, canvas.height);
  gl.clearColor(1, 1, 1, 1);
  gl.clear(gl.COLOR_BUFFER_BIT);

  // ------------------------------ //
  //        Sierpinski Gasket       //
  // ------------------------------ //

  // Define the outer triangle vertices
  const A = [0, 1], B = [-1, -1], C = [1, -1];

  // Queue holds rendering tasks to be executed step-by-step
  const queue = [];

  // Stores flattened triangle vertex data for the WebGL buffer
  const points = [];

  // Calculate midpoint between two vertices
  const midpoint = (p1, p2) => [
    (p1[0] + p2[0]) / 2,
    (p1[1] + p2[1]) / 2
  ];

  // Enqueue drawing of a triangle outline (with closing vertex to make a loop)
  const enqueueOutline = (a, b, c) => {
    queue.push(() => {
      points.push(...a, ...b, ...c, ...a);
      gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(points), gl.STATIC_DRAW);
      gl.clear(gl.COLOR_BUFFER_BIT);
      let offset = 0;
      while (offset < points.length) {
        gl.drawArrays(gl.LINE_LOOP, offset / 2, 4);
        offset += 8;
      }
    });
  };

  // Control how many recursive levels to draw and how quickly to draw them
  const depth = 8;
  const interval = 1;

  // Level 0: Outer shell 
  enqueueOutline(A, B, C);

  // Level 1: First 3 Subdivisions
  const A1 = midpoint(A, B);
  const A2 = midpoint(B, C);
  const A3 = midpoint(C, A);

  // Add Level 1 triangle outlines, or skip if Lv0 (for screenshooting purposes)
  if (depth > 0) {
    // Top triangle
    enqueueOutline(A, A1, A3);

    // Bottom-left triangle
    enqueueOutline(B, A2, A1);

    // Bottom-right triangle
    enqueueOutline(C, A3, A2);
  }

  // Initialize base triangle objects for Level 2+
  let triangles = [
    { label: 'A', points: [A, A1, A3] },
    { label: 'B', points: [B, A2, A1] },
    { label: 'C', points: [C, A3, A2] }
  ];

  // ------------------------------ //
  //  Levels 2 to depth: Recursive Gasket Building to Visualize EACH STEP At a Time
  // ------------------------------ //
  for (let level = 2; level <= depth; level++) {
    console.log(`Level ${level}: ${triangles.length} triangles`);

    const nextLevel = [];

    for (const tri of triangles) {
      const [p1, p2, p3] = tri.points;
      const label = tri.label;

      // Order vertices as top, left, right for consistent subdivision
      const [top, left, right] = [p1, p2, p3].sort((a, b) => {
        // Y-axis descending
        if (b[1] !== a[1]) return b[1] - a[1];
        // X-axis ascending
        return a[0] - b[0];
      });

      // Compute new midpoints for each triangle
      // top-left edge
      const m1 = midpoint(top, left);
      // top-right edge
      const m2 = midpoint(top, right);
      // base edge
      const m3 = midpoint(left, right);

      // Enqueue the 3 new sub-triangles
      // top sub-triangle
      enqueueOutline(top, m1, m2);
      nextLevel.push({ label: label + 'a', points: [top, m1, m2] });

      // bottom-left sub-triangle
      enqueueOutline(left, m3, m1);
      nextLevel.push({ label: label + 'b', points: [left, m3, m1] });

      // bottom-right sub-triangle
      enqueueOutline(right, m2, m3);
      nextLevel.push({ label: label + 'c', points: [right, m2, m3] });
    }

    // Prepare next iteration's triangle list
    triangles = nextLevel;
  }

  // ------------------------------ //
  //            Shaders             //
  // ------------------------------ //

  // Vertex shader positions each point in clip space
  const vertexShader = `
  attribute vec2 aPosition;
  void main() {
    gl_Position = vec4(aPosition, 0.0, 1.0);
  }
`;

  // Fragment shader colors everything red
  const fragmentShader = `
  void main() {
    gl_FragColor = vec4(1, 0, 0, 1);
  }
`;

  // Helper to compile shader source
  const compile = (src, type) => {
    const shader = gl.createShader(type);
    gl.shaderSource(shader, src);
    gl.compileShader(shader);
    if (!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
      throw new Error(gl.getShaderInfoLog(shader));
    }
    return shader;
  };

  const vs = compile(vertexShader, gl.VERTEX_SHADER);
  const fs = compile(fragmentShader, gl.FRAGMENT_SHADER);

  // ------------------------------ //
  //    Set Up Program and Buffer   //
  // ------------------------------ //

  const program = gl.createProgram();
  gl.attachShader(program, vs);
  gl.attachShader(program, fs);
  gl.linkProgram(program);
  gl.useProgram(program);

  const buffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, buffer);

  const pos = gl.getAttribLocation(program, 'aPosition');
  gl.enableVertexAttribArray(pos);
  gl.vertexAttribPointer(pos, 2, gl.FLOAT, false, 0, 0);


  function step() {
    if (queue.length === 0) return;
    const next = queue.shift();
    next();
    // Controls rendering pace
    setTimeout(step, interval);
  }

  // Triggers animation sequence
  step();
};
