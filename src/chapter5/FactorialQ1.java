package chapter5;

import java.util.Scanner;

public class FactorialQ1 {
	static int factorial(int n) {
		int f = 1;
		while ( n > 1 ) {
			f *= n;
			n--;
		}
		return (f);
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 >> ");
		int x = data.nextInt();
		data.close();
		
		System.out.println(x + " 의 팩토리얼은 [ " + factorial(x) + " ] 입니다.");
	}

}
