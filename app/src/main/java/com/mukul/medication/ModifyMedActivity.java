package com.mukul.medication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.journaldev.sqlite.R;

public class ModifyMedActivity extends Activity implements OnClickListener {

    private EditText et_med;
    private Button updateBtn, deleteBtn;
    private EditText et_dose;
    private EditText et_notes;

    private EditText et_time;
    private EditText et_units;
    private EditText et_frequency;



    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Edit Medication");

        setContentView(R.layout.activity_modify_med);

        dbManager = new DBManager(this);
        dbManager.open();

        et_med = (EditText) findViewById(R.id.et_med);
        et_dose = (EditText) findViewById(R.id.et_dose);
        et_notes = (EditText) findViewById(R.id.et_notes);

        et_time = (EditText) findViewById(R.id.et_time);
        et_units = (EditText) findViewById(R.id.et_units);
        et_frequency = (EditText) findViewById(R.id.et_freq);


        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        String dose = intent.getStringExtra("dose");
        String time = intent.getStringExtra("time");
        String units = intent.getStringExtra("units");
        String frequency = intent.getStringExtra("frequency");



        _id = Long.parseLong(id);

        et_med.setText(name);
        et_dose.setText(dose);
        et_notes.setText(desc);
        et_time.setText(time);
        et_units.setText(units);
        et_frequency.setText(frequency);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String title = et_med.getText().toString();
                String desc = et_notes.getText().toString();
                String dose = et_dose.getText().toString();
                String time = et_time.getText().toString();
                String units = et_units.getText().toString();
                String frequency = et_frequency.getText().toString();



                dbManager.update(_id, title, desc, dose, time, units, frequency);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MedListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
