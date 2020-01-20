package com.e.myebook.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.e.myebook.Model.Book;
import com.e.myebook.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Book> listaBooks;
    private Context context;
    private int click = 0;

    public BookAdapter(Context context, List<Book> listaBooks) {
        this.context = context;
        this.listaBooks = listaBooks;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapter, viewGroup, false);
        return new BookAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        final Book book = listaBooks.get(i);
        myViewHolder.title.setText(book.getTitle());
        myViewHolder.price.setText(book.getPrice());
        myViewHolder.writer.setText(book.getWriter());

        String url =  book.getThumbnailHd();
        Picasso.get().load(url).into(myViewHolder.thumbnailHd);

        //myViewHolder.moreButton.setOnClickListener(view -> updateItem(i));
        //myViewHolder.deleteButton.setOnClickListener(view -> removerItem(i));

        myViewHolder.buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = click + 1;

                SharedPreferences positionI = context.getSharedPreferences("POSITION", 0);
                SharedPreferences.Editor position = positionI.edit();
                position.putInt("position", click);
                position.apply();

                SharedPreferences titleB = context.getSharedPreferences("TITLE_BOOK", 0);
                SharedPreferences.Editor titleBook = titleB.edit();
                titleBook.putString("titleBook", book.getTitle());
                titleBook.apply();

                SharedPreferences ImageB = context.getSharedPreferences("IMAGE_BOOK", 0);
                SharedPreferences.Editor imageBook = ImageB.edit();
                imageBook.putString("imageBook", book.getThumbnailHd());
                imageBook.apply();

                SharedPreferences autorB = context.getSharedPreferences("AUTOR_BOOK", 0);
                SharedPreferences.Editor autorBook = autorB.edit();
                autorBook.putString("autorBook", book.getWriter());
                autorBook.apply();

                removerItem(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaBooks.size();
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        listaBooks.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listaBooks.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView    title;
        private TextView    price;
        private TextView    writer;
        private ImageView   thumbnailHd;
        //private TextView    date;
        private ImageButton buttonComprar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailHd = itemView.findViewById(R.id.imageBook);
            title = itemView.findViewById(R.id.textTitle);
            price = itemView.findViewById(R.id.textPrice);
            writer = itemView.findViewById(R.id.textWriter);
            buttonComprar = itemView.findViewById(R.id.buttonComprar);

        }
    }
}
