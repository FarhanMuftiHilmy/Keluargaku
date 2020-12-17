package com.rechit.keluargaku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.applandeo.materialcalendarview.CalendarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rechit.keluargaku.Model.Event;
import com.rechit.keluargaku.Model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;

    FirebaseUser fuser;

    User user;

    private List<Event> mEvents;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        Button button = (Button) findViewById(R.id.addNoteButton);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);
        databaseHelper = new DatabaseHelper(this);
        fuser = FirebaseAuth.getInstance().getCurrentUser();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Event event = new Event(datePicker.getSelectedDate(),
//                        R.drawable.ic_message_black_48dp, noteEditText.getText().toString());

                Intent returnIntent = new Intent();

                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.ic_message_black_48dp, noteEditText.getText().toString());

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("date", getFormattedDate(myEventDay.getCalendar().getTime()));
                hashMap.put("note", noteEditText.getText().toString());
                hashMap.put("detail_date", myEventDay.getCalendar());
                hashMap.put("detail_event", myEventDay);


                reference = FirebaseDatabase.getInstance().getReference("Events").child(fuser.getUid());

//                reference.setValue(event.getNote());

                reference.push().setValue(hashMap);

                if(noteEditText.getText().toString().length() > 0 &&
                        getFormattedDate(myEventDay.getCalendar().getTime()).length() > 0){
                    databaseHelper.addStudent(noteEditText.getText().toString(), getFormattedDate(myEventDay.getCalendar().getTime()));
                    onBackPressed();
                }

                returnIntent.putExtra(KalenderActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });











    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}