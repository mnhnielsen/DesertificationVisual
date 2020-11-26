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

        entry = new Room("At the entry room. To start the game type: go north", 1);
        tutorial = new Room("in the tutorial room. Here you can learn how to play the game. " +
                "\nHere are some basics about the game:\n" +
                "Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                "use commandword: help & roominfo for specific info on the current room", 2);
        currencyRoom = new Room("in the vendor room. Here you can exchange your trash for saplings", 3);

        currencyObtainRoom = new Room("trash room. Here you can harvest trash using the commandword: pickup", 4);
        currencyObtainRoom.addTrash(8);

        currencyObtainRoom1 = new Room("trash room. Here you can harvest trash using the commandword: pickup", 11);
        currencyObtainRoom1.addTrash(5);

        desertBaseRoom = new Room("in the desert base room. Choose a direction to go to a desert", 5);
        desert1 = new Room("in the first desert. Stop the desertification\n" +
                "Use commandword: roominfo for information about desertification", 6);
        desert2 = new Room("in the second desert. Stop the desertification\n" +
                "Use commandword: roominfo for information about desertification", 8);
        desert3 = new Room("in the third desert. Stop the desertification\n" +
                "Use commandword: roominfo for information about desertification", 9);
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

                System.out.println("You are almost finished. You need to answer the following questions correctly. Type A,B,C or D to give your answer.\n");
                System.out.println("Question 1: What is the main difference between natural deserts and those created by desertification?");
                System.out.println("A: Natural deserts contain their own ecosystem, while those created by desertification are lifeless.");
                System.out.println("B: Natural deserts are lifeless, while those created by desertification contain their own ecosystem.");
                System.out.println("C: In a natural desert, the temperature is much higher, which results in soil erosion.");
                System.out.println("D: There is no difference.");


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
                System.out.println("Here is the second question");
                System.out.println("Question 2: What is the main factor resulting in “soil death”?");
                System.out.println("A: Excessive watering of the soil, which drowns the plant life and flushes the nutrients away, resulting in “soil death”.");
                System.out.println("B: People using the land to create desert golf fields, resulting in “soil death”.");
                System.out.println("C: Overgrazing the land with livestock, and planting crops on the same land excessively, hereby draining the soil of nutrients, and resulting in “soil death”.");
                System.out.println("D: Radioactive rays from the sun “kills” the nutrients in the soil, hereby resulting in “soil death”.");
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
                System.out.println("Here is the last question");
                System.out.println("Question 3: What leads to overexploitation of fertile soil?");
                System.out.println("A: The rising world population.");
                System.out.println("B: The demand for food.");
                System.out.println("C: The increase in livestock and crops.");
                System.out.println("D: A combination of all of the above.");
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
        System.out.println("You need to help stop the desertification");
        System.out.println("to help you need to plant saplings in the desert");
        System.out.println("To get saplings you need to pick up trash to sell in the CurrencyObtainRoom");
    }

    public void printRoomInfo() {
        switch (getCurrentRoom().getType()) {
            case 1: {
                System.out.println( ("This is the entry room \n " +
                        " here you can information about desertification... and not much else :)  \b "));
                break;
            }
            case 2: {
                System.out.println("This is the tutorial room! Here are some basics about the game:  \n " +
                        "           Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                        "           use commandword help & roominfo for specific info on the current room");
                break;
            }
            case 3: {
                System.out.println("This is the CurrencyRoom, here you can sell your collected trash for coins \n and buy saplings for planting, commandwords are: buy & sell");
                break;
            }
            case 4,11: {
                System.out.println("This is the room where you collect trash.\n Collected trash can be sold for coins in the CurrencyRoom, commandwords are: pickup");
                break;
            }
            case 5: {
                System.out.println("This is the desertbase, this room will guide you to the other rooms");
                break;
            }
            case 6: {
                //desert1
                System.out.println("This is the desert! Here your job is to plant your saplings to stop desertification, commandword is: plant");
                System.out.println("The desertification is spreading in this room.\n" +
                        " Unlike natural deserts, which contain their own ecosystem, deserts created from the result of desertification are lifeless.\n" +
                        " The soil lacks nutrients and therefore not even microorganisms can thrive in it.");
                break;
            }
            case 8: {
                //desert2
                System.out.println("This is the desert! Here your job is to plant your saplings to stop desertification, commandword is: plant");
                System.out.println("The desertification is spreading in this room. \n" +
                        "The soil has eroded from the result of overgrazing the land with livestock and from planting crops on the same land excessively.\n" +
                        " This overexploitation drains the soil of nutrients and results in “soil death”.");
                break;
            }
            case 9: {
                //desert3
                System.out.println("This is the desert! Here your job is to plant your saplings to stop desertification, commandword is: plant");
                System.out.println("The desertification is spreading in this room. \n" +
                        "Just like it is worldwide. Because of the rising world population, the demand for food, and in turn livestock and crops, is also increasing.\n" +
                        " This leads to overexploitation of fertile soil and eventually the spread of desertification.");
                break;
            }

            case 7: {
                System.out.println("This is the endRoom. You have planted all the saplings required. " +
                        "\n You will now be quizzed about desertification");
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
