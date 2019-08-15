package com.e.myebook.Fragment;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.e.myebook.Adapter.BookAdapter;
import com.e.myebook.Api.ServiceBook;
import com.e.myebook.Listener.RecyclerItemClickListener;
import com.e.myebook.Model.Book;
import com.e.myebook.R;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComprarFragment extends Fragment {

    private Retrofit     retrofit;
    private RecyclerView recyclerBook;
    private List<Book>   listaBooks = new ArrayList<>();


    public ComprarFragment() {
        // Required empty public constructor

        //ctrl P
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comprar, container, false);

        recyclerBook = view.findViewById(R.id.recyclerBook);

        //Retrofit JSON
        retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarDados();

        return view;
    }
    private void recuperarDados(){

        ServiceBook serviceBook = retrofit.create(ServiceBook.class);
        Call<List<Book>> call = serviceBook.recuperarBook();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                if(response.isSuccessful()){
                    listaBooks = response.body();

                    assert listaBooks != null;
                    for (int i = 0; i<listaBooks.size(); i++){
                        configuraRecycleView();
                        //Log.d("retorno"," resultado " + book.getTitle() + "/" + book.getWriter());

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {

            }
        });
    }

    public void configuraRecycleView() {

        //Adapter
        BookAdapter adapterBook = new BookAdapter(listaBooks);

        //Configuração do RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(layoutManager);
        recyclerBook.setAdapter(adapterBook);

        //Configuração evento de clique
        recyclerBook.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity(),
                        recyclerBook,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


    }
}
