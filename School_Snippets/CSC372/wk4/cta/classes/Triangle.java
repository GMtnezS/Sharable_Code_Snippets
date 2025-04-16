package classes;

/**
 * Represents a triangle shape.
 * Extends the Shape class and implements area and perimeter calculations.
 */
public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    /**
     * Constructs a new Triangle with specified side lengths.
     * @param side1 The length of the first side.
     * @param side2 The length of the second side.
     * @param side3 The length of the third side.
     */
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * Calculates the area of the triangle using Heron's formula.
     * @return The area of the triangle.
     */
    @Override
    public double area() {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /**
     * Calculates the perimeter of the triangle.
     * @return The perimeter of the triangle (sum of all sides).
     */
    @Override
    public double perimeter() {
        return side1 + side2 + side3;
    }

    /**
     * Returns a string representation of the triangle.
     * @return A string containing the area and perimeter of the triangle.
     */
    @Override
    public String toString() {
        return String.format("Triangle - Area: %.1f, Perimeter: %.1f", area(), perimeter());
    }
}