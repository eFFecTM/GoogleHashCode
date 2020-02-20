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
    public static List<String> tempData2;
    public static List<Library> libraries;
    public static List<Library> sortedLibraries;

    public static void main(String... args) throws Exception {
        for (int i = 10; i < 20; i++) {
            //int i = 1;
        if (loadAndProcess("src/main/resources/a_example.txt", i) == 1) {
            output(i + "/output1.txt");
        }
        if (loadAndProcess("src/main/resources/b_read_on.txt", i)== 1) {
            output(i + "/output2.txt");
        }
        if (loadAndProcess("src/main/resources/c_incunabula.txt", i)== 1){
                output(i + "/output3.txt");
            }
        if (loadAndProcess("src/main/resources/d_tough_choices.txt", i)== 1){
                output(i + "/output4.txt");
            }
        if (loadAndProcess("src/main/resources/e_so_many_books.txt", i)== 1){
                output(i + "/output5.txt");
            }
        if (loadAndProcess("src/main/resources/f_libraries_of_the_world.txt", i)== 1){
                output(i + "/output6.txt");
            }
        }
        //output("output1.txt");
    }

    private static int loadAndProcess(String path, double weightFactor) throws Exception {
        scoreBooks = new ArrayList<>();
        tempData1 = new ArrayList<>();
        tempData2 = new ArrayList<>();
        libraries = new ArrayList<>();
        sortedLibraries = new ArrayList<>();
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
        tempData2.add("" + libraries.size());
        for (Library library : libraries) {
            StringBuilder bookString = new StringBuilder();
            for (java.util.Map.Entry<Integer, Integer> integerIntegerEntry : library.books.entrySet()) {
                bookString.append((int)integerIntegerEntry.getKey());
                bookString.append(" ");
            }

            tempData2.add((int)library.id + " " +(int)library.amountBooks);
            tempData2.add(bookString.toString());
        }
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
            for (Map.Entry<Integer, Integer> integerIntegerEntry : books.entrySet()) {
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

        if (libraries.isEmpty())
        {
            return 0;
        }

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
}
