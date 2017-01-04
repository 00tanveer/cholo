package com.example.root.listexample.message_app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.listexample.MainActivity;
import com.example.root.listexample.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MessageFeedActivity extends AppCompatActivity {

    private RecyclerView listView;
    private TextView room_name;
    private MessageShortAdapter adapter;
    private List<MessageShort> messageShortList = new ArrayList<>();
    private String name;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth;
    private String email = "tanveer_fh09@yahoo.com";
    private String password = "00T@nveer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if(user==null) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MessageFeedActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                            if (!task.isSuccessful()) {
                                // there was an error
                                if (password.length() < 6) {
                                    //inputPassword.setError(getString(R.string.minimum_password));
                                } else {
                                    Toast.makeText(MessageFeedActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MessageFeedActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                                //finish();
                            }
                        }
                    });
        }
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        listView = (RecyclerView) findViewById(R.id.event_room_list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        updateList();
        name = "00tanveer";


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String chat_msg, user_name, time;
                Set<String> set = new HashSet<String>();
                Query query = root.orderByChild("title");
                System.out.println(dataSnapshot.getValue());
                System.out.println(query.toString());
                Iterator i = dataSnapshot.getChildren().iterator();
                /*while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                    MessageShort messageShort = new MessageShort();
                    messageShort.setEvent_name(((DataSnapshot)i.next()).getKey());
                    //chat_msg = (String) ((DataSnapshot)i.next()).getValue();
                    //user_name = (String) ((DataSnapshot)i.next()).getValue();
                    //time = (String) ((DataSnapshot)i.next()).getValue();
                    //messageShort.setLast_message(chat_msg);
                    //messageShort.setTime(time);
                    messageShortList.add(messageShort);
                }*/

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), EventRoomActivity.class);
                String room_name = adapter.getItem(i).getEvent_name();
                intent.putExtra("room_name", room_name );
                intent.putExtra("user_name",name);
                startActivity(intent);
            }
        });*/
    }

    private void updateList() {
        adapter = new MessageShortAdapter(messageShortList, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStop(){
        super.onStop();
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
