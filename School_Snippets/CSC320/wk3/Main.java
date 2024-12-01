package wk3;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your weekly income: ");
        double incomeValue = scanner.nextDouble();

        Income income = new wk3.Income(incomeValue);

        income.printIncomeDetails();

        scanner.close();
    }

}
