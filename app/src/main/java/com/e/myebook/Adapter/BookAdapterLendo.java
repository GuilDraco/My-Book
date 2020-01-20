package com.e.myebook.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.myebook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapterLendo extends RecyclerView.Adapter<BookAdapterLendo.MyViewHolder> {

    private ArrayList<String> mTitle;
    private ArrayList<String> mImage;
    private ArrayList<String> mAutor;
    private Context context;

    public BookAdapterLendo(Context context, ArrayList<String> mTitle, ArrayList<String> mImage, ArrayList<String> mAutor) {
        this.context = context;
        this.mTitle = mTitle;
        this.mImage = mImage;
        this.mAutor = mAutor;
    }

    @NonNull
    @Override
    public BookAdapterLendo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookadapterlendo, viewGroup, false);
        return new BookAdapterLendo.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapterLendo.MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {

        myViewHolder.title.setText(mTitle.get(i));
        myViewHolder.writer.setText(mAutor.get(i));

        String url = mImage.get(i);
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
        return mTitle.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

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
