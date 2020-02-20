import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static int amountBooks;
    public static int amountLibraries;
    public static int amountDays;
    public static ArrayList<Integer> scoreBooks = new ArrayList<>();
    public static List<String> tempData1 = new ArrayList<>();
    public static List<Library> libraries = new ArrayList<>();

    public static void main(String... args) throws Exception {
        loadAndProcess("src/main/resources/a_example.txt");
        System.out.println("test");
        //output("output1.txt");
    }

    private static void loadAndProcess(String path) throws Exception {
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
                    String[] lFirstLine = tempData1.get(i).split(" ");
                    library = new Library(Integer.parseInt(lFirstLine[0]),
                            Integer.parseInt(lFirstLine[1]),
                            Integer.parseInt(lFirstLine[2]));
                } else {
                    String[] lSecondLine = tempData1.get(i).split(" ");
                    for (String s : lSecondLine) {
                        int index = Integer.parseInt(s);
                        library.books.put(index, scoreBooks.get(index));
                    }
                    libraries.add(library);
                }
            }
        }

        // process
    }

    private static void output(String fileName) throws Exception {
        Files.write(Paths.get(fileName), (Iterable<String>)tempData1.stream()::iterator);
    }
}
