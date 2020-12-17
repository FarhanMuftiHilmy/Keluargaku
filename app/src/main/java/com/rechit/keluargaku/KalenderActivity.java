package com.rechit.keluargaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rechit.keluargaku.Model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KalenderActivity extends AppCompatActivity {


    public static final String RESULT = "result";
    public static final String EVENT = "event";
    private static final int ADD_NOTE = 44;

    private CalendarView mCalendarView;
    private final List<EventDay> mEventDays = new ArrayList<>();
    ListView listView;

    ArrayList<Map<String, String>> arrayList;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        listView = findViewById(R.id.listview);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        databaseHelper = new DatabaseHelper(this);


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });

        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                previewNote(eventDay);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int id = Integer.parseInt(arrayList.get(i).get("id"));
                showConfirm(id);
                return true;
            }
        });


    }

    private void showConfirm(final int id){
        new AlertDialog.Builder(this)
                .setTitle("Hapus Data")
                .setMessage("Apakah anda yakiin ingin menghapus data ini?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(id);

                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }

    private void delete(int id){
        databaseHelper.delete(id);
        arrayList.clear();
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE && resultCode == RESULT_OK) {
            EventDay myEventDay = data.getParcelableExtra(RESULT);
            mCalendarView.setDate(myEventDay.getCalendar());
            mEventDays.add(myEventDay);
            mCalendarView.setEvents(mEventDays);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() { //mengambil data dari database helper
        arrayList = databaseHelper.getAllStudents();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList,
                android.R.layout.simple_list_item_2, new String[]{"nama", "alamat"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);//integrasi adapter
        simpleAdapter.notifyDataSetChanged();//refresh
    }

    private void addNote() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE);
    }

    private void previewNote(EventDay eventDay) {
        Intent intent = new Intent(this, NotePreviewActivity.class);
        if(eventDay instanceof MyEventDay){
            intent.putExtra(EVENT, (MyEventDay) eventDay);
        }
        startActivity(intent);
    }


//    FloatingActionButton floatingActionButton;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_kalender);
//
//        Toolbar toolbar = findViewById(R.id.toolbar2);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//
//
//        CalendarView calendarView = findViewById(R.id.calendarView);
//        floatingActionButton = findViewById(R.id.calendar_fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(KalenderActivity.this, EventActivity.class));
//            }
//        });
//
//    }
}