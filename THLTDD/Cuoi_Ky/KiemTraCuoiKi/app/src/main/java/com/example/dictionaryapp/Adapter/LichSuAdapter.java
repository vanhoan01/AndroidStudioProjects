package com.example.dictionaryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.Model.LichSu;
import com.example.dictionaryapp.R;

import java.util.List;

public class LichSuAdapter extends RecyclerView.Adapter<LichSuAdapter.LichSuViewHolder>{
    private List<LichSu> lichSuList;

    public void setData(List<LichSu> list){
        this.lichSuList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LichSuAdapter.LichSuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new LichSuAdapter.LichSuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuAdapter.LichSuViewHolder holder, int position) {
        LichSu lichSu = lichSuList.get(position);
        if (lichSu == null){
            return;
        }
        holder.tvNhap.setText(lichSu.getNhap());
        holder.tvXuat.setText(lichSu.getXuat());
    }

    @Override
    public int getItemCount() {
        if(lichSuList != null){
            return lichSuList.size();
        }
        return 0;
    }

    public class LichSuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNhap;
        private TextView tvXuat;

        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNhap = (TextView) itemView.findViewById(R.id.textView_nhap);
            tvXuat = (TextView) itemView.findViewById(R.id.textView_xuat);
        }
    }
}
