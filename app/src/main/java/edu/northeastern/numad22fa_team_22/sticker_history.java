package edu.northeastern.numad22fa_team_22;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class sticker_history extends AppCompatActivity {

    TextView uName;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    DatabaseReference mDatabase;
    ArrayList<Sticker> stickerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_history);
        recyclerView = (RecyclerView) findViewById(R.id.sticker_list);

//        Button back_button = (Button) findViewById(R.id.btn_back);
        uName = findViewById(R.id.tv_history_username);

        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
        uName.setText(username);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("sentStickerList");
        recyclerView.setLayoutManager(new LinearLayoutManager(sticker_history.this));

        stickerList = new ArrayList<>();
        adapter = new CustomAdapter(stickerList);
        recyclerView.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for ( DataSnapshot dataSnapshot : snapshot.getChildren() ) {
                    Sticker sticker = dataSnapshot.getValue(Sticker.class);
                    stickerList.add(sticker);
                }
                adapter.notifyDataSetChanged();
                //setting sticker count
                Log.v("firebase", String.valueOf(stickerList.size()));
                TextView stickerCount = findViewById(R.id.view_count);
                stickerCount.setText(String.valueOf(stickerList.size()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) { runBackButtonActivity(); }
//        });
    }

    public void runBackButtonActivity(){
        // send username to another activity
//        Intent intent = new Intent(this, StickerSendingActivity.class);
//        intent.putExtra("username", uName.getText().toString());
//        startActivity(intent);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close the History activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}