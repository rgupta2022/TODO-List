package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todoapp.databinding.ActivityDataInsertBinding;

import java.util.Calendar;
import java.util.Locale;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;
    EditText editDateText;
    int year;
    int month;
    int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Time
        TextView timeTextView = findViewById(R.id.selectTime);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }

            private void showTimePickerDialog () {
                //Get the current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // TimePickerDialog shown
                TimePickerDialog timePickerDialog = new TimePickerDialog(DataInsertActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Updated the textview with the selected time
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        timeTextView.setText(time);

                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });







        // Date
        editDateText = findViewById(R.id.editTextDate);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        String dateExtra = getIntent().getStringExtra("data");
        if(dateExtra != null && !dateExtra.isEmpty()){
            editDateText.setText(dateExtra);
        }

      editDateText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DatePickerDialog datePickerDialog = new DatePickerDialog(DataInsertActivity.this, new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                      editDateText.setText(selectedDate);
                  }
              },year,month,day);
                datePickerDialog.show();
          }
      });


                String type = getIntent().getStringExtra("type");
                if (type.equals("update")) {
                    setTitle("update");
                    binding.title.setText(getIntent().getStringExtra("title"));
                    binding.disp.setText(getIntent().getStringExtra("disp"));
                    binding.editTextDate.setText(getIntent().getStringExtra("date"));
                    binding.selectTime.setText(getIntent().getStringExtra("time"));
                    int id = getIntent().getIntExtra("id", 0);
                    binding.add.setText("Update List");

                    binding.add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("title", binding.title.getText().toString());
                            intent.putExtra("disp", binding.disp.getText().toString());
                            intent.putExtra("date",binding.editTextDate.getText().toString());
                            intent.putExtra("time",binding.selectTime.getText().toString());
                            intent.putExtra("id", id);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });

                } else {
                    setTitle("Add Mode");

                    CheckBox completedCheckBox = findViewById(R.id.completeChecbox);
                    TextView taskStatus = findViewById(R.id.timeStatus);

                    completedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                taskStatus.setText("Completed");
                                taskStatus.setTextColor(Color.GREEN);
                            } else {
                                taskStatus.setText("Ongoing");
                                taskStatus.setTextColor(Color.BLACK);
                            }
                        }
                    });

                    binding.add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.putExtra("title", binding.title.getText().toString());
                            intent.putExtra("disp", binding.disp.getText().toString());
                            intent.putExtra("date",binding.editTextDate.getText().toString());
                            intent.putExtra("time",binding.selectTime.getText().toString());
                            setResult(RESULT_OK, intent);
                            finish();
                        }

                    });
                }
            }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}