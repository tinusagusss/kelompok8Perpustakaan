package org.itenas.oop.uas.daoImpl;

import org.itenas.oop.uas.database.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.itenas.oop.uas.actor.Book;
import org.itenas.oop.uas.dao.BookBorDao;

public class BookBorDaoImpl implements BookBorDao{
	
	static DatabaseUtil db = new DatabaseUtil();
	static String query;
	@Override
	public void getBookBorByCode(String code) {
		
	}
	
	@Override
	public void saveBookBor(Book bookBor) {
		try {
			db.connect();
			query = "INSERT INTO book_loans(code, isbn, id_mem)\n"
					+ " VALUES ('"+bookBor.getCode()+"', '"
					+ bookBor.getIsbn()+"', '"
					+ bookBor.getIdMem()+"')";
			db.executeQuery(query);
		} catch (Exception ex) {
			System.out.println("Terjadi error: " + ex.getMessage());
		}
//		db.disconnect();
	}
	
	@Override
	public void updateBookBor(Book bookBor) {
		try {
			db.connect();
			query = "UPDATE book_loans SET status = false WHERE code='"+bookBor.getCode()+"'";
			db.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Terjadi error: " + e.getMessage());
		}
//		db.disconnect();
	}
	
	@Override
	public int getDayDifference(Book bookBor) {
		DatabaseUtil db = new DatabaseUtil();
		int result = 0;
		try {
			db.connect();
 
            String query = "SELECT due_date - CURRENT_DATE() AS 'result' \n"
            		+ "FROM book_loans WHERE code = '"+bookBor.getCode()+"';";
            ResultSet rs = db.readData(query);
 
            if (rs.next()) {
                result  = rs.getInt("result");
            } else {
            	return 0;
            }
            db.disconnect();            
 
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
		return result;
	}
	
	@Override
	public void deleteBookBor() {
		try {
			db.connect();
			query = "DELETE FROM book_loans WHERE status = '0';";
			db.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Terjadi error: " + e.getMessage());
		}
		
	}
	
	@Override
	public void deleteBookBor(Book bookBor) {
		try {
			db.connect();
			query = "DELETE FROM book_loans WHERE code = '"+bookBor.getCode()+"';";
			db.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Terjadi error: " + e.getMessage());
		}
	}
	
	@Override
	public List<Book> getAllBookBor() {
		List<Book> listBook = new ArrayList<Book>();
		DatabaseUtil db = new DatabaseUtil();
		try {
			db.connect();
			 
			query = "SELECT * FROM book_loans";
         
            ResultSet rs = db.readData(query);
 
            // process query results
            while (rs.next()) {
 
            	Book book = new Book();
                book.setCode(rs.getString("code"));
                book.setIsbn(rs.getString("isbn"));
                book.setIdMem(rs.getString("id_mem"));
                book.setStartDate(rs.getDate("start_date"));
                book.setDueDate(rs.getDate("due_date"));
                book.setStatus(rs.getBoolean("status"));
                listBook.add(book);
 
            } 
            //close db connection
            db.disconnect();
			
		} catch (SQLException ex) {
			System.out.println("Terjadi error: " + ex.getMessage());
		}
		
		return listBook;
	}
	
}
