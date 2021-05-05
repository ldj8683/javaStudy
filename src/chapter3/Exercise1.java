package chapter3;

import java.util.Scanner;

public class Exercise1 {

	static int seqSearch(int[] a, int n, int key) {
		int i = 0;
		while (true) {
			if (i == n)	// 배열이 끝까지 가서 못찾았을 경우 return -1
				return -1;
			if (a[i] == key)	// 요소중의 값에서 해당하는 key값이 있을때 return 1
				return 1;
			i++;
		}
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);

		System.out.print("요솟수 << ");
		int n = data.nextInt();
		int[] x = new int[n];

		for (int i = 0; i < n; i++) {
			System.out.print("x[" + i + "] << ");
			x[i] = data.nextInt();
		}

		System.out.print("검색할 값 << ");
		int ky = data.nextInt();
		data.close();

		int idx = seqSearch(x, n, ky);

		if (idx == -1)
			System.out.println("그 값의 요소가 없습니다.");
		else
			System.out.println(ky + "은(는) x[" + idx + "]에 위치하고 있습니다.");

	}

}
