package com.example.luizao.android.sharedpreferenceslecture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {


    private Button save;
    private TextInputEditText userInput;
    private TextView txtResult;
    private static final String PREFERENCE_FILE = "PREFERENCEFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.btnSave);
        userInput = findViewById(R.id.userInput);
        txtResult = findViewById(R.id.txtResult);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(PREFERENCE_FILE, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();


                //validar o nome - Validate name
                if(userInput.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please, fill all the fields",Toast.LENGTH_LONG).show();
                }else{
                    String name = userInput.getText().toString();
                    editor.putString("name", name);
                    editor.commit();
                    txtResult.setText("Hello " + name);
                }

            }
        });

        //Recuperar os Dados - Recover data from file
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_FILE, MODE_PRIVATE);

        //Validar se temos o nome preferencias - Validade if data exists

        if(preferences.contains("name")){
            String name = preferences.getString("name","Olá, undefined user");
            txtResult.setText("Hello "+ name);

        }else{
            txtResult.setText(R.string.User);
        }
    }
}