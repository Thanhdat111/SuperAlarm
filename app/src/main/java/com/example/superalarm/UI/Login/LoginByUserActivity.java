package com.example.superalarm.UI.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.superalarm.R;

public class LoginByUserActivity extends AppCompatActivity {

    private TextView btn_register,btn_resetpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_user);

        btn_register = (TextView)findViewById(R.id.lb_register_user);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginByUserActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_resetpassword = (TextView)findViewById(R.id._lb_reset_password);
        btn_resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginByUserActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
