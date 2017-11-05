package ORMinnlevering.Database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by hakonschutt on 05/11/2017.
 */
public class Connection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";

    public static ConnectionSource getConnection(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("data.properties")){
            properties.load(input);
            String database = properties.getProperty("db");
            String username = properties.getProperty("user");
            String password = properties.getProperty("pass");

            return new JdbcConnectionSource(DATABASE_URL + database, username, password);
        } catch (SQLException | IOException e){
            System.out.println("Unable to connect!");
            return null;
        }
    }
}
