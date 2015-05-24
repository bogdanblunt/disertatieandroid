package com.example.user.master.utils;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.user.master.R;

import java.util.List;

import com.example.user.master.dbUtils.DisertatieDatabaseHelper;

/**
 * Created by User on 29.04.2015.
 */
public class LiniiFragment extends Fragment {

    private static final String KEY_POSITION="position";
    static List<String> all_numar_linii_tramvai = null;
    static List<String> all_numar_linii_troleibuz = null;
    static List<String> all_numar_linii_autobuz = null;


    static LiniiFragment newInstance(int position) {

        LiniiFragment frag=new LiniiFragment();
        Bundle args=new Bundle();
        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.liniiswipe, container, false);

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

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(position==0){
            LinearLayout lTramvai =(LinearLayout)result.findViewById(R.id.liniiTramvai);
            lTramvai.setVisibility(View.VISIBLE);
            LinearLayout lTroleibuzt =(LinearLayout)result.findViewById(R.id.liniiTroleibuz);
            lTroleibuzt.setVisibility(View.GONE);
            LinearLayout lBus =(LinearLayout)result.findViewById(R.id.liniiAutobuz);
            lBus.setVisibility(View.GONE);

            Spinner spinner1 = (Spinner) result.findViewById(R.id.tramvai_spinner);

            spinner1.setAdapter(adapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = helper.getStatiiByLinie(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout) getActivity().findViewById(R.id.tableStatiiForLinie);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if (cursor.getCount() == 0) {
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


                        while (cursor.moveToNext()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(cursor.getString(0));
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(cursor.getString(1));
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                        cursor.close();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else if(position==1){

            LinearLayout lTroleibuz =(LinearLayout)result.findViewById(R.id.liniiTroleibuz);
            lTroleibuz.setVisibility(View.VISIBLE);
            LinearLayout lTramvai =(LinearLayout)result.findViewById(R.id.liniiTramvai);
            lTramvai.setVisibility(View.GONE);
            LinearLayout lBus =(LinearLayout)result.findViewById(R.id.liniiAutobuz);
            lBus.setVisibility(View.GONE);

            Spinner spinner1 = (Spinner) result.findViewById(R.id.troleibuz_spinner);
            spinner1.setAdapter(adapter2);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = helper.getStatiiByLinie(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout)getActivity().findViewById(R.id.tableStatiiForLinie2);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if(cursor.getCount()==0){} else{

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


                        while(cursor.moveToNext()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(cursor.getString(0));
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(cursor.getString(1));
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                        cursor.close();
                    }}

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } else {
            LinearLayout lBus =(LinearLayout)result.findViewById(R.id.liniiAutobuz);
            lBus.setVisibility(View.VISIBLE);
            LinearLayout lTramvai =(LinearLayout)result.findViewById(R.id.liniiTramvai);
            lTramvai.setVisibility(View.GONE);
            LinearLayout lTroleibuzt =(LinearLayout)result.findViewById(R.id.liniiTroleibuz);
            lTroleibuzt.setVisibility(View.GONE);

            Spinner spinner1 = (Spinner) result.findViewById(R.id.bus_spinner);
            spinner1.setAdapter(adapter3);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cursor = helper.getStatiiByLinie(parent.getItemAtPosition(position).toString());

                    TableLayout tableLayout = (TableLayout)getActivity().findViewById(R.id.tableStatiiForLinie3);

                    /* Columns labels */

                    tableLayout.removeAllViews();

                    if(cursor.getCount()==0){} else{

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


                        while(cursor.moveToNext()) {
                            TableRow tableRow = new TableRow(getActivity().getBaseContext());

                            TextView numeStatieTv = new TextView(getActivity().getBaseContext());
                            numeStatieTv.setText(cursor.getString(0));
                            numeStatieTv.setTextColor(Color.BLACK);
                            numeStatieTv.setTextSize(20);

                            TextView adresaStatieTv = new TextView(getActivity().getBaseContext());
                            adresaStatieTv.setText(cursor.getString(1));
                            adresaStatieTv.setTextColor(Color.BLACK);
                            adresaStatieTv.setTextSize(20);


                            tableRow.addView(numeStatieTv);
                            tableRow.addView(adresaStatieTv);
                            tableLayout.addView(tableRow);

                        }
                        cursor.close();
                    }}

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
        return(result);
    }
}