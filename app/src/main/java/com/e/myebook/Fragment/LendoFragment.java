package com.e.myebook.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.myebook.Adapter.BookAdapterLendo;
import com.e.myebook.DataBase.MyBooksDAO;
import com.e.myebook.Model.Book;
import com.e.myebook.R;

import java.util.List;

public class LendoFragment extends Fragment {

    private RecyclerView recyclerBookLendo;
    private MyBooksDAO myBooksDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lendo, container, false);
        recyclerBookLendo = view.findViewById(R.id.recyclerBooksComprados);

        myBooksDAO = new MyBooksDAO(getActivity());
        configuraRecycleView();
        return view;
    }

    private void configuraRecycleView() {
        List<Book> listaBooks = myBooksDAO.listar();

        //Adapter
        BookAdapterLendo bookAdapterLendo = new BookAdapterLendo(getActivity(), listaBooks);

        //Configuração do RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerBookLendo.setHasFixedSize(true);
        recyclerBookLendo.setLayoutManager(layoutManager);
        recyclerBookLendo.setAdapter(bookAdapterLendo);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
