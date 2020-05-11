package com.startng.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.startng.dbs.Note;

import java.util.List;

public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.MyViewHolder> {
    public List<Note> mDataset;
    private Context mContext;
 //   private final ClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final Button editBtn;

        public Note mItem;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.note_id);
            mContentView = (TextView) view.findViewById(R.id.note_body);
            editBtn = view.findViewById(R.id.editBtn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HeadlinesAdapter(HeadlinesActivity context, List<Note> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HeadlinesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = ((Activity) mContext).getLayoutInflater()
                .inflate(R.layout.note_card_fragment, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Note note = mDataset.get(position);
        holder.mContentView.setText(getDisplayString(note.note_body,40));
        holder.mIdView.setText(String.valueOf(note.note__id));
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("note", note.note_body);
                mContext.startActivity(intent);
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditNote.class);
                intent.putExtra("_id", String.valueOf(note.note__id));
                intent.putExtra("text", note.note_body);
                mContext.startActivity(intent);
            }
        });

    }

    public void clear() {
        mDataset.clear();
    }

    public void addAll (List<Note> notes){
        mDataset.addAll(notes);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private String getDisplayString(String val, int max ){
        if(val.length() > max ){
            return val.substring(0,max-1)+" ...";
        }
        return  val;
    }
}