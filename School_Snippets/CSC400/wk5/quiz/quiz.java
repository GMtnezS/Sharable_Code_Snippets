package wk5.quiz;

public class quiz { 
    public static void MyMethod(int b){
      if (b==0) System.out.println("Hello, World");
      else {
          System.out.println("Hello, World");
          MyMethod(b+1);
      }
    }

    public static int MyMethod1(int b) {
        if (b == 0) return 1;
        if (b == 1) return b;
        return b + MyMethod1(b-1);
    }

    public static void main(String[] args) { 
        System.out.println(MyMethod1(5));
        System.out.println(MyMethod1(6));
        System.out.println(MyMethod1(3));
        System.out.println(MyMethod1(4));
        // System.out.println(
          // MyMethod(12);
          // );
    }
}
