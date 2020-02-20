import java.util.ArrayList;
import java.util.HashMap;

public class Library {

    public int amountBooks;
    public int signupTime;
    public int amountBooksPerDay;
    public HashMap<Integer, Integer> books;

    public Library(int amountBooks, int signupTime, int amountBooksPerDay) {
        this.amountBooks = amountBooks;
        this.signupTime = signupTime;
        this.amountBooksPerDay = amountBooksPerDay;
        books = new HashMap<>();
    }
}
