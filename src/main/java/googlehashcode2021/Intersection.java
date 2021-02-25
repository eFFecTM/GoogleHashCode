package googlehashcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Intersection
{
    public int number;
    public List<String> streets;
    public List<Integer> time;

    public Intersection(int number)
    {
        streets = new ArrayList<>();
        time = new ArrayList<>();
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

    public void addStreet(String name)
    {
        streets.add(name);
    }

    public List<String> getStreets()
    {
        return streets;
    }

    public void addTime(int value)
    {
        time.add(value);
    }

    public List<Integer> getTimes()
    {
        return time;
    }
}
