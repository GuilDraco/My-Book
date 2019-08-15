package com.e.myebook.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.e.myebook.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class LendoFragment extends Fragment {


    public LendoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lendo, container, false);

        //Pega os dados e mostra no novo Fragment ( como faço? )


        return view;
    }

}
