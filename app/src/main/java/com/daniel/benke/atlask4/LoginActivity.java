package com.daniel.benke.atlask4;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.daniel.benke.atlask4.FirstActivity.nomelogin;

/**
 * Created by Andrea on 09/03/2017.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);



                final Button button = (Button) findViewById(R.id.botenviar);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText textologin = (EditText) findViewById(R.id.LoginText);


                         String login = textologin.getText().toString();

                    login = login.replaceAll("\\W+", "_");
                    login = login.replaceAll("é", "e");
                    login = login.replaceAll("ê", "e");
                    login = login.replaceAll("á", "a");
                    login = login.replaceAll("ó", "o");
                    login = login.replaceAll("ã", "a");
                    login = login.replaceAll("í", "i");
                    login = login.replaceAll("ç", "c");

                    StringBuilder str = new StringBuilder(login);
                    //str.insert(0, "a_");
                    login = String.valueOf(str);
                         //  FirstActivity.setNomeLogin(login);


                    EditText senhalogin = (EditText) findViewById(R.id.SenhaText);
                    String senha = senhalogin.getText().toString();

                    if(!senha.equals("1010")){ login="generico_"+login;}
                    //Toast.makeText(LoginActivity.this, "SENHA "+senha, Toast.LENGTH_SHORT).show();


                    String MyPREFERENCES = "com.daniel.benke.atlask4" ;
                    SharedPreferences sharedpreferences;


                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    //Toast.makeText(LoginActivity.this, "login entrado "+login, Toast.LENGTH_SHORT).show();

                    editor.putString("loginame", login);
                    editor.apply();

                    nomelogin= sharedpreferences.getString("loginame","sharedfalse");
                    //Toast.makeText(LoginActivity.this, "login usado "+nomelogin, Toast.LENGTH_SHORT).show();


                    Intent nextScreen = new Intent(getApplicationContext(), FirstActivity.class);
                    startActivity(nextScreen);


                }
            });





        }
    }

