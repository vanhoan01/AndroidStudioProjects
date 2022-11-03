package com.nguyenvanhoan.bai42_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TraiCay> traiCayList;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }

    @Override
    public int getCount() {
        return traiCayList.size();
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
        TextView txtTen, txtMoTa;
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
            holder.txtMoTa = (TextView) view.findViewById(R.id.textViewMoTa);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageViewHinh);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        //gan gia tri
        TraiCay traiCay = traiCayList.get(i);
        holder.txtTen.setText(traiCay.getTen());
        holder.txtMoTa.setText(traiCay.getMoTa());
        holder.imgHinh.setImageResource(traiCay.getHinh());

        return view;
    }
}
