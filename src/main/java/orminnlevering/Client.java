package orminnlevering;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import orminnlevering.dto.City;
import orminnlevering.dto.Country;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.logger.LocalLog;
import orminnlevering.dto.CountryLanguage;
import orminnlevering.dto.support.Continent;
import orminnlevering.dto.support.IsOfficial;
import orminnlevering.handler.Connection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hakonschutt on 07/11/2017.
 */
public class Client {
    private Dao<City, Integer> cityDao;
    private Dao<Country, String> countryDao;
    private Dao<CountryLanguage, Integer> countryLanguageDao;
    private Scanner sc = new Scanner(System.in);

    public void main(){
        try (ConnectionSource connectionSource = Connection.getConnection()) {
            if (connectionSource == null)
                throw new SQLException();

            initDao(connectionSource);

            TableUtils.createTableIfNotExists(connectionSource, Country.class);
            TableUtils.createTableIfNotExists(connectionSource, City.class);
            TableUtils.createTableIfNotExists(connectionSource, CountryLanguage.class);
        } catch (SQLException | IOException e){
            System.out.println("Unable to connect");
            System.out.println("Quiting program");
            return;
        }

        boolean quit = false;
        while(!quit){
            quit = runClient();
        }

        System.out.println("Quiting program");
    }

    public void initDao(ConnectionSource connection){
        try {
            countryDao = DaoManager.createDao(connection, Country.class);
            cityDao = DaoManager.createDao(connection, City.class);
            countryLanguageDao = DaoManager.createDao(connection, CountryLanguage.class);
        } catch (SQLException e) {
            System.out.println("Unable to setup dao for city, Country and CountryLanguage class.");
        }
    }

    public String getUserCommand(){
        System.out.print("What command do you want to execute: ");
        return sc.nextLine().toLowerCase().replace(" ", "");
    }

    public void printInstructions(){

    }

    public boolean runClient(){
        String command = getUserCommand();

        switch(command){
            case "info":
                printInstructions();
                break;
            case "create":
            case "read":
            case "search":
            case "update":
            case "delete":
                System.out.println("commands...");
                break;
            case "quit":
                return true;
            default:
                System.out.println("Unknown command");
                break;
        }
        return false;
    }

    public static void main( String[] args ) throws Exception {
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
        new Client().main();
    }
}
