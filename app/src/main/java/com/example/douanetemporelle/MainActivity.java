package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Houni n7ottou ekher 4 ar9am mel matricule mta3 etudiant.
    private static final String MATRICULE = "9203";

    private EditText nomVoyageur;
    private EditText anneeNaissance;
    private EditText anneeDestination;
    private Spinner motifVoyage;
    private Button boutonInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Njibou les elements mel interface XML.
        nomVoyageur = findViewById(R.id.et_nom_rm);
        anneeNaissance = findViewById(R.id.et_naissance_rm);
        anneeDestination = findViewById(R.id.et_destination_rm);
        motifVoyage = findViewById(R.id.sp_motif_rm);
        boutonInspecter = findViewById(R.id.btn_inspecter_rm);

        // Houni na3mlou liste mta3 asbeb el voyage.
        String[] motifs = {
                "Tourisme",
                "Modification du passé",
                "Fuite"
        };

        ArrayAdapter<String> adaptateur = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );

        adaptateur.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        motifVoyage.setAdapter(adaptateur);

        // Ki el voyageur yenzel 3al bouton Inspecter le profil.
        boutonInspecter.setOnClickListener(v -> {

            String nom = nomVoyageur.getText().toString().trim();
            String naissance = anneeNaissance.getText().toString().trim();
            String destination = anneeDestination.getText().toString().trim();

            // Nthabtou elli les champs el kol ma3abbin.
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Veuillez remplir tous les champs.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int anneeNaissanceInt;
            int anneeDestinationInt;

            // Nbadlou les annees men texte l ar9am s7a7.
            try {
                anneeNaissanceInt = Integer.parseInt(naissance);
                anneeDestinationInt = Integer.parseInt(destination);
            } catch (NumberFormatException e) {
                Toast.makeText(
                        MainActivity.this,
                        "Veuillez saisir des années valides.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Nthabtou elli les annees akber men sifr.
            if (anneeNaissanceInt <= 0 || anneeDestinationInt <= 0) {
                Toast.makeText(
                        MainActivity.this,
                        "Les années doivent être supérieures à zéro.",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            String motif = motifVoyage.getSelectedItem().toString();

            // Na3mlou Intent explicite bech nemchiw lel VerificationActivity.
            Intent intent = new Intent(
                    MainActivity.this,
                    VerificationActivity.class
            );

            // Nab3thou ma3loumet el voyageur lel activite 2.
            intent.putExtra(ExtraKeys.key(MATRICULE, "NOM_VOYAGEUR"), nom);
            intent.putExtra(ExtraKeys.key(MATRICULE, "ANNEE_NAISSANCE"), anneeNaissanceInt);
            intent.putExtra(ExtraKeys.key(MATRICULE, "ANNEE_DESTINATION"), anneeDestinationInt);
            intent.putExtra(ExtraKeys.key(MATRICULE, "MOTIF_VOYAGE"), motif);

            startActivity(intent);
        });
    }
}
