package agrendalath.bmi_calculator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BMICalculator {
    private static final Map<Pair, String> table = new HashMap<>();

    static {
        table.put(new Pair(Double.MIN_VALUE, 15), "Very severely underweight");
        table.put(new Pair(15, 16), "Severely underweight");
        table.put(new Pair(16, 18.5), "Underweight");
        table.put(new Pair(18.5, 25), "Normal (healthy weight)");
        table.put(new Pair(25, 30), "Overweight");
        table.put(new Pair(30, 35), "Obese Class I (Moderately obese)");
        table.put(new Pair(35, 40), "Obese Class II (Severely obese)");
        table.put(new Pair(40, Double.MAX_VALUE), "Obese Class III (Very severely obese)");
    }

    static double countBMI(double weight, double height) {
        if (weight < 0 || height < 0)
            throw new IllegalArgumentException("Weight nor height cannot have negative value.");

        return weight / (height * height);
    }

    static String classify(double bmi) {
        for (Map.Entry<Pair, String> entry : table.entrySet()) {
            if (entry.getKey().lower <= bmi && bmi < entry.getKey().upper) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static double getUserInput(String input, String unit) {
        System.out.print("Input your " + input + " in " + unit + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private static class Pair {
        private double lower, upper;

        Pair(double lower, double upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    public static void main(String[] args) {
        double bmi = countBMI(getUserInput("weight", "kg"), getUserInput("height", "m"));
        System.out.println("Your BMI equals " + new DecimalFormat("#0.00").format(bmi) + ", which means your weight category is: " + classify(bmi) + ".");
    }
}
