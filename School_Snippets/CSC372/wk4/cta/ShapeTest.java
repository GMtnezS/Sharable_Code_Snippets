import classes.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeTest {
    private Rectangle rectangle;
    private Circle circle;
    private Triangle triangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(4, 6);
        circle = new Circle(5);
        triangle = new Triangle(3, 4, 5);
    }

    @Test
    public void testRectangleArea() {
        assertEquals(24.0, rectangle.area(), 0.001);
    }

    @Test
    public void testRectanglePerimeter() {
        assertEquals(20.0, rectangle.perimeter(), 0.001);
    }

    @Test
    public void testCircleArea() {
        assertEquals(78.5398, circle.area(), 0.001);
    }

    @Test
    public void testCirclePerimeter() {
        assertEquals(31.4159, circle.perimeter(), 0.001);
    }

    @Test
    public void testTriangleArea() {
        assertEquals(6.0, triangle.area(), 0.001);
    }

    @Test
    public void testTrianglePerimeter() {
        assertEquals(12.0, triangle.perimeter(), 0.001);
    }

    @Test
    public void testTriangleToString() {
        String expected = "Triangle - Area: 6.0, Perimeter: 12.0";
        assertEquals(expected, triangle.toString());
    }

    @Test
    public void testCircleToString() {
        String expected = "Circle - Area: 78.5398, Perimeter: 31.4159";
        assertEquals(expected, circle.toString());
    }

    @Test
    public void testRectangleToString() {
        String expected = "Rectangle - Area: 24.0, Perimeter: 20.0";
        assertEquals(expected, rectangle.toString());
    }

    @Test
    public void testShapeArray() {
        Shape[] shapeArray = new Shape[3];

        shapeArray[0] = triangle;
        shapeArray[1] = circle;
        shapeArray[2] = rectangle;

        assertTrue(shapeArray[0] instanceof Triangle);
        assertTrue(shapeArray[1] instanceof Circle);
        assertTrue(shapeArray[2] instanceof Rectangle);
    }

    @Test
    public void testShapeArrayMessage() {
        Shape[] shapeArray = new Shape[3];
        shapeArray[0] = triangle;
        shapeArray[1] = circle;
        shapeArray[2] = rectangle;

        String expectedMessage = "Triangle - Area: 6.0, Perimeter: 12.0\n" +
                               "Circle - Area: 78.5398, Perimeter: 31.4159\n" +
                               "Rectangle - Area: 24.0, Perimeter: 20.0";

        String actualMessage = ShapeArray.buildShapeMessage(shapeArray);
        assertEquals(expectedMessage, actualMessage);
    }
}