package com.example.superalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superalarm.Database.questiondatabase.QuestionDBHelper;
import com.example.superalarm.Database.questiondatabase.QuestionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnswerQuestionsActivity extends AppCompatActivity {

    private List<QuestionModel> questionModels;
    private ImageView btnCautruoc, btnCauSau;
    private TextView lbCauThu, lbCauHoi,txt_correctAnswers;
    private RadioButton rb_answer1, rb_answer2, rb_answer3, rb_answer4;
    private Button btn_chuyentrang,btn_checkAnswer;
    private QuestionDBHelper questionDBHelper;
    private List<String> listAnswers;
    private Random random;
    private List<RadioButton> listRadioButton;
    private static int questionAnswered =0;
    private static int questionCorrect = 0;
    private static int answerQuestion=0;
    private static int correctAnswer=0;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_questions);

        // ánh xạ
        btnCautruoc = (ImageView)findViewById(R.id.imgBtn_cauTruoc_answer);
        btnCauSau = (ImageView)findViewById(R.id.imgBtn_cauSau_answer);
        lbCauThu = (TextView)findViewById(R.id.lb_cauSo_answer);
        lbCauHoi = (TextView)findViewById(R.id.lb_cauhoi_answer);
        txt_correctAnswers = (TextView) findViewById(R.id.txt_correctAnswers);
        rb_answer1 = (RadioButton)findViewById(R.id.cautraloi1_answer);
        rb_answer2 = (RadioButton)findViewById(R.id.cautraloi2_answer);
        rb_answer3 = (RadioButton)findViewById(R.id.cautraloi3_answer);
        rb_answer4 = (RadioButton)findViewById(R.id.cautraloi4_answer);
        radioGroup = (RadioGroup) findViewById(R.id.radioButton_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doOnAnswerQuestionChange(radioGroup,i);
            }
        });

        listRadioButton = new ArrayList<RadioButton>();
        if(listRadioButton.size()==0) {
            listRadioButton.add(rb_answer1);
            listRadioButton.add(rb_answer2);
            listRadioButton.add(rb_answer3);
            listRadioButton.add(rb_answer4);
        }
        btn_chuyentrang = (Button)findViewById(R.id.btn_nopbai_answer);

        btn_chuyentrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctAnswer =getQuestion();
                resetRadioButton();
                boolean isChecked =false;
                Log.d("cau tra loi dung",correctAnswer+"");
                txt_correctAnswers.setText(questionCorrect+"");

            }
        });
        btn_checkAnswer = (Button) findViewById(R.id.checkAnswer);
        btn_checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrectAnsewer(answerQuestion,correctAnswer);
                txt_correctAnswers.setText(questionCorrect+"");
            }
        });

        //random
        random  = new Random();

        //add Database
        questionDBHelper = new QuestionDBHelper(this);

        //get questions
        questionModels = new ArrayList<QuestionModel>();
        questionModels = questionDBHelper.getAllQuestion();
        addQuesiton();
        for (QuestionModel questionModel: questionModels) {
        }

        //listAnswer
        listAnswers = new ArrayList<String>();
        for (QuestionModel questionModel: questionModels) {
            listAnswers.add(questionModel.getAnswer());
        }
    }
    void doOnAnswerQuestionChange(RadioGroup radioGroup,int checkedId){
        int checkedRadioId = radioGroup.getCheckedRadioButtonId();

        switch (checkedRadioId){
            case R.id.cautraloi1_answer:
                answerQuestion = 1;
                Log.d("Checked box",answerQuestion+"");
                break;
            case R.id.cautraloi2_answer:
                answerQuestion = 2;
                Log.d("Checked box",answerQuestion+"");
                break;
            case R.id.cautraloi3_answer:
                answerQuestion = 3;
                Log.d("Checked box",answerQuestion+"");
                break;
            case R.id.cautraloi4_answer:
                answerQuestion = 4;
                Log.d("Checked box",answerQuestion+"");
                break;
        }

    }


    void resetRadioButton(){
        for (RadioButton rd: listRadioButton
             ) {rd.setChecked(false);

        }
        answerQuestion =0;
    }


    boolean checkCorrectAnsewer(int answerQuestion,int correctAnswer){
        Log.d("Checkbox trong ham",answerQuestion+"");
        if(answerQuestion==correctAnswer){
            questionCorrect++;
        }
        else{
            Log.d("tra loi","sai");
        }
        return false;
    }





    int randomAnswer(String correctAnswer,List<String> listFalseAnswers){
        int index = random.nextInt(5);
        if(index ==0 ||index==5){
            index =1;

        }
    //    Log.d("Cr answer dFunction",index+"");
        switch (index){
            case 1:
                rb_answer1.setText(correctAnswer);
                break;
            case 2:
                rb_answer2.setText(correctAnswer);
                break;
            case 3:
                rb_answer3.setText(correctAnswer);
                break;
            case 4:
                rb_answer4.setText(correctAnswer);
                break;
        }

        for(int i=0;i<listRadioButton.size();i++){
            if((index-1)==i){
                continue;
            }
            else{
                listRadioButton.get(i).setText(listFalseAnswers.get(random.nextInt(listFalseAnswers.size())).toString());
            }
        }
        lbCauThu.setText("Cau " +questionAnswered+" ");

        //return answer correct
        return index;
    }


    int getQuestion(){
        questionAnswered++;
        int answerQuestion=0;
        if(questionAnswered==10 && questionCorrect <7){
            questionAnswered=1 ;
            questionCorrect=0;
        }
        while (questionCorrect <8) {
            QuestionModel questionModel = questionModels.get(random.nextInt(questionModels.size()));
            lbCauHoi.setText(questionModel.getQuestion().toString());
            answerQuestion =randomAnswer(questionModel.getAnswer(), listAnswers);
      //      Log.d("trong while",answerQuestion+"");
            return answerQuestion;

        }
        Toast.makeText(this, "Da hoan thanh", Toast.LENGTH_SHORT).show();
  //      Log.d("ngoai while",answerQuestion+"
                Intent newIntent = new Intent(this, MainActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newIntent);Intent intent = new Intent();

        return answerQuestion;

    }

    void addQuesiton(){
        if(questionModels.size()==0) {
            QuestionModel questionModel1 = new QuestionModel("Bỏ ngoài nướng trong, ăn ngoài bỏ trong là gì?", "Nướng bắp ngô");
            QuestionModel questionModel2 = new QuestionModel("Bệnh gì bác sĩ bó tay?", "Đó là bệnh... gãy tay!");
            QuestionModel questionModel3 = new QuestionModel("Có 1 đàn chim đậu trên cành, người thợ săn bắn cái rằm. Hỏi chết mấy con?", " rằm là 15 - chết 15 con");
            QuestionModel questionModel4 = new QuestionModel("Con gì ăn lửa với nước than?", "Đó là con tàu.");
            QuestionModel questionModel5 = new QuestionModel(" Nắng ba năm tôi không bỏ bạn, mưa 1 ngày sao bạn lại bỏ tôi là cái gì?", "Đó là cái bóng của mình!");
            QuestionModel questionModel6 = new QuestionModel("Trên nhấp dưới giật là đang làm gì?", "Đó là đang câu cá!");
            QuestionModel questionModel7 = new QuestionModel(" Con gì mang được miếng gỗ lớn nhưng không mang được hòn sỏi?", "Con sông");
            QuestionModel questionModel8 = new QuestionModel("Cái gì mà đi thì nằm, đứng cũng nằm, nhưng nằm lại đứng?", "Bàn chân.");
            QuestionModel questionModel9 = new QuestionModel("Cơ quan quan trọng nhất của phụ nữ là gì?", "Hội Liên Hiệp Phụ Nữ.");
            QuestionModel questionModel10 = new QuestionModel("Càng chơi càng ra nước?", " Chơi cờ.");
            QuestionModel questionModel11 = new QuestionModel("Cái gì dài như trái chuối, cầm 1 lúc thì nó chảy nước ra?", "Que kem");
            QuestionModel questionModel12 = new QuestionModel("Tại sao khi bắn súng người ta lại nhắm một mắt?", "Nhắm cả hai mắt thì không nhìn thấy mục tiêu bắn.");

                //add database
            questionDBHelper.addQuestion(questionModel1);
            questionDBHelper.addQuestion(questionModel2);
            questionDBHelper.addQuestion(questionModel3);
            questionDBHelper.addQuestion(questionModel4);
            questionDBHelper.addQuestion(questionModel5);
            questionDBHelper.addQuestion(questionModel6);
            questionDBHelper.addQuestion(questionModel7);
            questionDBHelper.addQuestion(questionModel8);
            questionDBHelper.addQuestion(questionModel9);
            questionDBHelper.addQuestion(questionModel10);
            questionDBHelper.addQuestion(questionModel11);
            questionDBHelper.addQuestion(questionModel12);
        }
    }

}
