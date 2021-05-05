package chapter5;

import java.util.Scanner;

public class RecurQ4 {
	static void recur(int n) {
		if ( n > 0 ) {
			recur(n-2);
			System.out.println(n);
			recur(n-1);
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
