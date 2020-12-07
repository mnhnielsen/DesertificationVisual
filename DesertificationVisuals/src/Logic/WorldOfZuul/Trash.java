package Logic.WorldOfZuul;

public class Trash extends Item {

    public Trash() {
        super("Trash", 1, 1);
    }


    @Override
    String getName() {
        return this.name;
    }

    @Override
    void setName(String name) {
        this.name = name;
    }

    @Override
    int getValue() {
        return this.value;
    }

    @Override
    void setValue(int value) {
        this.value = value;
    }

    @Override
    int getSize() {
        return this.size;
    }

    @Override
    void setSize(int size) {
        this.size = size;
    }
}
