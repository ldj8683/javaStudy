package chapter5;

import java.util.Scanner;

public class EuclidGCD {
	// 정수 x, y의 최대공약수를 구해 반환
	static int gcd(int x, int y) {
		if (y == 0) {
			return x;
		} else {
			return gcd( y, x % y);
		}
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		System.out.println("두 정수의 최대공약수를 구합니다.");

		System.out.print("첫번쨰 정수를 입력하세요 >> ");
		int x = data.nextInt();
		System.out.print("두번째 정수를 입력하세요 >> ");
		int y = data.nextInt();
		data.close();
		
		System.out.println("최대공약수는 [ " + gcd(x, y) + " ] 입니다.");
	}

}
