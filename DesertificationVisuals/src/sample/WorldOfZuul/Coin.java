package sample.WorldOfZuul;

public class Coin {

    private static int amount;

    public Coin(){
        int amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addCoin(){
        this.amount++;
    }

    public void removeCoin(){
        this.amount--;
    }
}
