package sample;


public class FailResult {

    private final String articleName;
    private final long available;
    private final int amountPicked;

    public FailResult(String articleName, long available, int amountPicked){
        this.articleName = articleName;
        this.available = available;
        this.amountPicked = amountPicked;
    }

    @Override
    public String toString(){
        return articleName+ ":" + " (ausgewählt: " + amountPicked + ", verfügbar: " + available + ")";
    }
}
