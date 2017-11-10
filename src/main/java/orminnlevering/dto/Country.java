package orminnlevering.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class for country table.
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "country")
public class Country {

    public static final String COUNTRY_CODE_FIELD_NAME = "Code";
    public static final String NAME_FIELD_NAME = "Name";
    public static final String CONTINENT_FIELD_NAME = "Continent";
    public static final String REGION_FILED_NAME = "Region";
    public static final String SURFACE_AREA_FIELD_NAME = "SurfaceArea";
    public static final String INDEPYEAR_FIELD_NAME = "IndepYear";
    public static final String POPULATION_FIELD_NAME = "Population";
    public static final String LIFE_EXPECTANCY_FIELD_NAME = "LifeExpectancy";
    public static final String GNP_FIELD_NAME = "GNP";
    public static final String GNP_OLD_FIELD_NAME = "GNPOld";
    public static final String LOCAL_NAME_FIELD_NAME = "LocalName";
    public static final String GOVERNMENT_FORM_FIELD_NAME = "GovernmentForm";
    public static final String HEAD_OF_STATE_FIELD_NAME = "HeadOfState";
    public static final String CAPITAL_FIELD_NAME = "Capital";
    public static final String CODE_TWO_FIELD_NAME = "Code2";

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, id = true, canBeNull = false)
    private String code;

    @DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = CONTINENT_FIELD_NAME)
    private String continent;

    @DatabaseField(columnName = REGION_FILED_NAME, canBeNull = false)
    private String region;

    @DatabaseField(columnName = SURFACE_AREA_FIELD_NAME)
    private float surfaceArea;

    @DatabaseField(columnName = INDEPYEAR_FIELD_NAME)
    private short independenceYear;

    @DatabaseField(columnName = POPULATION_FIELD_NAME)
    private int population;

    @DatabaseField(columnName = LIFE_EXPECTANCY_FIELD_NAME)
    private double lifeExpectancy;

    @DatabaseField(columnName = GNP_FIELD_NAME)
    private double gnp;

    @DatabaseField(columnName = GNP_OLD_FIELD_NAME)
    private double gnpOld;

    @DatabaseField(columnName = LOCAL_NAME_FIELD_NAME, canBeNull = false)
    private String localName;

    @DatabaseField(columnName = GOVERNMENT_FORM_FIELD_NAME, canBeNull = false)
    private String governmentForm;

    @DatabaseField(columnName = HEAD_OF_STATE_FIELD_NAME)
    private String headOfState;

    @DatabaseField(columnName = CAPITAL_FIELD_NAME)
    private int capital;

    @DatabaseField(columnName = CODE_TWO_FIELD_NAME, canBeNull = false)
    private String secondCode;

    public Country() {
    }

    public Country(String code, String name, String continent, String region, float surfaceArea, short independanceYear, int population, double lifeExpectancy, double gnp, double gnpOld, String localName, String governmentForm, String headOfState, int capital, String secondCode) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.independenceYear = independanceYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.secondCode = secondCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public short getIndependenceYear() {
        return independenceYear;
    }

    public void setIndependenceYear(short independenceYear) {
        this.independenceYear = independenceYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public double getGnp() {
        return gnp;
    }

    public void setGnp(double gnp) {
        this.gnp = gnp;
    }

    public double getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(double gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        return code.equals(((Country) other).code);
    }
}
