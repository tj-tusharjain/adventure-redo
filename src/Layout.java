import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Layout {

    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;

    public String getStartingRoom() {
        return startingRoom;
    }

    public void setStartingRoom(String startingRoom) {
        this.startingRoom = startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public void setEndingRoom(String endingRoom) {
        this.endingRoom = endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public Layout fileLoading(String urlInput) throws Exception{

        Layout obj = null;

        try {
            Gson gson = new Gson();
            URL url = new URL(urlInput);
            InputStream inStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            obj = gson.fromJson(inputStreamReader, Layout.class);

        }catch (MalformedURLException e){

            System.out.println("URL Not Well Formed");

        }catch (java.io.IOException e){

            System.out.println("Not working");

        }
        return obj;


    }

}
