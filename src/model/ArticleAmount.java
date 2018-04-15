package model;

import java.util.Objects;

public class ArticleAmount {

    private final Article article;
    private final long count;

    public ArticleAmount(Article article, long count) {
        this.article = article;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAmount that = (ArticleAmount) o;
        return Objects.equals(article.getArticleNr(), that.article.getArticleNr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(article.getArticleNr());
    }

    @Override
    public String toString() {
        return article.getName() + " (x" + count + ")";
    }
}
