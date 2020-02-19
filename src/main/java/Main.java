import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static int amount;
    private static ArrayList<Photo> photos;
    public static ArrayList<Photo> verticals;
    public static ArrayList<Photo> newPhotos;

    public static void main(String[] args) throws IOException {
        loadFile("src/main/resources/a_example.txt");
        outputFile("output1.txt");
//        loadFile("src/main/resources/b_lovely_landscapes.txt");
//        outputFile("output2.txt");
//        loadFile("src/main/resources/c_memorable_moments.txt");
//        outputFile("output3.txt");
//        loadFile("src/main/resources/d_pet_pictures.txt");
//        outputFile("output4.txt");
//        loadFile("src/main/resources/e_shiny_selfies.txt");
//        outputFile("output5.txt");
    }

    public static void loadFile(String fileName) throws IOException {
        photos = new ArrayList<>();
        newPhotos = new ArrayList<>();
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        amount = Integer.parseInt(scanner.nextLine());
        int counter = 0;
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] elements = line.split(" ");

            ArrayList<String> tags = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(elements[1]); i++)
            {
                tags.add(elements[i+2]);
            }

            if (elements[0].equals("H")) {
                photos.add(new Photo(tags, true, counter));
            } else {
                photos.add(new Photo(tags, false, counter));
            }

            counter++;
        }
        scanner.close();

        verticals = new ArrayList<>();
        ArrayList<Photo> multiVerticals = new ArrayList<>();

        for(Photo photo: photos)
        {
            if(!photo.isHorizontal)
            {
                verticals.add(photo);
            }
        }

        /*boolean containsTag = false;
        for (int i = 0; i < verticals.size(); i++)
        {
            //if(verticals.get(i).sequence != multiVerticals.get())
            int j = i;
            while(j < verticals.size())
            {
                for (int x = 0; x < verticals.get(i).tags.size(); x++)
                {
                    if(verticals.get(j).tags.contains(verticals.get(i).tags.get(x)))
                    {
                        containsTag = true;
                        break;
                    }
                }
                if(!containsTag)
                {
                    ArrayList<String> tagsDoublePhoto = new ArrayList<>();
                    tagsDoublePhoto.addAll(verticals.get(i).tags);
                    tagsDoublePhoto.addAll(verticals.get(j).tags);
                    multiVerticals.add(new Photo(tagsDoublePhoto, true, verticals.get(i).sequence, verticals.get(j).sequence));
                    break;
                }
                containsTag = false;
                j++;
            }
        }*/
        
        for(int i = 0; i<verticals.size(); i++)
        {
            ArrayList<String> tagsDoublePhoto = new ArrayList<>();
            tagsDoublePhoto.addAll(verticals.get(i).tags);
            tagsDoublePhoto.addAll(verticals.get(i+1).tags);

            for(Photo photo: photos)
            {
                if(photo.sequence == verticals.get(i).sequence)
                {
                    photos.remove(photo);
                    break;
                }
            }
            for(Photo photo: photos)
            {
                if(photo.sequence == verticals.get(i+1).sequence)
                {
                    photos.remove(photo);
                    break;
                }
            }

            photos.add(new Photo(tagsDoublePhoto, true, verticals.get(i).sequence, verticals.get(i+1).sequence, true));
            i++;
        }

        Collections.sort(photos, new Comparator<Photo>(){
            public int compare(Photo p1, Photo p2) {
                return Integer.compare(p2.tags.size(), p1.tags.size());
            }
        });

        newPhotos.add(photos.get(0));
        Photo currentPhoto = photos.get(0);

        while (photos.size() > 1) {
            int maxEqualTags = 0;
            Photo maxPhoto = photos.get(photos.size()-1);
            int anotherCounter = 0;
            for (Photo photo : photos) {
                int tagCounter = 0;
                if (!photo.equals(currentPhoto)) {
                    for (String tag : currentPhoto.tags) {
                        if (photo.tags.contains(tag)) {
                            tagCounter++;
                        }
                    }
                    if (tagCounter > maxEqualTags) {
                        maxEqualTags = tagCounter;
                        maxPhoto = photo;
                    }
                }
                anotherCounter++;
                if (anotherCounter >= 5000) {
                    if (maxPhoto == null) {
                        maxPhoto = photo;
                    }
                    break;
                }
            }

            newPhotos.add(maxPhoto);
            photos.remove(currentPhoto);
            photos.remove(maxPhoto);
            currentPhoto = maxPhoto;
            System.out.println(photos.size());
        }

        for(int i = 0; i < newPhotos.size(); i++)
        {
            if(i>=(newPhotos.size()-verticals.size()/2))
            {
                System.out.println(newPhotos.get(i).sequence + " and " + newPhotos.get(i).secondPhoto);
            }
            else
            {
                //System.out.println(newPhotos.get(i).sequence);
            }
        }
    }

    public static void outputFile(String name) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        //create combinations
        writer.write(newPhotos.size() + "\n");
        for(int i = 0; i < newPhotos.size(); i++)
        {
            if(newPhotos.get(i).composed)
            {
                System.out.println(newPhotos.get(i).sequence + " and " + newPhotos.get(i).secondPhoto);
                writer.write(newPhotos.get(i).sequence + " " + newPhotos.get(i).secondPhoto + "\n");
            }
            else
            {
                //System.out.println(newPhotos.get(i).sequence);
                writer.write(newPhotos.get(i).sequence + "\n");
            }
        }
        writer.close();


    }

}
