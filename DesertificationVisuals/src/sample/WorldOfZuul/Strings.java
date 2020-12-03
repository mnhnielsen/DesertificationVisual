package sample.WorldOfZuul;

public class Strings {
    private String sentence;


    public static String GameTitle() {
        return ("                            _    _            _     _          __   ______            _                     \n" +
                "                           | |  | |          | |   | |        / _| |___  /           | |                    \n" +
                "                           | |  | | ___  _ __| | __| |   ___ | |_     / / _   _ _   _| |                    \n" +
                "                           | |/\\| |/ _ \\| '__| |/ _` |  / _ \\|  _|   / / | | | | | | | |                    \n" +
                "                           \\  /\\  | (_) | |  | | (_| | | (_) | |   ./ /__| |_| | |_| | |                    \n" +
                "                            \\/  \\/ \\___/|_|  |_|\\__,_|  \\___/|_|   \\_____/\\__,_|\\__,_|_|                    \n" +
                "          ______                   _   _  __ _           _   _               _____    _ _ _   _             \n" +
                "          |  _  \\                 | | (_)/ _(_)         | | (_)             |  ___|  | (_| | (_)            \n" +
                "          | | | |___ ___  ___ _ __| |_ _| |_ _  ___ __ _| |_ _  ___  _ __   | |__  __| |_| |_ _  ___  _ __  \n" +
                "          | | | / _ / __|/ _ | '__| __| |  _| |/ __/ _` | __| |/ _ \\| '_ \\  |  __|/ _` | | __| |/ _ \\| '_ \\ \n" +
                "          | |/ |  __\\__ |  __| |  | |_| | | | | (_| (_| | |_| | (_) | | | | | |__| (_| | | |_| | (_) | | | |\n" +
                "          |___/ \\___|___/\\___|_|   \\__|_|_| |_|\\___\\__,_|\\__|_|\\___/|_| |_| \\____/\\__,_|_|\\__|_|\\___/|_| |_|\n" +
                "                                                                                                            \n" +
                "                                                                                                            ");
    }

    public static String Info() {
        return ("You need to help stop the desertification \n" +
                "to help you need to plant saplings in the desert \n" +
                "To get saplings you need to pick up trash to sell in the CurrencyObtainRoom \n");
    }

    public static String Question1() {
        return ("You are almost finished. You need to answer the following questions correctly.\n" +
                "Question 1: What is the main difference between natural deserts and those created by desertification? \n");
    }

    public static String answer1(String input) {

        switch (input) {
            case "A":
                return ("A: Natural deserts contain their own ecosystem, while those created by desertification are lifeless.");
            case "B":
                return ("B: Natural deserts are lifeless, while those created by desertification contain their own ecosystem.");
            case "C":
                return ("C: In a natural desert, the temperature is much higher, which results in soil erosion.");
            case "D":
                return ("D: There is no difference.");
        }
        return "";
    }

    public static String Question2() {
        return ("Here is the second question \n" +
                "Question 2: What is the main factor resulting in “soil death”?");
    }

    public static String answer2(String input) {
        switch (input) {
            case "A":
                return ("A: Excessive watering of the soil, which drowns the plant life and flushes the nutrients away, resulting in “soil death”.");
            case "B":
                return ("B: People using the land to create desert golf fields, resulting in 'soil death'");
            case "C":
                return ("C: Overgrazing the land with livestock, and planting crops on the same land excessively, hereby draining the soil of nutrients, and resulting in 'soil death'.");
            case "D":
                return ("D: Radioactive rays from the sun “kills” the nutrients in the soil, hereby resulting in 'soil death'");
        }
        return "";
    }


    public static String Question3() {
        return ("Here is the last question \n" +
                "Question 3: What leads to overexploitation of fertile soil? \n");
    }

    public static String answer3(String input) {
        switch (input) {
            case "A":
                return ("A: The rising world population.");
            case "B":
                return ("B: The demand for food.");
            case "C":
                return ("C: The increase in livestock and crops.");
            case "D":
                return ("D: A combination of all of the above.");
        }
        return "";
    }

    public static String StartRoom() {
        return ("Welcome to The World of Zuul - Desertification Edition! \n" +
                "Here the environment is endangered by desertification. Desertification is unnatural creation and spread \n" +
                "of deserts, caused by us humans. When we continuously chose to grow crops on the same plots of land or when we \n" +
                "herd livestock excessively and cause overgrazing, we cause soil erosion. Essentially, we drain the nutritions \n" +
                "out of the soil and cause 'soil death', making the soil unfit for supporting plant life.\n" +
                "But the spread of desertification can be stopped, by planting trees along the border of the desert. Essentially \n" +
                "creating a 'green wall' to stop the spread. This is your task and purpose. To plant trees to stop the desertification");
    }

    public static String EntryRoomDescription() {
        return ("At the entry room. To start the game type: go north");
    }

    public static String TutorialRoomDescription() {
        return ("in the tutorial room. Here you can learn how to play the game. \n" +
                "Here are some basics about the game:\n" +
                "Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                "use commandword: help & roominfo for specific info on the current room");
    }

    public static String CurrencyRoomDescription() {
        return ("in the vendor room. Here you can exchange your trash for saplings");
    }

    public static String CurrencyObtainRoomDescription() {
        return ("trash room. Here you can harvest trash using the commandword: pickup");
    }

    public static String DesertBaseRoomDescription() {
        return ("in the desert base room. Choose a direction to go to a desert");
    }

    public static String DesertInfo() {
        return ("in the first desert. Stop the desertification\n" +
                "Use commandword: roominfo for information about desertification");
    }

    public static String entryRoomtxt() {
        return ("This is the entry room \n " +
                " here you can information about desertification... and not much else :)  \b ");
    }

    public static String TutorialRoomtxt() {
        return ("This is the tutorial room! Here are some basics about the game:  \n " +
                "           Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                "           use commandword help & roominfo for specific info on the current room");
    }

    public static String CurrencyRoomtxt() {
        return ("This is the CurrencyRoom, here you can sell your collected trash for coins \n and buy saplings for planting, commandwords are: buy & sell");
    }

    public static String CurrencyCollecttxt() {
        return ("This is the room where you collect trash.\n Collected trash can be sold for coins in the CurrencyRoom, commandwords are: pickup");
    }

    public static String DesertBasetxt() {
        return ("This is the desertbase, this room will guide you to the other rooms");
    }

    public static String Deserttxt() {
        return ("This is the desert! Here your job is to plant your saplings to stop desertification, commandword is: plant");
    }

    public static String Desert1txt() {
        return ("The desertification is spreading in this room.\n" +
                "Unlike natural deserts, which contain their own ecosystem, deserts created from the result of desertification are lifeless.\n" +
                "The soil lacks nutrients and therefore not even microorganisms can thrive in it.");
    }

    public static String Desert2txt() {
        return ("The desertification is spreading in this room. \n" +
                "The soil has eroded from the result of overgrazing the land with livestock and from planting crops on the same land excessively.\n" +
                "This overexploitation drains the soil of nutrients and results in “soil death”.");
    }

    public static String Desert3txt() {
        return ("The desertification is spreading in this room. \n" +
                "Just like it is worldwide. Because of the rising world population, the demand for food, and in turn livestock and crops, is also increasing.\n" +
                "This leads to overexploitation of fertile soil and eventually the spread of desertification.");
    }

    public static String endRoomtxt() {
        return ("This is the endRoom. You have planted all the saplings required. " +
                "\n You will now be quizzed about desertification");
    }


}


