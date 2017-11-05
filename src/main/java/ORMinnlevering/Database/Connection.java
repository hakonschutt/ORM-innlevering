package ORMinnlevering.Database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by hakonschutt on 05/11/2017.
 */
public class Connection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/orm";

    public static ConnectionSource getConnection(){
        try {

            ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL, "student", "student");
            return connectionSource;
        } catch (SQLException e){
            System.out.println("Unable to connect!");
            return null;
        }
    }
}
