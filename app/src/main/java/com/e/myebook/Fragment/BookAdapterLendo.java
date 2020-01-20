package com.e.myebook.Fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.myebook.Model.Book;
import com.e.myebook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class BookAdapterLendo extends RecyclerView.Adapter<BookAdapterLendo.MyViewHolder> {

    private List<Book> listaBooksLendo;
    private Activity activity;
    private Object SharedPreferences;

    public BookAdapterLendo (List<Book> listaBooks) {
        this.listaBooksLendo = listaBooks;
    }

    @NonNull
    @Override
    public BookAdapterLendo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapterlendo, viewGroup, false);
        return new BookAdapterLendo.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapterLendo.MyViewHolder myViewHolder, int i) {

        /*int nomeUsuario;

        if(SharedPreferences != null) {
            SharedPreferences prefs = activity.getSharedPreferences("nomeUsuario", 0);
            nomeUsuario = prefs.getInt("nomeUsuario", 0);

            Book book = listaBooksLendo.get(nomeUsuario);
            myViewHolder.title.setText(book.getTitle());
            myViewHolder.price.setText(book.getPrice());
            myViewHolder.writer.setText(book.getWriter());

            String url = book.getThumbnailHd();
            Picasso.get().load(url).into(myViewHolder.thumbnailHd);
        }*/
    }

    @Override
    public int getItemCount() {
        return listaBooksLendo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView    title;
        private TextView    price;
        private TextView    writer;
        private ImageView   thumbnailHd;
        private TextView    date;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailHd = itemView.findViewById(R.id.imageBook);
            title = itemView.findViewById(R.id.texttitle);
            price = itemView.findViewById(R.id.textPrice);
            writer = itemView.findViewById(R.id.textwriter);

        }
    }
}
