package com.example.user.master.utils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.master.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;

/**
 * Created by User on 29.04.2015.
 */
public class LiniiFragment extends Fragment {

    private static final String KEY_POSITION="position";
    static List<String> all_numar_linii_tramvai = null;
    static List<String> all_numar_linii_troleibuz = null;
    static List<String> all_numar_linii_autobuz = null;
    static HashMap<String, LinkedHashMap<String, String>> liniiSiStatii = null;


    static LiniiFragment newInstance(int position, HashMap<String, LinkedHashMap<String, String>> data) {

        LiniiFragment frag=new LiniiFragment();
        Bundle args=new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);
        liniiSiStatii = data;
        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        int position=getArguments().getInt(KEY_POSITION, -1);
        String [] tip_linie = new String[] {"tramvai", "troleibuz", "autobuz"};
        final DisertatieDatabaseHelper helper = new DisertatieDatabaseHelper(getActivity().getBaseContext());

        if(all_numar_linii_tramvai==null){
            all_numar_linii_tramvai = helper.getAllLiniiNumarByTip(tip_linie[0]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, all_numar_linii_tramvai);

        if(all_numar_linii_troleibuz==null){
            all_numar_linii_troleibuz = helper.getAllLiniiNumarByTip(tip_linie[1]);
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, all_numar_linii_troleibuz);

        if(all_numar_linii_autobuz==null) {
            all_numar_linii_autobuz = helper.getAllLiniiNumarByTip(tip_linie[2]);
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity().getBaseContext(), R.layout.support_simple_spinner_dropdown_item, all_numar_linii_autobuz);

        if(position==0){
            View tramvai=inflater.inflate(R.layout.tramvaie_panel, container, false);

            Spinner spinner1 = (Spinner) tramvai.findViewById(R.id.tramvai_spinner);

            spinner1.setAdapter(adapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> cursor = liniiSiStatii.get(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tableStatiiForLinie);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if (cursor==null) {
                    } else {

                        TableRow tableRowHeader = new TableRow(getActivity().getBaseContext());

                        TextView numeStatieTvHeader = new TextView(getActivity().getBaseContext());
                        numeStatieTvHeader.setText("Nume");
                        numeStatieTvHeader.setTextColor(Color.rgb(74, 112, 35));
                        numeStatieTvHeader.setTextSize(25);
                        TextView adresaStatieTvHeader = new TextView(getActivity().getBaseContext());
                        adresaStatieTvHeader.setText("Adresa");
                        adresaStatieTvHeader.setTextColor(Color.rgb(74, 112, 35));
                        adresaStatieTvHeader.setTextSize(25);


                        tableRowHeader.addView(numeStatieTvHeader);
                        tableRowHeader.addView(adresaStatieTvHeader);
                        tableLayout.addView(tableRowHeader);


                        for(Map.Entry<String, String> entry: cursor.entrySet()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(entry.getKey());
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(entry.getValue());
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return tramvai;
        }else if(position==1){

            View troleibuz=inflater.inflate(R.layout.troleibuze_panel, container, false);

            Spinner spinner1 = (Spinner) troleibuz.findViewById(R.id.troleibuz_spinner);
            spinner1.setAdapter(adapter2);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> cursor = liniiSiStatii.get(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout)getActivity().findViewById(R.id.tableStatiiForLinie2);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if(cursor==null){} else{

                        TableRow tableRowHeader = new TableRow(getActivity().getBaseContext());

                        TextView numeStatieTvHeader = new TextView(getActivity().getBaseContext());
                        numeStatieTvHeader.setText("Nume");
                        numeStatieTvHeader.setTextColor(Color.rgb(74,112,35));
                        numeStatieTvHeader.setTextSize(25);
                        TextView adresaStatieTvHeader = new TextView(getActivity().getBaseContext());
                        adresaStatieTvHeader.setText("Adresa");
                        adresaStatieTvHeader.setTextColor(Color.rgb(74,112,35));
                        adresaStatieTvHeader.setTextSize(25);

                        tableLayout.addView(tableRowHeader);
                        tableRowHeader.addView(numeStatieTvHeader);
                        tableRowHeader.addView(adresaStatieTvHeader);


                        for(Map.Entry<String, String> entry: cursor.entrySet()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(entry.getKey());
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(entry.getValue());
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return troleibuz;
        } else {
            View autobuze=inflater.inflate(R.layout.autobuze_panel, container, false);

            Spinner spinner1 = (Spinner) autobuze.findViewById(R.id.bus_spinner);
            spinner1.setAdapter(adapter3);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> cursor = liniiSiStatii.get(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout)getActivity().findViewById(R.id.tableStatiiForLinie3);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if(cursor==null){} else{

                        TableRow tableRowHeader = new TableRow(getActivity().getBaseContext());

                        TextView numeStatieTvHeader = new TextView(getActivity().getBaseContext());
                        numeStatieTvHeader.setText("Nume");
                        numeStatieTvHeader.setTextColor(Color.rgb(74,112,35));
                        numeStatieTvHeader.setTextSize(25);
                        TextView adresaStatieTvHeader = new TextView(getActivity().getBaseContext());
                        adresaStatieTvHeader.setText("Adresa");
                        adresaStatieTvHeader.setTextColor(Color.rgb(74,112,35));
                        adresaStatieTvHeader.setTextSize(25);

                        tableLayout.addView(tableRowHeader);
                        tableRowHeader.addView(numeStatieTvHeader);
                        tableRowHeader.addView(adresaStatieTvHeader);


                        for(Map.Entry<String, String> entry: cursor.entrySet()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(entry.getKey());
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(entry.getValue());
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                    }}

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            return autobuze;
        }
    }
}