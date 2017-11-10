package orminnlevering.dto.support;

/**
 * Continent enum. This enum is based of display name. The enum returns a string value since the continents have spaces in between the words.
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

    /**
     * Constructor for continent enum
     * @param displayName
     */
    Continent(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the enum string displayname.
     * @return
     */
    public String displayName() { return displayName; }
}
