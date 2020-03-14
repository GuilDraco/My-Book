package com.e.myebook.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.myebook.Model.Book;
import com.e.myebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapterLendo extends RecyclerView.Adapter<BookAdapterLendo.MyViewHolder> {

    private Context context;
    private List<Book> listaBooks;

    public BookAdapterLendo(Context context, List<Book> listaBooks) {
        this.context = context;
        this.listaBooks = listaBooks;
    }

    @NonNull
    @Override
    public BookAdapterLendo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapterlendo, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapterLendo.MyViewHolder myViewHolder, int position) {
        Book book = listaBooks.get(position);

        myViewHolder.title.setText(book.getTitle());
        myViewHolder.writer.setText(book.getWriter());

        String url = book.getThumbnailHd();
        Picasso.get().load(url).into(myViewHolder.thumbnailHd);

        myViewHolder.buttonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" +
                        "Favorito", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaBooks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView    title;
        //private TextView    price;
        private TextView    writer;
        private ImageView   thumbnailHd;
        //private TextView    date;
        private ImageButton buttonFavorito;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textTitle);
            writer = itemView.findViewById(R.id.textWriter);
            //price = itemView.findViewById(R.id.textPrice);
            thumbnailHd = itemView.findViewById(R.id.imageBook);
            buttonFavorito = itemView.findViewById(R.id.buttonFavorito);

        }
    }
}
