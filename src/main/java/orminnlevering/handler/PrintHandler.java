package orminnlevering.handler;

import orminnlevering.dto.City;
import orminnlevering.dto.Country;
import orminnlevering.dto.CountryLanguage;
import orminnlevering.dto.support.IsOfficial;

import java.util.List;

/**
 * Created by hakonschutt on 07/11/2017.
 */
public class PrintHandler {
    private static final String COUNTRY_FORMAT = "| %-5s| %-30s| %-15s| %-20s| %-20s| %-18s| %-13s| %-17s| %-12s| %-12s| %-35s| %-35s| %-10s| %-12s|";
    private static final String CITY_FORMAT = "| %-20s| %-15s| %-25s| %-12s|";
    private static final String COUNTRY_LANGUAGE_FORMAT = "| %-15s| %-15s| %-12s| %-12s|";

    private static String printLine(String intro){
        String line = "";
        for (int i = 0; i < intro.length(); i++){
            line += "-";
        }

        return line;
    }

    public static void printCountryList(List<Country> countryList){
        String introString = String.format(
                COUNTRY_FORMAT,
                "Code", "Name", "Continent", "Region",
                "Surface area", "Independence year",
                "Population", "Life expectancy",
                "GNP", "Old GNP", "Local name",
                "Government form", "Capital", "Second code"
        );

        String line = printLine(introString);
        System.out.println(line);
        System.out.println(introString);
        System.out.println(line);

        countryList.stream().forEach(e -> {
            System.out.println(
                String.format(COUNTRY_FORMAT,
                    e.getCode(),
                    e.getName(),
                    e.getContinent(),
                    e.getRegion(),
                    e.getSurfaceArea(),
                    e.getIndependenceYear(),
                    e.getPopulation(),
                    e.getLifeExpectancy(),
                    e.getGnp(),
                    e.getGnpOld(),
                    e.getLocalName(),
                    e.getGovernmentForm(),
                    e.getCapital(),
                    e.getSecondCode()
                )
            );
        });

        System.out.println(line);
        System.out.println();
    }

    public static void printCityList(List<City> cityList){
        String introString = String.format(
                CITY_FORMAT,
                "Name", "Country code", "District", "Population"
        );

        String line = printLine(introString);
        System.out.println(line);
        System.out.println(introString);
        System.out.println(line);

        cityList.stream().forEach(e -> {
            System.out.println(String.format(
                    CITY_FORMAT,
                    e.getName(),
                    e.getCountry().getCode(),
                    e.getDistrict(),
                    e.getPopulation()
            ));
        });

        System.out.println(line);
        System.out.println();
    }

    public static void printCountryLanguageList(List<CountryLanguage> countryLanguageList){
        String introString = String.format(
                COUNTRY_LANGUAGE_FORMAT,
                "Country code", "Language", "Is official", "Percentage"
        );

        String line = printLine(introString);
        System.out.println(line);
        System.out.println(introString);
        System.out.println(line);

        countryLanguageList.stream().forEach(e -> {
            System.out.println(String.format(
                    COUNTRY_LANGUAGE_FORMAT,
                    e.getCountry().getCode(),
                    e.getLanguage(),
                    e.getIsOfficial() == IsOfficial.T ? "True" : "False",
                    e.getPercentage()
            ));
        });

        System.out.println(line);
        System.out.println();
    }
}
