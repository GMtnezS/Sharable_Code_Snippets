package classes;

/**
 * Abstract class representing a geometric shape.
 * Provides abstract methods for calculating area and perimeter.
 */
public abstract class Shape {
    /**
     * Calculates the area of the shape.
     * @return The area of the shape as a double.
     */
    public abstract double area();

    /**
     * Calculates the perimeter of the shape.
     * @return The perimeter of the shape as a double.
     */
    public abstract double perimeter();
}