package com.task_management_system.non_spring_hw.home_work_4;

import java.util.Random;

public enum Drink {
    MOJITO("Mojito"),
    MARGARITA("Margarita"),
    MAI_TAI("Mai Tai"),
    PINA_COLADA("Pina Colada"),
    MARTINI("Martini"),
    BLUE_LAGOON("Blue Lagoon"),
    MANHATTAN("Manhattan"),
    SEX_ON_THE_BEACH("Sex on the Beach"),
    OLD_FASHIONED("Old Fashioned"),
    DAIQUIRI("Daiquiri"),
    BLOODY_MARY("Bloody Mary"),
    COSMOPOLITAN("Cosmopolitan"),
    GIN_AND_TONIC("Gin and Tonic"),
    NEGRONI("Negroni"),
    NORTH_BREWER("North Brewer");

    Drink(String originName) {
        this.originName = originName;
    }

    private final String originName;

    public String getOriginName() {
        return originName;
    }

    public static String getRandomDrink() {
        var drinks = values();

        return drinks[new Random().nextInt(drinks.length)].getOriginName();
    }
}