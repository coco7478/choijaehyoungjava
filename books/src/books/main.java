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

		System.out.println("���� ���� ���α׷��Դϴ�.");
		System.out.println("1. ��ü ��� ���");
		System.out.println("2. ���� �˻�");
		System.out.println("3. �ű� ���� �߰�");
		System.out.println("4. ���� ���� ���");
		System.out.println("0. ���α׷� ����");

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
				System.out.println("���� �մϴ�");
				System.exit(0);
				break;
			default:
				System.out.println("�ٽ� �Է����ּ���");
			}
		}
	}

	private static int inputChoice() {

		int choice = -1;

		System.out.println("���� �ϼ��� :");
		choice = scan.nextInt();

		System.out.println("������ �޴� : " + choice);
		System.out.println();

		return choice;
	}
	private static void printBook() throws FileNotFoundException {
		System.out.println("å ���");
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		int count = 0;
		String str = "";
		try {
			while ((str = br.readLine()) != null) {
				System.out.println(str);
				count++;
			}
			br.close();
			System.out.println("��� �� �� : " + count);
		} catch (IOException e) {
			System.out.println("å ������ �о� �� �� �����ϴ�.");
			System.out.println("����� ������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		}
	}
	private static void searchBook() throws FileNotFoundException {
		
		System.out.println("�˻� ����");
		System.out.println("1.å�̸�");
		System.out.println("2.����");
		System.out.println("3.����");
		System.out.println("���� �Է� :");
		int choice = scan.nextInt();
		scan.nextLine();
		System.out.println("�˻��� Ű���带 �Է��ϼ��� : ");
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
				System.out.println("�˻� �� �� : " + count);
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
				System.out.println("�˻� �� �� : " + count);
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
				System.out.println("�˻� �� �� : " + count);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("�ٽ� �Է����ּ���");
		}
	}

	

	private static void insertBook() throws IOException {
		File file = new File("c:\\test");
		if (!file.exists()) {
			file.mkdir();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(filepath, true));

		System.out.println("�߰��� å�� ������ �Է��ϼ���: ");

		scan.nextLine();
		System.out.println("å �̸� : ");
		String name = scan.nextLine();

		System.out.println("�� ��: ");
		String author = scan.nextLine();

		System.out.println("���ǻ�: ");
		String publisher = scan.nextLine();

		System.out.println("�� �� : ");
		String cost = scan.nextLine();

		System.out.println("�Էµ� ���� ");
		System.out.println("å �̸� : " + name);
		System.out.println("�� �� : " + author);
		System.out.println("���ǻ� : " + publisher);
		System.out.println("���� : " + cost);

		bw.write(name + "\t" + author + "\t" + publisher + "\t" + cost);
		bw.newLine();
		bw.close();
	}

	private static void deleteBook() {
		System.out.println("å ����");
		scan.nextLine();
		System.out.println("������ Ű���带 �Է��ϼ��� : ");

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