package com.moutamid.trivialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moutamid.trivialapp.models.QuestionsModel;

import java.util.ArrayList;

public class QuestionsMathActivity extends AppCompatActivity {
    RecyclerView  questionRC;
    QuestionsModel questionsModel;
    ArrayList<QuestionsModel> list;
    Button submit;
    int Score = 0;
    RadioGroup group1, group2, group3, group4, group5;
    RadioButton ans1,ans2,ans3,ans4,ans5;

    // CorrectAnswers
    String A1 = "25", A2 = "15.684", A3 = "12", A4 = "12", A5 = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_math);

        list = new ArrayList();

        //questionRC = findViewById(R.id.questionRC);
        submit = findViewById(R.id.submitBtn);
        group1 = findViewById(R.id.radioGroup1);
        group2 = findViewById(R.id.radioGroup2);
        group3 = findViewById(R.id.radioGroup3);
        group4 = findViewById(R.id.radioGroup4);
        group5 = findViewById(R.id.radioGroup5);

        submit.setOnClickListener(v-> {
            int id1 = group1.getCheckedRadioButtonId();
            int id2 = group2.getCheckedRadioButtonId();
            int id3 = group3.getCheckedRadioButtonId();
            int id4 = group4.getCheckedRadioButtonId();
            int id5 = group5.getCheckedRadioButtonId();
            ans1 = findViewById(id1);
            ans2 = findViewById(id2);
            ans3 = findViewById(id3);
            ans4 = findViewById(id4);
            ans5 = findViewById(id5);

            if (A1.equals(ans1.getText().toString())){
                Score++;
            }
            if (A2.equals(ans2.getText().toString())){
                Score++;
            }
            if (A3.equals(ans3.getText().toString())){
                Score++;
            }
            if (A4.equals(ans4.getText().toString())){
                Score++;
            }
            if (A5.equals(ans5.getText().toString())){
                Score++;
            }
            Toast.makeText(this, "You Score " + Score + "/5", Toast.LENGTH_SHORT).show();
        });

    }
}