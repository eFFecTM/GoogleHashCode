package googlehashcode2020;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;

import static java.lang.Math.min;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.max;

public class Main {

    public static int amountBooks;
    public static int amountLibraries;
    public static int amountDays;
    public static ArrayList<Integer> scoreBooks;
    public static List<String> tempData1;
    public static List<String> tempData2;
    public static List<Library> libraries;
    public static List<Library> original;

    public static void main(String... args) throws Exception {
        //for (int i = 10; i < 20; i++) {
            int i = 1;
        if (loadAndProcess("src/main/resources/googlehashcode2020/a_example.txt", i) == 1) {
            output(/*i + "/*/"output1.txt");
            //CalculatePoints("output1.txt");
        }
        if (loadAndProcess("src/main/resources/googlehashcode2020/b_read_on.txt", i)== 1) {
            output(/*i + "/*/"output2.txt");
            //CalculatePoints("output2.txt");
        }
        if (loadAndProcess("src/main/resources/googlehashcode2020/c_incunabula.txt", i)== 1){
                output(/*i + "/*/"output3.txt");
                //CalculatePoints("output3.txt");
            }


        /*double previousfactor = 0;
        double previousPoints = 0.0;
        if (loadAndProcess("src/main/resources/d_tough_choices.txt", previousfactor) == 1) {
            output(i + "/output4.txt");
            previousPoints = CalculatePoints("output4.txt");
        }
        System.out.println("iteration "+0+" with factor "+previousfactor+" gives points: "+previousPoints);


        double factor = 200;
        for (int j = 1; j <= 20; j++) {
            double currentPoints = 0.0;
            if (loadAndProcess("src/main/resources/d_tough_choices.txt", factor) == 1) {
                output(i + "/output4.txt");
                currentPoints = CalculatePoints("output4.txt");
            }

            double newFactor;
            if (currentPoints > previousPoints)
            {
                newFactor = factor + abs(factor - previousfactor);
            }
            else
            {
                newFactor = factor - abs(factor - previousfactor)/2;
            }

            previousfactor = factor;
            factor = newFactor;
            previousPoints = currentPoints;

            System.out.println("iteration "+j+" with factor "+previousfactor+" gives points: "+currentPoints);
        }*/
        //if (loadAndProcess("src/main/resources/e_so_many_books.txt", i)== 1){
        //        output(/*i + "/*/"output5.txt");
                //CalculatePoints("output5.txt");
        //    }

        /*
        double previousfactor = 0;
        double previousPoints = 0.0;
        double previousPreviousPoints = 0.0;
        if (loadAndProcess("src/main/resources/e_so_many_books.txt", previousfactor)== 1){
            output(/*i + "/output5.txt");
            previousPreviousPoints = CalculatePoints("output4.txt");
        }
        System.out.println("iteration "+0+" with factor "+previousfactor+" gives points: "+previousPoints);

        previousfactor = 100;
        if (loadAndProcess("src/main/resources/e_so_many_books.txt", previousfactor)== 1){
            output(/*i + "/output5.txt");
            previousPoints = CalculatePoints("output4.txt");
        }
        System.out.println("iteration "+0+" with factor "+previousfactor+" gives points: "+previousPoints);


        double factor = 200;
        for (int j = 1; j <= 20; j++) {
            double currentPoints = 0.0;
            if (loadAndProcess("src/main/resources/e_so_many_books.txt", factor)== 1){
                output(/*i + "/output5.txt");
                currentPoints = CalculatePoints("output4.txt");
            }

            double newFactor;

            double rico = (currentPoints - previousPoints) / (factor - previousfactor);

            if (rico == 0)
                break;
            
            newFactor = max(min(rico * 5 + factor, abs(factor-previousfactor)+factor), factor-abs(factor-previousfactor)/2);

            previousfactor = factor;
            factor = newFactor;
            previousPoints = currentPoints;

            System.out.println("iteration "+j+" with factor "+previousfactor+" gives points: "+currentPoints);
        }
*/
        double previousfactor = 0;
        double previousPoints = 0.0;
        if (loadAndProcess("src/main/resources/googlehashcode2020/f_libraries_of_the_world.txt", previousfactor)== 1){
            output(/*i + "/*/"output6.txt");
            previousPoints = CalculatePoints("output6.txt");
        }
        System.out.println("iteration "+0+" with factor "+previousfactor+" gives points: "+previousPoints);


        double factor = 20;
        for (int j = 1; j <= 20; j++) {
            double currentPoints = 0.0;
            if (loadAndProcess("src/main/resources/googlehashcode2020/f_libraries_of_the_world.txt", factor)== 1){
                output(/*i + "/*/"output6.txt");
                currentPoints = CalculatePoints("output6.txt");
            }

            double newFactor;
            System.out.println("iteration "+j+" with factor "+factor+" gives points: "+currentPoints);
            double rico = (currentPoints - previousPoints) / (factor - previousfactor);

            if (rico == 0)
                break;

            newFactor = max(min(rico * 5 + factor, abs(factor-previousfactor)+factor), factor-abs(factor-previousfactor)/2);

            previousfactor = factor;
            factor = newFactor;
            previousPoints = currentPoints;
        }
        //}
        //output("output1.txt");
    }

