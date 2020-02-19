import java.util.ArrayList;

public class Photo {

    public ArrayList<String> tags;
    public boolean isHorizontal;
    public int sequence;
    public int secondPhoto;
    public boolean composed;

    public Photo(ArrayList<String> tags, boolean isHorizontal, int sequence) {
        this.tags = tags;
        this.isHorizontal = isHorizontal;
        this.sequence = sequence;
    }

    public Photo(ArrayList<String> tags, boolean isHorizontal, int sequence, int secondPhoto, boolean composed) {
        this.tags = tags;
        this.isHorizontal = isHorizontal;
        this.sequence = sequence;
        this.secondPhoto = secondPhoto;
        this.composed = composed;
    }
}
