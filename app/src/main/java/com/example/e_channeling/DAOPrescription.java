package com.example.e_channeling;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPrescription {
    private DatabaseReference databaseReference;

    public DAOPrescription() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Prescription.class.getSimpleName());
    }
    public Task<Void> add(Prescription p){
        return  databaseReference.push().setValue(p);
    }
}
