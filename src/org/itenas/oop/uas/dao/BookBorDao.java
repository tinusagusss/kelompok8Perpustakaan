package org.itenas.oop.uas.dao;

import java.util.List;

import org.itenas.oop.uas.actor.Book;


public interface BookBorDao {

	public void getBookBorByCode(String code);
//    public String getNamaByNip(int nip);
    
    public List<Book> getAllBookBor();
    public void saveBookBor(Book bookBor);
    public void updateBookBor(Book bookBor);
    public void deleteBookBor();
    public void deleteBookBor(Book bookBor);
    
    public String getCodeBookBor();
    public int getDayDifference(Book bookBor);
}
