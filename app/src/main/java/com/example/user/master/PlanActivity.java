package com.example.user.master;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;
import com.example.user.master.utils.SlidingTabLayout;
import com.example.user.master.utils.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class PlanActivity extends ActionBarActivity {

    static DisertatieDatabaseHelper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Toolbar toolbar;
        SlidingTabLayout tabs;

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        helper = new DisertatieDatabaseHelper(getBaseContext());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plan, menu);
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

    public void planificaSimplu(View view){

        Spinner spinnerSimplu1 =(Spinner) findViewById(R.id.plecareSimplu_spinner);
        Spinner spinnerSimplu2 =(Spinner) findViewById(R.id.sosireSimplu_spinner);
        String statieP = spinnerSimplu1.getSelectedItem().toString();
        String statieS = spinnerSimplu2.getSelectedItem().toString();

        if(statieP.equals(statieS)){

            AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
            builder1.setMessage("Statia de plecare coincide cu statia de sosire!");
            builder1.setCancelable(true);
            builder1.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else {

           List<String> cursorLiniiP = helper.getLiniiByStatie(statieP);
           List<String> cursorLiniiS = helper.getLiniiByStatie(statieS);
           List<String> result = new ArrayList<String>();

           int ok=0;
           int aux=1000;
           String linieDirecta="";




          for (String liniiP : cursorLiniiP){
              for(String liniiS : cursorLiniiS){

                  if(liniiP.equals(liniiS)){
                      ok=1;
                      result.add(liniiP);
                      int timp = helper.getTimpByLinie(liniiP);
                      if(timp<aux){
                          aux = timp;
                          linieDirecta=liniiP;
                      }
                  }
              }
          }

            if(ok==1){

                LinkedHashMap<String, Integer> toateStatiile = new LinkedHashMap<>();

                System.out.println("Sugestia este:");
                System.out.println(linieDirecta);
                System.out.println("Timpul mediu de asteptare in statie");
                System.out.println(aux);

                System.out.println("Statiile prin care trece: ");

                Cursor c = helper.getStatiiByLinie(linieDirecta);
                while(c.moveToNext()){
                   toateStatiile.put(c.getString(0), c.getInt(2));
                }

                int sP = toateStatiile.get(statieP);
                int sS = toateStatiile.get(statieS);

                if(sS > sP) {

                    for(String s : toateStatiile.keySet()){
                        int id = toateStatiile.get(s);
                        if(id<= sS && id >= sP){
                            System.out.println(s);
                        }
                    }
                } else {
                    ArrayList<String> reverseList = new ArrayList<>();
                    reverseList.addAll(toateStatiile.keySet());
                    Collections.reverse(reverseList);

                    for(String s : reverseList){
                        int id = toateStatiile.get(s);
                        if(id<= sP && id >= sS){
                            System.out.println(s);
                        }
                    }
                                  }

                System.out.println("Alte sugestii:");

                if(result.size() > 1){

                    for(String i: result){

                        if(!(i.equals(linieDirecta))){

                            System.out.println(i);
                        }
                    }
                }


            }
            else {



           }


        }


    }
}
