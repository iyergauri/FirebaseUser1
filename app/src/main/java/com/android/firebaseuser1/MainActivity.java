package com.android.firebaseuser1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        options = new FirebaseOptions.Builder()
                .setApplicationId("1:27232811891:android:621056aa4bd9f64c")
                .setDatabaseUrl("https://fir-user2-ec77a.firebaseio.com/")
                .build();
        database = FirebaseDatabase.getInstance(FirebaseApp.initializeApp(this, options, "secondary"));
        myRef = database.getReferenceFromUrl("https://fir-user2-ec77a.firebaseio.com/");

        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();
        final TextView message = (TextView) findViewById(R.id.message);
        FirebaseDatabase localdatabase = FirebaseDatabase.getInstance();
        DatabaseReference localRef = localdatabase.getReference();

        localRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                message.setText(data);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void send(View button) {

        String sendText = ((EditText) findViewById(R.id.sendText)).getText().toString();
        myRef.setValue(sendText);
    }
}

