package com.ultrahack_healthid.health_id.query;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ultrahack_healthid.health_id.FireUtil;
import com.ultrahack_healthid.health_id.R;
import com.ultrahack_healthid.health_id.models.Record;

import java.util.ArrayList;

public class RecordsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String TAG = "RecordsFragment";
    ArrayList<Record> temp;

    private FireUtil fireUtil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fireUtil = new FireUtil(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_records, container, true);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.records_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        // specify an adapter (see also next example)
        currentRecords("01");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void currentRecords(String id){
        temp = new ArrayList<>();
        fireUtil.getDatabaseReference().child("/patients/01/records").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot s: dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: " + s.getValue().toString());
                    temp.add(s.getValue(Record.class));
                }

                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new RecordsAdapter(temp);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
