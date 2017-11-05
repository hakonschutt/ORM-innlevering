package ORMinnlevering.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "City")
public class City {

    public static final String NAME_FIELD_NAME = "Name";
    public static final String COUNTRY_CODE_FIELD_NAME = "CountryCode";
    public static final String DISTRICT_FIELD_NAME = "District";
    public static final String POPULATION_FIELD_NAME = "Population";

    @DatabaseField(generatedId = true, unique = true)
    private int id;

    @DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, foreign = true, canBeNull = false)
    private String countryCode;

    @DatabaseField(columnName = DISTRICT_FIELD_NAME, canBeNull = false)
    private String district;

    @DatabaseField(columnName = POPULATION_FIELD_NAME, canBeNull = false)
    private int population;

    public City() {}

    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        return id == ((City) other).id;
    }
}
