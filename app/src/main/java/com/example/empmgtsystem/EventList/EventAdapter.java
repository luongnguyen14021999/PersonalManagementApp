package com.example.empmgtsystem.EventList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.empmgtsystem.R;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    private final Context context;
    private final List<Event> values;
    private boolean checked = false;

    // boolean array for storing
    //the state of each CheckBox
    boolean[] checkBoxState;

    ViewHolder viewHolder;


    public EventAdapter(Context context, List<Event> values) {
        super(context, R.layout.row_layout, values);
        this.context = context;
        this.values = values;
        checkBoxState = new boolean[values.size()];
    }

    private class ViewHolder
    {
        TextView eventname;
        CheckBox checkBox;

        public CheckBox getCheckBox()
        {
            return checkBox;
        }

        public TextView getName()
        {
            return eventname;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.row_layout, null);
            viewHolder=new ViewHolder();

            //cache the views
            viewHolder.eventname=(TextView) convertView.findViewById(R.id.label);
            viewHolder.checkBox=(CheckBox) convertView.findViewById(R.id.checkBoxDelete);

            //link the cached views to the convertview
            convertView.setTag( viewHolder);


        }
        else
            viewHolder=(ViewHolder) convertView.getTag();

        viewHolder.eventname.setText( values.get(position).getEventName());


        String s = values.get(position).getEventName();



        //VITAL PART!!! Set the state of the
        //CheckBox using the boolean array
        viewHolder.checkBox.setChecked(checkBoxState[position]);


        //for managing the state of the boolean
        //array according to the state of the
        //CheckBox

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(((CheckBox)v).isChecked()) {
                    checkBoxState[position] = true;
                }
                else
                    checkBoxState[position]=false;

            }
        });

        //return the view to be displayed
        return convertView;
    }

    public boolean[] getCheckBoxState()
    {
        return checkBoxState;
    }

    public String getName(int pos)
    {
        String val = values.get(pos).getEventName();
        return val;
    }
}
