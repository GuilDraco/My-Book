package com.e.myebook.Api;

import com.e.myebook.Model.Book;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ServiceBook {

    @GET("Felcks/desafio-mobile-lemobs/master/products.json")
    Call<List<Book>> recuperarBook();


}
