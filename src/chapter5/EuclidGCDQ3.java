package chapter5;

import java.util.Scanner;

public class EuclidGCDQ3 {
	// 정수 x, y의 최대공약수를 구해 반환
	static int gcd(int x, int y) {
		while (y != 0) {
			int temp = y;
			y = x % y;
			x = temp;
		}
		return x;
	}

	static int gcdArray(int a[], int start, int no) {
		if (no == 1)
			return a[start];
		else if (no == 2)
			return gcd(a[start], a[start + 1]);
		else
			return gcd(a[start], gcdArray(a, start + 1, no - 1));
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		System.out.println("최대공약수를 구합니다.");
		System.out.print("몇개의 수를 입력하시겠습니까?");

		int num;
		do {
			num = data.nextInt();
		} while (num <= 1);

		int[] x = new int[num];
		for (int i = 0; i < num; i++) {
			System.out.print("x[ " + i + " ] : ");
			x[i] = data.nextInt();
		}
		data.close();

		System.out.println("최대공약수는 [ " + gcdArray(x, 0, num) + " ] 입니다.");
	}

}
