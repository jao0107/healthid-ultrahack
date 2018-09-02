package com.ultrahack_healthid.health_id.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.ultrahack_healthid.health_id.FireUtil;
import com.ultrahack_healthid.health_id.MainActivity;
import com.ultrahack_healthid.health_id.R;
import com.ultrahack_healthid.health_id.entry.RecordActivity;
import com.ultrahack_healthid.health_id.models.Patient;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText mTitleField;
    private EditText mBodyField;
    private EditText mPatientIDField;
    private EditText mTelephone;
    private FireUtil fireUtil;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        fireUtil = new FireUtil(this);
        initViews();
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_submit_post);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewPost(mPatientIDField.getText().toString(),mTitleField.getText().toString(),mBodyField.getText().toString(),mTelephone.getText().toString());
            }
        });
        super.onCreate(savedInstanceState);
    }

    private void initViews(){

        mTitleField = findViewById(R.id.field_title);
        mPatientIDField = findViewById(R.id.field_patient_id);
        mBodyField = findViewById(R.id.field_body);
        mTelephone = findViewById(R.id.telephone);
    }
    // [START write_fan_out]
    private void writeNewPost(String patientID, String name, String birthday,String telephone) {
        // Create new patient at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        //String key = mDatabase.child("healthid/patients/").child(patientID).getKey();
        Patient patient = new Patient(patientID, name, birthday,telephone);
        fireUtil.getDatabaseReference().child("healthid/patients/"+patientID).setValue(patient).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(RegisterActivity.this,"Successfully Added Patient" ,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                RegisterActivity.this.finish();
            }
        });
    }
    // [END write_fan_out]

}
