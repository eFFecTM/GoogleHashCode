package googlehashcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Intersection
{
    public int number;
    public List<String> streets;

    public Intersection(int number)
    {
        streets = new ArrayList<>();
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
}
