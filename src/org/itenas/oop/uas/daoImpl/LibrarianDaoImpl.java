package org.itenas.oop.uas.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.itenas.oop.uas.actor.Librarian;
import org.itenas.oop.uas.dao.LibrarianDao;
import org.itenas.oop.uas.database.DatabaseUtil;

public class LibrarianDaoImpl implements LibrarianDao {
	@Override
	public Librarian getEmailAndPassword(String username, String password) {
		DatabaseUtil db = new DatabaseUtil();
		Librarian user = new Librarian();
		user = null;
		try {
			db.connect();

			String query = "SELECT l.name, c.username, c.password FROM account c\n"
					+ "JOIN librarian l ON c.id_lib = l.id_lib\n"
					+ "WHERE username =  '"+username+"' AND password = '"+password+"' ;";
			
			ResultSet rs = db.readData(query);

			while (rs.next()) {
				Librarian us = new Librarian();
				us.setUsername(rs.getString("username"));
				us.setPassword(rs.getString("password"));
				us.setName(rs.getString("name"));
				
				if (username.equals(us.getUsername()) && password.equals(us.getPassword())) {
					return user = us;
				}
			}
			
			db.disconnect();

		} catch (SQLException ex) {
			System.out.println("The following error has occured: " + ex.getMessage());
		}
			return null;
	}

}
