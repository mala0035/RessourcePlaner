package sample;


public class FailResult {

    private final Article article;

    private final int amountPicked;

    public FailResult(Article article, int amountPicked){
        this.article = article;
        this.amountPicked = amountPicked;
    }

    @Override
    public String toString(){
        return article.getName()+ ":" + " (ausgewählt: " + amountPicked + ", verfügbar: " + article.getAmount() + ")";
    }
}
