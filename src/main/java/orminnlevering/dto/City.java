package orminnlevering.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "city")
public class City {

    public static final String ID_FIELD_NAME = "ID";
    public static final String NAME_FIELD_NAME = "Name";
    public static final String COUNTRY_CODE_FIELD_NAME = "CountryCode";
    public static final String DISTRICT_FIELD_NAME = "District";
    public static final String POPULATION_FIELD_NAME = "Population";

    @DatabaseField(columnName = ID_FIELD_NAME, generatedId = true, canBeNull = false)
    private int id;

    @DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, canBeNull = false, foreign = true, foreignColumnName = Country.COUNTRY_CODE_FIELD_NAME)
    private Country country;

    @DatabaseField(columnName = DISTRICT_FIELD_NAME, canBeNull = false)
    private String district;

    @DatabaseField(columnName = POPULATION_FIELD_NAME)
    private int population;

    public City() {}

    public City(String name, Country country, String district, int population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
