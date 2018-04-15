package db;

import model.Article;
import model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ArticleRepository extends BaseRepository {

    //Query to collect all Articles from selected Categorys
    public static Collection<Article> readCategory(List<Category> categoryList) {
        Collection<Article> articles = new ArrayList<>();

        try {
            Statement statement = getDatabaseConnection().createStatement();
            String collect = categoryList.stream().map(Category::name).collect(Collectors.joining("','"));
            String getCategory = "SELECT * FROM Storage WHERE Category IN ('" + collect + "') Group by ArticleNr";
            ResultSet categoryResult = statement.executeQuery(getCategory);
            while (categoryResult.next()) {
                String articleName = categoryResult.getString("ArticleName");
                int storageId = categoryResult.getInt("StorageID");
                String articleNr = categoryResult.getString("ArticleNr");
                articles.add(new Article(articleName, storageId, articleNr));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }

    //Query to search Articles by ID

    public static Collection<Article> findBy(Collection<Integer> ids) {
        Collection<Article> articles = new ArrayList<>();

        try {
            Statement statement = getDatabaseConnection().createStatement();
            String collect = ids.stream().map(id -> id.toString()).collect(Collectors.joining("','"));
            String getArticlesFromIds = "SELECT * FROM Storage WHERE StorageID IN ('" + collect + "')";
            ResultSet resultSet = statement.executeQuery(getArticlesFromIds);
            while (resultSet.next()) {
                String articleName = resultSet.getString("ArticleName");
                int id = resultSet.getInt("StorageID");
                String articleNr = resultSet.getString("ArticleNr");
                Article article = new Article(articleName, id, articleNr);
                articles.add(article);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }

    //search equal Article IDs
    public static Collection<Article> getBy(Collection<String> articleNrs) {
        Collection<Article> articles = new ArrayList<>();

        try {
            String collect = articleNrs.stream().collect(Collectors.joining("','"));
            Statement statement = getDatabaseConnection().createStatement();
            String sqlQuery = "SELECT * FROM Storage WHERE ArticleNr IN ('" + collect + "') ";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String articleName = resultSet.getString("ArticleName");
                String articleNr = resultSet.getString("ArticleNr");
                int articleId = resultSet.getInt("StorageId");
                Article article = new Article(articleName, articleId, articleNr);
                articles.add(article);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articles;
    }

}
