package chapter4;

import java.util.Scanner;

public class IntStackTeaster {

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		IntStack s = new IntStack(64); // 최대 64개를 넣을수 있는 스택

		while (true) {
			System.out.println("현재 데이터 수: " + s.size() + "/" + s.capacity());
			System.out.println("1.push\t2.pop\t3.peek\t4.dump\t0.exit");

			int menu = data.nextInt();
			if (menu == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			int x;
			switch (menu) {
			case 1:
				System.out.print("데이터: ");
				x = data.nextInt();
				try {
					s.push(x);
					System.out.println("데이터 " + x + "가 스택에 들어갔습니다.");
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;
			case 2:
				try {
					x = s.pop();
					System.out.println("pop을 한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("peek를 한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 4:
				s.dump();
				break;
			}
		}
		data.close();
	}
}
