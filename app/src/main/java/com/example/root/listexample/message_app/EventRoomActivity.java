package com.example.root.listexample.message_app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.example.root.listexample.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventRoomActivity extends AppCompatActivity {

    private ImageView sendButton;
    private EditText sendText;
    private ToggleButton followButton;

    private FirebaseApp app;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private DatabaseReference userDbRef;
    private DatabaseReference eventRef;

    private RecyclerView messagesList;
    private ChatMessageAdapter adapter;

    private String username = "00tanveer";
    private String eventName;
    private Boolean following = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_room);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        sendButton = (ImageView) findViewById(R.id.sendButton);
        followButton = (ToggleButton) findViewById(R.id.followButton);
        sendText = (EditText) findViewById(R.id.sendText);
        eventName = getIntent().getStringExtra("eventName");
        toolbar.setTitle(eventName);


        messagesList = (RecyclerView) findViewById(R.id.messagesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        messagesList.setHasFixedSize(false);
        messagesList.setLayoutManager(layoutManager);

        adapter = new ChatMessageAdapter(this);
        messagesList.setAdapter(adapter);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            public void onItemRangeInserted(int positionStart, int itemCount) {
                messagesList.smoothScrollToPosition(adapter.getItemCount());
            }
        });

        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);

        databaseRef = database.getReference("chat/"+eventName+"/");
        userDbRef = database.getReference("users/"+username+"/"+"chats/");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage chat = new ChatMessage(username, sendText.getText().toString());
                // Push the chat message to the database
                databaseRef.push().setValue(chat);
                sendText.setText("");
            }
        });

        eventRef = userDbRef.child(eventName);
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                Boolean temp;
                temp = (Boolean) dataSnapshot.getValue();
                if(temp == null){

                }
                else if(temp == true){
                    followButton.setChecked(true);
                    following = true;
                }
                else{
                    following = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(following == false) {
                    eventRef.setValue(true);
                    followButton.setChecked(true);
                }
                else{
                    eventRef.setValue(false);
                    followButton.setChecked(false);
                    following = true;
                }
            }
        });

        databaseRef.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot snapshot, String s) {
                // Get the chat message from the snapshot and add it to the UI
                ChatMessage chat = snapshot.getValue(ChatMessage.class);
                adapter.addMessage(chat);
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
            public void onCancelled(DatabaseError databaseError) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
