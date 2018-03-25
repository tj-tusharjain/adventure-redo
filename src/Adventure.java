import com.google.gson.Gson;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that implements the Adventure Game.
 */
public class Adventure {

    static final String DEFAULT_URL = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";

    /**
     * A method that implements the interface of the game.
     * @param gameObject the world of the Adventure game.
     * @param roomIndex the index of room.
     * @return a boolean whether the game will continue to run or not.
     */
    public boolean gameInterface(Layout gameObject, int roomIndex){


        boolean gameRun = true;
        Room presentRoom = gameObject.getRooms()[roomIndex];

        System.out.println(presentRoom.description);

        if (presentRoom.name.equalsIgnoreCase(gameObject.getEndingRoom())){
            System.out.println("You have reached your final destination.");
            gameRun = false;
            return gameRun;
        }

        if (presentRoom.name.equalsIgnoreCase(gameObject.getStartingRoom())){
            System.out.println("Your Journey begins here...");
        }

        if (presentRoom.items.size() == 0){
            System.out.print("This room contains nothing");
        }else {
            System.out.print("This room contains ");
            for (int i = 0; i < presentRoom.items.size(); i++){
                if (i == presentRoom.items.size() - 1 && i != 0 ){

                    System.out.print(" and "+presentRoom.items.get(i)+".");

                }else if(i != 0){

                    System.out.print(" "+presentRoom.items.get(i)+", ");

                }else{

                    System.out.print(presentRoom.items.get(i));

                }
            }
        }
        System.out.println("");

        if (presentRoom.directions.size() == 0){

            System.out.println("It's a Dead End");

        }else {

            System.out.print("From here, you can go: ");

            for (int i = 0; i < presentRoom.directions.size(); i++){

                if (i == presentRoom.directions.size() - 1 && i != 0){

                    System.out.print(" and " + presentRoom.directions.get(i).directionName+".");

                }else if(i != 0) {

                    System.out.print(" "+presentRoom.directions.get(i).directionName+", ");

                }else{

                    System.out.print(presentRoom.directions.get(i).directionName);

                }
            }
        }

        System.out.println("");
        return gameRun;
    }

    /**
     * A method that implements the direction navigation.
     * @param gameObject the world of the Adventure game.
     * @param commandVariable the action to take place.
     * @param presentRoom the present room object.
     * @param roomIndex the index of room.
     * @return the reference index.
     */
    public int go(Layout gameObject, String commandVariable, Room presentRoom, int roomIndex){

        boolean canGo = false;

        for (int i = 0; i < presentRoom.directions.size(); i++){
            String direction = presentRoom.directions.get(i).directionName;
            String roomName = presentRoom.directions.get(i).room;
            if (direction.equalsIgnoreCase(commandVariable)){
                for (int j = 0; j < gameObject.getRooms().length; j++){
                    if (gameObject.getRooms()[j].name.equalsIgnoreCase(roomName)){
                        canGo = true;
                        roomIndex = j;
                        break;
                    }
                }
                break;
            }
        }
        if (canGo == false){
            System.out.println("I cannot go  "+commandVariable);
        }
        return roomIndex;
    }

    /**
     * A method that takes the object inputted by the user
     * @param inventory the list of items the player is carrying.
     * @param commandVariable the command entered by the player.
     * @param presentRoom the present room of the player.
     */
    public void take(ArrayList<String> inventory, String commandVariable, Room presentRoom){
        boolean canTakeItem = false;
        for (String i: presentRoom.items) {
            if(i.equalsIgnoreCase(commandVariable)) {
                canTakeItem = true;
                inventory.add(commandVariable);
                presentRoom.items.remove(i);
                break;
            }
        }
        if (canTakeItem == false){
            System.out.println("I can't take "+commandVariable);
        }
        return;
    }


    /**
     *A method that drops an item that you are carrying.
     * @param inventory a list of items that the player is carrying.
     * @param commandVariable the command that the player gives.
     * @param presentRoom the present room the player is in.
     */
    public void drop(ArrayList<String> inventory, String commandVariable, Room presentRoom){
        boolean canDropItem = false;
        for (String i: inventory) {
            if(i.equalsIgnoreCase(commandVariable)) {
                canDropItem = true;
                presentRoom.items.add(commandVariable);
                inventory.remove(commandVariable);
                break;
            }
        }
        if (canDropItem == false){
            System.out.println("I can't drop "+commandVariable);
        }
        return;
    }


    /**
     *A method that lists the items the player is carrying.
     * @param inventory the list of items the player is carrying.
     */
    public void list(ArrayList<String> inventory){
        if (inventory.size() != 0){
            System.out.print("You are carrying ");
            for (int i = 0; i < inventory.size(); i++){
                if (i == inventory.size() - 1 && i != 0){
                    System.out.print(" and "+inventory.get(i)+".");
                }else if (i != 0) {
                    System.out.print(inventory.get(i)+" , ");
                }else{
                    System.out.print(inventory.get(i)+" ");
                }
            }
            System.out.println("");
        }else{
            System.out.println("You are carrying nothing");
        }
        return;
    }


    /**
     * The main method of the game.
     * @param args the list of Json's to be called.
     * @throws Exception any exceoption that's thrown is handled.
     */
    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);
        Adventure adventureObj = new Adventure();
        String urlInput = "";
        boolean game = false;
        Layout gameObject = new Layout();
        urlInput = args[0];

        if (gameObject.fileLoading(urlInput) == null){
            urlInput = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
            gameObject = gameObject.fileLoading(urlInput);
        }else{
            gameObject = gameObject.fileLoading(urlInput);
        }



        int roomIndex = 0;
        ArrayList<String> inventory = new ArrayList<>();

        System.out.println("Input START to begin your Adventure:");
        String gameCommand = sc.nextLine();

        if (gameCommand.equalsIgnoreCase("start")){
            game = true;
        }else {
            System.out.println("Game failed to run");
        }

        while (game){

            game = adventureObj.gameInterface(gameObject, roomIndex);

            if (game == false){
                continue;
            }

            System.out.println("Enter Command: ");
            gameCommand = sc.nextLine();

            gameCommand = gameCommand.toLowerCase();

            String[] commandArr = gameCommand.split(" +");
            String command = commandArr[0];

            String commandVariable = "";

            if (commandArr.length == 2){

                commandVariable = commandArr[1];

            }

            if (commandArr.length > 2){
                for (int i = 1; i < commandArr.length; i++) {
                    if (i == 1){
                        commandVariable += commandArr[i];
                    }else {
                        commandVariable += " "+commandArr[i];
                    }
                }
            }

            Room presentRoom = gameObject.getRooms()[roomIndex];

            switch (command)
            {
                case "go":
                    roomIndex = adventureObj.go(gameObject, commandVariable, presentRoom, roomIndex);
                    break;

                case "take":
                    adventureObj.take(inventory, commandVariable, presentRoom);
                    break;

                case "drop":
                    adventureObj.drop(inventory, commandVariable, presentRoom);
                    break;

                case "quit":
                case "exit":
                    game = false;
                    break;

                case "list":
                    adventureObj.list(inventory);
                    break;

                default:
                    System.out.println("I don't understand "+"\'"+gameCommand+"\'");
                    break;
            }

        }
    }

}