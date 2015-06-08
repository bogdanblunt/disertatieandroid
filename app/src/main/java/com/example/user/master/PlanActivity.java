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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


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
           List<String> l1 = new ArrayList<>();
           List<String> l2 = new ArrayList<>();
           List<Integer> l3 = new ArrayList<>();
           List<Integer> l4 = new ArrayList<>();
           List<String> l5 = new ArrayList<>();
           List<String> l6 = new ArrayList<>();
           List<String> l7 = new ArrayList<>();
           List<String> l8 = new ArrayList<>();
           LinkedHashMap<String,Integer> statiiDePlecare = new LinkedHashMap<>();
           List<String> statiiDeSosire = new ArrayList<>();
           Set<String> setLiniiXYZ = new HashSet<>();
           Set<String> statiiPeLiniiP = new HashSet<>();
           Set<String> statiiPeLiniiS = new HashSet<>();
           Set<String> statiiComune = new HashSet<>();
           String st1 = "";
           String st2 = "";
           Integer ok7 = 0;

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
            for (String p: cursorLiniiP){
                List<String> st = new ArrayList<>();
                Cursor c = helper.getStatiiByLinie(p);
                while(c.moveToNext()){
                    st.add(c.getString(0));
                }
                statiiPeLiniiP.addAll(st);

            }
                //for(String s: statiiPeLiniiP) System.out.println(s);

                for (String p: cursorLiniiS){
                    List<String> st = new ArrayList<>();
                    Cursor c = helper.getStatiiByLinie(p);
                    while(c.moveToNext()){
                        st.add(c.getString(0));
                    }
                    statiiPeLiniiS.addAll(st);

                }

                boolean b = false;
                for(String s1 : statiiPeLiniiP)
                    for(String s2 : statiiPeLiniiS){
                        if((s1.equals(s2)) && (!s1.equals(statieP)) && (!s1.equals(statieS))){

                            b = true;
                            statiiComune.add(s1);
                        }
                   }

                if(b==true){

                    for(String stat : statiiComune) {
                        l3.clear();
                        l4.clear();
                        l5.clear();
                        l6.clear();
                        l1=helper.getLiniiByStatie(stat);   //toate liniile care au si statia de legatura


                        for(String z : l1){

                            if(setLiniiXYZ.contains(z))
                            {
                                continue;
                            }

                            Cursor c = helper.getStatiiByLinie(z);
                            while(c.moveToNext()){
                               statiiDePlecare.put(c.getString(0), c.getInt(2));
                            }

                           //iau toate statiile care se gasesc pe liniile de mai sus

                            ArrayList<String> statiiPl = new ArrayList<>();
                            statiiPl.addAll(statiiDePlecare.keySet());

                            for(String r : statiiPl){

                                if(r.equals(statieP)) {

                                    l3.add(helper.getTimpByLinie(z));
                                    l5.add(z);
                                    l7.add(z);

                                }

                                if(r.equals(statieS)){

                                    l4.add(helper.getTimpByLinie(z));
                                    l6.add(z);
                                    l8.add(z);
                                }

                            }

                            setLiniiXYZ.add(z);
                        }

                        Integer suma=1000;
                        Integer se=0;

                        for(Integer r=0;r<l3.size();r++)
                            for(Integer t=0;t<l4.size();t++){

                                se = l3.get(r) +l4.get(t);

                                if(se<suma)
                                {
                                    suma = se;
                                    st1 = l5.get(r);
                                    st2 = l6.get(t);
                                }

                            }
                        if(ok7==0){

                            System.out.println("Se pleaca cu: ");

                            System.out.println(st1);
                            System.out.println("Se trece prin statiile:");
                            LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                            Cursor c2 = helper.getStatiiByLinie(st1);
                            while(c2.moveToNext()){
                                toateStatile.put(c2.getString(0), c2.getInt(2));
                            }

                            int sPs = toateStatile.get(statieP);
                            int sSs = toateStatile.get(stat);

                            if(sSs > sPs) {

                                for(String s : toateStatile.keySet()){
                                    int id = toateStatile.get(s);
                                    if(id<= sSs && id >= sPs){
                                        System.out.println(s);
                                    }
                                }
                            } else {
                                ArrayList<String> reverseList = new ArrayList<>();
                                reverseList.addAll(toateStatile.keySet());
                                Collections.reverse(reverseList);

                                for(String s : reverseList){
                                    int id = toateStatile.get(s);
                                    if(id<= sPs && id >= sSs){
                                        System.out.println(s);
                                    }
                                }
                            }

                           System.out.println("Se schimba in: ");
                           System.out.println(stat);
                           System.out.println("Se ajunge cu: ");
                           System.out.println(st2);

                           System.out.println("Se trece prin statiile: ");

                           LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                           Cursor c3 = helper.getStatiiByLinie(st2);
                            while(c3.moveToNext()){
                                toateStatile2.put(c3.getString(0), c3.getInt(2));
                            }

                            int sPs1 = toateStatile2.get(stat);
                            int sSs1 = toateStatile2.get(statieS);

                            if(sSs1 > sPs1) {

                                for(String s : toateStatile2.keySet()){
                                    int id = toateStatile2.get(s);
                                    if(id<= sSs1 && id >= sPs1){
                                        System.out.println(s);
                                    }
                                }
                            } else {
                                ArrayList<String> reverseList = new ArrayList<>();
                                reverseList.addAll(toateStatile2.keySet());
                                Collections.reverse(reverseList);

                                for(String s : reverseList){
                                    int id = toateStatile2.get(s);
                                    if(id<= sPs1 && id >= sSs1){
                                        System.out.println(s);
                                    }


           }}
            System.out.println("Timpul mediu total de asteptare in statii:"+ suma+"  minute");

            ok7=1;
        }
        }}}}}}


