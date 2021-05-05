package chapter9;

import java.util.Comparator;
// 원형 이중 연결 리스트 클래스

public class DblLinkedList<E> {
	// 노드
	class Node<E> {
		private E data;			// 데이터
		private Node<E> prev;	// 앞쪽 포인터 (앞쪽 노드에 대한 참조)
		private Node<E> next;	// 뒤쪽 포인터 (다음 노드에 대한 참조)

		// 생성자
		// data는 null 값이고
		// 왼쪽을 가리키는 prev와 오른쪽을 가리키는 next 모두 this(자기자신)을 가리키는 노드 생성
		Node() {
			data = null;
			prev = next = this;
		}

		// 생성자
		Node(E obj, Node<E> prev, Node<E> next) {					
			data = obj;	// 노드 안의 data(데이터)가 obj 의 파라미터를 통해서 저장
			this.prev = prev;	// 해당 노드에서 왼쪽을 가리키는 앞쪽을 가리키는 포인터
			this.next = next;	// 해당 노드에서 앞쪽을 가리키는 뒤쪽을 가리키는 포인터
		}
	}

	private Node<E> head;		// 머리 노드 생성
	private Node<E> crnt;		// 선택 노드 생성

	// 생성자
	public DblLinkedList() {
		head = crnt = new Node<E>();	// 더미 노드를 생성
										// 데이터를 갖지 않는 노드를 생성해서 계속 유지 시켜줌
										// 그 이유는, 삽임과 삭제를 원활하게 만들어 주기 때문
	}

	// 리스트가 비어있는지를 조사하는 메서드
	public boolean isEmpty() {
		// 더미노드에서 next를 통해서 다음을 가리키는 노드가 자기 자신인가를 확인
		return head.next == head;	
	}

	// 노드를 연결 검색
	public E search(E obj, Comparator<? super E> c) {
		// 현재 스캔 중인  노드, 머리노드부터 시작
		// head가 아닌 head.next부터인 이유는 이중연결 리스트는 더미 노드부터 시작하기 때문에
		Node<E> ptr = head.next;

		// 리스트 끝까지 갈때까지 반복(원형 이중연결 리스트이기때문에 null이 아닌 head)
		while (ptr != head) {
			// 입력받은 데이터와 리스트 안의 포인터가 가리키는 데이터가 같은지 확인
			// 두 데이터를 비교한 결과가 0일 경우에 검색에 성공한 것임
			if (c.compare(obj, ptr.data) == 0) {
				crnt = ptr;		// 선택 노드 crnt는 찾은 노드 ptr을 가리키도록 설정
				return ptr.data;			// 검색 성공
			}
			ptr = ptr.next;	// 현재 ptr이 가리키는 노드의 데이터가 같지 않을경우 ptr을 다음으로 넘겨줌
		}
		return null; // 끝까지 검색이 되지 않을 경우 null을 반환
	}

	// 선택 노드를 출력 
	public void printCurrentNode() {
		if (isEmpty())
			System.out.println("선택 노드가 없습니다.");
		else
			System.out.println(crnt.data);//crnt.data 는 현재 선택 노드의 데이터를 출력
	}

	// 모든 노드를 출력
	public void dump() {
		Node<E> ptr = head.next;			// 더미 노드의 다음 노드

		while (ptr != head) {	// 연결 리스트가 끝낼때가지 ptr이 하나씩 이동하면서 해당 데이터 출력
			System.out.println(ptr.data);
			ptr = ptr.next;
		}
	}

	// 모든 노드를 거꾸로 출력
	// prev는 현재 노드의 왼쪽을 가리키는데, 원형 리스트이기때문에 head의 왼쪽은 리스트의 맨 뒤를 가리키고 있음
	public void dumpReverse() {
		Node<E> ptr = head.prev;	// ptr의 위치를 리스트의 맨뒤로 이동시킴

		while (ptr != head) {	// 리스트가 끝날때까지 반복
			System.out.println(ptr.data);	// ptr이 가리키고있는 노드의 데이터를 출력
			ptr = ptr.prev;		// ptr을 왼쪽(앞쪽)으로 이동시킴
		}
	}

