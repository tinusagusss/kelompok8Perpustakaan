package org.itenas.oop.uas.form;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.itenas.oop.uas.dao.BookBorDao;
import org.itenas.oop.uas.daoImpl.BookBorDaoImpl;
import org.itenas.oop.uas.display.Display;
import org.itenas.oop.uas.entity.BookBor;

public class FormView {
	
	static Scanner scanner = new Scanner(System.in);
	static char back;
	static BookBor bookBor = new BookBor();
	static BookBorDao operation = new BookBorDaoImpl();
	
	private static final Pattern[] regex = new Pattern[2];
	static {
		regex[0] = Pattern.compile(".*[0-9].*");
		regex[1] = Pattern.compile(".*[A-Z].*");
		}
	
	public static void backToMainMenu() {
        System.out.print("[B] Tekan Tombol B untuk kembali ke menu sebelumnya: ");
		back = scanner.next().charAt(0);
		if (back == 'B' || back == 'b')
			Display.mainMenu();
	}
	
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
        operation.inserBookBor(bookBor);
        backToMainMenu();
	}
	
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
		boolean check = false;
		String code;
		do {
        code = scanner.next();
        check = isMatchingRegex(code);
        } while (!check);
		System.out.println();
		System.out.println("    | ------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println("    |							Daftar Peminjaman Buku				                                        |");										
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println("    |\tKode Pinjam\t|\tNomor ISBN\t|\tKode Peminjaman\t|\tTanggal Pinjam\t|\tBatas Pengembalian\t|\tstatus\t|");
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		for (BookBor data : operation.findByCode(code)) {
			System.out.println("    |\t"+data.getCode()+"\t\t|\t"+data.getIsbn()+"\t\t|\t"+data.getIdMem()+"\t\t|\t"+data.getStartDate()+"\t|\t"+data.getDueDate()+"\t\t| \t"+data.isStatus()+"\t|");
		}
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		backToMainMenu();
	}
	

	public static void displayTableBookBor() {
		System.out.println();
		System.out.println("    | ------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println("    |							Daftar Peminjaman Buku				                                        |");										
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println("    |\tKode Pinjam\t|\tNomor ISBN\t|\tKode Peminjaman\t|\tTanggal Pinjam\t|\tBatas Pengembalian\t|\tstatus\t|");
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		for (BookBor data : operation.getAllBookBor()) {
			System.out.println("    |\t"+data.getCode()+"\t\t|\t"+data.getIsbn()+"\t\t|\t"+data.getIdMem()+"\t\t|\t"+data.getStartDate()+"\t|\t"+data.getDueDate()+"\t\t| \t"+data.isStatus()+"\t|");
		}
		System.out.println("    |-------------------------------------------------------------------------------------------------------------------------------------------|");
		backToMainMenu();
	}
	
	private static boolean isMatchingRegex(String input) {
	    boolean inputMatches = true;
	    for (Pattern inputRegex : regex) {
	        if (!inputRegex.matcher(input).matches()) {
	            inputMatches = false;
	        }
	    }
	    return inputMatches;
	}
}
