package com.create_event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class CreateEvent extends AppCompatActivity implements OnClickListener {

    private EditText event_date;
    private EditText event_time;
    private Button time_button;
    private DatePickerDialog event_date_PickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button date_button;
    private int CalendarHour, CalendarMinute;
    private String format;
    private Calendar calendarForTime;
    private TimePickerDialog timepickerdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        event_date = (EditText)findViewById(R.id.event_date_id);
        event_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField();
                event_date_PickerDialog.show();
            }
        });



        date_button = (Button)findViewById(R.id.date_button_id);
        date_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField();
                event_date_PickerDialog.show();
            }
        });

        event_time=(EditText)findViewById(R.id.event_time_id);
        time_button=(Button)findViewById(R.id.time_button_id);



        //listeners
        event_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeDialog();
            }
        });
        time_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeDialog();
            }
        });
        getSupportActionBar().setTitle("Create Event");

        //back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void setTimeDialog() {
        calendarForTime = Calendar.getInstance();
        CalendarHour = calendarForTime.get(Calendar.HOUR_OF_DAY);
        CalendarMinute = calendarForTime.get(Calendar.MINUTE);

        timepickerdialog = new TimePickerDialog(CreateEvent.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        if (hourOfDay == 0) {

                            hourOfDay += 12;

                            format = "AM";
                        } else if (hourOfDay == 12) {

                            format = "PM";

                        } else if (hourOfDay > 12) {

                            hourOfDay -= 12;

                            format = "PM";

                        } else {

                            format = "AM";
                        }

                        event_time.setText(hourOfDay + ":" + minute +" "+ format);

                    }
                }, CalendarHour, CalendarMinute, false);
        timepickerdialog.show();

    }


    private void setDateTimeField() {
        final Calendar newCalendar = Calendar.getInstance();
        event_date_PickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

                int dayOfWeek = newDate.get(Calendar.DAY_OF_WEEK);
                String day = days[dayOfWeek-1];
                event_date.setText(day+", "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
 }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(this,"Cannot go back",Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);


    }
    public void done(MenuItem item){
        Toast.makeText(this, "Event Created Successfully", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View view) {

    }
}
