package com.ultrahack_healthid.health_id.query;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ultrahack_healthid.health_id.FireUtil;
import com.ultrahack_healthid.health_id.R;
import com.ultrahack_healthid.health_id.entry.RecordActivity;

import org.w3c.dom.Text;

import java.util.Date;

public class QueryActivity extends AppCompatActivity {

    private static final String TAG = "QueryActivity";
    private FireUtil fireUtil;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_query);
        fireUtil = new FireUtil(this);
        button = findViewById(R.id.search);
        final ConstraintLayout smsLayout = findViewById(R.id.smsLayout);
        final Button smsConfirm = findViewById(R.id.smsConfirmButton);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_entry);


        final Fragment fragment = new RecordsFragment();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QueryActivity.this, RecordActivity.class));
                QueryActivity.this.finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireUtil.getDatabaseReference().child("access").setValue(new Date().getTime());
                smsLayout.setVisibility(View.VISIBLE);
            }
        });

        smsConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                smsLayout.setVisibility(View.GONE);
                TextView textView = findViewById(R.id.patientName);
                textView.setText(getString(R.string.string,"SANTOS, JUAN D."));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, fragment).commit();
            }
        });
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        super.onBackPressed();
    }
}
