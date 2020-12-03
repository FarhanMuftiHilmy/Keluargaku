package com.rechit.keluargaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rechit.keluargaku.Model.Pesan;
import com.rechit.keluargaku.Model.PesanGrup;
import com.rechit.keluargaku.Model.User;

import java.util.ArrayList;
import java.util.List;

public class PesanGrupActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseAuth auth;
    FirebaseDatabase db;
    FirebaseUser fuser;

    RecyclerView recyclerView;

    DatabaseReference reference;
    PesanGrupAdapter pesanGrupAdapter;

    ImageButton btn_send;
    EditText text_send;
    List<PesanGrup> pesan; //pesan

    PesanGrup pg;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_grup);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        init();
    }

    private void init() {

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        u = new User();

        recyclerView = (RecyclerView)findViewById(R.id.rvPesanGrup);
        text_send = (EditText)findViewById(R.id.text_send);
        btn_send = (ImageButton)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        pesan = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(text_send.getText().toString()))
        {
            pg = new PesanGrup(text_send.getText().toString(), u.getUsername());
            text_send.setText("");
            reference.push().setValue(pg);
        }
        else{
            Toast.makeText(getApplicationContext(), "You can't send blank message", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        final  FirebaseUser fuser = auth.getCurrentUser();

        u.setId(fuser.getUid());
        u.setEmail(fuser.getEmail());

        db.getReference("Users").child(fuser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                u = snapshot.getValue(User.class);
                u.setId(fuser.getUid());
                AllMethods.name = u.getUsername();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        reference = db.getReference("GroupChatlist");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                PesanGrup pg = snapshot.getValue(PesanGrup.class);
                pg.setKey(snapshot.getKey());
                pesan.add(pg);
                tampilkanPesan(pesan);
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
                PesanGrup pg = snapshot.getValue(PesanGrup.class);
                pg.setKey(snapshot.getKey());

                List<PesanGrup> pesanBaru = new ArrayList<PesanGrup>();

                for(PesanGrup p: pesan)
                {
                    if(p.getKey().equals(pg.getKey())){
                        pesanBaru.add(pg);
                    } else{
                        pesanBaru.add(p);
                    }
                }

                pesan = pesanBaru;
                tampilkanPesan(pesan);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                PesanGrup pg = snapshot.getValue(PesanGrup.class);
                pg.setKey(snapshot.getKey());
                List<PesanGrup> pesanBaru = new ArrayList<PesanGrup>();

                for(PesanGrup p: pesan)
                {
                    if(!p.getKey().equals(pg.getKey()))
                    {
                        pesanBaru.add(p);
                    }
                }

                pesan = pesanBaru;
                tampilkanPesan(pesan);

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        pesan = new ArrayList<>();
    }

    private void tampilkanPesan(List<PesanGrup> pesan) {
        recyclerView.setLayoutManager(new LinearLayoutManager(PesanGrupActivity.this));
        pesanGrupAdapter = new PesanGrupAdapter(PesanGrupActivity.this, pesan, reference);
        recyclerView.setAdapter(pesanGrupAdapter);
    }
}