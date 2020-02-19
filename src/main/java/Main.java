import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static List<String> tempData1 = new ArrayList<>();

    public static void main(String... args) throws Exception {
        loadAndProcess("src/main/resources/test.txt");
        output("output1.txt");
    }

    private static void loadAndProcess(String path) throws Exception {
        tempData1 = Files.lines(Paths.get(path)).sorted((s, t) -> {
            if (true) {
                return 1;
            } else if (true) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());

        // process
        tempData1.forEach(a -> {
            System.out.println("hello processing1");
            System.out.println("hello processing2");
        });
    }

    private static void output(String fileName) throws Exception {
        Files.write(Paths.get(fileName), (Iterable<String>)tempData1.stream()::iterator);
    }
}
