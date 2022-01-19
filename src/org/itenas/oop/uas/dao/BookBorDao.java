package org.itenas.oop.uas.dao;

import java.util.List;

import org.itenas.oop.uas.entity.BookBor;


public interface BookBorDao {
    
    public List<BookBor> getAllBookBor();
    public void saveBookBor(BookBor bookBor);
    public void updateBookBor(BookBor bookBor);
    public void deleteBookBor();
    public void deleteBookBor(BookBor bookBor);
    public void PaymentBookBor(BookBor bookBor);
    
    public String getCodeBookBor();
    public int getDayDifference(BookBor bookBor);
}
