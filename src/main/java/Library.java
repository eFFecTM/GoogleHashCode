import java.util.Map;
import java.util.TreeMap;

public class Library {

    public int amountBooks;
    public int signupTime;
    public int amountBooksPerDay;
    public Map<Integer, Integer> books;

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

    public Library(int amountBooks, int signupTime, int amountBooksPerDay) {
        this.amountBooks = amountBooks;
        this.signupTime = signupTime;
        this.amountBooksPerDay = amountBooksPerDay;
        books = new TreeMap<>();
    }


}
