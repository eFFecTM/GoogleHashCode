package googlehashcode2021;

import java.util.Map;

public class Car {

    public Map<String, Street> streets;
    public int totalTime;
    public double cost; // lower is better, less streets for the same amount of total time is better
    public Street currentStreet; // For simulation
    public boolean finished; //For Simulation

    public Car(Map<String, Street> streets) {
        this.streets = streets;
        for (Street street : streets.values()) {
            totalTime += street.time;
        }
        currentStreet = streets.entrySet().iterator().next().getValue();
        //totalTime = streets.values().iterator().next().time;
        cost = (double) streets.size() / (double) totalTime;
        finished = false;
    }
}
