import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AdventureTest {

    Layout gameObject = new Layout();
    Adventure adventure = new Adventure();

    @Before
    public void initializer() throws Exception{
        gameObject = gameObject.fileLoading("http://tusharjain.me/test.json");
    }

    @Test
    public void takeTest() throws Exception {
        ArrayList<String> inventory = new ArrayList<>();
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "coin";
        adventure.take(inventory, commandVariable,presentRoom);
        assertEquals("coin",inventory.get(0));
    }

    @Test
    public void takeSpacedItemTest() throws Exception{
        ArrayList<String> inventory = new ArrayList<>();
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "usb-c cable";
        adventure.take(inventory, commandVariable,presentRoom);
        assertEquals("usb-c cable",inventory.get(0));
    }

    @Test
    public void takeMixedCaseItemTest() throws Exception{
        ArrayList<String> inventory = new ArrayList<>();
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "GlOvE";
        adventure.take(inventory, commandVariable,presentRoom);
        assertEquals("glove",inventory.get(0).toLowerCase());
    }

    @Test
    public void takeFailTest() throws Exception{
        ArrayList<String> inventory = new ArrayList<>();
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "invalid_item";
        adventure.take(inventory, commandVariable,presentRoom);
        assertEquals(0,inventory.size());
    }

    @Test
    public void dropTest() throws Exception {
        ArrayList<String> inventory = new ArrayList<String>(Arrays.asList("laptop"));
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "laptop";
        adventure.drop(inventory, commandVariable,presentRoom);
        assertEquals(0,inventory.size());
    }

    @Test
    public void dropFailTest() throws Exception{
        ArrayList<String> inventory = new ArrayList<String>(Arrays.asList("laptop"));
        Room presentRoom =gameObject.getRooms()[0];
        String commandVariable = "my Game";
        adventure.drop(inventory, commandVariable,presentRoom);
        assertEquals(1,inventory.size());
    }

    @Test
    public void goTest() throws Exception {
        int refIndex = 0;
        Room presentRoom =gameObject.getRooms()[refIndex];
        String commandVariable = "east";
        refIndex = adventure.go(gameObject,commandVariable,presentRoom,refIndex);
        presentRoom =gameObject.getRooms()[refIndex];
        assertEquals("ISRFrontDesk",presentRoom.getName());
    }

    @Test
    public void goFailTest() throws Exception{
        int refIndex = 0;
        Room presentRoom =gameObject.getRooms()[refIndex];
        String commandVariable = "to play";
        refIndex = adventure.go(gameObject,commandVariable,presentRoom,refIndex);
        presentRoom =gameObject.getRooms()[refIndex];
        assertEquals("EastJohnStreet",presentRoom.getName());
    }

    @Test
    public void listTest() throws Exception{
        ArrayList<String> inventory = new ArrayList<String>(Arrays.asList("laptop","pen","awesomeness"));
        adventure.list(inventory);
        assertEquals(3,inventory.size());
    }

}