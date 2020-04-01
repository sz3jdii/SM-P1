package pl.adamswiatkowski.smp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    boolean isNameValid = false;
    boolean isSurnameValid = false;
    boolean areMarksValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = (EditText)findViewById(R.id.nameValue);
        final EditText surname = (EditText)findViewById(R.id.surnameValue);
        final EditText marks = (EditText)findViewById(R.id.marksValue);
        final Button submit = (Button)findViewById(R.id.showMarksAverageButton);
        final Button finish = (Button)findViewById(R.id.end);

        // Listenery
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(areFieldsValid()) {
                    Intent intent = new Intent(MainActivity.this, ShowOceny.class);
                    intent.putExtra("marksCount", Integer.parseInt(marks.getText().toString()));
                    startActivityForResult(intent, 1);
                }
                else {
                    Toast tost = Toast.makeText(getApplicationContext(), "Nie wypełniono wszystkich danych!!!", Toast.LENGTH_SHORT);
                    tost.show();
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nm = name.getText().toString();
                isNameValid = true;
                if (nm.isEmpty()) {
                    Toast tost = Toast.makeText(getApplicationContext(), "Pole imię nie może być puste", Toast.LENGTH_SHORT);
                    tost.show();
                    isNameValid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sr = surname.getText().toString();
                isSurnameValid = true;
                if (sr.isEmpty()) {
                    Toast tost = Toast.makeText(getApplicationContext(), "Pole nazwisko nie może być puste", Toast.LENGTH_SHORT);
                    tost.show();
                    isSurnameValid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        marks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mr = marks.getText().toString();
                areMarksValid = true;
                if (mr.isEmpty()) {
                    Toast tost = Toast.makeText(getApplicationContext(), "Pole ocen nie może być puste", Toast.LENGTH_SHORT);
                    tost.show();
                    areMarksValid = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast tost = Toast.makeText(getApplicationContext(), "Do widzenia :)", Toast.LENGTH_SHORT);
                tost.show();
                finish();
                System.exit(0);
            }
        });

    }

    protected boolean areFieldsValid()
    {
        if (this.isNameValid && this.isSurnameValid && this.areMarksValid) {
            return true;
        } else {
            return false;
        }
    }

    protected void onActivityResult(int kodZadania, int kodWyniku, Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodWyniku == RESULT_OK) {
            Bundle tobolek = dane.getExtras();
            float liczba = tobolek.getFloat("srednia");
            TextView text2 = (TextView)findViewById(R.id.marksAverageValue);
            text2.setText("Twoja srednia to " + liczba);
            final Button finish = (Button)findViewById(R.id.end);

            if (liczba >= 3.0) {
                finish.setText("Super :)");

            } else {
                finish.setText("Tym razem nie poszło");
            }
        }
    }
}
