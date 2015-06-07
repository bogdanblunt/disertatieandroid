package com.example.user.master;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;

import java.util.HashMap;
import java.util.List;


public class StatiiActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statii);

        AutoCompleteTextView autoCompleteStatii = (AutoCompleteTextView)findViewById(R.id.autocompleteStatii);
        final DisertatieDatabaseHelper disertatieDatabaseHelper = new DisertatieDatabaseHelper(this);
        List<String> statiiNumarList = disertatieDatabaseHelper.getNumeStatiiList();
        ArrayAdapter<String> statiiArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, statiiNumarList);
        autoCompleteStatii.setAdapter(statiiArrayAdapter);
        autoCompleteStatii.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, List<String>> liniiForStatie = disertatieDatabaseHelper.getLiniiAndStatiiByStatie(parent.getItemAtPosition(position).toString());//getLiniiByStatie(parent.getItemAtPosition(position).toString());


                for (String linie : liniiForStatie.keySet()) {
                    List<String> statiiList = liniiForStatie.get(linie);
                    TextView liniiByStatiiTv = (TextView)findViewById(R.id.liniiByStatiiResult);
                    liniiByStatiiTv.setText(statiiList.toString());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statii, menu);
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


}
