package com.ultrahack_healthid.health_id;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireUtil {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public FireUtil(Context context){

    FirebaseApp.initializeApp(context);
      firebaseDatabase = FirebaseDatabase.getInstance();
      databaseReference = firebaseDatabase.getReference("healthid");
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
