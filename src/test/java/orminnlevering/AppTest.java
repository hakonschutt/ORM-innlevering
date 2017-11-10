package orminnlevering;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableInfo;
import com.j256.ormlite.table.TableUtils;
import orminnlevering.dto.City;
import orminnlevering.dto.Country;
import orminnlevering.dto.CountryLanguage;
import orminnlevering.handler.Connection;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 * Simple test for ORMlite
 */
public class AppTest{
    private String dbName;
    private App app;
    private ConnectionSource connectionSource;

    @Before
    public void setup() throws SQLException, IOException {
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
        app = new App();
        connectionSource = testConnection();
        app.initDao(connectionSource);
        app.createTables(connectionSource);
    }

    @After
    public void tearDown() throws IOException, SQLException {
        TableUtils.dropTable(connectionSource, City.class, false);
        TableUtils.dropTable(connectionSource, Country.class, false);
        TableUtils.dropTable(connectionSource, CountryLanguage.class, false);

        connectionSource.close();
    }

    @Test
    public void connectionNotNullTest(){
        ConnectionSource connection = Connection.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void daoNotNullTest(){
        assertNotNull(app.cityDao);
        assertNotNull(app.countryLanguageDao);
        assertNotNull(app.countryDao);
    }

    @Test
    public void tablesExistsTest() throws SQLException {
        TableInfo<Country, String> countryTableInfo = new TableInfo<>(connectionSource, null, Country.class);
        TableInfo<City, Integer> cityTableInfo = new TableInfo<>(connectionSource, null, City.class);
        TableInfo<CountryLanguage, Integer> countryLangTableInfo = new TableInfo<>(connectionSource, null, CountryLanguage.class);

        assertEquals(Country.class, countryTableInfo.getDataClass());
        assertEquals("country", countryTableInfo.getTableName());

        assertEquals(City.class, cityTableInfo.getDataClass());
        assertEquals("city", cityTableInfo.getTableName());

        assertEquals(CountryLanguage.class, countryLangTableInfo.getDataClass());
        assertEquals("countrylanguage", countryLangTableInfo.getTableName());
    }

    @Test
    public void createElementsTest() throws IOException, SQLException {
        app.createElements();

        List<Country> countryItem = app.countryDao.queryForEq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
        List<City> cities = app.cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
        List<CountryLanguage> cl = app.countryLanguageDao.queryForEq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");

        assertTrue(countryItem.size() == 1);
        assertTrue(countryItem.get(0).getCode().equals("XNL"));

        assertTrue(cities.size() == 2);
        cities.stream().forEach(e -> {
            assertTrue(e.getCountry().getCode().equals("XNL"));
        });

        assertTrue(cl.size() == 2);
        cl.stream().forEach(e -> {
            assertTrue(e.getCountry().getCode().equals("XNL"));
        });

        app.deleteElements();
    }

    @Test
    public void updateElementsTest() throws SQLException {
        app.createElements();
        app.updateElements();

        List<City> cities = app.cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
        List<CountryLanguage> cl = app.countryLanguageDao.queryForEq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");

        assertTrue(cities.size() == 2);
        assertTrue(cities.get(1).getName().equals("Oldest city"));

        assertTrue(cl.size() == 2);
        assertTrue(cl.get(0).getLanguage().equals("NewerLanguage"));

        app.deleteElements();
    }

    @Test
    public void deleteElementsTest() throws SQLException {
        app.createElements();

        List<City> cities = app.cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
        assertTrue(cities.size() == 2);

        app.deleteElements();

        List<Country> countryItem = app.countryDao.queryForEq(Country.COUNTRY_CODE_FIELD_NAME, "XNL");
        cities = app.cityDao.queryForEq(City.COUNTRY_CODE_FIELD_NAME, "XNL");
        List<CountryLanguage> cl = app.countryLanguageDao.queryForEq(CountryLanguage.COUNTRY_CODE_FIELD_NAME, "XNL");

        assertTrue(countryItem.size() == 0);
        assertTrue(cities.size() == 0);
        assertTrue(cl.size() == 0);
    }

    public ConnectionSource testConnection(){
        try {
            return new JdbcConnectionSource("jdbc:h2:~/test", "sa", "");
        } catch (SQLException e){
            System.out.println("Unable to connect!");
            return null;
        }
    }
}
