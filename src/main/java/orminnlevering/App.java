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
import orminnlevering.dto.CountryLanguage;
import orminnlevering.dto.support.Continent;
import orminnlevering.dto.support.IsOfficial;
import orminnlevering.handler.Connection;
import java.util.List;

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

            initDao(connectionSource);

            TableUtils.createTableIfNotExists(connectionSource, Country.class);
            TableUtils.createTableIfNotExists(connectionSource, City.class);
            TableUtils.createTableIfNotExists(connectionSource, CountryLanguage.class);

            Country country = createCountry();
            City city = createCity();
            CountryLanguage countryLanguage = createLanguage();

            countryDao.createIfNotExists(country);
            cityDao.createIfNotExists(city);
            countryLanguageDao.create(countryLanguage);

            List<City> cities = cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "NOR");

            for (City c : cities){
                String overview = String.format("%-8s %-20s %-8s %-20s %-15s", c.getId(), c.getName(), c.getCountryCode(), c.getDistrict(), c.getPopulation());
                System.out.println(overview);
            }

            List<CountryLanguage> countryLanguages = countryLanguageDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");

            System.out.println("Got it! " + countryLanguages.get(0).getLanguage());

            UpdateBuilder<CountryLanguage, Integer> updateBuilder = countryLanguageDao.updateBuilder();
            updateBuilder.where().eq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");
            updateBuilder.updateColumnValue(CountryLanguage.LANGUAGE_FIELD_NAME, "NewerLanguage");
            updateBuilder.update();

            List<CountryLanguage> countryLanguagesTwo = countryLanguageDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");

            System.out.println("Got it! " + countryLanguagesTwo.get(0).getLanguage());

            DeleteBuilder<CountryLanguage, Integer> deleteBuilder = countryLanguageDao.deleteBuilder();
            deleteBuilder.where().eq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");
            deleteBuilder.delete();

            List<CountryLanguage> countryLanguagesThree = countryLanguageDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");

            System.out.println("Size of query = " + countryLanguagesThree.size());

            DeleteBuilder<City, Integer> cityDelete = cityDao.deleteBuilder();
            cityDelete.where().eq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
            cityDelete.delete();

            DeleteBuilder<Country, String> countryDelete = countryDao.deleteBuilder();
            countryDelete.where().eq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
            countryDelete.delete();

        } catch (SQLException | IOException e){
            System.out.println("Unable to continue.");
        }

        try (ConnectionSource connectionSource = Connection.getConnection()) {
            if (connectionSource == null)
                throw new SQLException();

            List<City> cities = cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "NOR");

            for (City c : cities){
                String overview = String.format("%-8s %-20s %-8s %-20s %-15s", c.getId(), c.getName(), c.getCountryCode(), c.getDistrict(), c.getPopulation());
                System.out.println(overview);
            }

        } catch (SQLException | IOException e){
            System.out.println("Unable to continue.");
        }
    }

    public void initDao(ConnectionSource connection){
        try {
            cityDao = DaoManager.createDao(connection, City.class);
            countryDao = DaoManager.createDao(connection, Country.class);
            countryLanguageDao = DaoManager.createDao(connection, CountryLanguage.class);
        } catch (SQLException e) {
            System.out.println("Unable to setup dao for city, Country and CountryLanguage class.");
        }
    }

    public City createCity(){
        City city = new City();
        city.setCountryCode("XNL");
        city.setDistrict("North Land");
        city.setName("New city");
        city.setPopulation(200_000);

        return city;
    }

    public Country createCountry(){
        Country country = new Country();
        country.setCode("XNL");
        country.setName("New Land");
        country.setContinent(Continent.OCEANIA);
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

    public CountryLanguage createLanguage(){
        CountryLanguage lang = new CountryLanguage();
        lang.setCountryCode("XNL");
        lang.setLanguage("NewLang");
        lang.setIsOfficial(IsOfficial.T);
        lang.setPercentage((float) 13.4);

        return lang;
    }

    public static void main( String[] args ) throws Exception {
        //System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
        new App().main();
    }
}
