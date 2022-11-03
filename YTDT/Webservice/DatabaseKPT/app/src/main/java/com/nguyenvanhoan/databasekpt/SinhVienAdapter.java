package com.nguyenvanhoan.databasekpt;

import android.content.Context;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(Context context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtHoTen, txtNamSinh, txtDiaChi;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtHoTen = (TextView) view.findViewById(R.id.textViewHoTen);
            holder.txtNamSinh = (TextView) view.findViewById(R.id.textViewNamSinh);
            holder.txtDiaChi = (TextView) view.findViewById(R.id.textViewDiaCHi);

            holder.imgEdit = (ImageView) view.findViewById(R.id.imageViewEdit);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageViewDelete);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        SinhVien sinhvien = sinhVienList.get(i);
        holder.txtHoTen.setText(sinhvien.getHoTen());
        holder.txtDiaChi.setText("Năm sinh: " + sinhvien.getDiaChi());
        holder.txtDiaChi.setText(sinhvien.getDiaChi());
        return view;
    }
}
