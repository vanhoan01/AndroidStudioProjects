package com.nguyenvanhoan.datecountdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Event> eventList;

    public EventAdapter(Context context, int layout, List<Event> eventList) {
        this.context = context;
        this.layout = layout;
        this.eventList = eventList;
    }

    @Override
    public int getCount() {
        return eventList.size();
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
        ImageButton ibtXoa;
        TextView txtTitle, txtEvent, txtDay;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new ViewHolder();
            //anh xa view
            holder.txtTitle = (TextView) view.findViewById(R.id.textViewTitle);
            holder.txtEvent = (TextView) view.findViewById(R.id.textViewEvent);
            holder.txtDay = (TextView) view.findViewById(R.id.textViewDay);
            holder.ibtXoa = (ImageButton) view.findViewById(R.id.imageButtonXoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.ibtXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventList.remove(i);
                notifyDataSetChanged();
            }
        });
        //gan gia tri
        Event ev = eventList.get(i);
        holder.txtTitle.setText(ev.getTitle());
        holder.txtEvent.setText(ev.getEvent());
        holder.txtDay.setText(ev.getDay());

        return view;
    }
}
