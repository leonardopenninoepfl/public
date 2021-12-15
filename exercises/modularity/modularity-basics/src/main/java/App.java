import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {
        List<Double> normalized = new ArrayList<>();
        List<Double> numbers = getNumbersFromFile("data");
        double mean = calculateMean(numbers);
        double sumSquare = getSumSquare(numbers, mean);
        normalize(normalized, numbers, mean, sumSquare);
        System.out.println(normalized);

        try {
            FileWriter fw = new FileWriter("output");
            for (double n : normalized) {
                fw.write(Double.toString(n));
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing output file");
        }
        System.out.println("Wrote output file.");
        return normalized;
    }

    private static void normalize(List<Double> normalized, List<Double> numbers, double mean, double sumSquare) {
        double std = Math.sqrt(sumSquare / numbers.size());
        for (double f : numbers) {
            normalized.add((f - mean) / std);
        }
    }

    private static double getSumSquare(List<Double> numbers, double mean) {
        double sumSquare = 0;
        for (double f : numbers) {
            double diff = f - mean;
            sumSquare += diff * diff;
        }
        return sumSquare;
    }

    private static double calculateMean(List<Double> numbers) {
        double sum = 0;
        for (double f : numbers) {
            sum += f;
        }
        double mean = sum / numbers.size();
        return mean;
    }

    private static List<Double> getNumbersFromFile(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<Double> numbers = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            numbers.add(number);
        }
        return numbers;
    }
}
