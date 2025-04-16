package classes;

/**
 * Represents a rectangle shape.
 * Extends the Shape class and implements area and perimeter calculations.
 */
public class Rectangle extends Shape {
    private double width;
    private double length;

    /**
     * Constructs a new Rectangle with specified width and length.
     * @param width The width of the rectangle.
     * @param length The length of the rectangle.
     */
    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    /**
     * Calculates the area of the rectangle.
     * @return The area of the rectangle (width * length).
     */
    @Override
    public double area() {
        return width * length;
    }

    /**
     * Calculates the perimeter of the rectangle.
     * @return The perimeter of the rectangle (2 * (width + length)).
     */
    @Override
    public double perimeter() {
        return 2 * (width + length);
    }

    /**
     * Returns a string representation of the rectangle.
     * @return A string containing the area and perimeter of the rectangle.
     */
    @Override
    public String toString() {
        return String.format("Rectangle - Area: %.1f, Perimeter: %.1f", area(), perimeter());
    }
}