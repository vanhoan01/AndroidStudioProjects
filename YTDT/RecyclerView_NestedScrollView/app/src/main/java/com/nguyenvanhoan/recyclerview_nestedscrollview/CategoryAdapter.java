package com.nguyenvanhoan.recyclerview_nestedscrollview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
     private List<Category> categoryList;

     public void setData(List<Category> list){
         this.categoryList = list;
         notifyDataSetChanged();
     }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if (category == null){
            return;
        }
        holder.imgCategory.setImageResource(category.getResoureId());
        holder.tvTitle.setText(category.getTitle());
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCategory;
        private TextView tvTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory);
            tvTitle = (TextView) itemView.findViewById(R.id.textView_Title);
        }
    }
}
