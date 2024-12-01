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

// After checking out different options, I felt like Scanner was the most straightforward approach.
// So first, we initiate the Scanner.
// Then we prompt the user to enter their weekly income.
// Then we initiate the Income class that handles all the heavy lifting.
//    The most important part of Income is the calculateTaxRate method that actually handles calculating the tax rate,
//    and calculateTaxWithheld that calculates the tax withheld.
// Finally, we print the details, which I thought would be ideal for these to be functions in Income as well.
// Last but not least, we close the Scanner to finalize.