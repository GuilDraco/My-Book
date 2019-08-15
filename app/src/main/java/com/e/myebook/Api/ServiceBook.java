package com.e.myebook.Api;

import com.e.myebook.Model.Book;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;


public interface ServiceBook {

    @GET("Felcks/desafio-mobile-lemobs/master/products.json")
    Call<List<Book>> recuperarBook();

    @PATCH("Felcks/desafio-mobile-lemobs/master/products.json")
    Call<List<Book>> salvarCompra(@Body Book book);

}
