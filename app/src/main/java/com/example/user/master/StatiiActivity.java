package com.example.user.master;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
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
        ArrayAdapter<String> statiiArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, statiiNumarList);
        autoCompleteStatii.setAdapter(statiiArrayAdapter);
        autoCompleteStatii.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, List<String>> liniiForStatie = disertatieDatabaseHelper.getLiniiAndStatiiByStatie(parent.getItemAtPosition(position).toString());
                LinearLayout container = (LinearLayout) findViewById(R.id.statiiPanelContainer);
                container.removeAllViews();
                int counter = 0;
                LinearLayout rowContainer = null;
                for (String linie : liniiForStatie.keySet()) {

                    if(counter%3==0){
                        rowContainer = new LinearLayout(getBaseContext());
                        rowContainer.setOrientation(LinearLayout.HORIZONTAL);
                        rowContainer.setWeightSum(3);
                        container.addView(rowContainer);
                    }

                    LinearLayout statiiContainer = new LinearLayout(getBaseContext());
                    statiiContainer.setOrientation(LinearLayout.VERTICAL);
                    rowContainer.addView(statiiContainer, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    statiiContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                    List<String> statiiList = liniiForStatie.get(linie);
                    TextView tvLinieNumber = new TextView(getBaseContext());
                    tvLinieNumber.setText(linie);
                    tvLinieNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                    tvLinieNumber.setTextColor(Color.BLACK);
                    statiiContainer.addView(tvLinieNumber, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    for(String statie : statiiList){
                        TextView tvLinieWithStatii = new TextView(getBaseContext());
                        tvLinieWithStatii.setText(statie);
                        tvLinieWithStatii.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        tvLinieWithStatii.setTextColor(Color.BLACK);
                        statiiContainer.addView(tvLinieWithStatii, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    }
                    counter++;
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