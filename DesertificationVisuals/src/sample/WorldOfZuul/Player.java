package sample.WorldOfZuul;

public class Player {


    private static String name;
    private static Inventory inventory;
    private static Coin coin;


    public Player(String playerName){

        inventory = new Inventory();
        coin = new Coin();
        name = playerName;

    }


    public boolean hasTrash(){
        return inventory.hasTrash();
    }

    public void plantTree(){
       inventory.removeSapling();

    }

    public void sellTrash(){
        while(inventory.hasTrash()){
            inventory.removeTrash();
            coin.addCoin();
        }
    }

    public void addTrash(){
        inventory.addTrash();
    }

    public void addSapling(){
        inventory.addSapling();
        coin.removeCoin();
    }

    public int getCoins(){
        return coin.getAmount();
    }

    public String getName(){
        return name;
    }

    public void printPlayerInventory(){
        System.out.println("---Player inventory " + "Coins:" + getCoins() + " Trash:" + inventory.countTrash() +" Sapling:" + inventory.countSapling()+"---");
    }

    public void plant() {
        inventory.removeSapling();
    }

    public boolean hasSapling() {
        return inventory.hasSapling();
    }
}
