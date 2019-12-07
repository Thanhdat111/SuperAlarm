package com.example.superalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.superalarm.Database.questiondatabase.Question;

import java.util.List;

public class AnswerQuestionsActivity extends AppCompatActivity {

    List<Question> questions;
    ImageView btnCautruoc, btnCauSau;
    TextView lbCauThu, lbCauHoi;
    RadioButton rb1, rb2, rb3, rb4;
    Button btn_nop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);

        // ánh xạ
        btnCautruoc = (ImageView)findViewById(R.id.imgBtn_cauTruoc_answer);
        btnCauSau = (ImageView)findViewById(R.id.imgBtn_cauSau_answer);
        lbCauThu = (TextView)findViewById(R.id.lb_cauSo_answer);
        lbCauHoi = (TextView)findViewById(R.id.lb_cauhoi_answer);
        rb1 = (RadioButton)findViewById(R.id.cautraloi1_answer);
        rb2 = (RadioButton)findViewById(R.id.cautraloi2_answer);
        rb3 = (RadioButton)findViewById(R.id.cautraloi3_answer);
        rb4 = (RadioButton)findViewById(R.id.cautraloi4_answer);
        btn_nop = (Button)findViewById(R.id.btn_nopbai_answer);
    }
}
