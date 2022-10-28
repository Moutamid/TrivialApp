package com.moutamid.trivialapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.moutamid.trivialapp.R;
import com.moutamid.trivialapp.listners.CategoryClickListner;
import com.moutamid.trivialapp.models.CategoryModel;
import com.moutamid.trivialapp.models.QuestionsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsVH> {
    Context context;
    List<QuestionsModel> list;

    public QuestionsAdapter(Context context, List<QuestionsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionsAdapter.QuestionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.questions_layout, parent, false);
        return new QuestionsAdapter.QuestionsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionsVH holder, int position) {
        QuestionsModel model = list.get(position);

        String question= model.getQuestion();

        final ArrayList<String> options = new ArrayList<>();
        options.add(model.getAns1());
        options.add(model.getAns2());
        options.add(model.getAns3());
        options.add(model.getAns4());
        Collections.shuffle(options);
        String o1 = options.get(0);
        String o2 = options.get(1);
        String o3 = options.get(2);
        String o4 =  options.get(3);
        final String[] answer = new String[1];
        holder.question.setText(question);
        holder.ans1.setText(o1);
        holder.ans2.setText(o2);
        holder.ans3.setText(o3);
        holder.ans4.setText(o4);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QuestionsVH extends RecyclerView.ViewHolder{
        RadioGroup answer;
        TextView question;
        RadioButton ans1, ans2, ans3, ans4;

        public QuestionsVH(@NonNull View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.radioGroup);
            question = itemView.findViewById(R.id.question);
            ans1 = itemView.findViewById(R.id.answer1);
            ans2 = itemView.findViewById(R.id.answer2);
            ans3 = itemView.findViewById(R.id.answer3);
            ans4 = itemView.findViewById(R.id.answer4);
        }
    }
}
