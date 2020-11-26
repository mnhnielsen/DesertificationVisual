package sample.WorldOfZuul;

public class Strings {
    private String sentence;


    public static String GameTitle()
    {
        return  ("                            _    _            _     _          __   ______            _                     \n" +
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

    public static String Info()
    {
        return ("You need to help stop the desertification \n" +
                "to help you need to plant saplings in the desert \n" +
                "To get saplings you need to pick up trash to sell in the CurrencyObtainRoom \n");
    }

    public static String Question1()
    {
        return ("You are almost finished. You need to answer the following questions correctly. Type A,B,C or D to give your answer.\n" +
                "Question 1: What is the main difference between natural deserts and those created by desertification? \n" +
                "A: Natural deserts contain their own ecosystem, while those created by desertification are lifeless. \n" +
                "B: Natural deserts are lifeless, while those created by desertification contain their own ecosystem. \n" +
                "C: In a natural desert, the temperature is much higher, which results in soil erosion. \n" +
                "D: There is no difference.");
    }

    public static String Question2()
    {
        return ("Here is the second question \n" +
        "Question 2: What is the main factor resulting in “soil death”? \n" +
        "A: Excessive watering of the soil, which drowns the plant life and flushes the nutrients away, resulting in “soil death”. \n" +
        "B: People using the land to create desert golf fields, resulting in “soil death”. \n" +
        "C: Overgrazing the land with livestock, and planting crops on the same land excessively, hereby draining the soil of nutrients, and resulting in “soil death”. \n" +
        "D: Radioactive rays from the sun “kills” the nutrients in the soil, hereby resulting in “soil death”. \n");
    }

    public static String Question3()
    {
        return ("Here is the last question \n" +
        "Question 3: What leads to overexploitation of fertile soil? \n" +
        "A: The rising world population. \n" +
        "B: The demand for food. \n" +
        "C: The increase in livestock and crops. \n" +
        "D: A combination of all of the above.");
    }
    public static String EntryRoomDescription()
    {
        return ("At the entry room. To start the game type: go north");
    }

    public static String TutorialRoomDescription()
    {
        return ("in the tutorial room. Here you can learn how to play the game. \n" +
                "Here are some basics about the game:\n" +
                "Go between rooms to pick up trash to sell for coins. Coins are used to buy saplings to plant \n" +
                "use commandword: help & roominfo for specific info on the current room");
    }
    public static String CurrencyRoomDescription()
    {
        return ("in the vendor room. Here you can exchange your trash for saplings");
    }

    public static String CurrencyObtainRoomDescription()
    {
        return ("trash room. Here you can harvest trash using the commandword: pickup");
    }

    public static String DesertBaseRoomDescription()
    {
        return ("in the desert base room. Choose a direction to go to a desert");
    }

    public static String DesertInfo()
    {
        return ("in the first desert. Stop the desertification\n" +
                "Use commandword: roominfo for information about desertification");
    }


    
}
