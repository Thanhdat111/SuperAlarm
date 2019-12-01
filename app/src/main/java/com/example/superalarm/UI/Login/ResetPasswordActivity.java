package com.example.superalarm.UI.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.superalarm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edt_reset;
    Button btn_reset;
    ProgressBar loadingProgress;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        edt_reset = (EditText)findViewById(R.id.edt_email_reset);
        btn_reset = (Button)findViewById(R.id.btn_reset);
        loadingProgress = (ProgressBar)findViewById(R.id.loadingProgressBar_reset);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = edt_reset.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    edt_reset.setError("Nhập email cần đổi mật khẩu");
                    return;
                }

                loadingProgress.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(ResetPasswordActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ResetPasswordActivity.this, "Link thay đổi mật khẩu đã được gửi đến email của bạn.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(ResetPasswordActivity.this, "Gửi link không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
