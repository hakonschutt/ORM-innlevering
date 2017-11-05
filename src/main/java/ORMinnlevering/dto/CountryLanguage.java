package ORMinnlevering.dto;

import ORMinnlevering.dto.support.IsOfficial;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by hakonschutt on 05/11/2017.
 */
@DatabaseTable(tableName = "CountryLanguage")
public class CountryLanguage {

    public static final String COUNTRY_CODE_FIELD_NAME = "CountryCode";
    public static final String LANGUAGE_FIELD_NAME = "Language";
    public static final String IS_OFFICE_FIELD_NAME = "IsOffice";
    public static final String PERCENTAGE_FIELD_NAME = "Percentage";

    @DatabaseField(columnName = COUNTRY_CODE_FIELD_NAME, uniqueCombo = true, foreign = true, canBeNull = false)
    private String countryCode;

    @DatabaseField(columnName = LANGUAGE_FIELD_NAME, uniqueCombo = true, canBeNull = false)
    private String language;

    @DatabaseField(columnName = IS_OFFICE_FIELD_NAME, canBeNull = false)
    private IsOfficial isOfficial;

    @DatabaseField(columnName = PERCENTAGE_FIELD_NAME, canBeNull = false)
    private double percentage;

    public CountryLanguage() {
        // all persisted classes must define a no-arg
        // constructor with at least package visibility
    }

    public CountryLanguage(String countryCode, String language, IsOfficial isOfficial, double percentage) {
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
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
