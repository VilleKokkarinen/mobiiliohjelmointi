package com.example.login.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        final EditText edit1 = (EditText) findViewById(R.id.edit1);
        final EditText edit2 = (EditText) findViewById(R.id.edit2);
        Button btn=(Button) findViewById(R.id.painike);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/html");
                i.putExtra(Intent.EXTRA_EMAIL, new String("test@test.com"));
                i.putExtra(Intent.EXTRA_SUBJECT,"Palaute verkkokaupasta");
                i.putExtra(Intent.EXTRA_TEXT, "Käyttäjän nimi:"+edit1.getText()+ "\n Viesti:"+edit2.getText());
                try {
                    startActivity(Intent.createChooser(i, "Valitse sähköposti"));

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Feedback.this, "Sähköposti sovellusta ei löytynyt", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}


