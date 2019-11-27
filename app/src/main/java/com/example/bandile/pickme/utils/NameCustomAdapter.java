package com.example.bandile.pickme.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bandile.pickme.MainActivity;
import com.example.bandile.pickme.R;

import java.util.ArrayList;

public class NameCustomAdapter extends ArrayAdapter<String> implements View.OnClickListener{

    private ArrayList<String> dataSet;
    Context mContext;
    MainActivity inst;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        ImageView btnDelete;
    }

    public NameCustomAdapter(ArrayList<String> data, Context context, MainActivity inst) {
        super(context, R.layout.name_row_item, data);
        this.dataSet = data;
        this.mContext=context;
        this.inst = inst;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        String dataModel=(String)object;

        switch (v.getId())
        {
            case R.id.delete:
                inst.deleteName(object.toString());
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.name_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.btnDelete = (ImageView) convertView.findViewById(R.id.delete);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(dataModel);
        viewHolder.btnDelete.setOnClickListener(this);
        viewHolder.btnDelete.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}