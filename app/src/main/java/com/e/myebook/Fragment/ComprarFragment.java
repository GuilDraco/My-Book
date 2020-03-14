package com.e.myebook.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.myebook.Adapter.BookAdapterComprar;
import com.e.myebook.Api.ServiceBook;
import com.e.myebook.DataBase.MyBooksDAO;
import com.e.myebook.Listener.RecyclerClickListener;
import com.e.myebook.Model.Book;
import com.e.myebook.R;
import com.e.myebook.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<Book> listaBooks = new ArrayList<>();
    private BookAdapterComprar adapter;
    private MyBooksDAO myBooksDAO;
    private TextView bookstoreSaldo;

    public ComprarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comprar, container, false);

        recyclerBook = view.findViewById(R.id.recyclerBook);
        myBooksDAO = new MyBooksDAO(getActivity());

        //Retrofit JSON
        retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarDados();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //bookstoreSaldo.setText("R$ " + preferences.getSaldo());
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

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {

            }
        });
    }

    private void configuraRecycleView() {

        adapter = new BookAdapterComprar(listaBooks, new RecyclerClickListener() {
            @Override
            public void onClick(int position) {
                buyBook(position);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerBook.setHasFixedSize(true);
        recyclerBook.setLayoutManager(layoutManager);
        recyclerBook.setAdapter(adapter);

    }

    // Método responsável por remover um item da lista.
    private void removerItem(int position) {
        listaBooks.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, listaBooks.size());
    }

    private void buyBook(final int position){
        //1-remove da lista da loja
        //2-adiciona no banco
        //3-subtrai do saldo

        final ImageView bookThumb = new ImageView(getContext());
        Picasso.get().load(listaBooks.get(position).getThumbnailHd()).into(bookThumb);

        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("Deseja comprar este livro?").setMessage(listaBooks.get(position).getTitle()
                + "\n" + listaBooks.get(position).getWriter() + "\n\n");
        builder.setView(bookThumb);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //float saldo = Float.valueOf(preferences.getSaldo());
                //float price = Float.valueOf(listaBooks.get(position).getPrice());
                //float res = saldo - price;
                //if(res >= 0) {
                  //  preferences.setSaldo(String.format("%.0f", res));
                    myBooksDAO.salvar(listaBooks.get(position));
                    removerItem(position);
                   // bookstoreSaldo.setText(String.format("R$ %.0f", res));
                  //  Toast.makeText(getContext(), "Compra efetuada!", Toast.LENGTH_LONG).show();
                //} else {
                    //Toast.makeText(getContext(), "Saldo insuficiente", Toast.LENGTH_LONG).show();
                //}

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
