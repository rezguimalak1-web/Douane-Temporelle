
package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

    // Nafs el matricule elli nesta3mlouh fel activites lokhrin.
    private static final String MATRICULE = "9203";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Norbtou el activite bel interface XML.
        setContentView(R.layout.activity_result);

        // Njibou les elements mel interface.
        LinearLayout layout = findViewById(R.id.layout_resultat_rm);
        TextView message = findViewById(R.id.tv_message_resultat_rm);
        Button boutonNouveau = findViewById(R.id.btn_nouveau_voyageur_rm);

        // Na9raw el verdict wel nom elli ba3thhom VerificationActivity.
        Intent intent = getIntent();

        boolean decision = intent.getBooleanExtra(
                ExtraKeys.key(MATRICULE, "DECISION"),
                false
        );

        String nom = intent.getStringExtra(
                ExtraKeys.key(MATRICULE, "NOM_VOYAGEUR")
        );

        // Nthabtou elli el nom mahouche feragh.
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Voyageur";
        }

        // Nbaddlou el couleur wel message 7asb el verdict.
        if (decision) {
            layout.setBackgroundColor(Color.rgb(46, 125, 50));
            message.setText("Bon voyage " + nom);
        } else {
            layout.setBackgroundColor(Color.rgb(198, 40, 40));
            message.setText(nom + " est en état d'arrestation temporelle");
        }

        // Ki nenzel 3al bouton, narj3ou lel formulaire
        // w nsakkrou les activites elli 9bal.
        boutonNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            retour.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK
            );

            startActivity(retour);
            finish();
        });
    }
}
