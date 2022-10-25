package com.example.e_channeling;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChoose;
    private Uri mImageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.inpt_name);
        EditText address = findViewById(R.id.inpt_address);
        EditText number = findViewById(R.id.inpt_phone);

        Button btn = findViewById(R.id.btn);
        DAOPrescription daop = new DAOPrescription();

        mButtonChoose = findViewById(R.id.btn_upld);

        btn.setOnClickListener(view -> {
            Prescription p = new Prescription(name.getText().toString(),address.getText().toString(),number.getText().toString());
            daop.add(p).addOnSuccessListener(suc->{
                Toast.makeText(this, "Prescription ordered successfully", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_LONG).show();
            });
        });

        mButtonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null){
            mImageUri = data.getData();

        }
    }
}