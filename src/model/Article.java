package model;

public class Article {

    private final int id;
    private final String name;
    private final String articleNr;
    private int amount;
    private boolean available;

    //private final Categories category;

    public Article(String name, int id, String articleNr) {
        this.name = name;
        this.id = id;
        this.articleNr = articleNr;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount1) {
        this.amount = amount1;
    }

    public String getArticleNr() {
        return articleNr;
    }
}
