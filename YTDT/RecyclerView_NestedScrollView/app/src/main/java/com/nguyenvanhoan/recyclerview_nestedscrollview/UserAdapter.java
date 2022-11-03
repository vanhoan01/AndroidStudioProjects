package com.nguyenvanhoan.recyclerview_nestedscrollview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
     private List<User> userList;

     public void setData(List<User> list){
         this.userList = list;
         notifyDataSetChanged();
     }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null){
            return;
        }
        holder.imgUser.setImageResource(user.getResouceId());
        holder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(userList != null){
            return userList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = (ImageView) itemView.findViewById(R.id.imageViewUser);
            tvName = (TextView) itemView.findViewById(R.id.textView_Name);
        }
    }
}
