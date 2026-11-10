package com.example.douanetemporelle;

public class ExtraKeys {

    // Houni nsan3ou les cles mel 4 ar9am lokhrin mta3 el matricule.
    public static String key(String matricule, String suffix) {

        if (matricule == null || matricule.length() < 4) {
            throw new IllegalArgumentException("Matricule invalide");
        }

        String derniersChiffres =
                matricule.substring(matricule.length() - 4);

        return derniersChiffres + "_" + suffix;
    }
}