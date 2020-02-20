import java.util.Map;
import java.util.TreeMap;

public class Library {

    public int id;
    public int amountBooks;
    public int signupTime;
    public int amountBooksPerDay;
    public Map<Integer, Integer> books;
    public double booksRatio;
    public double totalTimeNeeded;

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getMaxScoreId() {
        return maxScoreId;
    }

    public void setMaxScoreId(int maxScoreId) {
        this.maxScoreId = maxScoreId;
    }

    public int maxScore;
    public int maxScoreId;
    public int totalScore;

    public Library(int id, int amountBooks, int signupTime, int amountBooksPerDay) {
        this.id = id;
        this.amountBooks = amountBooks;
        this.signupTime = signupTime;
        this.amountBooksPerDay = amountBooksPerDay;
        books = new TreeMap<>();
        this.totalTimeNeeded = signupTime + amountBooks/amountBooksPerDay;
    }


}
