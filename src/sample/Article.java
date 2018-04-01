package sample;

public class Article {

    private final int id;
    private final String name;
    private int amount;

    //private final Categories category;

    public Article(String name,int id,int amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
        //this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getId() { return id;}

    public int getAmount() {return amount;}

    public void setAmount(int amount1){
        this.amount = amount1;
    }
}
