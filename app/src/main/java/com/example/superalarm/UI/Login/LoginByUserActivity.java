package com.example.superalarm.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superalarm.MainActivity;
import com.example.superalarm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class LoginByUserActivity extends AppCompatActivity {

    private TextView btn_register,btn_resetpassword;
    EditText edt_userName, edt_password;
    Button btn_login;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_user);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //ánh xạ
        edt_userName = (EditText)findViewById(R.id.edt_tendangnhap);
        edt_password = (EditText)findViewById(R.id.edt_matkhau);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_resetpassword = (TextView)findViewById(R.id._lb_reset_password);
        btn_register = (TextView)findViewById(R.id.lb_register_user);
        progressBar = (ProgressBar)findViewById(R.id.loadingProgressBar_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginByUserActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginByUserActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String username = edt_userName.getText().toString().trim();
                 final String password = edt_password.getText().toString().trim();

                 if(TextUtils.isEmpty(username)){
                     edt_userName.setError("Bạn chưa nhập tên tài khoản");
                     return;
                 }else
                 if(TextUtils.isEmpty(password)){
                     edt_password.setError("Bạn chưa nhập mật khẩu");
                     return;
                 }

                 progressBar.setVisibility(View.VISIBLE);
                 auth.signInWithEmailAndPassword(username,password)
                         .addOnCompleteListener(LoginByUserActivity.this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 progressBar.setVisibility(View.GONE);
                                if(!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        edt_password.setError("Mật khẩu phải dài hơn 6 ký tự");
                                    } else {
                                        Toast.makeText(LoginByUserActivity.this, "Mật khẩu hoặc tên tài khoản không đúng", Toast.LENGTH_LONG).show();

                                    }
                                }else {
                                    Intent intent = new Intent(LoginByUserActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                             }
                         });
            }
        });
    }
}
