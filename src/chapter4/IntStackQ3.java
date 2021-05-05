package chapter4;

public class IntStackQ3 {
	private int max; // 스택의 용량(A, B 합계)
	private int ptrA; // 스택 A의 포인터
	private int ptrB; // 스택 B의 포인터
	private int[] stk; // 스택의 본체

	// enum class: 열겨형 클래스, 서로 연관된 상수들의 집합
	// choice 이라는 상수 타입을 정의
	// choice 타입인 StackA, StackB 를 할당(choice.StackA, choicet.StackB)처럼 사용
	public enum choice {
		StackA, StackB
	}

	// 실행할 때 예외：스택이 비어 있음
	public class EmptyIntStackX2Exception extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public EmptyIntStackX2Exception() {
		}
	}

	// 실행할 때 예외：스택이 가득 참
	public class OverflowIntStackX2Exception extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public OverflowIntStackX2Exception() {
		}
	}

	// 생성자
	public IntStackQ3(int capacity) {
		ptrA = 0;
		ptrB = capacity - 1;
		max = capacity;
		try {
			stk = new int[max]; // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없습니다.
			max = 0;
		}
	}

	// 스택에 x를 푸시
	// choice 타입을 사용하기위해서 push에 choice 타입을 선언
	public int push(choice sw, int x) throws OverflowIntStackX2Exception {
		if (ptrA >= ptrB + 1) // 스택이 가득 참
			throw new OverflowIntStackX2Exception();
		switch (sw) { // StackA를 고르면 ptrA 증가
		case StackA:
			stk[ptrA++] = x;
			break;
		case StackB: // StackB를 고르면 PtrA 감소
			stk[ptrB--] = x;
			break;
		}
		return x;
	}

	// 스택에서 데이터를 팝(꼭대기의 데이터를 꺼냄)
	public int pop(choice sw) throws EmptyIntStackX2Exception {
		int x = 0;
		switch (sw) {
		case StackA:
			if (ptrA <= 0) // 스택 A가 비어 있음
				throw new EmptyIntStackX2Exception();
			x = stk[--ptrA];
			break;
		case StackB:
			if (ptrB >= max - 1) // 스택 B가 비어 있음
				throw new EmptyIntStackX2Exception();
			x = stk[++ptrB];
			break;
		}
		return x;
	}

	// 스택에서 데이터를 피크(꼭대기의 데이터를 살펴 봄)
	public int peek(choice sw) throws EmptyIntStackX2Exception {
		int x = 0;
		switch (sw) {
		case StackA:
			if (ptrA <= 0) // 스택 A가 비어 있음
				throw new EmptyIntStackX2Exception();
			x = stk[ptrA - 1];
			break;
		case StackB:
			if (ptrB >= max - 1) // 스택 B가 비어 있음
				throw new EmptyIntStackX2Exception();
			x = stk[ptrB + 1];
			break;
		}
		return x;
	}

	// 스택에서 x를 검색하여 index(찾지 못하면 -1)를 반환
	public int indexOf(choice sw, int x) {
		switch (sw) {
		case StackA:
			for (int i = ptrA - 1; i >= 0; i--) // 꼭대기쪽부터 선형 검색
				if (stk[i] == x)
					return i; // 검색성공
			break;
		case StackB:
			for (int i = ptrB + 1; i < max; i++) // 꼭대기쪽부터 선형 검색
				if (stk[i] == x)
					return i; // 검색성공
			break;
		}
		return -1; // 검색실패
	}

	// 스택을 비움
	public void clear(choice sw) {
		switch (sw) {
		case StackA:
			ptrA = 0;
			break;
		case StackB:
			ptrB = max - 1;
			break;
		}
	}

	// 스택의 용량을 반환 (A와 B의 합계)
	public int capacity() {
		return max;
	}

	// 스택에 쌓여있는 데이터 수를 반환
	public int size(choice sw) {
		switch (sw) {
		case StackA:
			return ptrA;
		case StackB:
			return max - ptrB - 1;
		}
		return 0;
	}

	// 스택이 비어 있는가?
	public boolean isEmpty(choice sw) {
		switch (sw) {
		case StackA:
			return ptrA <= 0;
		case StackB:
			return ptrB >= max - 1;
		}
		return true;
	}

	// 스택이 가득 찼는가?
	public boolean isFull() {
		return ptrA >= ptrB + 1;
	}

	// 스택 안의 터이터를 바닥 → 꼭대기의 차례로 나타냄
	public void dump(choice sw) {
		switch (sw) {
		case StackA:
			if (ptrA <= 0)
				System.out.println("스택이 비었습니다.");
			else {
				for (int i = 0; i < ptrA; i++)
					System.out.print(stk[i] + " ");
				System.out.println();
			}
			break;
		case StackB:
			if (ptrB >= max - 1)
				System.out.println("스택이 비었습니다.");
			else {
				for (int i = max - 1; i > ptrB; i--)
					System.out.print(stk[i] + " ");
				System.out.println();
			}
			break;
		}
	}

}
