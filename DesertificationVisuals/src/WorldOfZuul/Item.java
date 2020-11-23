package WorldOfZuul;

public abstract class Item {

    String name;
    int value;
    int size;

    public Item(String name, int value, int size) {
        this.name = name;
        this.value = value;
        this.size = size;
    }

    abstract String getName();

    abstract void setName(String name);

    abstract int getValue();

    abstract void setValue(int value);

    abstract int getSize();

    abstract void setSize(int size);
}
