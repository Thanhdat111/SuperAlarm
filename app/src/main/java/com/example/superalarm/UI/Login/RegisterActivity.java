package com.example.superalarm.UI.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.superalarm.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView btn_guideSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_guideSignup = (TextView)findViewById(R.id.lb_guide_signup);
        btn_guideSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogGuide();
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
}
