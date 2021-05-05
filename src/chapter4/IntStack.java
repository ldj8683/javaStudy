package chapter4;

public class IntStack {
	private int max; // 스택 용량
	private int ptr; // 스택 포인터
	private int[] stk; // 스택 본체

	// 실행 시 예외 : 스택이 비어 있다
	public class EmptyIntStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public EmptyIntStackException() {
		}
	}

	// 실행 시 예외 : 스택이 가득 차 있다
	public class OverflowIntStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public OverflowIntStackException() {
		}
	}

	// 생성자
	public IntStack(int capacity) {
		ptr = 0;
		max = capacity;
		try {
			// 스책의 본체용 배열 생성
			stk = new int[max];
		} catch (OutOfMemoryError e) {
			// 생성이 불가능
			max = 0;
		}
	}

	// x 값을 스택에 push(집어 넣음)
	public int push(int x) throws OverflowIntStackException {
		if (ptr >= max)	//스택이 가득 참
			throw new OverflowIntStackException();
		return stk[ptr++] = x;
	}

	// 스택에 들어있는 값을 top(정상)에서부터 pop(꺼내줌)
	public int pop() throws EmptyIntStackException {
		if (ptr <= 0) { // 스택이 비어있음
			throw new EmptyIntStackException();
		}
		return stk[--ptr];
	}

	// top(정상)에 있는 데이터를 볼때 peek를 사용함
	public int peek() throws EmptyIntStackException {
		if (ptr <= 0)	// 스택이 비어있음
			throw new EmptyIntStackException();
		return stk[ptr - 1];
	}

	// 스택에서 x 값을 찾아서 인덱스를 반환, x 값이 없다면 -1로 반환
	public int indexOf(int x) {
		for (int i = ptr - 1; i >= 0; i--) {
			if (stk[i] == x)
				return i;
		}
		return -1;
	}

	// 스택을 비워줌
	public void clear() {
		ptr = 0;
	}

	// 스택의 용량을 반환
	public int capacity() {
		return max;
	}

	// 스택에 쌓여 있는 데이터 수를 반환
	public int size() {
		return ptr;
	}

	// 스택이 비어 있는가?
	public boolean isEmpty() {
		return ptr <= 0;
	}

	// 스택이 가득 찼는가?
	public boolean isFull() {
		return ptr >= max;
	}

	// 스택 안의 모든 데이터를 바닥에서부터 꼭대기 순서로 출력
	public void dump() {
		if (ptr <= 0) {
			System.out.print("스택이 비어있습니다.");
		} else {
			for (int i = 0; i < ptr; i++) {
				System.out.println(stk[i] + " ");
			}
			System.out.println();
		}
	}
}