    private static int loadAndProcess(String path, double weightFactor) throws Exception {
        scoreBooks = new ArrayList<>();
        tempData1 = new ArrayList<>();
        tempData2 = new ArrayList<>();
        libraries = new ArrayList<>();
        original = new ArrayList<>();
        tempData1 = Files.lines(Paths.get(path)).collect(Collectors.toList());
        Library library = null;
        int count = 0;
        for (int i = 0; i < tempData1.size(); i++) {
            if (i == 0) {
                String[] firstLine = tempData1.get(0).split(" ");
                amountBooks = Integer.parseInt(firstLine[0]);
                amountLibraries = Integer.parseInt(firstLine[1]);
                amountDays = Integer.parseInt(firstLine[2]);
            } else if (i == 1) {
                String[] secondLine = tempData1.get(1).split(" ");
                for (String s : secondLine) {
                    scoreBooks.add(Integer.valueOf(s));
                }
            } else {
                if (i % 2 == 0) {
                    String[] lFirstLine = tempData1.get(i).trim().split(" ");
                    if (!tempData1.get(i).equals("")) {
                        library = new Library(count,
                                Integer.parseInt(lFirstLine[0]),
                                Integer.parseInt(lFirstLine[1]),
                                Integer.parseInt(lFirstLine[2]),
                                weightFactor);
                        count++;
                    }
                } else {
                    String[] lSecondLine = tempData1.get(i).split(" ");
                    for (String s : lSecondLine) {
                        int index = Integer.parseInt(s);
                        library.books.put(index, scoreBooks.get(index));
                    }
                    library.books = sortByValues(library.books);
                    libraries.add(library);
                }
            }
        }

        return Calculate();
        // process
    }

    private static void output(String fileName) throws Exception {
        //tempData2.add("" + libraries.size());
        TreeSet<Integer> bookList = new TreeSet<>();
        int amountOfLibraries = 0;
        for (Library library : libraries) {
            StringBuilder bookString = new StringBuilder();
            int amountOfBooks = 0;
            for (java.util.HashMap.Entry<Integer, Integer> integerIntegerEntry : library.books.entrySet()) {
                if (!bookList.contains(integerIntegerEntry.getKey())) {
                    amountOfBooks++;
                    bookList.add(integerIntegerEntry.getKey());
                    bookString.append((int) integerIntegerEntry.getKey());
                    bookString.append(" ");
                }
            }
            if (amountOfBooks > 0) {
                amountOfLibraries++;
                tempData2.add((int) library.id + " " + (int) amountOfBooks);
                tempData2.add(bookString.toString());
            }
        }
        tempData2.add(0, ""+amountOfLibraries);
        Files.write(Paths.get(fileName), (Iterable<String>)tempData2.stream()::iterator);
    }

