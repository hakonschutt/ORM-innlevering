package ORMinnlevering.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "Country")
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
    public static final String LOCALNAME_FIELD_NAME = "LocalName";
    public static final String GOVERNMENT_FORM_FIELD_NAME = "GovernmentForm";
    public static final String HEAD_OF_STATE_FIELD_NAME = "HeadOfState";
    public static final String CAPITAL_FIELD_NAME = "Capital";
    public static final String CODE_TWO_FIELD_NAME = "Code2";

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, uniqueCombo = true, foreign = true, canBeNull = false)
    private String countryCode;
}
