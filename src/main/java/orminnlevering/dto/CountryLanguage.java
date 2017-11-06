package orminnlevering.dto;

import com.j256.ormlite.field.DataType;
import orminnlevering.dto.support.IsOfficial;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Statement;

import javax.xml.crypto.Data;

/**
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "countrylanguage")
public class CountryLanguage {

    public static final String COUNTRY_CODE_FIELD_NAME = "CountryCode";
    public static final String LANGUAGE_FIELD_NAME = "Language";
    public static final String IS_OFFICIAL_FIELD_NAME = "IsOfficial";
    public static final String PERCENTAGE_FIELD_NAME = "Percentage";

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, uniqueCombo = true, canBeNull = false)
    private String countryCode;

    @DatabaseField(columnName = LANGUAGE_FIELD_NAME, uniqueCombo = true, canBeNull = false)
    private String language;

    @DatabaseField(columnName = IS_OFFICIAL_FIELD_NAME, dataType = DataType.ENUM_STRING)
    private IsOfficial isOfficial;

    @DatabaseField(columnName = PERCENTAGE_FIELD_NAME)
    private float percentage;

    @DatabaseField(generatedId = true, version = true, persisted = false)
    private int countryCodeLanguage;

    public CountryLanguage() {}

    public CountryLanguage(String countryCode, String language, IsOfficial isOfficial, float percentage) {
        this.countryCode = countryCode;
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public IsOfficial getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(IsOfficial isOfficial) {
        this.isOfficial = isOfficial;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        return (countryCode + language).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return (countryCode + language).equals((((CountryLanguage) other).countryCode) + (((CountryLanguage) other).language));
    }
}
