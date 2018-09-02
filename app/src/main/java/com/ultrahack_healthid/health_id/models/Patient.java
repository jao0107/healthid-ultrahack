package com.ultrahack_healthid.health_id.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Patient {

    public String patientID;
    public String name;
    public String birthday;
    public String telephone;

    public Patient() {
        // Default constructor required for calls to DataSnapshot.getValue(Patient.class)
    }

    public Patient( String patientID, String name, String birthday,String telephone) {
        this.patientID = patientID;
        this.name = name;
        this.birthday = birthday;
        this.telephone = telephone;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("patientID", patientID);
        result.put("name", name);
        result.put("birthday", birthday);
        result.put("telephone", telephone);
        return result;
    }
    // [END post_to_map]

}
// [END post_class]
