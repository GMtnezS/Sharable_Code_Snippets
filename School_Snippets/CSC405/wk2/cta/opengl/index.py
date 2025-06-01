from OpenGL.GL import *
from OpenGL.GLUT import *
from OpenGL.GLU import *
import numpy as np
import time

# Outer triangle vertices
A = np.array([0.0, 1.0])
B = np.array([-1.0, -1.0])
C = np.array([1.0, -1.0])

# Queue holds rendering tasks to be executed step-by-step
queue = []

# Control how many recursive levels to draw and how quickly to draw them
depth = 8
interval = 1 

# Calculate midpoint between two vertices
def midpoint(p1, p2):
    return (p1 + p2) / 2

# Enqueue drawing of a triangle outline (with closing vertex to make a loop)
def enqueueOutline(a, b, c):
    def draw():
        glBegin(GL_LINE_LOOP)
        glVertex2f(a[0], a[1])
        glVertex2f(b[0], b[1])
        glVertex2f(c[0], c[1])
        glEnd()
        glFlush()
    queue.append(draw)

# Recursive Gasket Building to Visualize EACH STEP At a Time
def generateTriangles():
    # Level 0
    enqueueOutline(A, B, C)

    # Level 1 Midpoints
    A1 = midpoint(A, B)
    A2 = midpoint(B, C)
    A3 = midpoint(C, A)

    # Level 1 triangle outlines, or skip if Lv0
    if depth >= 1:
        enqueueOutline(A, A1, A3)
        enqueueOutline(B, A2, A1)
        enqueueOutline(C, A3, A2)
    # Initialize base triangle objects for Level 2+
    triangles = [
        {'label': 'A', 'points': [A, A1, A3]},
        {'label': 'B', 'points': [B, A2, A1]},
        {'label': 'C', 'points': [C, A3, A2]}
    ]
    #Levels 2 to depth
    for level in range(2, depth + 1):
        nextLevel = []
        for tri in triangles:
            p1, p2, p3 = tri['points']

            # Order vertices as top, left, right for consistent subdivision
            pts = sorted([p1, p2, p3], key=lambda p: (-p[1], p[0]))
            top, left, right = pts

            # Compute new midpoints for each triangle
            m1 = midpoint(top, left)
            m2 = midpoint(top, right)
            m3 = midpoint(left, right)

            # Enqueue the 3 new sub-triangles
            enqueueOutline(top, m1, m2)
            nextLevel.append({'label': tri['label'] + 'a', 'points': [top, m1, m2]})

            enqueueOutline(left, m3, m1)
            nextLevel.append({'label': tri['label'] + 'b', 'points': [left, m3, m1]})

            enqueueOutline(right, m2, m3)
            nextLevel.append({'label': tri['label'] + 'c', 'points': [right, m2, m3]})
            
        triangles = nextLevel

# ------------------------------ #
#         OpenGL Setup           #
# ------------------------------ #

def init():
    glClearColor(1.0, 1.0, 1.0, 1.0)
    glColor3f(1.0, 0.0, 0.0)
    glMatrixMode(GL_PROJECTION)
    glLoadIdentity()
    gluOrtho2D(-1.2, 1.2, -1.2, 1.2)

def display():
    glClear(GL_COLOR_BUFFER_BIT)
    glFlush()

def stepDraw(value):
    if queue:
        queue.pop(0)()
        glutTimerFunc(interval, stepDraw, 0)

# ------------------------------ #
#              Main              #
# ------------------------------ #

def main():
    glutInit()
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB)
    glutInitWindowSize(800, 800)
    glutCreateWindow(b"Sierpinski Gasket (OpenGL - Level by Level)")
    init()
    generateTriangles()
    glutDisplayFunc(display)
    glutTimerFunc(0, stepDraw, 0)
    glutMainLoop()

main()