    private static int Calculate()
    {
        int i = 0;
        for (Iterator<Library> iterator = libraries.iterator(); iterator.hasNext(); ) {
            Library library = iterator.next();
            Map<Integer, Integer> books = library.books;
            int maxScore = 0;
            int maxScoreId = 0;
            for (HashMap.Entry<Integer, Integer> integerIntegerEntry : books.entrySet()) {
                int score = integerIntegerEntry.getValue();
                library.totalScore += score;
                if (score > maxScore) {
                    maxScore = score;
                    maxScoreId = integerIntegerEntry.getKey();
                }
            }

            //System.out.println("library: "+i+ " best book "+maxScoreId+" with score "+maxScore);

            library.setMaxScore(maxScore);
            library.setMaxScoreId(maxScoreId);

            library.booksRatio = library.totalScore / library.totalTimeNeeded;

            i++;
        }

        original = new ArrayList<Library>(libraries);

        if (libraries.isEmpty())
        {
            return 0;
        }
        Collections.sort(libraries, new Comparator<Library>() {
            @Override
            public int compare(Library library, Library t1) {
                if (library.totalTimeNeeded < t1.totalTimeNeeded) {
                    return 1;
                }
                else if (library.totalTimeNeeded > t1.totalTimeNeeded)
                    return -1;
                else
                    return 0;
            }
        });

        boolean throughSet = false;
        //while (!throughSet) {
            TreeSet<Integer> bookList = new TreeSet<>();
            int libraryWithoutBooks = 0;
            boolean libraryMoved = false;
            //libraries.clear();
            //libraries = new ArrayList<googlehashcode2020.Library>(copy);
            int amountOfLibraries = 0;
            for (Library library : libraries) {
                int amountOfBooks = 0;

                /*Iterator<HashMap.Entry<Integer, Integer>> it = library.books.entrySet().iterator();
                while (it.hasNext())
                {
                    HashMap.Entry<Integer, Integer> integerIntegerEntry = it.next();
                    if (!bookList.contains(integerIntegerEntry.getKey())) {
                        amountOfBooks++;
                        bookList.add(integerIntegerEntry.getKey());
                    } else {
                        it.remove();
                    }
                }*/

                for (java.util.HashMap.Entry<Integer, Integer> integerIntegerEntry : library.books.entrySet()) {
                    if (!bookList.contains(integerIntegerEntry.getKey())) {
                        amountOfBooks++;
                        bookList.add(integerIntegerEntry.getKey());
                    } else {
                        library.books.remove(integerIntegerEntry.getKey());
                    }
                }
                if (amountOfBooks > 0) {
                    amountOfLibraries++;
                }
            }

            //throughSet = !libraryMoved || libraryWithoutBooks < libraries.size()/2;
        //}

        Collections.sort(libraries, new Comparator<Library>() {
            @Override
            public int compare(Library library, Library t1) {
                if (library.booksRatio < t1.booksRatio)
                    return 1;
                else if (library.booksRatio > t1.booksRatio)
                    return -1;
                else
                    return 0;
            }
        });

        System.out.println(libraries.get(0).id);
        return 1;
    }

    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k2).compareTo(map.get(k1));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };
        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    /*private static HashMap<Integer, Integer> sortByValues(Map<Integer, Integer> unsortMap)
    {

        List<Entry<Integer, Integer>> list = new LinkedList<Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Integer, Integer>>()
        {
            //public int compare(K k1, K k2) {
            //    int compare =
            //            map.get(k2).compareTo(map.get(k1));
            //    if (compare == 0)
            //        return 1;
            //    else
            //        return compare;
            //}
            public int compare(Entry<Integer, Integer> o1,
                               Entry<Integer, Integer> o2)
            {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Maintaining insertion order with the help of LinkedList
        HashMap<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }*/

    public static double CalculatePoints(String fileName) throws Exception
    {
        double totalPoints = 0;
        //tempData1 = new ArrayList<>();
        //tempData2 = new ArrayList<>();
        //tempData1 = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        int amountLibrariesUsed;
        int count = 0;
        TreeMap<Integer, Library> originalSorted = new TreeMap<>();

        int index = 0;
        for (Library library: libraries)
        {
            originalSorted.put(index, original.get(library.id));
            index++;
        }

        if (originalSorted.size() == libraries.size())
        {
            System.out.println("Size is still same.");
        }

        int days = 1;
        int libraryLastSignup = 0;
        int totalSignupDays = 0;
        HashSet<Integer> booksList = new HashSet<>();

        while (amountDays > days) {
            //long startTime = System.nanoTime();
            for (Map.Entry<Integer, Library> entry : originalSorted.entrySet()) {
                Library library = entry.getValue();
                if (library.signedUp) {
                    totalPoints += CalculatePointsFromLibrary(library, booksList);
                }
            }
            //System.out.println("Loop: "+(System.nanoTime()-startTime));

            double signupTime = originalSorted.get(libraryLastSignup).signupTime;
            if (days > (totalSignupDays + signupTime)) {
                totalSignupDays += signupTime;
                Library library = originalSorted.get(libraryLastSignup);
                library.signedUp = true;
                libraryLastSignup++;

                totalPoints += CalculatePointsFromLibrary(library, booksList);
            }

            days++;
        }

        System.out.println("Points for file "+fileName+" are "+totalPoints);
        return totalPoints;
    }

    private static double CalculatePointsFromLibrary(Library library, HashSet<Integer> booksList) {
        double totalPoints = 0.0;
        int booksThisDay = 0;
        int key = 0;
        Iterator<HashMap.Entry<Integer, Integer>> it = library.books.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap.Entry<Integer, Integer> entry = it.next();
            key = entry.getKey();
            if (booksThisDay < library.amountBooksPerDay && !booksList.contains(key)) {
                totalPoints += entry.getValue();
                it.remove();
                booksThisDay++;
                booksList.add(key);
            }
        }
        return totalPoints;
    }
}
