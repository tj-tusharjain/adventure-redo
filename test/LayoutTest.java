import org.junit.Test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class LayoutTest {

    String urlInput = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";

    @Test
    public void getItemTest() throws Exception {

        Layout obj1 = new Layout();
        obj1 = obj1.fileLoading(urlInput);
        assertEquals("coin", obj1.getRooms()[0].items.get(0));

    }

    @Test
    public void getDescriptionTest() throws Exception{
        Layout obj1 = new Layout();
        obj1 = obj1.fileLoading(urlInput);
        assertEquals("You are on Matthews, outside the Siebel Center",
                obj1.getRooms()[0].getDescription());
    }

    @Test
    public void getName() throws Exception{

    }

    @Test
    public void malformedUrl() throws Exception{
        urlInput = "malformed_URL";
        Layout obj1 = new Layout();
        obj1 = obj1.fileLoading(urlInput);

        assertEquals(null, obj1);
    }

}