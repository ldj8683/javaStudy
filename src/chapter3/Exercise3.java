package chapter3;

import java.util.Scanner;

public class Exercise3 {

	static int seqSearch(int[] a, int n, int key) {
		int i = 0;
		a[n] = key;	// 보초 값을 생성
		while (true) {
			if (a[i] == key)
				break;
			i++;	// 키값이 아닐경우 인덱스 i를 1씩 증가
		}
		return i == n ? -1 : i;
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);

		System.out.print("요솟수 << ");
		int n = data.nextInt();
		int[] x = new int[n + 1]; // 보초 값이 들어갈 배열공간을 하나 추가로 생성

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
