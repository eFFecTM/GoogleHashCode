package googlehashcode2021;

public class Street {

    public int start, end, time, amountOfVisits, seconds;
    public String name;

    public Street(int start, int end, int time, String name) {
        this.start = start;
        this.end = end;
        this.time = time;
        seconds = 0;
        this.name = name;
    }
}
