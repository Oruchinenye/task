package com.startng.dbs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.startng.newsapp.R;

import java.util.ArrayList;

public class NoteList extends ArrayAdapter<Note> {
    private final Context context;
    private final ArrayList<Note> data;
    private final int layoutResourceId;
    private final boolean viewMode;

    public NoteList(@NonNull Context context, @NonNull ArrayList<Note> objects) {
        this(context,objects , false);
    }

    public NoteList(@NonNull Context context, @NonNull ArrayList<Note> objects, boolean viewMode) {
        super(context,
                R.layout.note_card_fragment,
                objects
        );
        this.context = context;
        this.data = objects;
        this.layoutResourceId = R.layout.note_card_fragment;
        this.viewMode = viewMode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)this.getContext()).getLayoutInflater();
            row = inflater.inflate(this.layoutResourceId,parent, false);
            holder = new ViewHolder();
            holder.id = (TextView)row.findViewById(R.id.note_id);
            holder.body = (TextView)row.findViewById(R.id.note_body);
            holder.created_at = (TextView)row.findViewById(R.id.date);
            row.setTag(holder);
            if (viewMode){
             //   holder.deleteBtn =   (Button)  row.findViewById(R.id.button);
            }
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }



        Note item = data.get(position);

        holder.id.setText(String.valueOf(item.note__id));
        holder.body.setText(item.note_body);
        holder.created_at.setText(item.note_created_at.toString());
        return row;
    }

    static class ViewHolder
    {
        TextView id;
        TextView body;
        TextView created_at;
    }
}

