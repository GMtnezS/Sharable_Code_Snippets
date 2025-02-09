import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;

public class InfixCalculator {
    static Stack<Integer> operandStack = new Stack<>();
    static Stack<Character> operatorStack = new Stack<>();
    private static class IndexWrapper {
        int value = 0;
    }
    private static final Map<Character, BiConsumer<Stack<Integer>, int[]>> OPERATION_MAP = Map.ofEntries(
        Map.entry('+', (stack, values) -> handleSum(stack, values[0], values[1])),
        Map.entry('-', (stack, values) -> handleSubtraction(stack, values[0], values[1])),
        Map.entry('*', (stack, values) -> handleMultiplication(stack, values[0], values[1])),
        Map.entry('/', (stack, values) -> handleDivision(stack, values[0], values[1])),
        Map.entry('%', (stack, values) -> handleModulo(stack, values[0], values[1]))
    );
            
    private enum CharacterType { WHITESPACE, DIGIT, PARENTHESIS, OPERATOR, UNKNOWN }
    private static final Map<CharacterType, BiConsumer<String, IndexWrapper>> CHARACTER_PROCESSORS = Map.of(
        CharacterType.WHITESPACE, (_, _) -> {}, // We're skipping spaces, so nothing needs to be done here.
        CharacterType.DIGIT, (expr, idx) -> idx.value = processNumber(expr, idx.value, operandStack),
        CharacterType.PARENTHESIS, (expr, idx) -> processParenthesis(expr.charAt(idx.value), operandStack, operatorStack),
        CharacterType.OPERATOR, (expr, idx) -> processOperator(operandStack, operatorStack, expr.charAt(idx.value))
    );

    // OPERATION FUNCTIONS:
    private static void handleSum(Stack<Integer> stack, int a, int b) {
        stack.push(a + b);
    }

    private static void handleSubtraction(Stack<Integer> stack, int a, int b) {
        stack.push(a - b);
    }

    private static void handleMultiplication(Stack<Integer> stack, int a, int b) {
        stack.push(a * b);
    }

    private static void handleDivision(Stack<Integer> stack, int a, int b) {
        if (b == 0) throw new ArithmeticException("Division by zero");
        stack.push(a / b);
    }

    private static void handleModulo(Stack<Integer> stack, int a, int b) {
        if (b == 0) throw new ArithmeticException("Modulo by zero");
        stack.push(a % b);
    }

    // HELPER FUNCTIONS: 

    // Function to perform operation and push result to the operand stack
    private static void performOperation(Stack<Integer> operandStack, Stack<Character> operatorStack) {
      if (operandStack.size() < 2) return;

      int b = operandStack.pop();
      int a = operandStack.pop();
      char op = operatorStack.pop();

      OPERATION_MAP.getOrDefault(op, (_,_) -> {
          throw new IllegalArgumentException("Invalid operator: " + op);
      }).accept(operandStack, new int[]{a, b});
    }

    // Function to check if character is an operator
    private static boolean isOperator(char ch) {
        return (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%');
    }

    // Function to determine precedence of different operations
    private static int precedence(char op) {
      return (
        (op == '+' || op == '-') ? 1 
           : (op == '*' || op == '/' || op == '%') ? 2 
           : -1
        );
    }

    // Funtion to process multi-digit numbers
    private static int processNumber(String expression, int index, Stack<Integer> operandStack) {
        int num = 0;
        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            num = num * 10 + (expression.charAt(index) - '0');
            index++;
        }
        operandStack.push(num);
        return index - 1; // We're just taking a step because the loop increments index per character
    }

    private static void processClosingParenthesis(Stack<Integer> operandStack, Stack<Character> operatorStack) {
        while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
            performOperation(operandStack, operatorStack);
        }
        operatorStack.pop(); // Remove '(' once done
    }

    private static void processParenthesis(char ch, Stack<Integer> operandStack, Stack<Character> operatorStack) {
        // If opening parenthesis, push to operator stack
        // If closing parenthesis, process until '(' is found
        if (ch == '(') {
            operatorStack.push(ch);
        } else if (ch == ')') {
             processClosingParenthesis(operandStack, operatorStack);
        }
    }

    private static void processOperator(Stack<Integer> operandStack, Stack<Character> operatorStack, char op) {
        while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(op)) {
            performOperation(operandStack, operatorStack);
        }
        operatorStack.push(op);
    }

    private static CharacterType getCharacterType(char ch) {
        return (
            (Character.isWhitespace(ch)) ? CharacterType.WHITESPACE 
            : (Character.isDigit(ch) )? CharacterType.DIGIT 
            : (ch == '(' || ch == ')') ? CharacterType.PARENTHESIS 
            : (isOperator(ch)) ? CharacterType.OPERATOR 
            : CharacterType.UNKNOWN
        );
    }

    // MAIN PROCESS FUNCTION: 
    // Function to evaluate an infix expression
    public static int evaluateInfix(String expression) {
        IndexWrapper index = new IndexWrapper();
        operandStack.clear();
        operatorStack.clear();

        while (index.value < expression.length()) {
            char ch = expression.charAt(index.value);
            CHARACTER_PROCESSORS.getOrDefault(getCharacterType(ch), (_,_) -> {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }).accept(expression, index);
            index.value++;
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        return operandStack.pop();
    }

    public static void main(String[] args) {
        String[] text_expressions = {
            "(4+2)*3",   // 18
            "5+(3*7)",   // 26
            "10 + 2 * 6", // 22
            "100 * (2 + 12) / 14", // 100
            "42 / 0",    // Should throw an error
            "42%0",    // Should throw an error
            "(3 + 5) * (8 - 4) / 2" // 16
        };

        // For each test expression, print expression and result.
        for (String expr : text_expressions) {
            try {
                System.out.println("Expression: " + expr);
                System.out.println("Result: " + evaluateInfix(expr));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
