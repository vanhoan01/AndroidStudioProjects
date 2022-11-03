package com.nguyenvanhoan.thongtinsinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        ImageView imgHinh;
        TextView txtTen, txtMSV, txtLop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa view
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.txtMSV = (TextView) view.findViewById(R.id.textViewMSV);
            holder.txtLop = (TextView) view.findViewById(R.id.textViewLop);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        SinhVien sinhVien = sinhVienList.get(i);
        holder.txtTen.setText(sinhVien.getTen());
        holder.txtMSV.setText(sinhVien.getMsv());
        holder.txtLop.setText(sinhVien.getLop());
        holder.imgHinh.setImageResource(sinhVien.getHinh());
        return view;
    }
}
