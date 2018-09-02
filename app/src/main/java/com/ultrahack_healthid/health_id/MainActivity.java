package com.ultrahack_healthid.health_id;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.ultrahack_healthid.health_id.query.QueryActivity;
import com.ultrahack_healthid.health_id.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Button query = findViewById(R.id.proceed);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QueryActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        super.onCreate(savedInstanceState);

    }



}
