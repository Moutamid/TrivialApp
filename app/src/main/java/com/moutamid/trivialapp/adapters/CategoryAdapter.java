package com.moutamid.trivialapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.moutamid.trivialapp.R;
import com.moutamid.trivialapp.listners.CategoryClickListner;
import com.moutamid.trivialapp.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryVH> {

    Context context;
    List<CategoryModel> list;
    CategoryClickListner clickListner;

    public CategoryAdapter(Context context, List<CategoryModel> list, CategoryClickListner clickListner) {
        this.context = context;
        this.list = list;
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catagories_layout, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        CategoryModel model = list.get(position);
        holder.categoryName.setText(model.getCategoryName());
        holder.imageView.setImageResource(model.getImage());
        if (!model.isLockState()){
            holder.lock.setVisibility(View.GONE);
            holder.lockStatus.setVisibility(View.GONE);
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.primary));
        } else {
            holder.lock.setVisibility(View.VISIBLE);
            holder.lockStatus.setVisibility(View.VISIBLE);
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.dark));
        }
        holder.card.setOnClickListener(v -> clickListner.onClick(list.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryVH extends RecyclerView.ViewHolder{
        MaterialCardView card;
        TextView categoryName, lockStatus;
        ImageView imageView, lock;

        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.cataogryName);
            lockStatus = itemView.findViewById(R.id.lockStatus);
            imageView = itemView.findViewById(R.id.categoryImage);
            lock = itemView.findViewById(R.id.lockState);
            card = itemView.findViewById(R.id.categoryCard);
        }
    }
}
