package com.e.myebook.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.myebook.Adapter.BookAdapter;
import com.e.myebook.Api.ServiceBook;
import com.e.myebook.Model.Book;
import com.e.myebook.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


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
