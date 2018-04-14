package db;
import model.Article;
import model.Category;
import model.Event;
import model.EventArticle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

abstract class BaseRepository {

    //create connection to database
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/RessourcePlaner";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection databaseConnection;

    //constructor
    protected BaseRepository() {
    }

    //check if there is already a connection. If it's not, create one
    static {

        if (databaseConnection == null) {

            try {
                Class.forName(JDBC_DRIVER);

                System.out.println("Baut Verbindung auf");
                databaseConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();


            }
        }
    }

    protected static Connection getDatabaseConnection(){
        return databaseConnection;

    }

















}



