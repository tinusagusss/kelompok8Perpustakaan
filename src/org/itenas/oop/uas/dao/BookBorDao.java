package org.itenas.oop.uas.dao;

import java.util.List;

import org.itenas.oop.uas.entity.BookBor;


public interface BookBorDao {
    
    public List<BookBor> getAllBookBor();
    public List<BookBor> findByCode(String code);
    
    public void inserBookBor(BookBor bookBor);
    public void updateBookBor(BookBor bookBor);
    public void PaymentBookBor(BookBor bookBor);
    public void deleteBookBor(BookBor bookBor);
    public void deleteBookBor();
    
    public String getCodeBookBor();
    public int getDayDifference(BookBor bookBor);
}
