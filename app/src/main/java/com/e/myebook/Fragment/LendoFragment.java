package com.e.myebook.Fragment;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.myebook.Adapter.BookAdapterLendo;
import com.e.myebook.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LendoFragment extends Fragment {

    private RecyclerView recyclerBookLendo;
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mAutor = new ArrayList<>();

    public LendoFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lendo, container, false);
        recyclerBookLendo = view.findViewById(R.id.recyclerBooksComprados);

        iniciarComprados();
        configuraRecycleView();
        return view;
    }

    private void iniciarComprados() {
    }

    public void configuraRecycleView() {

        //Adapter
        BookAdapterLendo bookAdapterLendo = new BookAdapterLendo(getContext(), mTitle, mImage, mAutor);

        //Configuração do RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerBookLendo.setHasFixedSize(true);
        recyclerBookLendo.setLayoutManager(layoutManager);
        recyclerBookLendo.setAdapter(bookAdapterLendo);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
