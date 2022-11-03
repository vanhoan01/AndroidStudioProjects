package com.nguyenvanhoan.spinnerlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrContact;

    public CustomAdapter(Context context, int resource, ArrayList<Contact> arrContact) {
        super(context, resource, arrContact);
        this.context = context;
        this.resource = resource;
        this.arrContact = arrContact;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvNumberPhone = (TextView) convertView.findViewById(R.id.tvMaSinhVien);
            viewHolder.tvAvatar = (TextView) convertView.findViewById(R.id.tvAvatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrContact.get(position);
        viewHolder.tvAvatar.setBackgroundColor(contact.getColor());
        viewHolder.tvAvatar.setText(String.valueOf(position+1));
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumberPhone.setText(contact.getIdStudent());
        return convertView;
    }

    public class ViewHolder {
        TextView tvName, tvNumberPhone, tvAvatar;
    }
}
