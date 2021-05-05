package chapter5;

import java.util.Scanner;

public class RecurX1 {
	static void recur(int n) {
		while ( n > 0 ) {
			recur(n-1);
			System.out.println(n);
			n = n-2;
		}
	}
	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		
		System.out.print("정수를 입력 하세요 >> ");
		int x = data.nextInt();
		data.close();
		
		recur(x);
	}

}
