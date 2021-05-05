package chapter3;

import java.util.Arrays;
import java.util.Scanner;

public class Exercise5 {

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);

		System.out.print("요솟수 << ");
		int num = data.nextInt();
		int[] x = new int[num];

		System.out.println("오름차순으로 입력하세요");
		// 오름차순을 입력받을때 앞에 것과 비교를 하기 때문에
		// 배열의 처음 요소를 먼저 입력 받습니다.
		System.out.print("x[0] << ");
		x[0] = data.nextInt();

		// 배열의 첫 요소를 제외한 나머지를 입력 받는다.
		for (int i = 1; i < num; i++) {
			do {
				System.out.print("x[" + i + "] << ");
				x[i] = data.nextInt();
			} while (x[i] < x[i - 1]);
		}
		System.out.print("검색할 값 << ");
		int ky = data.nextInt();
		
		data.close();

		int idx = Arrays.binarySearch(x, ky);

		if (idx < 0) {
			System.out.println("그 값에 대한 요소값이 없습니다.");
		} else {
			System.out.println(ky + "은(는) x[" + idx + "]에 위치합니다.");
		}
	}

}
