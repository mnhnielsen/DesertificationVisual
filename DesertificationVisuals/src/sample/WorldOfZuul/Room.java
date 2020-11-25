package sample.WorldOfZuul;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    private Inventory inventory;
    private String description;
    private HashMap<String, Room> exits;


    // 1 = Entry, 2 = Tutorial, 3 = CurrencyRoom, 4 = currencyObtain, 5 = desertBase, 6 = desert, 7 = endRoom
    private int type;


    public Room(String description, int type)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        inventory = new Inventory();
        this.type = type;
    }

    public void addTrash(int amountOfTrash){
        for (int i = 0; i < amountOfTrash; i++) {
            inventory.addTrash();
        }
    }
    public void removeTrash(){
        inventory.removeTrash();
    }
    public int getType(){
        return this.type;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        int exitCount=0;

        for(String exit : keys) {
            if(exits.get(exit).getType()==3) {
                returnString += " " + exit +"(Vendor room)";
                exitCount++;
            }
            else if(exits.get(exit).getType()==4){
                returnString += " " + exit +"(Trash Room)";
                exitCount++;
            }
            else if(exits.get(exit).getType()==5){
                returnString += " " + exit+ "(Desert base)";
                exitCount++;
            }
            else if (exits.get(exit).getType()==6||exits.get(exit).getType()==8||exits.get(exit).getType()==9){
                returnString += " " + exit +"(Desert)";
                exitCount++;
            }
            else if(exits.get(exit).getType()==7){
                returnString += " " + exit+ "(End Quiz)";
                exitCount++;
            }
            else{
                returnString += " " + exit;
                exitCount++;
            }
            if(exitCount< keys.size()){
                returnString += ",";
            }

        }

        return returnString + "\n";
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void printRoomInventory(){
        System.out.println("Room contains " + inventory.countTrash() + " pieces of trash.");
    }

    public boolean containsTrash(){
        return inventory.hasTrash();
    }

}

