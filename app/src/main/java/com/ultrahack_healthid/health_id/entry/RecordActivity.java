package com.ultrahack_healthid.health_id.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.ultrahack_healthid.health_id.FireUtil;
import com.ultrahack_healthid.health_id.R;
import com.ultrahack_healthid.health_id.models.Prescription;
import com.ultrahack_healthid.health_id.models.Record;
import com.ultrahack_healthid.health_id.query.QueryActivity;

import java.util.Date;
import java.util.HashMap;

public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "RecordActivity";

    private FireUtil fireUtil;
    private Record record;
    private Spinner urgency;
    private EditText summary;
    private EditText details;
    private EditText medicine1;
    private EditText quanitity1;
    private EditText medicine2;
    private EditText quanitity2;
    private EditText medicine3;
    private EditText quanitity3;
    private EditText medicine4;
    private EditText quanitity4;


    private String sampleName = "Dr. Samson";
    private String sampleProfession = "Cardiologist";
    private String sampleId = "01";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_record_entry);
        fireUtil = new FireUtil(this);
        FloatingActionButton button = findViewById(R.id.submit_entry);
        initViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(sampleId);
            }
        });
        record = new Record();
        super.onCreate(savedInstanceState);
    }


    private void submit(String id){

        long time = new Date().getTime();
        String recordId = ("record"+time);
        String prescriptionId = ("prescription"+time);

        record.setProfession(sampleProfession);
        record.setName(sampleName);
        record.setTimestamp(time);
        record.setDetail(details.getText().toString());
        record.setUrgency(urgency.getSelectedItem().toString());
        record.setSummary(summary.getText().toString());



        HashMap<String, Integer> medicine = new HashMap<>();
        try {
            medicine.put(medicine1.getText().toString(), (Integer.parseInt(quanitity1.getText().toString())));
            medicine.put(medicine2.getText().toString(), (Integer.parseInt(quanitity2.getText().toString())));
            medicine.put(medicine3.getText().toString(), (Integer.parseInt(quanitity3.getText().toString())));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }



        Prescription prescription = new Prescription();
        prescription.setId(prescriptionId);
        prescription.setRecordId(recordId);
        prescription.setStatus("pending");
        prescription.setMedicine(medicine);
        record.setPrescription(prescription);


        //submit record
        fireUtil.getDatabaseReference().child("/patients/"+id+"/records/"+recordId).setValue(record);
        fireUtil.getDatabaseReference().child("/patients/"+id+"/active_prescription/" + prescriptionId).setValue(prescription).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(RecordActivity.this, QueryActivity.class));
                RecordActivity.this.finish();
            }
        })
        ;
        Log.d(TAG, "submit: " + new Gson().toJson(record));




    }

    private void initViews(){

      urgency = findViewById(R.id.urgency_entry);
      summary = findViewById(R.id.summary_entry);
      details = findViewById(R.id.entry_detail);

      medicine1 = findViewById(R.id.medicine_entry);
      quanitity1 = findViewById(R.id.quantity_entry);
      medicine2 = findViewById(R.id.medicine_entry2);
      quanitity2 = findViewById(R.id.quantity_entry2);
      medicine3 = findViewById(R.id.medicine_entry3);
      quanitity3 = findViewById(R.id.quantity_entry3);
      medicine4 = findViewById(R.id.medicine_entry4);
      quanitity4 = findViewById(R.id.quantity_entry4);

    }
}
