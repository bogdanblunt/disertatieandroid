package com.example.user.master;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;

import org.w3c.dom.Text;

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
                List<String> liniiForStatie = disertatieDatabaseHelper.getLiniiByStatie(parent.getItemAtPosition(position).toString());

                TextView liniiByStatiiTv = (TextView)findViewById(R.id.liniiByStatiiResult);
                liniiByStatiiTv.setText(liniiForStatie.toString());
//                for (String linie : liniiForStatie) {
//
//                }
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
