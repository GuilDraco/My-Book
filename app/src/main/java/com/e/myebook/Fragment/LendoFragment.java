package com.e.myebook.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.myebook.R;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class LendoFragment extends Fragment {

    private RecyclerView recyclerBookLendo;
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();

    public LendoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lendo, container, false);
        recyclerBookLendo = view.findViewById(R.id.recyclerBooksComprados);

        iniciarComprados();

        return view;
    }

    private void iniciarComprados(){
        mTitle.add("Comprados");
        mImage.add("https://images-na.ssl-images-amazon.com/images/I/51MRErYPVBL.jpg");
        mTitle.add("Vendidos");
        mImage.add("https://www.publishnews.com.br/estaticos/uploads/2015/08/SSD47DiXYZczBBE1UhKU68VrA4oTA6NAqe5Ic5C1TQhKgsEcy1Ch5BusqeN1UnCfhi4TqzETdEbcokXI.jpg");

        configuraRecycleView();
    }

    public void configuraRecycleView() {

        //Adapter
        BookAdapterLendo bookAdapterLendo = new BookAdapterLendo(getContext(), mTitle, mImage);

        //Configuração do RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerBookLendo.setHasFixedSize(true);
        recyclerBookLendo.setLayoutManager(layoutManager);
        recyclerBookLendo.setAdapter(bookAdapterLendo);
    }
}
