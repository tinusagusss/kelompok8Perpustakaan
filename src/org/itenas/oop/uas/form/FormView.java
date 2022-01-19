package org.itenas.oop.uas.form;

import java.util.*;

import org.itenas.oop.uas.dao.BookBorDao;
import org.itenas.oop.uas.daoImpl.BookBorDaoImpl;
import org.itenas.oop.uas.display.Display;
import org.itenas.oop.uas.entity.BookBor;

public class FormView {
	
	static Scanner scanner = new Scanner(System.in);
	static char back;
	static BookBor bookBor = new BookBor();
	static BookBorDao operation = new BookBorDaoImpl();
	
	public static void backToMainMenu() {
        System.out.print("[B] Tekan Tombol B untuk kembali ke menu sebelumnya: ");
		back = scanner.next().charAt(0);
		if (back == 'B' || back == 'b')
			Display.mainMenu();
	}
	
	/* Memasukan Data Peminjaman Buku */
	public static void formInsertBookBor() {
		System.out.println("              =========================================                 ");
        System.out.println("              |      Form Insert Peminjaman Buku      |                  ");
        System.out.println("              =========================================                  ");
        System.out.print("              | ISBN		        :");
        bookBor.setIsbn(scanner.next());
        System.out.print("              | ID Member               :");
        bookBor.setIdMem(scanner.next());
        System.out.println("              =========================================					 ");
        bookBor.setCode(operation.getCodeBookBor());
        operation.saveBookBor(bookBor);
//        backToMainMenu();
	}
	
	/* Pengembalian Buku */
	public static void formUpdateBoorBor() {
		System.out.print("Masukkan code peminjaman: ");
        bookBor.setCode(scanner.next());
        int nilai = operation.getDayDifference(bookBor);
        if (nilai < 0) {
        	System.out.println("Denda : " + bookBor.getRansom(nilai));
        	operation.PaymentBookBor(bookBor);
		} else {
			System.out.println("Tidak ada Denda");
		}
        operation.updateBookBor(bookBor);
        backToMainMenu();
	}
//	
	public static void formDeleteAutomatic() {
        System.out.print("Apakah Anda yakin untuk menghapus data?");
		back = scanner.next().charAt(0);
		if (back == 'y' || back == 'Y')
			operation.deleteBookBor();
		backToMainMenu();
	}
	
	public static void formDeleteBookBor() {
		System.out.print("Masukkan code peminjaman: ");
        bookBor.setCode(scanner.next());
        System.out.print("Apakah Anda yakin untuk menghapus data?");
		back = scanner.next().charAt(0);
		if (back == 'y' || back == 'Y')
			operation.deleteBookBor(bookBor);
        backToMainMenu();
	}

	public static void formSearchBookBorByCode() {
		System.out.print("Masukkan code peminjaman: ");
        bookBor.setCode(scanner.next());
//		operation.getBookBorByCode(bookBor);	
        backToMainMenu();
	}
	
	/* Tugas 1: Step 1: Perbaiki Tampilan pada displayTableBookBor*/
	public static void displayTableBookBor() {
		System.out.println();
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |							DATA KARYAWAN TETAP					     |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		System.out.println("    |\tNIP\t\t|\tNama\t\t\t|       Email       			|       Total Gaji   |");
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		for (BookBor data : operation.getAllBookBor()) {
			System.out.println("    |\t"+data.getCode()+"\t|       "+data.getIsbn()+"\t        |       "+data.getIdMem()+"\t|\t"+data.getStartDate()+"    |");
		}
		System.out.println("     ----------------------------------------------------------------------------------------------------------------");
		backToMainMenu();
	}
}
