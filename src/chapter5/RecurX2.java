package chapter5;

import java.util.Scanner;

public class RecurX2 {
	static void recur(int n) {
		IntStack s = new IntStack(n);
		while (true) {
			if (n > 0) {
				s.push(n);					// n의 값을 push
				n = n - 1;
				continue;
			}
			if (s.isEmpty() != true) {		// 스택이 비었는지의 여부를 확인
				n = s.pop();				// 저장한 스택의 값을 pop
				System.out.println(n);
				n = n - 2;
				continue;
			}
			break;
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
