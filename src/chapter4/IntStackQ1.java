package chapter4;

import java.util.Scanner;

public class IntStackQ1 {

	public static void main(String[] args) {
		Scanner data = new Scanner(System.in);
		IntStack s = new IntStack(64); // 최대 64개를 넣을수 있는 스택

		while (true) {
			// 메뉴 선택하는 것을 입력
			System.out.println("현재 데이터 수: " + s.size() + "/" + s.capacity());
			System.out.println("1.push\t2.pop\t3.peek\t4.dump\t5.index\t6.clear\t7.information\t0.exit");
			System.out.print("원하는 것에 해당하는 번호를 눌러주세요! >> ");
			int menu = data.nextInt();

			// 입력받은 데이터가 0 일떄 종료
			if (menu == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			int x; // 저장이 될 데이터를 받아주는 변수
			// switch-case 문을 가지고 입력 받은 각 메뉴의 숫자에 따라서 각 case 를 수행, break로 switch-case문을 나온다
			switch (menu) {
			case 1:// push
				System.out.print("데이터 >> ");
				x = data.nextInt();
				try {
					s.push(x);
					System.out.println("데이터 " + x + "가 스택에 들어갔습니다.");
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;
			case 2:// pop
				try {
					x = s.pop();
					System.out.println("pop을 한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 3:// peek
				try {
					x = s.peek();
					System.out.println("peek를 한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 4:// dump
				s.dump();
				break;
			case 5:// index
				System.out.print("찾고 싶은 데이터 >> ");
				x = data.nextInt();
				int i = s.indexOf(x);
				;
				if (i >= 0) {
					System.out.println("찾고 싶은 데이터 " + x + "의 인덱스는 위에서 " + (s.size() - i) + "번 쨰에 위치합니다.");
				} else {
					System.out.println("데이터가 존재하지 않아요 ㅠㅠ.");
				}
				break;
			case 6:
				s.clear();
				break;
			case 7:
				System.out.println("용량 >> " + s.capacity());
				System.out.println("데이터 수 >> " + s.size());
				System.out.println("Stack이 현재 비어" + (s.isEmpty() ? " 있습니다." : " 있지 않습니다."));
				System.out.println("Stack이 현재 가득" + (s.isFull() ? " 찼습니다." : " 차지 않았습니다."));
			}
			data.close();
		}
	}

}
