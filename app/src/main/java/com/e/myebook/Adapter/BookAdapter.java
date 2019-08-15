package com.e.myebook.Adapter;

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


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Book> listaBooks;
    public BookAdapter(List<Book> listaBooks) {
        this.listaBooks = listaBooks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapter, viewGroup, false);
        return new BookAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Book book = listaBooks.get(i);
        myViewHolder.title.setText(book.getTitle());
        myViewHolder.price.setText(book.getPrice());
        myViewHolder.writer.setText(book.getWriter());

        String url =  book.getThumbnailHd();
        Picasso.get().load(url).into(myViewHolder.thumbnailHd);


    }

    @Override
    public int getItemCount() {
        return listaBooks.size();
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
