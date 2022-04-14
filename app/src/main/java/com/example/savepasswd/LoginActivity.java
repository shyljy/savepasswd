package com.example.savepasswd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Button login;
    private EditText editText1;
    private EditText editText2;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.button1);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editText1 = (EditText) findViewById(R.id.text_account);
        editText2 = (EditText) findViewById(R.id.text_passwd);
        rememberPass = (CheckBox) findViewById(R.id.remember);
        boolean isRemember = pref.getBoolean("remember_passwd", false);
        if (isRemember) {
            //将账号密码设置到文本框
            String account = pref.getString("text_account", "");
            String passwd = pref.getString("text_passwd", "");
            editText1.setText(account);
            editText2.setText(passwd);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = editText1.getText().toString();
                String passwd = editText2.getText().toString();
                if (account.equals("admin") && passwd.equals("admin")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_passwd", true);
                        editor.putString("account", account);
                        editor.putString("passwd", passwd);
                        editor.commit();
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or passwd is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}