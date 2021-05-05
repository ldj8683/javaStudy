package chapter3;

import java.util.Scanner;

public class Exercise3Q1 {

	static int seqSearch(int[] a, int n, int key) {
		int i;
		a[n] = key;

		for (i = 0; i < n; i++) {
			if (a[i] == key)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);

		System.out.print("요솟수 << ");
		int n = data.nextInt();
		int[] x = new int[n + 1];

		for (int i = 0; i < n; i++) {
			System.out.print("x[" + i + "] << ");
			x[i] = data.nextInt();
		}

		System.out.print("검색할 값 << ");
		int ky = data.nextInt();
		data.close();

		int idx = seqSearch(x, n, ky);

		if (idx == -1)
			System.out.println("그 값을 가진 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 위치하고 있습니다.");

	}

}
