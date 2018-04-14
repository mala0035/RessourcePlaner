package model;

public class EventArticle {


    private final int articleId;

    private String eventId;

    public EventArticle(int articleId, String eventId){
        this.articleId = articleId;
        this.eventId = eventId;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getEventId() {
        return eventId;
    }

}
