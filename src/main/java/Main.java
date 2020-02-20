import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static int amountBooks;
    public static int amountLibraries;
    public static int amountDays;
    public static ArrayList<Integer> scoreBooks;
    public static List<String> tempData1;
    public static List<Library> libraries;

    public static void main(String... args) throws Exception {
        loadAndProcess("src/main/resources/a_example.txt");
        loadAndProcess("src/main/resources/b_read_on.txt");
        System.out.println("test");
        //output("output1.txt");
    }

    private static void loadAndProcess(String path) throws Exception {
        scoreBooks = new ArrayList<>();
        tempData1 = new ArrayList<>();
        libraries = new ArrayList<>();
        tempData1 = Files.lines(Paths.get(path)).collect(Collectors.toList());
        Library library = null;
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
                        try {
                            library = new Library(Integer.parseInt(lFirstLine[0]),
                                    Integer.parseInt(lFirstLine[1]),
                                    Integer.parseInt(lFirstLine[2]));
                        } catch (Exception ex) {
                            System.out.println("Ex.");
                        }
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

        Calculate();
        // process
    }

    private static void output(String fileName) throws Exception {
        Files.write(Paths.get(fileName), (Iterable<String>)tempData1.stream()::iterator);
    }

    private static void Calculate()
    {
        int i = 0;
        for (Library library: libraries)
        {
             Map<Integer, Integer> books = library.books;
             int maxScore = 0;
             int maxScoreId = 0;
            for (java.util.Map.Entry<Integer, Integer> integerIntegerEntry : books.entrySet())
            {
                int score = integerIntegerEntry.getValue();
                library.totalScore += score;
                if (score > maxScore)
                {
                    maxScore = score;
                    maxScoreId = integerIntegerEntry.getKey();
                }
            }

            System.out.println("library: "+i+ " best book "+maxScoreId+" with score "+maxScore);

             library.setMaxScore(maxScore);
             library.setMaxScoreId(maxScoreId);

            library.booksRatio = library.totalScore / library.totalTimeNeeded;
            i++;
        }
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
}
