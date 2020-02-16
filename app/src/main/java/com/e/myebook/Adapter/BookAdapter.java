package com.e.myebook.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.myebook.Listener.RecyclerClickListener;
import com.e.myebook.Model.Book;
import com.e.myebook.R;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<Book> listaBooks;
    private final RecyclerClickListener listener;

    public BookAdapter(List<Book> listaBooks, RecyclerClickListener listener) {
        this.listaBooks = listaBooks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapter, viewGroup, false);
        return new BookAdapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        final Book book = listaBooks.get(i);
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

//    Método responsável por remover um item da lista.
//    private void removerItem(int position) {
//        listaBooks.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, listaBooks.size());
//    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView    title;
        private TextView    price;
        private TextView    writer;
        private ImageView   thumbnailHd;
        private WeakReference<RecyclerClickListener> listenerRef;

        MyViewHolder(@NonNull View itemView, RecyclerClickListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);

            thumbnailHd = itemView.findViewById(R.id.imageBook);
            title = itemView.findViewById(R.id.textTitle);
            price = itemView.findViewById(R.id.textPrice);
            writer = itemView.findViewById(R.id.textWriter);
            //private TextView    date;
            ImageButton buttonComprar = itemView.findViewById(R.id.buttonComprar);

            buttonComprar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onClick(getAdapterPosition());
        }
    }
}
