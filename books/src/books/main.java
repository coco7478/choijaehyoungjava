package books;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class main {

	static Scanner scan = new Scanner(System.in);

	static String filepath = "c:\\test\\book.txt";

	public static void main(String[] args) throws IOException {

		System.out.println("도서 관리 프로그램입니다.");
		System.out.println("1. 전체 목록 출력");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 노후 도서 폐기");
		System.out.println("0. 프로그램 종료");

		int choice = -1;

		while (choice != 0) {
			choice = inputChoice();
			switch (choice) {

			case 1:
				printBook();
				break;
			case 2:
				searchBook();
				break;
			case 3:
				insertBook();
				break;
			case 4:
				deleteBook();
				break;
			case 0:
				System.out.println("종료 합니다");
				System.exit(0);
				break;
			default:
				System.out.println("다시 입력해주세요");
			}
		}
	}

	private static int inputChoice() {

		int choice = -1;

		System.out.println("선택 하세요 :");
		choice = scan.nextInt();

		System.out.println("선택한 메뉴 : " + choice);
		System.out.println();

		return choice;
	}
	private static void printBook() throws FileNotFoundException {
		System.out.println("책 출력");
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		int count = 0;
		String str = "";
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				count++;
			}
			br.close();
			System.out.println("출력 권 수 : " + count);
		} catch (IOException e) {
			System.out.println("책 정보를 읽어 올 수 없습니다.");
			System.out.println("저장된 파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
	private static void searchBook() throws FileNotFoundException {
		
		System.out.println("검색 조건");
		System.out.println("1.책이름");
		System.out.println("2.저자");
		System.out.println("3.통합");
		System.out.println("조건 입력 :");
		int choice = scan.nextInt();
		scan.nextLine();
		System.out.println("검색할 키워드를 입력하세요 : ");
		String keyword = scan.nextLine();

		String str = "";
		BufferedReader br = new BufferedReader(new FileReader(filepath));
	
		switch (choice) {
		case 1:
			try {
				int count = 0;
				while ((str = br.readLine()) != null) {
					StringTokenizer tokens = new StringTokenizer(str, "\t");
					String name = tokens.nextToken();
					if (name.contains(keyword)) {
						System.out.println(str);
						count++;
					}

				}
				br.close();
				System.out.println("검색 권 수 : " + count);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				int count = 0;
				while ((str = br.readLine()) != null) {
					StringTokenizer tokens = new StringTokenizer(str, "\t");
					tokens.nextToken();
					String author = tokens.nextToken();
					
					if (author.contains(keyword)) {
						System.out.println(str);
						count++;
					}

				}
				br.close();
				System.out.println("검색 권 수 : " + count);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				int count = 0;
				while ((str = br.readLine()) != null) {

					if (str.contains(keyword)) {
						System.out.println(str);
						count++;
					}

				}
				br.close();
				System.out.println("검색 권 수 : " + count);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("다시 입력해주세요");
		}
	}

	

	private static void insertBook() throws IOException {
		File file = new File("c:\\test");
		if (!file.exists()) {
			file.mkdir();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));

		System.out.println("추가할 책의 정보를 입력하세요: ");

		scan.nextLine();
		System.out.println("책 이름 : ");
		String name = scan.nextLine();

		System.out.println("저 자: ");
		String author = scan.nextLine();

		System.out.println("출판사: ");
		String publisher = scan.nextLine();

		System.out.println("가 격 : ");
		String cost = scan.nextLine();

		System.out.println("입력된 값들 ");
		System.out.println("책 이름 : " + name);
		System.out.println("저 자 : " + author);
		System.out.println("출판사 : " + publisher);
		System.out.println("가격 : " + cost);

		bw.write(name + "\t" + author + "\t" + publisher + "\t" + cost);
		bw.newLine();
		bw.close();
	}

	private static void deleteBook() {
		System.out.println("책 삭제");
		scan.nextLine();
		System.out.println("삭제할 키워드를 입력하세요 : ");

		String keyword = scan.nextLine();

		File copy = new File("c:\\test\\bookcopy.txt");
		File won = new File(filepath);

		String str = "";
		try {
			copy.createNewFile();

			BufferedReader br = new BufferedReader(new FileReader(filepath));
			while ((str = br.readLine()) != null) {

				if (!str.contains(keyword)) {
					BufferedWriter w = new BufferedWriter(new FileWriter(copy, true));
					w.write(str);
					w.newLine();
					w.close();
				}

			}

			br.close();
			won.delete();
			won.createNewFile();

			BufferedReader bfr = new BufferedReader(new FileReader(copy));
			while ((str = bfr.readLine()) != null) {

				BufferedWriter w = new BufferedWriter(new FileWriter(filepath, true));
				w.write(str);
				w.newLine();
				w.close();

			}
			bfr.close();
			copy.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}