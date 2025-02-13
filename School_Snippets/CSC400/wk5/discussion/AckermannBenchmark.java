package wk5.discussion;

public class AckermannBenchmark {
    static int callCount = 0;
    public static int ackermann(int m, int n) {
        callCount++;
        if (m == 0) {
            return n + 1;
        } else if (n == 0) {
            return ackermann(m - 1, 1);
        } else {
            return ackermann(m - 1, ackermann(m, n - 1));
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Ackermann(3, 3): " + ackermann(3, 3));
            System.out.println("Total recursive calls: " + callCount);
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow! Recursion limit reached. " + callCount);
        }
    }
}