	// 선택 노드를 하나 뒤쪽으로 이동
	public boolean next() {
		if (isEmpty() || crnt.next == head)	// 비어있을 경우에는 이동을 할수 없도록 설정
			return false;
		crnt = crnt.next;	// 선택 노드를 다음(오른쪽)으로 넘김
		return true;		// 넘기고 난뒤 넘겼다는 의미로 true를 반환
	}

	// 선택 노드를 하나 앞쪽으로 이동
	// 위의 next 메서드와 같은 방식이지만 반대로 앞으로 이전(왼쪽)으로 넘김 
	public boolean prev() {
		if (isEmpty() || crnt.prev == head)
			return false;
		crnt = crnt.prev;
		return true;
	}

	// 선택 노드의 바로 뒤에 노드를 삽입
	public void add(E obj) {
		// 삽입이 될 새로운 노드를 생성, obj는 데이터, crnt는 (선택 노드가 가리키는)앞쪽 포인터
		// crnt.next는 선택 노드가 가리키는 노드의 다음 노드
		// 즉, 선택 노드의 바로 뒤에 생성을 하기 위해서
		// 새로 생성되는 노드를 연결하기 위해서는 새 노드의 prev, next를 기존에 있던 리스트에서
		// 선택 노드를 prev와 연결하고 선택 노드의 다음을 next와 연결을 해주어야함
		Node<E> node = new Node<E>(obj, crnt, crnt.next);
		
		// crnt.next = node
		// crnt.next는 선택 노드의 다음노드를 의미하며, 선택노드의 다음 노드를 새 노드로 바꾸어줌
		// crnt.next.prev = node
		// crnt.next.preve는 선택 노드의 다음노드의 prev를 의미
		// 이 말은 선택 노드의 다음노드의 prev가 원래는 선택 노드를 가리키고 있는데 이것을 새로 생성되는 노드로 바꾸어줌 
		crnt.next = crnt.next.prev = node;
		
		// 선택 노드가 새로 삽입한 노드를 가리킴
		crnt = node;
	}

	// 머리에 노드를 삽입 
	public void addFirst(E obj) {
		crnt = head;				// 더미 노드 head의 바로 뒤에 삽입
		add(obj);
	}

	// 꼬리에 노드를 삽입
	public void addLast(E obj) {
		// 우리가 기본적으로 리스트의 맨마지막이 정확히 어디있는지는 알수 없다.
		// 하지만 확실한것은 head의 이전 노드는 꼬리노드라는 사실이다
		// 따라서 꼬리노드에 삽입을 하기 위해서는 head의 왼쪽노드로 이동을 시킨다면,
		// 선택 노드(crnt)가 꼬리노드로 이동이 되어있을 것임
		crnt = head.prev;
		add(obj);
	}

	// 선택 노드를 삭제
	public void removeCurrentNode() {
		if (!isEmpty()) {// !는 부정을 의미함 즉 여기서는 비어있지 않는 경우에 아래 구문 실행
			crnt.prev.next = crnt.next; // 선택 노드의 앞쪽(왼쪽) 노드의 next를 선택 노드의 다음 노드로 연결시켜줌
			crnt.next.prev = crnt.prev; // 선택 노드의 뒤쪽(오른쪽) 노드의 prev를 선택 노드의 이전 노드로 연결시켜줌
			crnt = crnt.prev;	// 선택 노드의 이전 노드로 이동을 시켜줌
			
			// 이전으로 이동시켰을떼 선택노드가 head를 가리키면 다시 다음 노드로 옴겨줌
			if (crnt == head) crnt = head.next;	
		}
	}

