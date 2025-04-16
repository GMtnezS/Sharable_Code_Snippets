package classes;

/**
 * Represents a circle shape.
 * Extends the Shape class and implements area and perimeter calculations.
 */
public class Circle extends Shape {
    private double radius;

    /**
     * Constructs a new Circle with specified radius.
     * @param radius The radius of the circle.
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Calculates the area of the circle.
     * @return The area of the circle (π * radius²).
     */
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    /**
     * Calculates the perimeter (circumference) of the circle.
     * @return The perimeter of the circle (2 * π * radius).
     */
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Returns a string representation of the circle.
     * @return A string containing the area and perimeter of the circle.
     */
    @Override
    public String toString() {
        return String.format("Circle - Area: %.4f, Perimeter: %.4f", area(), perimeter());
    }
}