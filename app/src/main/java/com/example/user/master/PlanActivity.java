package com.example.user.master;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    private String isHead(String statieCurr, String statieP, String statieS) {
        if(statieP.equals(statieCurr) || statieS.equals(statieCurr)) {
            return "<b>" + statieCurr + "</b>";
        }
        return statieCurr;
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
                    String dialogMessage = "";
                    dialogMessage+="Sugestia este: "; System.out.println("Sugestia este:");
                    dialogMessage+=linieDirecta + "<br/>"; System.out.println(linieDirecta);
                    dialogMessage+="Timpul mediu de asteptare in statie: "; System.out.println("Timpul mediu de asteptare in statie");
                    dialogMessage+=aux + "<br/>"; System.out.println(aux);

                    dialogMessage+="Statiile prin care trece: "; System.out.println("Statiile prin care trece: ");

                    Cursor c = helper.getStatiiByLinie(linieDirecta);
                    while(c.moveToNext()){
                        toateStatiile.put(c.getString(0), c.getInt(2));
                    }

                    int sP = toateStatiile.get(statieP);
                    int sS = toateStatiile.get(statieS);
                    boolean firstElem = true;
                    if(sS > sP) {
                        for(String s : toateStatiile.keySet()){
                            int id = toateStatiile.get(s);
                            if(id<= sS && id >= sP){
                                if(firstElem==true) {
                                    dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                } else {
                                    dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    } else {
                        ArrayList<String> reverseList = new ArrayList<>();
                        reverseList.addAll(toateStatiile.keySet());
                        Collections.reverse(reverseList);

                        for(String s : reverseList){
                            int id = toateStatiile.get(s);
                            if(id<= sP && id >= sS){
                                if(firstElem==true) {
                                    dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                } else {
                                    dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    }

                    if(result.size() > 1){
                        dialogMessage+="<br/>";
                        dialogMessage+="Alte sugestii: ";
                        System.out.println("Alte sugestii:");
                        boolean firstAlt = true;
                        for(String i: result){
                            if(!(i.equals(linieDirecta))){
                                if(firstAlt==true) {
                                    dialogMessage+=i; System.out.println(i);
                                } else {
                                    dialogMessage+=", " + i; System.out.println(i);
                                }
                                firstAlt=false;
                            }
                        }
                    }
                    dialogMessage+="<br/>";
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                    builder1.setCancelable(true);
                    builder1.setMessage(Html.fromHtml(dialogMessage));
                    builder1.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                } else {

                    String dialogMessage = "";
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

                                dialogMessage+="Se pleaca cu: "; System.out.println("Se pleaca cu: ");

                                dialogMessage+=st1 + "<br/>"; System.out.println(st1);
                                dialogMessage+="Se trece prin statiile:"; System.out.println("Se trece prin statiile: ");
                                LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                                Cursor c2 = helper.getStatiiByLinie(st1);
                                while(c2.moveToNext()){
                                    toateStatile.put(c2.getString(0), c2.getInt(2));
                                }

                                int sPs = toateStatile.get(statieP);
                                int sSs = toateStatile.get(stat);

                                boolean firstElem = true;
                                if(sSs > sPs) {
                                    for(String s : toateStatile.keySet()){
                                        int id = toateStatile.get(s);

                                        if(id<= sSs && id >= sPs){
                                            if(firstElem==true) {
                                                dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                            } else {
                                                dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile.keySet());
                                    Collections.reverse(reverseList);

                                    for(String s : reverseList){
                                        int id = toateStatile.get(s);
                                        if(id<= sPs && id >= sSs){
                                            if(firstElem==true) {
                                                dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                            } else {
                                                dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                }
                                dialogMessage+= "<br/><br/>";
                                dialogMessage+="Se schimba in: "; System.out.println("Se schimba in: ");
                                dialogMessage+=stat + "<br/>"; System.out.println(stat);
                                dialogMessage+= "<br/><br/>";
                                dialogMessage+="Se ajunge cu: "; System.out.println("Se ajunge cu: ");
                                dialogMessage+=st2 + "<br/>"; System.out.println(st2);

                                dialogMessage+="Se trece prin statiile: "; System.out.println("Se trece prin statiile: ");

                                LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                                Cursor c3 = helper.getStatiiByLinie(st2);
                                while(c3.moveToNext()){
                                    toateStatile2.put(c3.getString(0), c3.getInt(2));
                                }

                                int sPs1 = toateStatile2.get(stat);
                                int sSs1 = toateStatile2.get(statieS);
                                firstElem = true;
                                if(sSs1 > sPs1) {

                                    for(String s : toateStatile2.keySet()){
                                        int id = toateStatile2.get(s);
                                        if(id<= sSs1 && id >= sPs1){
                                            if(firstElem==true) {
                                                dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                            } else {
                                                dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile2.keySet());
                                    Collections.reverse(reverseList);

                                    for(String s : reverseList){
                                        int id = toateStatile2.get(s);
                                        if(id<= sPs1 && id >= sSs1){
                                            if(firstElem==true) {
                                                dialogMessage+=isHead(s, statieP, statieS); System.out.println(s);
                                            } else {
                                                dialogMessage+=", " + isHead(s, statieP, statieS); System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                }
                                dialogMessage+= "<br/><br/>";
                                dialogMessage+="Timpul mediu total de asteptare in statii: "+ suma+"  minute"; System.out.println("Timpul mediu total de asteptare in statii: "+ suma+"  minute");

                                dialogMessage+="<br/>";
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                                builder1.setCancelable(true);
                                builder1.setMessage(Html.fromHtml(dialogMessage));
                                builder1.setPositiveButton("Close",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();

                                ok7=1;
                            }
                        }
                    }
                }
            }
        }



    public void planificaAvansat(View view) {

        Spinner spinnerSimplu1 = (Spinner) findViewById(R.id.plecareAvansat_spinner);
        Spinner spinnerSimplu2 = (Spinner) findViewById(R.id.sosireAvansat_spinner);
        Spinner spinnerSimplu3 = (Spinner) findViewById(R.id.evita_spinner3);
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroupPrefer);
        RadioGroup rg2 = (RadioGroup) findViewById(R.id.radioGroupRapid);
        String statieP = spinnerSimplu1.getSelectedItem().toString();
        String statieS = spinnerSimplu2.getSelectedItem().toString();
        String linieEvitata = spinnerSimplu3.getSelectedItem().toString();
        int idRB1 = rg1.getCheckedRadioButtonId();
        int idRB2 = rg2.getCheckedRadioButtonId();

        String mijlocEvitat = "";

        if(idRB2==(R.id.tramvaiRadio)) mijlocEvitat = "tramvai";

        else if(idRB2==(R.id.troleibuzRadio))  mijlocEvitat = "troleibuz";
        else if(idRB2==(R.id.autobuzRadio)) mijlocEvitat = "autobuz";

        if (statieP.equals(statieS)) {

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
        } else {


            // voi avea de tratat in total 8 cazuri
            // tratez cazul in care nu doresc sa evit nicio linie, vreau linie directa si nu evit niciun mijloc

            if ((linieEvitata.equals("nu doresc sa evit")) && (idRB1 == R.id.unicaLinieRadio) && (idRB2 == R.id.nimicRadio)) {

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
                LinkedHashMap<String, Integer> statiiDePlecare = new LinkedHashMap<>();
                List<String> statiiDeSosire = new ArrayList<>();
                Set<String> setLiniiXYZ = new HashSet<>();
                Set<String> statiiPeLiniiP = new HashSet<>();
                Set<String> statiiPeLiniiS = new HashSet<>();
                Set<String> statiiComune = new HashSet<>();
                String st1 = "";
                String st2 = "";
                Integer ok7 = 0;

                int ok = 0;
                int aux = 1000;
                String linieDirecta = "";

                {
                    for (String liniiP : cursorLiniiP) {
                        for (String liniiS : cursorLiniiS) {

                            if (liniiP.equals(liniiS)) {
                                ok = 1;
                                result.add(liniiP);
                                int timp = helper.getTimpByLinie(liniiP);
                                if (timp < aux) {
                                    aux = timp;
                                    linieDirecta = liniiP;
                                }
                            }
                        }
                    }

                    if (ok == 1) {

                        LinkedHashMap<String, Integer> toateStatiile = new LinkedHashMap<>();
                        String dialogMessage = "";
                        dialogMessage += "Sugestia este: ";
                        System.out.println("Sugestia este:");
                        dialogMessage += linieDirecta + "<br/>";
                        System.out.println(linieDirecta);
                        dialogMessage += "Timpul mediu de asteptare in statie: ";
                        System.out.println("Timpul mediu de asteptare in statie");
                        dialogMessage += aux + "<br/>";
                        System.out.println(aux);

                        dialogMessage += "Statiile prin care trece: ";
                        System.out.println("Statiile prin care trece: ");

                        Cursor c = helper.getStatiiByLinie(linieDirecta);
                        while (c.moveToNext()) {
                            toateStatiile.put(c.getString(0), c.getInt(2));
                        }

                        int sP = toateStatiile.get(statieP);
                        int sS = toateStatiile.get(statieS);
                        boolean firstElem = true;
                        if (sS > sP) {
                            for (String s : toateStatiile.keySet()) {
                                int id = toateStatiile.get(s);
                                if (id <= sS && id >= sP) {
                                    if (firstElem == true) {
                                        dialogMessage += isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    } else {
                                        dialogMessage += ", " + isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    }
                                    firstElem = false;
                                }
                            }
                        } else {
                            ArrayList<String> reverseList = new ArrayList<>();
                            reverseList.addAll(toateStatiile.keySet());
                            Collections.reverse(reverseList);

                            for (String s : reverseList) {
                                int id = toateStatiile.get(s);
                                if (id <= sP && id >= sS) {
                                    if (firstElem == true) {
                                        dialogMessage += isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    } else {
                                        dialogMessage += ", " + isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    }
                                    firstElem = false;
                                }
                            }
                        }

                        if (result.size() > 1) {
                            dialogMessage += "<br/>";
                            dialogMessage += "Alte sugestii: ";
                            System.out.println("Alte sugestii:");
                            boolean firstAlt = true;
                            for (String i : result) {
                                if (!(i.equals(linieDirecta))) {
                                    if (firstAlt == true) {
                                        dialogMessage += i;
                                        System.out.println(i);
                                    } else {
                                        dialogMessage += ", " + i;
                                        System.out.println(i);
                                    }
                                    firstAlt = false;
                                }
                            }
                        }
                        dialogMessage += "<br/>";
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                        builder1.setCancelable(true);
                        builder1.setMessage(Html.fromHtml(dialogMessage));
                        builder1.setPositiveButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }


                    if (ok == 0) {
                        for (String p : cursorLiniiP) {
                            List<String> st = new ArrayList<>();
                            Cursor c = helper.getStatiiByLinie(p);
                            while (c.moveToNext()) {
                                st.add(c.getString(0));
                            }
                            statiiPeLiniiP.addAll(st);

                        }
                    //for(String s: statiiPeLiniiP) System.out.println(s);

                    for (String p : cursorLiniiS) {
                        List<String> st = new ArrayList<>();
                        Cursor c = helper.getStatiiByLinie(p);
                        while (c.moveToNext()) {
                            st.add(c.getString(0));
                        }
                        statiiPeLiniiS.addAll(st);

                    }

                    boolean b = false;
                    for (String s1 : statiiPeLiniiP)
                        for (String s2 : statiiPeLiniiS) {
                            if ((s1.equals(s2)) && (!s1.equals(statieP)) && (!s1.equals(statieS))) {

                                b = true;
                                statiiComune.add(s1);
                            }
                        }

                    if (b == true) {

                        for (String stat : statiiComune) {
                            l3.clear();
                            l4.clear();
                            l5.clear();
                            l6.clear();
                            l1 = helper.getLiniiByStatie(stat);   //toate liniile care au si statia de legatura


                            for (String z : l1) {

                                if (setLiniiXYZ.contains(z)) {
                                    continue;
                                }

                                Cursor c = helper.getStatiiByLinie(z);
                                while (c.moveToNext()) {
                                    statiiDePlecare.put(c.getString(0), c.getInt(2));
                                }

                                //iau toate statiile care se gasesc pe liniile de mai sus

                                ArrayList<String> statiiPl = new ArrayList<>();
                                statiiPl.addAll(statiiDePlecare.keySet());

                                for (String r : statiiPl) {

                                    if (r.equals(statieP)) {

                                        l3.add(helper.getTimpByLinie(z));
                                        l5.add(z);
                                        l7.add(z);

                                    }

                                    if (r.equals(statieS)) {

                                        l4.add(helper.getTimpByLinie(z));
                                        l6.add(z);
                                        l8.add(z);
                                    }

                                }

                                setLiniiXYZ.add(z);
                            }

                            Integer suma = 1000;
                            Integer se = 0;

                            for (Integer r = 0; r < l3.size(); r++)
                                for (Integer t = 0; t < l4.size(); t++) {

                                    se = l3.get(r) + l4.get(t);

                                    if (se < suma) {
                                        suma = se;
                                        st1 = l5.get(r);
                                        st2 = l6.get(t);
                                    }

                                }
                            if (ok7 == 0) {
                                String dialogMessage = "";
                                dialogMessage+="Se pleaca cu: ";
                                System.out.println("Se pleaca cu: ");
                                dialogMessage+=st1 + "<br/>";
                                System.out.println(st1);
                                dialogMessage+="Se trece prin statiile: ";
                                System.out.println("Se trece prin statiile: ");
                                LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                                Cursor c2 = helper.getStatiiByLinie(st1);
                                while (c2.moveToNext()) {
                                    toateStatile.put(c2.getString(0), c2.getInt(2));
                                }

                                int sPs = toateStatile.get(statieP);
                                int sSs = toateStatile.get(stat);

                                boolean firstElem = true;
                                if (sSs > sPs) {

                                    for (String s : toateStatile.keySet()) {
                                        int id = toateStatile.get(s);
                                        if (id <= sSs && id >= sPs) {
                                            if (firstElem == true) {
                                                dialogMessage += isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            } else {
                                                dialogMessage += ", " + isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile.keySet());
                                    Collections.reverse(reverseList);

                                    for (String s : reverseList) {
                                        int id = toateStatile.get(s);
                                        if (id <= sPs && id >= sSs) {
                                            if (firstElem == true) {
                                                dialogMessage += isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            } else {
                                                dialogMessage += ", " + isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                }
                                dialogMessage+= "<br/><br/>";
                                dialogMessage+= "Se schimba in: ";
                                System.out.println("Se schimba in: ");
                                System.out.println(stat);
                                dialogMessage+= "<br/><br/>";
                                dialogMessage+= "Se ajunge cu: ";
                                System.out.println("Se ajunge cu: ");
                                dialogMessage+= st2 + "<br/>";
                                System.out.println(st2);

                                dialogMessage+= "Se trece prin statiile: ";
                                System.out.println("Se trece prin statiile: ");

                                LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                                Cursor c3 = helper.getStatiiByLinie(st2);
                                while (c3.moveToNext()) {
                                    toateStatile2.put(c3.getString(0), c3.getInt(2));
                                }

                                int sPs1 = toateStatile2.get(stat);
                                int sSs1 = toateStatile2.get(statieS);

                                if (sSs1 > sPs1) {

                                    for (String s : toateStatile2.keySet()) {
                                        int id = toateStatile2.get(s);
                                        if (id <= sSs1 && id >= sPs1) {
                                            if (firstElem == true) {
                                                dialogMessage += isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            } else {
                                                dialogMessage += ", " + isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            }
                                            firstElem = false;
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile2.keySet());
                                    Collections.reverse(reverseList);

                                    for (String s : reverseList) {
                                        int id = toateStatile2.get(s);
                                        if (id <= sPs1 && id >= sSs1) {
                                            if (firstElem == true) {
                                                dialogMessage += isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            } else {
                                                dialogMessage += ", " + isHead(s, statieP, statieS);
                                                System.out.println(s);
                                            }
                                            firstElem = false;
                                        }


                                    }
                                }
                                dialogMessage+= "Timpul mediu total de asteptare in statii:" + suma + "  minute";
                                System.out.println("Timpul mediu total de asteptare in statii:" + suma + "  minute");

                                ok7 = 1;

                                dialogMessage += "<br/>";
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                                builder1.setCancelable(true);
                                builder1.setMessage(Html.fromHtml(dialogMessage));
                                builder1.setPositiveButton("Close",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();
                            }}


                            }
                        }
                    }
                }
            }

            //tratez cazul in care doresc sa evit o linie, calatoresc pe o singua linie si nu evit niciun mijloc

            if ((!linieEvitata.equals("nu doresc sa evit")) && (idRB1 == R.id.unicaLinieRadio) && (idRB2 == R.id.nimicRadio)) {

                System.out.println(linieEvitata);

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
                LinkedHashMap<String, Integer> statiiDePlecare = new LinkedHashMap<>();
                List<String> statiiDeSosire = new ArrayList<>();
                Set<String> setLiniiXYZ = new HashSet<>();
                Set<String> statiiPeLiniiP = new HashSet<>();
                Set<String> statiiPeLiniiS = new HashSet<>();
                Set<String> statiiComune = new HashSet<>();
                String st1 = "";
                String st2 = "";
                Integer ok7 = 0;
                String vb2 = "";
                int vb3 = 0;
                int ok2 = 0;
                int ok4 = 0;
                int ok3 = 0;
                int ok5 = 0;
                int ok6 = 0;

                int ok = 0;
                int aux = 1000;
                String linieDirecta = "";

                for (String liniiP : cursorLiniiP) {
                    for (String liniiS : cursorLiniiS) {

                        if (liniiP.equals(liniiS)) {
                            if (liniiP.equals(linieEvitata)) {
                                vb2 = liniiP;
                                ok = 1;
                            } else {
                                vb3 = 1;
                                result.add(liniiP);
                                int timp = helper.getTimpByLinie(liniiP);
                                if (timp < aux) {
                                    aux = timp;
                                    linieDirecta = liniiP;
                                }
                            }
                        }
                    }
                }

                // situatia in care poate ca linia evitata face parte din sugestii, dar mai sunt si altele de recomandat
                // se gaseste linie directa

                if (vb3 == 1) {

                    LinkedHashMap<String, Integer> toateStatiile = new LinkedHashMap<>();
                    String dialogMessage = "";
                    dialogMessage += "Sugestia este: ";
                    System.out.println("Sugestia este:");
                    dialogMessage += linieDirecta + "<br/>";
                    System.out.println(linieDirecta);
                    dialogMessage += "Timpul mediu de asteptare in statie: ";
                    System.out.println("Timpul mediu de asteptare in statie");
                    dialogMessage += aux + "<br/>";
                    System.out.println(aux);

                    dialogMessage += "Statiile prin care trece: ";
                    System.out.println("Statiile prin care trece: ");

                    Cursor c = helper.getStatiiByLinie(linieDirecta);
                    while (c.moveToNext()) {
                        toateStatiile.put(c.getString(0), c.getInt(2));
                    }

                    int sP = toateStatiile.get(statieP);
                    int sS = toateStatiile.get(statieS);
                    boolean firstElem = true;
                    if (sS > sP) {
                        for (String s : toateStatiile.keySet()) {
                            int id = toateStatiile.get(s);
                            if (id <= sS && id >= sP) {
                                if (firstElem == true) {
                                    dialogMessage += isHead(s, statieP, statieS);
                                    System.out.println(s);
                                } else {
                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                    System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    } else {
                        ArrayList<String> reverseList = new ArrayList<>();
                        reverseList.addAll(toateStatiile.keySet());
                        Collections.reverse(reverseList);

                        for (String s : reverseList) {
                            int id = toateStatiile.get(s);
                            if (id <= sP && id >= sS) {
                                if (firstElem == true) {
                                    dialogMessage += isHead(s, statieP, statieS);
                                    System.out.println(s);
                                } else {
                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                    System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    }

                    if (result.size() > 1) {
                        dialogMessage += "<br/>";
                        dialogMessage += "Alte sugestii: ";
                        System.out.println("Alte sugestii:");
                        boolean firstAlt = true;
                        for (String i : result) {
                            if (!(i.equals(linieDirecta))) {
                                if (firstAlt == true) {
                                    dialogMessage += i;
                                    System.out.println(i);
                                } else {
                                    dialogMessage += ", " + i;
                                    System.out.println(i);
                                }
                                firstAlt = false;
                            }
                        }
                    }
                    dialogMessage += "<br/>";
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                    builder1.setCancelable(true);
                    builder1.setMessage(Html.fromHtml(dialogMessage));
                    builder1.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                // cazul in care se gaseste decat o linie directa si aceea e cea de evitat

                if (ok == 1 && vb3 == 0) {
                    String dialogMessage = "";
                    dialogMessage += "Singura legatura directa este linia pe care doresti sa o eviti!";
                    dialogMessage += "<br/><br/>";
                    System.out.println("Singura legatura directa este linia pe care doresti sa o eviti!");
                    dialogMessage += "Aceasta este: <b>" + linieEvitata + "</b>";
                    System.out.println("Aceasta este: " + linieEvitata);
                    dialogMessage += "<br/><br/>";
                    dialogMessage += "Pentru noi sugestii, intoarce-te la formularul de calatorie.";
                    System.out.println("Pentru noi sugestii, intoarce-te la formularul de calatorie.");
                    dialogMessage += "<br/>";
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                    builder1.setCancelable(true);
                    builder1.setMessage(Html.fromHtml(dialogMessage));
                    builder1.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }

                // cazul in care nu gasesc linie directa, chiar daca as include linia evitata

                if (ok == 0 && vb3 == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                    builder1.setCancelable(true);
                    builder1.setMessage(Html.fromHtml("Nu exista legatura directa!"));
                    builder1.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                    System.out.println("Nu exista legatura directa!");

                    int timp = 1000;


                    if (ok == 0)
                        for (String p : cursorLiniiP) {
                            List<String> st = new ArrayList<>();
                            Cursor c = helper.getStatiiByLinie(p);
                            while (c.moveToNext()) {
                                st.add(c.getString(0));
                            }
                            statiiPeLiniiP.addAll(st);
                        }

                    for (String p : cursorLiniiS) {
                        List<String> st = new ArrayList<>();
                        Cursor c = helper.getStatiiByLinie(p);
                        while (c.moveToNext()) {
                            st.add(c.getString(0));
                        }
                        statiiPeLiniiS.addAll(st);

                    }

                    boolean b = false;
                    for (String s1 : statiiPeLiniiP)
                        for (String s2 : statiiPeLiniiS) {
                            if ((s1.equals(s2)) && (!s1.equals(statieP)) && (!s1.equals(statieS))) {

                                b = true;
                                statiiComune.add(s1);
                            }
                        }

                    if (b == true) {

                        for (String stat : statiiComune) {
                            l3.clear();
                            l4.clear();
                            l5.clear();
                            l6.clear();
                            l1 = helper.getLiniiByStatie(stat);   //toate liniile care au si statia de legatura


                            for (String z : l1) {

                                if (setLiniiXYZ.contains(z)) {
                                    continue;
                                }


                                Cursor c = helper.getStatiiByLinie(z);
                                while (c.moveToNext()) {
                                    statiiDePlecare.put(c.getString(0), c.getInt(2));
                                }

                                //iau toate statiile care se gasesc pe liniile de mai sus

                                ArrayList<String> statiiPl = new ArrayList<>();
                                statiiPl.addAll(statiiDePlecare.keySet());

                                for (String r : statiiPl) {

                                    if (r.equals(statieP)) {
                                        if (z.equals(linieEvitata)) {
                                            ok2 = 1;
                                            vb2 = z;
                                        } else {
                                            ok4 = 1;
                                            l3.add(helper.getTimpByLinie(z));
                                            l5.add(z);
                                            l7.add(z);

                                        }
                                    }

                                    if (r.equals(statieS)) {
                                        if (z.equals(linieEvitata)) {
                                            ok3 = 1;
                                            vb2 = z;
                                        }
                                        ok5 = 1;
                                        l4.add(helper.getTimpByLinie(z));
                                        l6.add(z);
                                        l8.add(z);
                                    }

                                }

                                setLiniiXYZ.add(z);
                            }

                            if (ok4 == 1 && ok5 == 1) {
                                ok6 = 1;
                                Integer suma = 1000;
                                Integer se = 0;

                                for (Integer r = 0; r < l3.size(); r++)
                                    for (Integer t = 0; t < l4.size(); t++) {

                                        se = l3.get(r) + l4.get(t);

                                        if (se < suma) {
                                            suma = se;
                                            st1 = l5.get(r);
                                            st2 = l6.get(t);
                                        }

                                    }
                                ok4 = 0;
                                ok5 = 0;

                                if (ok7 == 0 && ((!st1.equals(linieEvitata))) && (!st2.equals(linieEvitata)))

                                {
                                    String dialogMessage = "";
                                    dialogMessage += "Se pleaca cu: ";
                                    System.out.println("Se pleaca cu: ");
                                    dialogMessage += st1 + "<br/>";
                                    System.out.println(st1);
                                    dialogMessage += "Se trece prin statiile: ";
                                    System.out.println("Se trece prin statiile: ");
                                    LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                                    Cursor c2 = helper.getStatiiByLinie(st1);
                                    while (c2.moveToNext()) {
                                        toateStatile.put(c2.getString(0), c2.getInt(2));
                                    }

                                    int sPs = toateStatile.get(statieP);
                                    int sSs = toateStatile.get(stat);
                                    boolean firstElem = true;
                                    if (sSs > sPs) {

                                        for (String s : toateStatile.keySet()) {
                                            int id = toateStatile.get(s);
                                            if (id <= sSs && id >= sPs) {
                                                if (firstElem == true) {
                                                    dialogMessage += isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                } else {
                                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                }
                                                firstElem = false;
                                            }
                                        }
                                    } else {
                                        ArrayList<String> reverseList = new ArrayList<>();
                                        reverseList.addAll(toateStatile.keySet());
                                        Collections.reverse(reverseList);

                                        for (String s : reverseList) {
                                            int id = toateStatile.get(s);
                                            if (id <= sPs && id >= sSs) {
                                                if (firstElem == true) {
                                                    dialogMessage += isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                } else {
                                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                }
                                                firstElem = false;
                                            }
                                        }
                                    }
                                    dialogMessage += "Se schimba in: ";
                                    System.out.println("Se schimba in: ");
                                    dialogMessage += stat + "<br/>";
                                    System.out.println(stat);
                                    dialogMessage += "Se ajunge cu: ";
                                    System.out.println("Se ajunge cu: ");
                                    dialogMessage += st2 + "<br/>";
                                    System.out.println(st2);

                                    dialogMessage += "Se trece prin statiile: ";
                                    System.out.println("Se trece prin statiile: ");

                                    LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                                    Cursor c3 = helper.getStatiiByLinie(st2);
                                    while (c3.moveToNext()) {
                                        toateStatile2.put(c3.getString(0), c3.getInt(2));
                                    }

                                    int sPs1 = toateStatile2.get(stat);
                                    int sSs1 = toateStatile2.get(statieS);
                                    firstElem = true;
                                    if (sSs1 > sPs1) {

                                        for (String s : toateStatile2.keySet()) {
                                            int id = toateStatile2.get(s);
                                            if (id <= sSs1 && id >= sPs1) {
                                                if (firstElem == true) {
                                                    dialogMessage += isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                } else {
                                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                                    System.out.println(s);
                                                }
                                                firstElem = false;
                                            }
                                        }
                                    } else {
                                        ArrayList<String> reverseList = new ArrayList<>();
                                        reverseList.addAll(toateStatile2.keySet());
                                        Collections.reverse(reverseList);

                                        for (String s : reverseList) {
                                            int id = toateStatile2.get(s);
                                            if (id <= sPs1 && id >= sSs1) {
                                                    if (firstElem == true) {
                                                        dialogMessage += isHead(s, statieP, statieS);
                                                        System.out.println(s);
                                                    } else {
                                                        dialogMessage += ", " + isHead(s, statieP, statieS);
                                                        System.out.println(s);
                                                    }
                                                    firstElem = false;
                                            }


                                        }
                                    }
                                    dialogMessage += "Timpul mediu total de asteptare in statii:" + suma + "  minute";
                                    System.out.println("Timpul mediu total de asteptare in statii:" + suma + "  minute");

                                    ok7 = 1;
                                    dialogMessage += "<br/>";
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(PlanActivity.this);
                                    builder2.setCancelable(true);
                                    builder2.setMessage(Html.fromHtml(dialogMessage));
                                    builder2.setPositiveButton("Close",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                                    AlertDialog alert22 = builder2.create();
                                    alert22.show();
                                }
                            }
                        }
                        if (ok6 == 0) {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(PlanActivity.this);
                            builder3.setCancelable(true);
                            builder3.setMessage(Html.fromHtml("Nu se poate gasi traseu care sa evite linia selectata!"));
                            builder3.setPositiveButton("Close",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert33 = builder3.create();
                            alert33.show();
                            System.out.println("Nu se poate gasi traseu care sa evite linia selectata!");
                        }
                    }
                }


            }
            //tratez cazul in care nu evit nicio linie, vreau linie directa si evit un mijloc

            if ((linieEvitata.equals("nu doresc sa evit")) && (idRB1 == R.id.unicaLinieRadio) && (!(idRB2 == R.id.nimicRadio)))

            {

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
                LinkedHashMap<String, Integer> statiiDePlecare = new LinkedHashMap<>();
                List<String> statiiDeSosire = new ArrayList<>();
                Set<String> setLiniiXYZ = new HashSet<>();
                Set<String> statiiPeLiniiP = new HashSet<>();
                Set<String> statiiPeLiniiS = new HashSet<>();
                Set<String> statiiComune = new HashSet<>();
                String st1 = "";
                String st2 = "";
                Integer ok7 = 0;

                int ok = 0;
                int aux = 1000;
                String linieDirecta = "";

                    for (String liniiP : cursorLiniiP) {
                        for (String liniiS : cursorLiniiS) {

                            if (liniiP.equals(liniiS)) {
                                ok = 1;
                                result.add(liniiP);
                                int timp = helper.getTimpByLinie(liniiP);
                                if (timp < aux) {
                                    aux = timp;
                                    linieDirecta = liniiP;
                                }
                            }
                        }
                    }

                    if (ok == 1) {

                        LinkedHashMap<String, Integer> toateStatiile = new LinkedHashMap<>();
                        String dialogMessage = "";
                        dialogMessage += "Sugestia este: ";
                        System.out.println("Sugestia este:");

                        dialogMessage += linieDirecta + "<br/>";
                        System.out.println(linieDirecta);
                        dialogMessage += "Timpul mediu de asteptare in statie: ";
                        System.out.println("Timpul mediu de asteptare in statie");

                        if(Integer.parseInt(linieDirecta) >=1 && Integer.parseInt(linieDirecta)<=60 && mijlocEvitat.equals("tramvai"))
                            System.out.println("Atentie, este "+ mijlocEvitat );
                        if(Integer.parseInt(linieDirecta) >61 && Integer.parseInt(linieDirecta)<=99 && mijlocEvitat.equals("troleibuz"))
                            System.out.println("Atentie, este "+ mijlocEvitat );
                        if(Integer.parseInt(linieDirecta) >100 && Integer.parseInt(linieDirecta)<800 && mijlocEvitat.equals("autobuz"))
                            System.out.println("Atentie, este "+ mijlocEvitat );
                        dialogMessage += aux + "<br/>";
                        System.out.println(aux);

                        dialogMessage += "Statiile prin care trece: ";
                        System.out.println("Statiile prin care trece: ");

                        Cursor c = helper.getStatiiByLinie(linieDirecta);
                        while (c.moveToNext()) {
                            toateStatiile.put(c.getString(0), c.getInt(2));
                        }

                        int sP = toateStatiile.get(statieP);
                        int sS = toateStatiile.get(statieS);
                        boolean firstElem = true;
                        if (sS > sP) {
                            for (String s : toateStatiile.keySet()) {
                                int id = toateStatiile.get(s);
                                if (id <= sS && id >= sP) {
                                    if (firstElem == true) {
                                        dialogMessage += isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    } else {
                                        dialogMessage += ", " + isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    }
                                    firstElem = false;
                                }
                            }
                        } else {
                            ArrayList<String> reverseList = new ArrayList<>();
                            reverseList.addAll(toateStatiile.keySet());
                            Collections.reverse(reverseList);

                            for (String s : reverseList) {
                                int id = toateStatiile.get(s);
                                if (id <= sP && id >= sS) {
                                    if (firstElem == true) {
                                        dialogMessage += isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    } else {
                                        dialogMessage += ", " + isHead(s, statieP, statieS);
                                        System.out.println(s);
                                    }
                                    firstElem = false;
                                }
                            }
                        }

                        if (result.size() > 1) {
                            dialogMessage += "<br/>";
                            dialogMessage += "Alte sugestii: ";
                            System.out.println("Alte sugestii:");
                            boolean firstAlt = true;
                            for (String i : result) {
                                if (!(i.equals(linieDirecta))) {
                                    if (firstAlt == true) {
                                        dialogMessage += i;
                                        System.out.println(i);

                                    } else {
                                        dialogMessage += ", " + i;
                                        System.out.println(i);
                                    }
                                    firstAlt = false;
                                }
                            }
                        }
                        dialogMessage += "<br/>";
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                        builder1.setCancelable(true);
                        builder1.setMessage(Html.fromHtml(dialogMessage));
                        builder1.setPositiveButton("Close",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();




                    }


                    if (ok == 0){
                        for (String p : cursorLiniiP) {
                            List<String> st = new ArrayList<>();
                            Cursor c = helper.getStatiiByLinie(p);
                            while (c.moveToNext()) {
                                st.add(c.getString(0));
                            }
                            statiiPeLiniiP.addAll(st);

                        }
                    //for(String s: statiiPeLiniiP) System.out.println(s);

                    for (String p : cursorLiniiS) {
                        List<String> st = new ArrayList<>();
                        Cursor c = helper.getStatiiByLinie(p);
                        while (c.moveToNext()) {
                            st.add(c.getString(0));
                        }
                        statiiPeLiniiS.addAll(st);

                    }

                    boolean b = false;
                    for (String s1 : statiiPeLiniiP)
                        for (String s2 : statiiPeLiniiS) {
                            if ((s1.equals(s2)) && (!s1.equals(statieP)) && (!s1.equals(statieS))) {

                                b = true;
                                statiiComune.add(s1);
                            }
                        }

                    if (b == true) {

                        for (String stat : statiiComune) {
                            l3.clear();
                            l4.clear();
                            l5.clear();
                            l6.clear();
                            l1 = helper.getLiniiByStatie(stat);   //toate liniile care au si statia de legatura


                            for (String z : l1) {

                                if (setLiniiXYZ.contains(z)) {
                                    continue;
                                }

                                Cursor c = helper.getStatiiByLinie(z);
                                while (c.moveToNext()) {
                                    statiiDePlecare.put(c.getString(0), c.getInt(2));
                                }

                                //iau toate statiile care se gasesc pe liniile de mai sus

                                ArrayList<String> statiiPl = new ArrayList<>();
                                statiiPl.addAll(statiiDePlecare.keySet());

                                for (String r : statiiPl) {

                                    if (r.equals(statieP)) {

                                        l3.add(helper.getTimpByLinie(z));
                                        l5.add(z);
                                        l7.add(z);

                                    }

                                    if (r.equals(statieS)) {

                                        l4.add(helper.getTimpByLinie(z));
                                        l6.add(z);
                                        l8.add(z);
                                    }

                                }

                                setLiniiXYZ.add(z);
                            }

                            Integer suma = 1000;
                            Integer se = 0;

                            for (Integer r = 0; r < l3.size(); r++)
                                for (Integer t = 0; t < l4.size(); t++) {

                                    se = l3.get(r) + l4.get(t);

                                    if (se < suma) {
                                        suma = se;
                                        st1 = l5.get(r);
                                        st2 = l6.get(t);
                                    }

                                }
                            if (ok7 == 0) {

                                System.out.println("Se pleaca cu: ");

                                System.out.println(st1);

                                if(Integer.parseInt(st1) >=1 && Integer.parseInt(st1)<=60 && mijlocEvitat.equals("tramvai"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );
                                if(Integer.parseInt(st1) >61 && Integer.parseInt(st1)<=99 && mijlocEvitat.equals("troleibuz"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );
                                if(Integer.parseInt(st1) >100 && Integer.parseInt(st1)<800 && mijlocEvitat.equals("autobuz"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );
                                System.out.println("Se trece prin statiile:");
                                LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                                Cursor c2 = helper.getStatiiByLinie(st1);
                                while (c2.moveToNext()) {
                                    toateStatile.put(c2.getString(0), c2.getInt(2));
                                }

                                int sPs = toateStatile.get(statieP);
                                int sSs = toateStatile.get(stat);

                                if (sSs > sPs) {

                                    for (String s : toateStatile.keySet()) {
                                        int id = toateStatile.get(s);
                                        if (id <= sSs && id >= sPs) {
                                            System.out.println(s);
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile.keySet());
                                    Collections.reverse(reverseList);

                                    for (String s : reverseList) {
                                        int id = toateStatile.get(s);
                                        if (id <= sPs && id >= sSs) {
                                            System.out.println(s);
                                        }
                                    }
                                }

                                System.out.println("Se schimba in: ");
                                System.out.println(stat);
                                System.out.println("Se ajunge cu: ");
                                System.out.println(st2);

                                if(Integer.parseInt(st2) >=1 && Integer.parseInt(st2)<=60 && mijlocEvitat.equals("tramvai"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );
                                if(Integer.parseInt(st2) >61 && Integer.parseInt(st2)<=99 && mijlocEvitat.equals("troleibuz"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );
                                if(Integer.parseInt(st2) >100 && Integer.parseInt(st2)<800 && mijlocEvitat.equals("autobuz"))
                                    System.out.println("Atentie, este "+ mijlocEvitat );

                                System.out.println("Se trece prin statiile: ");

                                LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                                Cursor c3 = helper.getStatiiByLinie(st2);
                                while (c3.moveToNext()) {
                                    toateStatile2.put(c3.getString(0), c3.getInt(2));
                                }

                                int sPs1 = toateStatile2.get(stat);
                                int sSs1 = toateStatile2.get(statieS);

                                if (sSs1 > sPs1) {

                                    for (String s : toateStatile2.keySet()) {
                                        int id = toateStatile2.get(s);
                                        if (id <= sSs1 && id >= sPs1) {
                                            System.out.println(s);
                                        }
                                    }
                                } else {
                                    ArrayList<String> reverseList = new ArrayList<>();
                                    reverseList.addAll(toateStatile2.keySet());
                                    Collections.reverse(reverseList);

                                    for (String s : reverseList) {
                                        int id = toateStatile2.get(s);
                                        if (id <= sPs1 && id >= sSs1) {
                                            System.out.println(s);
                                        }


                                    }
                                }
                                System.out.println("Timpul mediu total de asteptare in statii:" + suma + "  minute");

                                ok7 = 1;
                            }}
                        }
                    }
    }

            // tratez cazul in care evit o linie, caut linia directa, evit un mijloc

            if ((!linieEvitata.equals("nu doresc sa evit")) && (idRB1 == R.id.unicaLinieRadio) && (!(idRB2 == R.id.nimicRadio))) {

                System.out.println(linieEvitata);

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
                LinkedHashMap<String, Integer> statiiDePlecare = new LinkedHashMap<>();
                List<String> statiiDeSosire = new ArrayList<>();
                Set<String> setLiniiXYZ = new HashSet<>();
                Set<String> statiiPeLiniiP = new HashSet<>();
                Set<String> statiiPeLiniiS = new HashSet<>();
                Set<String> statiiComune = new HashSet<>();
                String st1 = "";
                String st2 = "";
                Integer ok7 = 0;
                String vb2 = "";
                int vb3 = 0;
                int ok2 = 0;
                int ok4 = 0;
                int ok3 = 0;
                int ok5 = 0;
                int ok6 = 0;

                int ok = 0;
                int aux = 1000;
                String linieDirecta = "";

                for (String liniiP : cursorLiniiP) {
                    for (String liniiS : cursorLiniiS) {

                        if (liniiP.equals(liniiS)) {
                            if (liniiP.equals(linieEvitata)) {
                                vb2 = liniiP;
                                ok = 1;
                            } else {
                                vb3 = 1;
                                result.add(liniiP);
                                int timp = helper.getTimpByLinie(liniiP);
                                if (timp < aux) {
                                    aux = timp;
                                    linieDirecta = liniiP;
                                }
                            }
                        }
                    }
                }

                // situatia in care poate ca linia evitata face parte din sugestii, dar mai sunt si altele de recomandat
                // se gaseste linie directa

                if (vb3 == 1) {

                    LinkedHashMap<String, Integer> toateStatiile = new LinkedHashMap<>();
                    String dialogMessage = "";
                    dialogMessage += "Sugestia este: ";
                    System.out.println("Sugestia este:");
                    dialogMessage += linieDirecta + "<br/>";
                    System.out.println(linieDirecta);
                    if(Integer.parseInt(linieDirecta) >=1 && Integer.parseInt(linieDirecta)<=60 && mijlocEvitat.equals("tramvai"))
                        System.out.println("Atentie, este "+ mijlocEvitat );
                    if(Integer.parseInt(linieDirecta) >61 && Integer.parseInt(linieDirecta)<=99 && mijlocEvitat.equals("troleibuz"))
                        System.out.println("Atentie, este "+ mijlocEvitat );
                    if(Integer.parseInt(linieDirecta) >100 && Integer.parseInt(linieDirecta)<800 && mijlocEvitat.equals("autobuz"))
                        System.out.println("Atentie, este "+ mijlocEvitat );
                    dialogMessage += "Timpul mediu de asteptare in statie: ";
                    System.out.println("Timpul mediu de asteptare in statie");
                    dialogMessage += aux + "<br/>";
                    System.out.println(aux);

                    dialogMessage += "Statiile prin care trece: ";
                    System.out.println("Statiile prin care trece: ");

                    Cursor c = helper.getStatiiByLinie(linieDirecta);
                    while (c.moveToNext()) {
                        toateStatiile.put(c.getString(0), c.getInt(2));
                    }

                    int sP = toateStatiile.get(statieP);
                    int sS = toateStatiile.get(statieS);
                    boolean firstElem = true;
                    if (sS > sP) {
                        for (String s : toateStatiile.keySet()) {
                            int id = toateStatiile.get(s);
                            if (id <= sS && id >= sP) {
                                if (firstElem == true) {
                                    dialogMessage += isHead(s, statieP, statieS);
                                    System.out.println(s);
                                } else {
                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                    System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    } else {
                        ArrayList<String> reverseList = new ArrayList<>();
                        reverseList.addAll(toateStatiile.keySet());
                        Collections.reverse(reverseList);

                        for (String s : reverseList) {
                            int id = toateStatiile.get(s);
                            if (id <= sP && id >= sS) {
                                if (firstElem == true) {
                                    dialogMessage += isHead(s, statieP, statieS);
                                    System.out.println(s);
                                } else {
                                    dialogMessage += ", " + isHead(s, statieP, statieS);
                                    System.out.println(s);
                                }
                                firstElem = false;
                            }
                        }
                    }

                    if (result.size() > 1) {
                        dialogMessage += "<br/>";
                        dialogMessage += "Alte sugestii: ";
                        System.out.println("Alte sugestii:");
                        boolean firstAlt = true;
                        for (String i : result) {
                            if (!(i.equals(linieDirecta))) {
                                if (firstAlt == true) {
                                    dialogMessage += i;
                                    System.out.println(i);
                                } else {
                                    dialogMessage += ", " + i;
                                    System.out.println(i);

                                }
                                firstAlt = false;
                            }
                        }
                    }
                    dialogMessage += "<br/>";
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PlanActivity.this);
                    builder1.setCancelable(true);
                    builder1.setMessage(Html.fromHtml(dialogMessage));
                    builder1.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

                // cazul in care se gaseste decat o linie directa si aceea e cea de evitat

                if (ok == 1 && vb3 == 0) {
                    System.out.println("Singura legatura directa este linia pe care doesti sa o eviti");

                    System.out.println("Aceasta este: " + linieEvitata);
                    System.out.println("Pentru noi sugestii, intoarce-te la formularul de calatorie");

                }

                // cazul in care nu gasesc linie directa, chiar daca as include linia evitata

                if (ok == 0 && vb3 == 0) {
                    System.out.println("Nu exista legatura directa!");

                    int timp = 1000;


                    if (ok == 0)
                        for (String p : cursorLiniiP) {
                            List<String> st = new ArrayList<>();
                            Cursor c = helper.getStatiiByLinie(p);
                            while (c.moveToNext()) {
                                st.add(c.getString(0));
                            }
                            statiiPeLiniiP.addAll(st);

                        }
                    for (String p : cursorLiniiS) {
                        List<String> st = new ArrayList<>();
                        Cursor c = helper.getStatiiByLinie(p);
                        while (c.moveToNext()) {
                            st.add(c.getString(0));
                        }
                        statiiPeLiniiS.addAll(st);

                    }

                    boolean b = false;
                    for (String s1 : statiiPeLiniiP)
                        for (String s2 : statiiPeLiniiS) {
                            if ((s1.equals(s2)) && (!s1.equals(statieP)) && (!s1.equals(statieS))) {

                                b = true;
                                statiiComune.add(s1);
                            }
                        }

                    if (b == true) {

                        for (String stat : statiiComune) {
                            l3.clear();
                            l4.clear();
                            l5.clear();
                            l6.clear();
                            l1 = helper.getLiniiByStatie(stat);   //toate liniile care au si statia de legatura


                            for (String z : l1) {

                                if (setLiniiXYZ.contains(z)) {
                                    continue;
                                }


                                Cursor c = helper.getStatiiByLinie(z);
                                while (c.moveToNext()) {
                                    statiiDePlecare.put(c.getString(0), c.getInt(2));
                                }

                                //iau toate statiile care se gasesc pe liniile de mai sus

                                ArrayList<String> statiiPl = new ArrayList<>();
                                statiiPl.addAll(statiiDePlecare.keySet());

                                for (String r : statiiPl) {

                                    if (r.equals(statieP)) {
                                        if (z.equals(linieEvitata)) {
                                            ok2 = 1;
                                            vb2 = z;
                                        } else {
                                            ok4 = 1;
                                            l3.add(helper.getTimpByLinie(z));
                                            l5.add(z);
                                            l7.add(z);

                                        }
                                    }

                                    if (r.equals(statieS)) {
                                        if (z.equals(linieEvitata)) {
                                            ok3 = 1;
                                            vb2 = z;
                                        }
                                        ok5 = 1;
                                        l4.add(helper.getTimpByLinie(z));
                                        l6.add(z);
                                        l8.add(z);
                                    }

                                }

                                setLiniiXYZ.add(z);
                            }

                            if (ok4 == 1 && ok5 == 1) {
                                ok6 = 1;
                                Integer suma = 1000;
                                Integer se = 0;

                                for (Integer r = 0; r < l3.size(); r++)
                                    for (Integer t = 0; t < l4.size(); t++) {

                                        se = l3.get(r) + l4.get(t);

                                        if (se < suma) {
                                            suma = se;
                                            st1 = l5.get(r);
                                            st2 = l6.get(t);
                                        }

                                    }
                                ok4 = 0;
                                ok5 = 0;

                                if (ok7 == 0 && ((!st1.equals(linieEvitata))) && (!st2.equals(linieEvitata)))

                                {

                                    System.out.println("Se pleaca cu: ");

                                    System.out.println(st1);
                                    if(Integer.parseInt(st1) >=1 && Integer.parseInt(st1)<=60 && mijlocEvitat.equals("tramvai"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );
                                    if(Integer.parseInt(st1) >61 && Integer.parseInt(st1)<=99 && mijlocEvitat.equals("troleibuz"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );
                                    if(Integer.parseInt(st1) >100 && Integer.parseInt(st1)<800 && mijlocEvitat.equals("autobuz"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );
                                    System.out.println("Se trece prin statiile:");
                                    LinkedHashMap<String, Integer> toateStatile = new LinkedHashMap<>();

                                    Cursor c2 = helper.getStatiiByLinie(st1);
                                    while (c2.moveToNext()) {
                                        toateStatile.put(c2.getString(0), c2.getInt(2));
                                    }

                                    int sPs = toateStatile.get(statieP);
                                    int sSs = toateStatile.get(stat);

                                    if (sSs > sPs) {

                                        for (String s : toateStatile.keySet()) {
                                            int id = toateStatile.get(s);
                                            if (id <= sSs && id >= sPs) {
                                                System.out.println(s);
                                            }
                                        }
                                    } else {
                                        ArrayList<String> reverseList = new ArrayList<>();
                                        reverseList.addAll(toateStatile.keySet());
                                        Collections.reverse(reverseList);

                                        for (String s : reverseList) {
                                            int id = toateStatile.get(s);
                                            if (id <= sPs && id >= sSs) {
                                                System.out.println(s);
                                            }
                                        }
                                    }

                                    System.out.println("Se schimba in: ");
                                    System.out.println(stat);
                                    System.out.println("Se ajunge cu: ");
                                    System.out.println(st2);

                                    if(Integer.parseInt(st2) >=1 && Integer.parseInt(st2)<=60 && mijlocEvitat.equals("tramvai"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );
                                    if(Integer.parseInt(st2) >61 && Integer.parseInt(st2)<=99 && mijlocEvitat.equals("troleibuz"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );
                                    if(Integer.parseInt(st2) >100 && Integer.parseInt(st2)<800 && mijlocEvitat.equals("autobuz"))
                                        System.out.println("Atentie, este "+ mijlocEvitat );

                                    System.out.println("Se trece prin statiile: ");

                                    LinkedHashMap<String, Integer> toateStatile2 = new LinkedHashMap<>();

                                    Cursor c3 = helper.getStatiiByLinie(st2);
                                    while (c3.moveToNext()) {
                                        toateStatile2.put(c3.getString(0), c3.getInt(2));
                                    }

                                    int sPs1 = toateStatile2.get(stat);
                                    int sSs1 = toateStatile2.get(statieS);

                                    if (sSs1 > sPs1) {

                                        for (String s : toateStatile2.keySet()) {
                                            int id = toateStatile2.get(s);
                                            if (id <= sSs1 && id >= sPs1) {
                                                System.out.println(s);
                                            }
                                        }
                                    } else {
                                        ArrayList<String> reverseList = new ArrayList<>();
                                        reverseList.addAll(toateStatile2.keySet());
                                        Collections.reverse(reverseList);

                                        for (String s : reverseList) {
                                            int id = toateStatile2.get(s);
                                            if (id <= sPs1 && id >= sSs1) {
                                                System.out.println(s);
                                            }


                                        }
                                    }
                                    System.out.println("Timpul mediu total de asteptare in statii:" + suma + "  minute");

                                    ok7 = 1;
                                }
                            }
                        }
                        if (ok6 == 0)
                            System.out.println("Nu se poate gasi traseu care sa evite linia selectata!");
                    }
                }


            }






        }

    }