	// 노드 p를 삭제
	public void remove(Node p) {
		Node<E> ptr = head.next;	// ptr을 맨 앞으로 이동

		while (ptr != head) {		// ptr이 리스트를 전부 확인할떄까지 반복
			if (ptr == p) {			// ptr이 입력된 p와 같은 경우 아래 구문을 수행
				crnt = p;			// 선택 노드를 p와 같은 위치로 이동시켜줌
				removeCurrentNode();	// 이동된 crnt에 의해서 선택 노드 삭제를 실행
				break;				// 마저 while문을 돌지 않고 while문 종료
			}
			ptr = ptr.next;		// ptr을 계속 다음으로 이도잇킴
		}
	}

	// 머리 노드를 삭제
	public void removeFirst() {
		crnt = head.next;			// 선택 노드를 머리노드로 이동시킴
		removeCurrentNode();		// 이동시킨 선택 노드를 삭제
	}

	// 꼬리 노드를 삭제
	public void removeLast() {
		crnt = head.prev;			// 선택 노드를 꼬리 노드로 이동시킴
		removeCurrentNode();		// 선택 노드 삭제
	}

	// 모든 노드를 삭제
	public void clear() {
		while (!isEmpty())			// 리스트가 비어질때까지 반복
			removeFirst();			// 머리 노드를 삭제 해줌
	}
	
	// 연습문제 9-9
	public void purge(Comparator<? super E> c) {
		Node<E> ptr = head.next;	// 머리노드로 ptr 이동

		while (ptr.next != head) {	// 전체 반복
			int count = 0;			// count를 반복 할때 마다 0으로 초기화
			Node<E> ptr2 = ptr;		// 비교대상이 될 ptr2
			Node<E> pre = ptr;		// 미리 이동해주는 가이드같은 역할

			while (pre.next != head) {	// pre가 끝가지 갈때까지 반복
				ptr2 = pre.next;	// ptr2를 다음 노드로 이동시켜줌
				// 현재 노드 ptr의 데이터와 ptr2가 가리키는 비교가 될 노드의 데이터를 비교
				// 두 데이터가 같을 경우 0을 반환 받음
				if (c.compare(ptr.data, ptr2.data) == 0) { 
					pre.next = ptr2.next; // ptr2가 가리키는 노드의 다음 노드와 pre의 다음노드를 일치시켜줌
					count++;	// 삭제를 하기위해서 count를 1로 만들어줌
				} else	// 같은 데이터가 없을경우
					pre = ptr2; // 다음으로 넘어가기 위해서 pre를 ptr2의 위치로 가져옴
			}
			if (count == 0)	// count가 0이라면, 같은데이 터를 찾지 못했다는말, ptr을 다음으로 넘겨줌
				ptr = ptr.next;
			else {			// 위에서 같은데이터를 찾았을 
				Node<E> temp = ptr;	// temp 노드를 생성해 ptr을 임시로 저장
				remove(ptr);		// ptr이 가리키는 노드 삭제
				ptr = temp.next;	// temp에 저장된 ptr이 가리키는 노드의 다음 노드를 ptr로 저장해서 다음으로 넘어감
			}
		}
		crnt = head; // 현재 노드를 head로 옮겨줌
	}
	
	// 연습문제 9-10
	// E retrieve(int n), 머리부터 n개 뒤의 노드에 대한 참조를 반환하는 다음의 메서드를 작성.
	public E retrieve(int n) {
		Node<E> ptr = head.next;// ptr을 머리노드로 이동

		while (n >= 0 && ptr.next.next != head) { // 입력받은 n이 양수이면서, 끝까지 갈때까지 반복
			if (n-- == 0) {	//반복이 될 때마다 n이 1씩 감소
				crnt = ptr;	// crnt를 ptr의 위치로 이동
				return ptr.data; // 검색해서 성공한 데이터를 반환
			}
			ptr = ptr.next; // ptr을 다음으로 이동
		}
		return (null);	// n이 음수이거나 비어있을 경우 null을 반환
	}
}