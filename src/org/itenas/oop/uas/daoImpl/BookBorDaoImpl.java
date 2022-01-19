package org.itenas.oop.uas.daoImpl;

import org.itenas.oop.uas.database.DatabaseUtil;
import org.itenas.oop.uas.entity.BookBor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.itenas.oop.uas.dao.BookBorDao;

public class BookBorDaoImpl implements BookBorDao{
	
	static DatabaseUtil db = new DatabaseUtil();
	static String query;
	
	@Override
	public void saveBookBor(BookBor bookBor) {
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
		db.disconnect(0);
	}
	
	@Override
	public void PaymentBookBor(BookBor bookBor) {
		try {
			db.connect();
			query = "INSERT INTO payment(id_mem, amount)\n"
					+ " VALUES ('"+bookBor.getIdMem()+"', "
					+ bookBor.getAmount()+");";
			db.executeQuery(query);
		} catch (Exception ex) {
			System.out.println("Terjadi error: " + ex.getMessage());
		}
		db.disconnect(0);
		
	}
	@Override
	public void updateBookBor(BookBor bookBor) {
		try {
			db.connect();
			query = "UPDATE book_loans SET status = false WHERE code='"+bookBor.getCode()+"'";
			db.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Terjadi error: " + e.getMessage());
		}
		db.disconnect(0);
	}
	
	@Override
	public String getCodeBookBor() {		
		DatabaseUtil db = new DatabaseUtil();
		StringBuffer sb = null; 
		String result = null;
		try {
			db.connect();
 
            String query = "SELECT MAX(code) AS 'result' FROM book_loans;";
            ResultSet rs = db.readData(query);
 
            if (rs.next()) {
            	sb = new StringBuffer(rs.getString("result"));
            	sb.deleteCharAt(0);
                int max = Integer.parseInt(sb.toString());
                if(max > 0 && max <9)
                	result = "B0" + (max + 1);
                else
                    result = "B" + (max + 1);
            } else {
            	return result = null;
            }
            db.disconnect();            
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return result;
	}
	@Override
	public int getDayDifference(BookBor bookBor) {
		DatabaseUtil db = new DatabaseUtil();
		int result = 0;
		try {
			db.connect();
 
            String query = "SELECT due_date - CURRENT_DATE() AS 'result', id_mem\n"
            		+ "FROM book_loans WHERE code = '"+bookBor.getCode()+"';";
            ResultSet rs = db.readData(query);
 
            if (rs.next()) {
                result  = rs.getInt("result");
                bookBor.setIdMem(rs.getString("id_mem"));
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
	public void deleteBookBor(BookBor bookBor) {
		try {
			db.connect();
			query = "DELETE FROM book_loans WHERE code = '"+bookBor.getCode()+"';";
			db.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Terjadi error: " + e.getMessage());
		}
	}
	
	@Override
	public List<BookBor> getAllBookBor() {
		List<BookBor> listBook = new ArrayList<BookBor>();
		DatabaseUtil db = new DatabaseUtil();
		try {
			db.connect();
			 
			query = "SELECT * FROM book_loans";
         
            ResultSet rs = db.readData(query);
 
            // process query results
            while (rs.next()) {
 
            	BookBor book = new BookBor();
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
