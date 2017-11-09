package orminnlevering.dto.support;

/**
 * I would have liked to use an enum here, but it wont work when the string hava spaces on south and north america.
 * Created by hakonschutt on 05/11/2017.
 */
public enum Continent {
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AFRICA("Africa"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    SOUTH_AMERICA("South America");

    private String displayName;

    Continent(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }
}
