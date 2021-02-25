package googlehashcode2021;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static List<String> tempDataInput = new ArrayList<>();
    public static List<String> tempDataOutput = new ArrayList<>();

    public static int duration, amountOfIntersections, amountOfStreets, amountOfCars, bonusPoints;
    public static Map<String, Street> streets = new LinkedHashMap<>();
    public static List<Street> streetsSortedPopularityDesc = new ArrayList<>();
    public static List<Car> cars = new ArrayList<>();
    public static List<Intersection> intersections = new ArrayList<>();

    public static void main(String... args) throws Exception {
        String fileLetter = "a";

        for (int i = 97; i < 103; ++i)
        {
            if (loadAndProcess("src/main/resources/googlehashcode2021/"+(char) i+".txt", i) == 1) {
                output("output"+(char) i+".txt");
            }
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
        tempDataOutput = new ArrayList<>();
        tempDataInput = new ArrayList<>();
        tempDataInput = Files.lines(Paths.get(path)).collect(Collectors.toList());

        streets = new LinkedHashMap<>();
        cars = new ArrayList<>();
        intersections = new ArrayList<>();

        String[] s = tempDataInput.get(0).split(" ");
        duration = Integer.parseInt(s[0]);
        amountOfIntersections = Integer.parseInt(s[1]);
        amountOfStreets = Integer.parseInt(s[2]);
        amountOfCars = Integer.parseInt(s[3]);
        bonusPoints = Integer.parseInt(s[4]);

        for (int i = 1; i <= amountOfStreets; i++) {
            s = tempDataInput.get(i).split(" ");
            streets.put(s[2], new Street(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[3]), s[2]));
        }

        for (int i = amountOfStreets + 1; i <= amountOfStreets + amountOfCars; i++) {
            s = tempDataInput.get(i).split(" ");
            Map<String, Street> carStreets = new LinkedHashMap<>();
            for (int j = 1; j < s.length; j++) {
                Street street = streets.get(s[j]);
                carStreets.put(s[j], street);
                street.amountOfVisits++;
            }
            cars.add(new Car(carStreets));
        }

        cars.sort((car1, car2) -> {
            if (car1.cost < car2.cost) {
                return -1;
            }
            return 0;
        });

        cars.removeIf(car -> car.totalTime > duration);

        List<Street> collect = streets.values().stream().sorted((street1, street2) -> {
            if (street1.amountOfVisits > street2.amountOfVisits) {
                return -1;
            }
            return 0;
        }).collect(Collectors.toList());
        streets.clear();
        for (Street street : collect) {
            streets.put(street.name, street);
        }

        return calculate(2);
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
        tempDataOutput.add(String.valueOf(intersections.size()));

        System.out.println("Intersections added: "+intersections.size());
        for (Intersection intersection: intersections)
        {
            tempDataOutput.add(String.valueOf(intersection.getNumber()));
            List<String> streets = intersection.getStreets();
            tempDataOutput.add(String.valueOf(streets.size()));
            int i = 0;
            for (String streetName: streets)
            {
                tempDataOutput.add(streetName+" "+intersection.getTimes().get(i));
                ++i;
            }
        }

        Files.write(Paths.get(fileName), (Iterable<String>) tempDataOutput.stream()::iterator);
    }

    private static int calculate(double timeFactor) {
        double maxAmountOfVisits = (double) streets.values().iterator().next().amountOfVisits;
        double duration = (double) Main.duration;

        for (int i = 0; i < amountOfIntersections; ++i)
        {
            Intersection intersection = new Intersection(i);

            Iterator it = streets.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry pair = (Map.Entry) it.next();
                String streetName = (String) pair.getKey();
                Street street = (Street) pair.getValue();

                if (street.end == i)
                {
                    intersection.addStreet(streetName);
                    double amount = (double) street.amountOfVisits;
//                    intersection.addTime((int) (Math.max(1, Math.min(((((double) duration) / maxAmountOfVisits * amount)), duration) / (double) timeFactor)));

                    intersection.addTime((int) Math.max(Math.min(Math.min(timeFactor / maxAmountOfVisits * amount + new Random().nextInt(3), duration), timeFactor), 1));
                }
            }

            if (intersection.getStreets().isEmpty())
            {
                break;
            }

            intersections.add(intersection);
        }

        return 1;
    }


    public static double calculatePoints(String fileName) throws Exception {
        double totalPoints = 0;

        //

        System.out.println("Points for file " + fileName + " are " + totalPoints);
        return totalPoints;
    }
}
