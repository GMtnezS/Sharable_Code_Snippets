package wk3;

public class Income {
    private double weeklyIncome;

    public Income(double weeklyIncome) {
        this.weeklyIncome = weeklyIncome;
    }

    public double getWeeklyIncome() {
        return weeklyIncome;
    }

    public double calculateTaxRate() {
        if (weeklyIncome < 500) {
            return 0.10;
        } else if (weeklyIncome >= 500 && weeklyIncome < 1500) {
            return 0.15;
        } else if (weeklyIncome >= 1500 && weeklyIncome < 2500) {
            return 0.20;
        } else {
            return 0.30;
        }
    }

    public double calculateTaxWithheld() {
        return weeklyIncome * calculateTaxRate();
    }

    public void printWeeklyIncome() {
        System.out.println("Weekly Income: $" + weeklyIncome);
    }

    public void printTaxRate() {
        System.out.println("Tax Rate: " + (calculateTaxRate() * 100) + "%");
    }

    public void printTaxWithheld() {
        System.out.println("Tax Withheld: $" + calculateTaxWithheld());
    }

    public void printIncomeDetails() {
        printWeeklyIncome();
        printTaxRate();
        printTaxWithheld();
    }
}
