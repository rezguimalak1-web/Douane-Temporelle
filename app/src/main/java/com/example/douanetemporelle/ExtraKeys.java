package com.example.douanetemporelle;

public class ExtraKeys {

    // Construction des clés à partir du matricule
    public static String key(String matricule, String suffix) {

        if (matricule == null || matricule.length() < 4) {
            throw new IllegalArgumentException("Matricule invalide");
        }

        String derniersChiffres =
                matricule.substring(matricule.length() - 4);

        return derniersChiffres + "_" + suffix;
    }
}