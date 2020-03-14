package com.e.myebook.DataBase;


import com.e.myebook.Model.Book;

import java.util.List;

public interface IMyBooksDAO {
    boolean salvar(Book book);
    boolean procurarLivro(Book book);
    List<Book> listar();
    void cleanBooks();
}
