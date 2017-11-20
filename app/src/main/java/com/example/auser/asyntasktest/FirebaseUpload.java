package com.example.auser.asyntasktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class FirebaseUpload extends AppCompatActivity {
    TextView textView;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_upload);

        textView=(TextView)findViewById(R.id.textView2);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference  myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        et=(EditText)findViewById(R.id.editText);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("firebase", "Value is: " + value);
                textView.setText(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebase", "Failed to read value.", error.toException());
            }
        });
    }


    public void openClick(View target){

        textView.setText(et.getText());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference  myRef = database.getReference("message");
        myRef.setValue(et.getText().toString());

    }


}
