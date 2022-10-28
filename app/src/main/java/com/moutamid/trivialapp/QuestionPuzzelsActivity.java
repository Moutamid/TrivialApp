package com.moutamid.trivialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionPuzzelsActivity extends AppCompatActivity {

    Button submit;
    int Score = 0;
    RadioGroup group1, group2, group3, group4, group5;
    RadioButton ans1,ans2,ans3,ans4,ans5;

    // CorrectAnswers
    String A1 = "25", A2 = "15.684", A3 = "12", A4 = "12", A5 = "12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_puzzels);

        //questionRC = findViewById(R.id.questionRC);
        submit = findViewById(R.id.submitBtn);
        group1 = findViewById(R.id.radioGroup1);
        group2 = findViewById(R.id.radioGroup2);
        group3 = findViewById(R.id.radioGroup3);
        group4 = findViewById(R.id.radioGroup4);
        group5 = findViewById(R.id.radioGroup5);

        submit.setOnClickListener(v-> {
            ans1 = findViewById(group1.getCheckedRadioButtonId());
            ans2 = findViewById(group2.getCheckedRadioButtonId());
            ans3 = findViewById(group3.getCheckedRadioButtonId());
            ans4 = findViewById(group4.getCheckedRadioButtonId());
            ans5 = findViewById(group5.getCheckedRadioButtonId());
            if (A1 == ans1.getText().toString()){
                Score++;
            }
            if (A2 == ans2.getText().toString()){
                Score++;
            }
            if (A3 == ans2.getText().toString()){
                Score++;
            }
            if (A4 == ans4.getText().toString()){
                Score++;
            }
            if (A5 == ans5.getText().toString()){
                Score++;
            }
            Toast.makeText(this, "You Score " + Score + "/5", Toast.LENGTH_SHORT).show();
        });

    }
}