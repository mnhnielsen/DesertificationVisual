package sample.WorldOfZuul;

import java.util.Scanner;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private static int saplingCount1, saplingCount2, saplingCount3;
    boolean desert1, desert2, desert3;


    Scanner input = new Scanner(System.in);

    public Game() {
        createRooms();
        parser = new Parser();
    }


    private void createRooms() {

        Room entry, tutorial, currencyRoom,
                currencyObtainRoom1, currencyObtainRoom, desertBaseRoom, desert1,
                desert2, desert3, endRoom;

        entry = new Room(Strings.EntryRoomDescription(), 1);
        tutorial = new Room(Strings.TutorialRoomDescription(), 2);
        currencyRoom = new Room(Strings.CurrencyRoomDescription(), 3);

        currencyObtainRoom = new Room(Strings.CurrencyObtainRoomDescription(), 4);
        currencyObtainRoom.addTrash(8);

        currencyObtainRoom1 = new Room(Strings.CurrencyObtainRoomDescription(), 11);
        currencyObtainRoom1.addTrash(5);

        desertBaseRoom = new Room(Strings.DesertBaseRoomDescription(), 5);
        desert1 = new Room(Strings.DesertInfo(), 6);
        desert2 = new Room(Strings.DesertInfo(), 8);
        desert3 = new Room(Strings.DesertInfo(), 9);
        endRoom = new Room("", 7);


        entry.setExit("north", tutorial);

        tutorial.setExit("north", currencyRoom);
        tutorial.setExit("south", tutorial);

        currencyRoom.setExit("north", desertBaseRoom);
        currencyRoom.setExit("west", currencyObtainRoom);
        currencyRoom.setExit("east", currencyObtainRoom1);
        currencyRoom.setExit("south", tutorial);

        currencyObtainRoom1.setExit("west", currencyRoom);

        currencyObtainRoom.setExit("east", currencyRoom);

        desertBaseRoom.setExit("north", desert3);
        desertBaseRoom.setExit("west", desert1);
        desertBaseRoom.setExit("east", desert2);
        desertBaseRoom.setExit("south", currencyRoom);

        desert1.setExit("east", desertBaseRoom);

        desert2.setExit("west", desertBaseRoom);

        desert3.setExit("south", desertBaseRoom);

        desert3.setExit("north", endRoom);

        endRoom.setExit("south", desert3);
        currentRoom = entry;
    }

    public void play() {

        System.out.println(Strings.GameTitle());
        System.out.println("Enter your name: ");

        player = new Player(input.nextLine());

        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing " + player.getName() + ". Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome " + player.getName() + " to the World of Zuul - Desertification edition!");
        System.out.println("In this game, you will learn about desertificaiton, how to slow it down and even try it out yourself");
        System.out.println("Type '" + CommandWord.HELP + "' if you need assistance along the way!");
        System.out.println();
        player.printPlayerInventory();
        System.out.println(getCurrentRoom().getLongDescription());
    }

    public boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("That's not a valid command " + "Type" + CommandWord.HELP);
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.INFO) {
            printInfo();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.PICKUP) {
            if (getCurrentRoom().getType() == 4 || getCurrentRoom().getType() == 11 && getCurrentRoom().containsTrash() ) {
                while (getCurrentRoom().containsTrash()) {
                    player.addTrash();
                    getCurrentRoom().removeTrash();
                }
                player.printPlayerInventory();
                getCurrentRoom().printRoomInventory();
            } else {
                System.out.println("Room contains no trash.");
            }
        } else if (commandWord == CommandWord.ROOMINFO) {
            printRoomInfo();
        } else if (commandWord == CommandWord.SELL) {
           if(getCurrentRoom().getType() == 3){
               if (player.hasTrash()) {
                   player.sellTrash();
                   player.printPlayerInventory();
               } else {
                   System.out.println("You have no trash to sell.");
               }
           }else{
               System.out.println("You can't sell anything in this room.");
           }

        } else if (commandWord == CommandWord.BUY) {
            if(getCurrentRoom().getType() == 3){
                int coins = player.getCoins();
                if (coins == 0) {
                    System.out.println("You do not have enough coins to buy any saplings.");
                }
                for (int i = 0; i < coins; i++) {
                    player.addSapling();
                }
                player.printPlayerInventory();
            }else{
                System.out.println("You can't buy anything in this room");
            }


        } else if (commandWord == CommandWord.PLANT) {
            if (player.hasSapling()) {
                if (getCurrentRoom().getType() == 6) {
                    if (saplingCount1 < 3 && !desert1) {
                        player.plant();
                        System.out.println("A tree has been planted");
                        saplingCount1++;

                        if(saplingCount1 >= 2){
                            System.out.println("Desertification in the western desert has been stopped");
                            desert1 = true;
                        }
                    }
                    if(desert1){
                        System.out.println("Desertification in the western desert has been stopped");
                    }

                }
                if (getCurrentRoom().getType() == 8) {
                    if (saplingCount2 < 4 && !desert2) {
                        player.plant();
                        System.out.println("A tree has been planted");
                        saplingCount2++;


                        if(saplingCount2 >= 3){
                            System.out.println("Desertification in the eastern desert has been stopped");
                            desert2 = true;
                        }
                    }
                    if(desert2){
                        System.out.println("Desertification in the eastern desert has been stopped");
                    }
                }
                if (getCurrentRoom().getType() == 9) {
                    if (saplingCount3 < 5 && !desert3) {
                        player.plant();
                        System.out.println("A tree has been planted");
                        saplingCount3++;

                        if(saplingCount3 >= 4){
                            System.out.println("Desertification in the northern desert has been stopped");
                            System.out.println("If desertification has been stopped in all rooms, go north to finish the game");
                            desert3 = true;
                        }
                    }
                    if(desert3){
                        System.out.println("Desertification in the northern desert has been stopped");
                    }
                }

            }
            if (!player.hasSapling()) {
                System.out.println("You don't have any saplings");
            }
            if(getCurrentRoom().getType() != 6 && getCurrentRoom().getType() != 8 && getCurrentRoom().getType() != 9) {
                System.out.println("You can't plant here");
            }
            player.printPlayerInventory();
        }
        endRoom();
        return wantToQuit;
    }

    private void endRoom() {
        boolean isAnswered = false;

        if (getCurrentRoom().getType() == 7) {
            if (desert1 && desert2 && desert3) {
                boolean question1 = false, question2 = false, question3 = false;

                System.out.println(Strings.Question1());


                while (!question1) {
                    System.out.print(">");
                    if (input.nextLine().equalsIgnoreCase("A")) {
                        System.out.println("Correct!\n");
                        question1 = true;
                    } else {
                        System.out.println("Incorrect. Go back to the deserts to look for information.");
                        currentRoom = getCurrentRoom().getExit("south");
                        System.out.println();
                        System.out.println(getCurrentRoom().getLongDescription());
                    }
                }
                System.out.println(Strings.Question2());
                while (!question2) {
                    System.out.print(">");
                    if (input.nextLine().equalsIgnoreCase("C")) {
                        System.out.println("Correct\n");
                        question2 = true;
                    } else {
                        System.out.println("Incorrect. Go back to the deserts to look for information.");
                        currentRoom = getCurrentRoom().getExit("south");
                        System.out.println();
                        System.out.println(getCurrentRoom().getLongDescription());
                    }
                }
                System.out.println(Strings.Question3());
                while (!question3) {
                    System.out.print(">");
                    if (input.nextLine().equalsIgnoreCase("D")) {
                        System.out.println("Correct. Thanks for playing");
                        question3 = true;
                        isAnswered = true;
                    } else {
                        System.out.println("Incorrect. Go back to the deserts to look for information.");
                        currentRoom = getCurrentRoom().getExit("south");
                        System.out.println();
                        System.out.println(getCurrentRoom().getLongDescription());
                    }
                }
            } else {
                System.out.println("You need to stop the desertification before you can end the game.");
                currentRoom = getCurrentRoom().getExit("south");
                System.out.println();
                System.out.println(getCurrentRoom().getLongDescription());
            }
        }
        if (isAnswered) {
            System.exit(0);
        }
    }

    private void printInfo() {
        System.out.println(Strings.Info());
    }

    public void printRoomInfo() {
        switch (getCurrentRoom().getType()) {
            case 1: {
                System.out.println(Strings.entryRoomtxt());
                break;
            }
            case 2: {
                System.out.println(Strings.TutorialRoomtxt());
                break;
            }
            case 3: {
                System.out.println(Strings.CurrencyRoomtxt());
            }
            case 4,11: {
                System.out.println(Strings.CurrencyCollecttxt());
                break;
            }
            case 5: {
                System.out.println(Strings.DesertBasetxt());
            }
            case 6: {
                //desert1
                System.out.println(Strings.Deserttxt());
                System.out.println(Strings.Desert1txt());
                break;
            }
            case 8: {
                //desert2
                System.out.println(Strings.Deserttxt());
                System.out.println(Strings.Desert2txt());
                break;
            }
            case 9: {
                //desert3
                System.out.println(Strings.Deserttxt());
                System.out.println(Strings.Desert3txt());
                break;
            }

            case 7: {
                System.out.println(Strings.endRoomtxt());
                break;
            }
        }
    }

    private void printHelp() {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    public void goRoom(Command command) {

        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            //player.printPlayerInventory();
            System.out.println();
            if (nextRoom != null && nextRoom.getType() == 4) {
                nextRoom.printRoomInventory();
            }
            if (nextRoom.getType() == 7) {
                System.out.print(getCurrentRoom().getShortDescription());
            } else {
                System.out.println(getCurrentRoom().getLongDescription());
            }
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    public String getRoomInfo(){
        return getCurrentRoom().getLongDescription();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
