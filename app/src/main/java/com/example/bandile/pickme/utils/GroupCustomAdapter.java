package com.example.bandile.pickme.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bandile.pickme.R;

import java.util.ArrayList;

public class GroupCustomAdapter extends ArrayAdapter<String> {

    private ArrayList<String> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public GroupCustomAdapter(ArrayList<String> data, Context context) {
        super(context, R.layout.group_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        GroupCustomAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new GroupCustomAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.group_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupCustomAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(dataModel);
        // Return the completed view to render on screen
        return convertView;
    }
}

