package orminnlevering;

import orminnlevering.dto.City;
import orminnlevering.dto.Country;
import orminnlevering.dto.CountryLanguage;
import orminnlevering.dto.support.Continent;
import orminnlevering.dto.support.IsOfficial;
import orminnlevering.handler.Connection;
import orminnlevering.handler.PrintHandler;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.logger.LocalLog;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    private Dao<City, Integer> cityDao;
    private Dao<Country, String> countryDao;
    private Dao<CountryLanguage, Integer> countryLanguageDao;

    public void main() {

        try (ConnectionSource connectionSource = Connection.getConnection()) {
            if (connectionSource == null)
                throw new SQLException();

            System.out.println("This program will show execution of CRUD operation with OrmLite.");
            initDao(connectionSource);
            System.out.println("To show simple crud methods will start by creating elements, updating them and finally deleting them.");
            int ans = getUserAnswer();

            createTables(connectionSource);
            System.out.println();
            createElements();

            if (ans > 1) {
                updateElements();

                if (ans > 2) {
                    tryDeletingCountry();
                    deleteElements();
                    System.out.println();
                    System.out.println("Trying to query for elements to check if they still exists.");
                    List<City> cities = cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");

                    if (cities.size() == 0){
                        System.out.println("No result was found for country code XNL!");
                    }
                }
            }
        } catch (SQLException | IOException e){
            System.out.println("Unable to continue.");
        }
    }

    public int getUserAnswer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("(1) Create/Read");
        System.out.println("(2) Create/Read/Update");
        System.out.println("(3) Create/Read/Update/Delete");

        while(true){
            System.out.print("What CRUD commands should we execute? ");
            int ans = sc.nextInt();
            if(ans > 0 || ans < 4){
                return ans;
            } else {
                System.out.println("Invalid answer. Try again.");
            }
        }
    }

    public void initDao(ConnectionSource connection) {
        try {
            countryDao = DaoManager.createDao(connection, Country.class);
            cityDao = DaoManager.createDao(connection, City.class);
            countryLanguageDao = DaoManager.createDao(connection, CountryLanguage.class);
        } catch (SQLException e) {
            System.out.println("Unable to setup dao for city, Country and CountryLanguage class.");
        }
    }

    public void createTables(ConnectionSource connection) throws SQLException {
        try {
            System.out.println("Creating tables if they don't exists.");
            TableUtils.createTableIfNotExists(connection, Country.class);
            TableUtils.createTableIfNotExists(connection, City.class);
            TableUtils.createTableIfNotExists(connection, CountryLanguage.class);
        } catch (SQLException e) {
            System.out.println("Unable to create tables.");
        }
    }

    public void createElements() {
        try {
            System.out.println("Creating country New Land");
            Country country = createCountry();
            countryDao.createIfNotExists(country);
            List<Country> countryItem = countryDao.queryForEq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
            PrintHandler.printCountryList(countryItem);

            System.out.println("Creating cities in the newly founded country New Land");
            City city = createCity(country);
            cityDao.createIfNotExists(city);
            City cityTwo = createCityTwo(country);
            cityDao.createIfNotExists(cityTwo);
            List<City> cities = cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
            PrintHandler.printCityList(cities);

            System.out.println("Creating languages in the newly founded country New Land");
            CountryLanguage countryLanguage = createLanguage(country);
            countryLanguageDao.create(countryLanguage);
            CountryLanguage countryLanguageTwo = createLanguageTwo(country);
            countryLanguageDao.create(countryLanguageTwo);
            List<CountryLanguage> cl = countryLanguageDao.queryForEq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");
            PrintHandler.printCountryLanguageList(cl);
        } catch (SQLException e){
            System.out.println("Unable to create elements.");
        }
    }

    public void updateElements(){
        try {
            System.out.println("Updating language with a new language name.");
            UpdateBuilder<CountryLanguage, Integer> updateBuilder = countryLanguageDao.updateBuilder();
            updateBuilder.where().eq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL")
            .and().eq(CountryLanguage.LANGUAGE_FIELD_NAME, "NewLang");
            updateBuilder.updateColumnValue(CountryLanguage.LANGUAGE_FIELD_NAME, "NewerLanguage");
            updateBuilder.update();
            List<CountryLanguage> cl = countryLanguageDao.queryForEq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");
            PrintHandler.printCountryLanguageList(cl);

            System.out.println("Updating city with a new name.");
            UpdateBuilder<City, Integer> updateBuilderCity = cityDao.updateBuilder();
            updateBuilderCity.where().eq(City.COUNTRY_CODE_FIELD_NAME, "XNL")
                    .and().eq(City.NAME_FIELD_NAME, "Old city");
            updateBuilderCity.updateColumnValue(City.NAME_FIELD_NAME, "Oldest city");
            updateBuilderCity.update();
            List<City> cities = cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
            PrintHandler.printCityList(cities);

        } catch (SQLException e){
            System.out.println("Unable to update elements.");
        }
    }

    public void tryDeletingCountry(){
        try {
            System.out.println("Trying to delete XNL from the country table.");
            DeleteBuilder<Country, String> countryDelete = countryDao.deleteBuilder();
            countryDelete.where().eq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
            countryDelete.delete();
        } catch (SQLException e){
            System.out.println("Unable to delete Country due to foreign keys.\nYou have to delete the rows from country language and city first.");
            System.out.println();
        }
    }

    public void deleteElements(){
        try {
            System.out.println("Deleting city elements.");
            DeleteBuilder<City, Integer> cityDelete = cityDao.deleteBuilder();
            cityDelete.where().eq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
            cityDelete.delete();

            System.out.println("Deleting language elements");
            DeleteBuilder<CountryLanguage, Integer> countryLanguageDelete = countryLanguageDao.deleteBuilder();
            countryLanguageDelete.where().eq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");
            countryLanguageDelete.delete();

            System.out.println("Deleting country element.");
            DeleteBuilder<Country, String> countryDelete = countryDao.deleteBuilder();
            countryDelete.where().eq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
            countryDelete.delete();
        } catch (SQLException e){
            System.out.println("Unable to delete elements.");
        }
    }

    public Country createCountry(){
        Country country = new Country();
        country.setCode("XNL");
        country.setName("New Land");
        country.setContinent(Continent.SOUTH_AMERICA.displayName());
        country.setRegion("North new");
        country.setSurfaceArea((float) 3421.00);
        country.setIndependenceYear((short)2017);
        country.setPopulation(180000);
        country.setLifeExpectancy(80.1);
        country.setGnp(6041.00);
        country.setGnpOld(5729.00);
        country.setLocalName("New");
        country.setGovernmentForm("Republic");
        country.setHeadOfState("Mister wong");
        country.setCapital(4068);
        country.setSecondCode("XL");

        return country;
    }

    public City createCity(Country country){
        City city = new City();
        city.setCountry(country);
        city.setDistrict("North Land");
        city.setName("New city");
        city.setPopulation(200_000);

        return city;
    }

    public City createCityTwo(Country country){
        City city = new City();
        city.setCountry(country);
        city.setDistrict("South Land");
        city.setName("Old city");
        city.setPopulation(150_000);

        return city;
    }

    public CountryLanguage createLanguage(Country country){
        CountryLanguage lang = new CountryLanguage();
        lang.setCountry(country);
        lang.setLanguage("NewLang");
        lang.setIsOfficial(IsOfficial.T);
        lang.setPercentage((float) 13.4);

        return lang;
    }

    public CountryLanguage createLanguageTwo(Country country){
        CountryLanguage lang = new CountryLanguage();
        lang.setCountry(country);
        lang.setLanguage("OldLang");
        lang.setIsOfficial(IsOfficial.T);
        lang.setPercentage((float) 87.6);

        return lang;
    }

    public static void main( String[] args ) throws Exception {
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
        new App().main();
    }
}
