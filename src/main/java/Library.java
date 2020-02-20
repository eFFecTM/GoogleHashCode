import java.util.Map;
import java.util.TreeMap;

public class Library {

    public int id;
    public double amountBooks;
    public double signupTime;
    public double amountBooksPerDay;
    public Map<Integer, Integer> books;
    public double booksRatio;
    public double totalTimeNeeded;
    public double weightFactor;

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

    public Library(int id, int amountBooks, int signupTime, double amountBooksPerDay, double weightFactor) {
        this.id = id;
        this.amountBooks = amountBooks;
        this.signupTime = signupTime;
        this.amountBooksPerDay = amountBooksPerDay;
        books = new TreeMap<>();
        this.weightFactor = weightFactor/2;
        this.totalTimeNeeded = weightFactor * signupTime + amountBooks/amountBooksPerDay;
    }


}
