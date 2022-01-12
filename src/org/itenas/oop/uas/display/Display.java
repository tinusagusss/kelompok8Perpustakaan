package org.itenas.oop.uas.display;

import java.util.*;

import org.itenas.oop.uas.actor.Librarian;
import org.itenas.oop.uas.dao.LibrarianDao;
import org.itenas.oop.uas.daoImpl.LibrarianDaoImpl;
import org.itenas.oop.uas.form.FormView;

public class Display {

	public static void mainMenu() {
		int menu;
		Scanner in = new Scanner(System.in);
        System.out.println();		        
        System.out.println("              *****************************************                  ");
        System.out.println("              |      Selamat Datang Di Menu Utama     |                  ");
        System.out.println("              *****************************************                  ");
        System.out.println("              | Pilihan:                              |                  ");
        System.out.println("              |        1. Data Karyawan Tetap         |                  ");
        System.out.println("              |        2. Data Karyawan Kontrak       |                  ");
        System.out.println("              |        3. Keluar Aplikasi             |                  ");
        System.out.println("              *****************************************                  ");	
        System.out.println();
        System.out.print("Pilih menu: ");
        menu = Integer.parseInt(in.next());
        
        
        while (!(menu == 1 || menu == 2 || menu == 3 || menu == 4)) {
        	System.out.println("Warning: Menu yang Anda masukkan salah!");
        	System.out.println();
        	System.out.print("Silahkan pilih menu kembali: ");
        	menu = Integer.parseInt(in.next());
        }
        
        switch (menu) {
	        case 1:
	        	FormView.formInsertBookBor();
	        	break;
	        case 2:
	        	FormView.formUpdateBoorBor();
	        	break;
	        case 3:
	        	FormView.formUpdateBoorBor();
	        	break;
	        case 4:
	        	FormView.displayTableBookBor();
	        	break;
	        case 5:
	        	FormView.formUpdateBoorBor();
	        	break;
	        case 9:
	        	System.out.println("Terima kasih, Anda keluar dari aplikasi!");
	        	System.exit(0);
	        default:
	        	System.out.println("Warning: Pilihan yang anda masukkan salah!");  	
        }
	}
	
public static void loginMenu() {
		
		Scanner in = new Scanner(System.in);
		LibrarianDao operation = new LibrarianDaoImpl();
		Librarian user;
		String username = "Lorem01", password = "12345";
//		String username, password;
		boolean login = false;
		
		do {
			System.out.println("\n+-------------------------------------------+");
			System.out.println("|LOGIN                                      |");
			System.out.println("+-------------------------------------------+");
//			System.out.print("| Username      : "); username = in.next();
//			System.out.print("| Password   : "); password = in.next();
			System.out.println("+-------------------------------------------+");
			System.out.println();
			user = operation.getEmailAndPassword(username, password);
			if (user != null) {
				login = true;
				mainMenu();
			} else {
				System.out.println("Email atau password yang Anda masukkan salah, coba lagi...!");
			}
		} while (!login);
		in.close();
		
	}

}
