package com.ultrahack_healthid.health_id.query;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ultrahack_healthid.health_id.R;
import com.ultrahack_healthid.health_id.models.Record;

import java.util.ArrayList;
import java.util.Date;

import static com.ultrahack_healthid.health_id.R.color.lightred;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {
    private ArrayList<Record> recordArrayList;
    private Gson gson = new Gson();
    private static final String TAG = "RecordsAdapter";
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecordsAdapter(ArrayList<Record> arrayList) {
        Log.d(TAG, "RecordsAdapter: " + gson.toJson(arrayList));
        this.recordArrayList = arrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecordsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_record_packet, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
         Record record = recordArrayList.get(position);
        setTexts(holder,record);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return recordArrayList.size();
    }
    private void setTexts(MyViewHolder holder, Record record){

        // set name set profession
        ((TextView) holder.view.findViewById(R.id.worker_attr)).setText(record.getName()+" - " + record.getProfession());


        //set timestamp
        ((TextView) holder.view.findViewById(R.id.record_timestamp)).setText(new Date(record.getTimestamp()).toString());

        //setsummary
        ((TextView) holder.view.findViewById(R.id.summary)).setText(record.getSummary());


        //set info
        ((TextView) holder.view.findViewById(R.id.detail)).setText(record.getDetail());

        //set urgency
        ((TextView) holder.view.findViewById(R.id.urgency)).setText(record.getUrgency());

        //set prescription status
        ((TextView) holder.view.findViewById(R.id.status)).setText(record.getPrescription().getStatus());

        //set prescription
        String prescription = record.getPrescription().getMedicine().toString();
        ((TextView) holder.view.findViewById(R.id.medicine)).setText(prescription.substring(1,prescription.length()-1).replace(",","\n"));

    }
}