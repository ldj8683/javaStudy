package chapter3;

import java.util.Scanner;

public class Exercise4 {

	static int binSearch(int[] a, int n, int key) {
		int pl = 0; // 검색범위의 첫 인덱스로 왼쪽부터 시작
		int pr = n - 1; // 검색범위의 끝 인덱스로 오른쪽부터 시작
		do {
			int pc = (pl + pr) / 2; // pl과 pr의 중간에 위치한 요소값
			if (a[pc] == key)
				return pc; // 검색을 성공을 하면 그대로 pc의 값을 반환
			else if (a[pc] < key) // 중앙 요소의 값이 key보다 작은지를 판단
				pl = pc + 1; // 중간 값으로 부터 뒤쪽에 있다고 판단해 pl값을 중간값의 뒤쪽으로 둠
			else // 그외 나머지 (중앙 요소의 값이 key보다 큼)
				pr = pc - 1; // 중간 값으로 부터 앞족에 있다 판단해 pr값을 중간값의 앞으로 이동
		} while (pl <= pr);
		// pl값은 가장 작은 값부터 시작했고, pr값은 가장 큰 값부터 시작했다.
		// 즉, pl이 pr보다 크거나 두 값이 같다면 검색 범위가 더이상 존재하지 않는다는 의미이다.
		// 따라서 pl이 pr보다 작거나 같은 경우만 do-while문을 계속 반복하도록 조건을 pl <= pr로 주었다.

		return -1; // 검색 범위가 끝날때까지 찾지 못한다면 입력한 값들에는 존재하지 않기때문에 검색 실패를 의미
	}

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);

		System.out.print("요솟수 << ");
		int num = data.nextInt();
		int[] x = new int[num];

		System.out.println("오름차순으로 입력해주세요!");

		System.out.print("x[0] << "); // 첫번째 요소를 먼저 입력 받는다.
		// 요소 0을 따로받는 이유는 아래 do-while 문에서 반복을 하는 조건이 앞의 수보다 작은지를 판별하여 반복을 하기 때문이다.
		// 따라서 첫번쨰 수를 do-while 문에 넣게 되면 while부분에서 에러가 발생한다.
		x[0] = data.nextInt();

		for (int i = 1; i < num; i++) { // 이미 첫번쨰 요소를 입력 받았기 때문에 i는 1부터 시작하여야 한다.
			do {
				System.out.print("x[" + i + "] << ");
				x[i] = data.nextInt();
			} while (x[i] < x[i - 1]);	// 입력 받은 수가 앞의 수보다 작으면 다시 입력을 받도록 하는 do-while문을 생성
		}
		System.out.print("검색할 값 << ");
		int ky = data.nextInt();
		data.close();
		
		int idx = binSearch(x, num, ky);
		
		if(idx==-1) {
			System.out.println("그 값을 가진 요소가 없습니다.");
		} else {
			System.out.println(ky + "은(는) x[" + idx + "]에 있습니다.");
		}
	}

}
