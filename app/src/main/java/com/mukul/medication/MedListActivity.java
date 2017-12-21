package com.mukul.medication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.journaldev.sqlite.R;

public class MedListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseDriver._ID,
            DatabaseDriver.SUBJECT, DatabaseDriver.DESC, DatabaseDriver.DOSE, DatabaseDriver.TIME, DatabaseDriver.UNITS, DatabaseDriver.FREQUENCY };

    final int[] to = new int[] { R.id.id, R.id.tv_med_name, R.id.tv_notes, R.id.tv_dose, R.id.tv_time, R.id.tv_units, R.id.tv_freq };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_med, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.tv_med_name);
                TextView descTextView = (TextView) view.findViewById(R.id.tv_notes);
                TextView doseTextView = (TextView) view.findViewById(R.id.tv_dose);

                TextView timeTextView = (TextView) view.findViewById(R.id.tv_time);
                TextView unitsTextView = (TextView) view.findViewById(R.id.tv_units);
                TextView frequencyTextView = (TextView) view.findViewById(R.id.tv_freq);


                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String dose = doseTextView.getText().toString();
                String time = timeTextView.getText().toString();
                String units = unitsTextView.getText().toString();
                String frequency = frequencyTextView.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), ModifyMedActivity.class);
                modify_intent.putExtra("frequency", frequency);
                modify_intent.putExtra("units", units);
                modify_intent.putExtra("time", time);
                modify_intent.putExtra("dose", dose);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddMedActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}