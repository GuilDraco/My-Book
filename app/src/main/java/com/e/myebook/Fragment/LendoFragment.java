package com.e.myebook.Fragment;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.e.myebook.Adapter.BookAdapterLendo;
import com.e.myebook.R;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class LendoFragment extends Fragment {

    private RecyclerView recyclerBookLendo;
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mAutor = new ArrayList<>();

    private String titleBook;
    private String imageBook;
    private String autorBook;
    private ImageButton imageFavoritos;

    int position;
    int positionFinal;


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
        imageFavoritos = view.findViewById(R.id.buttonFavorito);

        SharedPreferences positionF = Objects.requireNonNull(getActivity()).getSharedPreferences("POSITION_F", 0);
        positionFinal = positionF.getInt("positionf", 0);

        iniciarComprados();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void iniciarComprados(){

        SharedPreferences positionI = Objects.requireNonNull(getActivity()).getSharedPreferences("POSITION", 0);
        position = positionI.getInt("position", 0);

        positionFinal = positionFinal + position;

        for(int i = 1; i <= positionFinal; i++){

            SharedPreferences titleB = Objects.requireNonNull(getActivity()).getSharedPreferences("TITLE_BOOK", 0);
            titleBook = titleB.getString("titleBook", null);

            SharedPreferences imageB = Objects.requireNonNull(getActivity()).getSharedPreferences("IMAGE_BOOK", 0);
            imageBook = imageB.getString("imageBook", null);

            SharedPreferences autorB = Objects.requireNonNull(getActivity()).getSharedPreferences("AUTOR_BOOK", 0);
            autorBook = autorB.getString("autorBook", null);

            if(mTitle != null){
                mTitle.add(titleBook);
                mImage.add(imageBook);
                mAutor.add(autorBook);
            }
        }

        configuraRecycleView();
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

        SharedPreferences positionF = Objects.requireNonNull(getActivity()).getSharedPreferences("POSITION_F", 0);
        SharedPreferences.Editor positionf = positionF.edit();
        positionf.putInt("positionf", positionFinal);
        positionf.apply();
    }
}
