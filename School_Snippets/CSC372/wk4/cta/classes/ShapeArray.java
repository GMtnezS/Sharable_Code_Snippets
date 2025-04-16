package classes;

/**
 * Driver class for demonstrating the Shape hierarchy.
 * Creates instances of different shapes and stores them in an array.
 */
public class ShapeArray {
     /**
     * Builds a message string containing information about all shapes in the array.
     * @param shapes Array of Shape objects
     * @return A string containing the information of all shapes, one per line
     */
    public static String buildShapeMessage(Shape[] shapes) {
        StringBuilder message = new StringBuilder();
        for (Shape shape : shapes) {
            message.append(shape.toString()).append("\n");
        }
        return message.toString().trim();
    }
    
    /**
     * Main method that creates and displays different shapes.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Shape[] shapeArray = new Shape[3];
        
        shapeArray[0] = new Triangle(3, 4, 5);
        shapeArray[1] = new Circle(5);
        shapeArray[2] = new Rectangle(4, 6);
        
        String message = buildShapeMessage(shapeArray);
        System.out.println(message);
    }
} 