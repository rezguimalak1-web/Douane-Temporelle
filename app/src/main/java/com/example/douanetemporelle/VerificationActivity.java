package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class VerificationActivity extends Activity {

    // Nafs el matricule elli mawjoud fi MainActivity.
    private static final String MATRICULE = "9203";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Norbtou el activite bel interface XML.
        setContentView(R.layout.activity_verification);

        // Njibou el TextView elli bech n7ottou fih el resume.
        TextView resume = findViewById(R.id.tv_resume_rm);

        // Njibou el Intent elli ba3thou MainActivity.
        Intent intent = getIntent();

        // Na3mlou nafs el cles elli nesta3melhom fi el envoi.
        String cleNom = ExtraKeys.key(MATRICULE, "NOM_VOYAGEUR");
        String cleNaissance = ExtraKeys.key(MATRICULE, "ANNEE_NAISSANCE");
        String cleDestination = ExtraKeys.key(MATRICULE, "ANNEE_DESTINATION");
        String cleMotif = ExtraKeys.key(MATRICULE, "MOTIF_VOYAGE");

        // Na9raw el nom wel motif.
        String nom = intent.getStringExtra(cleNom);
        String motif = intent.getStringExtra(cleMotif);

        // Nthabtou elli les donnees mawjoudin 9bal ma nkamlou.
        if (nom == null
                || motif == null
                || !intent.hasExtra(cleNaissance)
                || !intent.hasExtra(cleDestination)) {

            Toast.makeText(
                    this,
                    "Données manquantes. Revenez au formulaire.",
                    Toast.LENGTH_LONG
            ).show();

            finish();
            return;
        }

        // Na9raw les annees elli ba3thom MainActivity.
        int naissance = intent.getIntExtra(cleNaissance, 0);
        int destination = intent.getIntExtra(cleDestination, 0);

        // Na7sbou el 3omr temporel.
        int ageTemporel = destination - naissance;

        // Naffichiw les informations fi passeport.
        String texteResume =
                "Nom : " + nom
                        + "\n\nAnnée de naissance : " + naissance
                        + "\n\nAnnée de destination : " + destination
                        + "\n\nÂge temporel : " + ageTemporel + " ans"
                        + "\n\nMotif : " + motif;

        resume.setText(texteResume);

        // Njibou les deux boutons mel interface.
        Button boutonApprouver = findViewById(R.id.btn_approuver_rm);
        Button boutonRefuser = findViewById(R.id.btn_refuser_rm);
        // تعطيل زر الموافقة إذا كان السفر لتغيير الماضي والعمر الزمني عددا أوليا
        if ("Modification du passé".equals(motif) && estPremier(ageTemporel)) {
            boutonApprouver.setEnabled(false);
        }

// Ki el voyageur yenzel 3al bouton Approuver.
        boutonApprouver.setOnClickListener(v -> {
            Intent resultat = new Intent(
                    VerificationActivity.this,
                    ResultActivity.class
            );

            // إرسال الحكم النهائي واسم المسافر إلى النشاط الثالث
            resultat.putExtra(
                    ExtraKeys.key(MATRICULE, "DECISION"),
                    true
            );

            resultat.putExtra(
                    ExtraKeys.key(MATRICULE, "NOM_VOYAGEUR"),
                    nom
            );

            startActivity(resultat);
        });

// Ki el voyageur yenzel 3al bouton Refuser.
        boutonRefuser.setOnClickListener(v -> {
            Intent resultat = new Intent(
                    VerificationActivity.this,
                    ResultActivity.class
            );

            // إرسال قرار الرفض واسم المسافر إلى النشاط الثالث
            resultat.putExtra(
                    ExtraKeys.key(MATRICULE, "DECISION"),
                    false
            );

            resultat.putExtra(
                    ExtraKeys.key(MATRICULE, "NOM_VOYAGEUR"),
                    nom
            );

            startActivity(resultat);
        });
    }
    // التحقق مما إذا كان العدد أوليا
    private boolean estPremier(int nombre) {
        if (nombre < 2) {
            return false;
        }

        for (int i = 2; i <= nombre / i; i++) {
            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }
}
