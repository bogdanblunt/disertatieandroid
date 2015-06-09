package com.example.user.master.utils;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.user.master.R;

/**
 * Created by User on 27.04.2015.
 */
public class FormulareFragment extends Fragment {

    private static final String KEY_POSITION="position";

    static FormulareFragment newInstance(int position) {
        FormulareFragment frag=new FormulareFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        int position=getArguments().getInt(KEY_POSITION, -1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.planets_array, R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.linii_array, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(position==0){
            View form_simplu=inflater.inflate(R.layout.formular_simplu, container, false);

            Spinner spinner1 = (Spinner) form_simplu.findViewById(R.id.plecareSimplu_spinner);

            Spinner spinner2 = (Spinner) form_simplu.findViewById(R.id.sosireSimplu_spinner);

            spinner1.setAdapter(adapter);
            spinner2.setAdapter(adapter);

            return form_simplu;
        } else{
            View form_avansat=inflater.inflate(R.layout.formular_avansat, container, false);

            Spinner spinner3 = (Spinner) form_avansat.findViewById(R.id.evita_spinner3);

            Spinner spinner4 = (Spinner) form_avansat.findViewById(R.id.plecareAvansat_spinner);

            Spinner spinner5 = (Spinner) form_avansat.findViewById(R.id.sosireAvansat_spinner);

            spinner3.setAdapter(adapter2);
            spinner4.setAdapter(adapter);
            spinner5.setAdapter(adapter);
            return form_avansat;
        }
    }
}
