package com.example.superalarm.UI.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.superalarm.MainActivity;
import com.example.superalarm.R;
import com.google.firebase.auth.FirebaseAuth;

public class PreLoginActivity extends AppCompatActivity {

    private Button btn_loginByUser, btn_loginByFaceId;
    private TextView btn_guide;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(PreLoginActivity.this, MainActivity.class));
            finish();
        }

        btn_loginByUser = (Button)findViewById(R.id.btn_login_by_user);
        btn_loginByUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreLoginActivity.this, LoginByUserActivity.class);
                startActivity(intent);
            }
        });
        btn_loginByFaceId = (Button)findViewById(R.id.btn_login_by_faceid);
        btn_loginByFaceId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        // btn guide
        btn_guide = (TextView)findViewById(R.id.lb_guide_preLogin);
        btn_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogGuide();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Chỉ có thể đăng nhập khi bạn đã có tài khoản");
        builder.setCancelable(false);
        builder.setPositiveButton("Chưa có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đã có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(PreLoginActivity.this, LoginByFaceIdActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogGuide(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hướng dẫn");
        builder.setMessage("- Với đăn nhập bằng tài khoản thì bạn tao một user gồm tên đăng nhập là gmail và mật khẩu của bạn."+ "\n" + "- Với đăng nhập bằng face id thì bạn cần có tài khoản và thêm ảnh có chứa mặt bạn vào database");
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
}
