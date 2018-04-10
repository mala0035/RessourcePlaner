package sample;

public class Article {

    private final int id;
    private final String name;
    private int amount;
    private final String articleNr;
    private  boolean available;


    //private final Categories category;

    public Article(String name,int id,String articleNr) {
        this.name = name;
        this.id = id;
        this.amount = amount;
        this.articleNr = articleNr;

    }



    public String getName() {
        return name;
    }

    public int getId() { return id;}

    public int getAmount() {return amount;}

    public void setAmount(int amount1){
        this.amount = amount1;
    }

    public String getArticleNr(){
        return articleNr;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    }
}
