package com.example.dictionaryapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.Model.TuKhoa;
import com.example.dictionaryapp.R;

import java.util.List;

public class TuKhoaAdapter extends RecyclerView.Adapter<TuKhoaAdapter.TuKhoaViewHolder> {
    private List<TuKhoa> tuKhoaList;

    public void setData(List<TuKhoa> list){
        this.tuKhoaList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TuKhoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tukhoa, parent, false);
        return new TuKhoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TuKhoaViewHolder holder, int position) {
        TuKhoa tukhoa = tuKhoaList.get(position);
        if (tukhoa == null){
            return;
        }
        holder.tvSTT.setText(String.valueOf(tukhoa.getStt()));
        holder.tvNhap.setText(tukhoa.getNhap());
        holder.tvXuat.setText(tukhoa.getXuat());
    }

    @Override
    public int getItemCount() {
        if(tuKhoaList != null){
            return tuKhoaList.size();
        }
        return 0;
    }

    public class TuKhoaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSTT;
        private TextView tvNhap;
        private TextView tvXuat;

        public TuKhoaViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSTT = (TextView) itemView.findViewById(R.id.textViewSTT);
            tvNhap = (TextView) itemView.findViewById(R.id.textView_nhap);
            tvXuat = (TextView) itemView.findViewById(R.id.textView_xuat);
        }
    }
}