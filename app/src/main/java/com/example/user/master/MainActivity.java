package com.example.user.master;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;


public class MainActivity extends ActionBarActivity {

    public static final int TIME_ENTRY_REQUEST_CODE = 1;
    public DisertatieDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button plan_button = (Button)findViewById(R.id.formulare_button);
//        helper = new DisertatieDatabaseHelper(this);
//        Cursor cursor = helper.getAllLinii();
//
//       if(cursor.moveToNext()) {
//            plan_button.setText(cursor.getString(3));
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void afiseazaFormular(View view){

        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);

    }

    public void afiseazaStatii(View view){

        Intent intent = new Intent(this, StatiiActivity.class);
        startActivity(intent);

    }

    public void afiseazaLinii(View view){

        Intent intent = new Intent(this, LiniiActivity.class);
        startActivity(intent);

    }
}
