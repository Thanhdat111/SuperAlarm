package com.example.superalarm.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.api.DistributionOrBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextView btn_guideSignup;
    FirebaseAuth auth;
    EditText edt_emailSignUp, edt_passwordSignUp, edt_confirmPasswordSignUp;
    Button btn_signUp;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //ánh xạ
        edt_emailSignUp = (EditText)findViewById(R.id.edt_email_signup);
        edt_passwordSignUp = (EditText)findViewById(R.id.edt_password_signup);
        edt_confirmPasswordSignUp = (EditText)findViewById(R.id.edt_confirmPassword_signup);
        btn_signUp = (Button) findViewById(R.id.btn_signup);
        btn_guideSignup = (TextView)findViewById(R.id.lb_guide_signup);
        progressBar = (ProgressBar)findViewById(R.id.loadingProgressBar_signUp);

        btn_guideSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogGuide();
            }
        });

        //Nhấn nút đăng ký
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = edt_emailSignUp.getText().toString().trim();
                final String password = edt_passwordSignUp.getText().toString().trim();
                final String confirmpassword = edt_confirmPasswordSignUp.getText().toString().trim();

                if(TextUtils.isEmpty(username)){
                    edt_emailSignUp.setError("Bạn cần nhập email");
                    return;
                }else
                if(TextUtils.isEmpty(password)){
                    edt_passwordSignUp.setError("Bạn cần nhập mật khẩu");
                    return;
                }else
                if(TextUtils.isEmpty(confirmpassword)){
                    edt_confirmPasswordSignUp.setError("Bạn cần xác nhận lại mật khẩu");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(username,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thất bại" + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }

    public void showDialogGuide(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hướng dẫn");
        builder.setMessage("- Tên tài khoản là gmail của bạn, gmail phải chính xác để khi bạn quên mật khẩu để chúng tôi gửi mail cho bạn để reset mật khẩu"+ "\n" + "- Mật khẩu phải dài hơn 6 ký tự");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
