package googlehashcode2021;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<String> tempDataInput = new ArrayList<>();
    public static List<String> tempDataOutput = new ArrayList<>();

    public static int duration, amountOfIntersections, amountOfStreets, amountOfCars, bonusPoints;
    public static Map<String, Street> streets = new HashMap<>();
    public static List<Car> cars = new ArrayList<>();

    public static void main(String... args) throws Exception {
        int i = 1;
        if (loadAndProcess("src/main/resources/googlehashcode2021/a.txt", i) == 1) {
            output("output1.txt");
        }

//        double previousfactor = 0;
//        double previousPoints = 0.0;
//        if (loadAndProcess("src/main/resources/f_libraries_of_the_world.txt", previousfactor) == 1) {
//            output("output6.txt");
//            previousPoints = calculatePoints("output6.txt");
//        }
//        System.out.println("iteration " + 0 + " with factor " + previousfactor + " gives points: " + previousPoints);
//
//        double factor = 20;
//        for (int j = 1; j <= 20; j++) {
//            double currentPoints = 0.0;
//            if (loadAndProcess("src/main/resources/f_libraries_of_the_world.txt", factor) == 1) {
//                output(/*i + "/*/"output6.txt");
//                currentPoints = calculatePoints("output6.txt");
//            }
//
//            double newFactor;
//            System.out.println("iteration " + j + " with factor " + factor + " gives points: " + currentPoints);
//            double rico = (currentPoints - previousPoints) / (factor - previousfactor);
//
//            if (rico == 0)
//                break;
//
//            newFactor = max(min(rico * 5 + factor, abs(factor - previousfactor) + factor), factor - abs(factor - previousfactor) / 2);
//
//            previousfactor = factor;
//            factor = newFactor;
//            previousPoints = currentPoints;
//        }
    }

    private static int loadAndProcess(String path, double weightFactor) throws Exception {
        tempDataInput = Files.lines(Paths.get(path)).collect(Collectors.toList());

        String[] s = tempDataInput.get(0).split(" ");
        duration = Integer.parseInt(s[0]);
        amountOfIntersections = Integer.parseInt(s[1]);
        amountOfStreets = Integer.parseInt(s[2]);
        amountOfCars = Integer.parseInt(s[3]);
        bonusPoints = Integer.parseInt(s[4]);

        for (int i = 1; i <= amountOfStreets; i++) {
            s = tempDataInput.get(i).split(" ");
            streets.put(s[2], new Street(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[3])));
        }

        for (int i = amountOfStreets + 1; i <= amountOfStreets + amountOfCars; i++) {
            s = tempDataInput.get(i).split(" ");
            Map<String, Street> carStreets = new HashMap<>();
            for (int j = 1; j < s.length; j++) {
                carStreets.put(s[j], streets.get(s[j]));
            }
            cars.add(new Car(carStreets));
        }

        return calculate();
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare =
                        map.get(k2).compareTo(map.get(k1));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    private static void output(String fileName) throws Exception {
        //
        tempDataOutput.add("test1");
        tempDataOutput.add("test2");
        tempDataOutput.add("test3");
        Files.write(Paths.get(fileName), (Iterable<String>) tempDataOutput.stream()::iterator);
    }

    private static int calculate() {
        //

//        Collections.sort(libraries, new Comparator<Library>() {
//            @Override
//            public int compare(Library library, Library t1) {
//                if (library.booksRatio < t1.booksRatio)
//                    return 1;
//                else if (library.booksRatio > t1.booksRatio)
//                    return -1;
//                else
//                    return 0;
//            }
//        });


        return 1;
    }


    public static double calculatePoints(String fileName) throws Exception {
        double totalPoints = 0;

        //

        System.out.println("Points for file " + fileName + " are " + totalPoints);
        return totalPoints;
    }
}
