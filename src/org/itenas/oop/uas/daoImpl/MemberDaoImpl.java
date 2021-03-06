package org.itenas.oop.uas.daoImpl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.itenas.oop.uas.dao.MemberDao;
import org.itenas.oop.uas.database.DatabaseUtil;
import org.itenas.oop.uas.entity.Member;

public class MemberDaoImpl implements MemberDao{

	@Override
	public Member getMemberByNip(int nip) {
		return null;
	}

	@Override
	public void getMemberByNama(String name) {
		DatabaseUtil db = new DatabaseUtil();
		Member member = new Member();
		try {
			db.connect();
 
            String query = "SELECT * FROM member WHERE name = '"+name+"';";
            ResultSet rs = db.readData(query);
            System.out.println(query);

            if (rs.next()) {
 
                ResultSetMetaData metaData = rs.getMetaData();
                int jumlahKolom = metaData.getColumnCount();
                do {
                    for (int i = 1; i <= jumlahKolom; i++) {
                    	member.setId(rs.getString("id_mem"));
                    	member.setName(rs.getString("name"));
                    	member.setCity(rs.getString("city"));
                    	member.setPhone(rs.getString("phone"));
                    	member.setMemberExp(rs.getDate("mem_exp"));
                    }
                } while (rs.next());
                
            } else {
            	member.setId(null);
            }

            db.disconnect();            
 
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
		
	}

	@Override
	public void getMemberById(String id) {
		DatabaseUtil db = new DatabaseUtil();
		Member member = new Member();
		try {
			db.connect();
 
            String query = "SELECT * FROM member WHERE id_mem = '"+id+"';";
            ResultSet rs = db.readData(query);
            System.out.println(query);
 
            if (rs.next()) {
 
                ResultSetMetaData metaData = rs.getMetaData();
                int jumlahKolom = metaData.getColumnCount();
                do {
                    for (int i = 1; i <= jumlahKolom; i++) {
                    	member.setId(rs.getString("id_mem"));
                    	member.setName(rs.getString("name"));
                    	member.setCity(rs.getString("city"));
                    	member.setPhone(rs.getString("phone"));
                    	member.setMemberExp(rs.getDate("mem_exp"));
                    }
                } while (rs.next());
                
            } else {
            	member.setId(null);
            }

            db.disconnect();            
 
        } catch (SQLException ex) {
            System.out.println("The following error has occured: " + ex.getMessage());
        }
	}
}
