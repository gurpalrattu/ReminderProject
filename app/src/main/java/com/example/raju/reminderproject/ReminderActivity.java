package com.example.raju.reminderproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.sql.SQLException;

public class ReminderActivity extends AppCompatActivity {

    private ListView mListView;
    private RemindersDBAdapter mDbAdapter;
    private RemindersSimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        mListView = (ListView) findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);
        mDbAdapter = new RemindersDBAdapter(this);
        try {
            mDbAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = mDbAdapter.fetchAllReminders();

        // from columns defined in the db
        String[] from = new String[]{
                RemindersDBAdapter.COL_CONTENT
        };

        // to the ids of views in the layout
        int[] to = new int[] {
                R.id.row_text
        };

        mCursorAdapter = new RemindersSimpleCursorAdapter(
                //context
                ReminderActivity.this,
                //the layout of the row
                R.layout.reminders_row,
                // cursor
                cursor,
                // from columns defined in the db
                from,
                // to the ids of views in the layout
                to,
                // flag - not used
                0);

        // the cursorAdapter (controller) is now updating the listView (view)
        // with data from the db(model)
        mListView.setAdapter(mCursorAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_new:
                Log.d(getLocalClassName(), "create new Reminder");
                return true;

            case R.id.action_exit:
                finish();
                return true;

            default:
                return false;
        }
    }
}
