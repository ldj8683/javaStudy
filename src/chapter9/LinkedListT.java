package chapter9;

import java.util.Comparator;

public class LinkedListT<E> {
	//노드
	class Node<E> {
		private E data;			// 데이터를 가리킴
		private Node<E> next;	// 다음 노드를 가리키는 포인터
		
		//생성자
		// 인수로 전달 받은 data, next를 해당 필드에 설정해준다.
		Node(E data, Node<E> next){
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E> head;	// 머리 노드
	private Node<E> tail;	// 꼬리 노드
	private Node<E> crnt;	// 선택 노드
	
	public LinkedListT() {
		head = tail = crnt = null;	// 노드가 하나도 없는 비어있는 연결리스트 생성
	}
	
	// obj: 검색새 key가 되는 데이터를 넣어둔 변수
	// c: obj와 연결리스트의 개별 노드 안에 있는 데이터를 비교하기 위한 comparator
	//					, obj와 선택한 노드의 데이터를 비교해 결과가 0이면 검색 조건이 성립
	public E search(E obj, Comparator<? super E> c) {
		Node<E> ptr = head;		// 포인터가 처음부터 시작할 수 있도록 head로 초기화를 해줘야함
		
		while (ptr != null) {	// ptr이 null이 되면 스캔할 노드가 없는 꼬리 노드까지 이동했음을 의미 
			if(c.compare(obj, ptr.data) == 0) {	// 판단을 할 데이터 obj 와 스캔하고있는 노드 데이터 ptr.data를 comparator c로 비교
				crnt = ptr;			// 참이 되면, crnt에 ptr을 대입
				return ptr.data;	// 찾은 노드의 데이터인 ptr.data 를 반환
			}
			ptr = ptr.next;			// ptr이 다음 노드를 가리키기 때문에 계속 스캔을 할 수 있게 됨
		}
		return null;	// 검색에 실패하면 null을 반환
	}
	
	// 머리 노드에 삽입
	public void addFirst(E obj) {
		boolean empty = (tail == null);
		Node<E> ptr = head;			// 삽입 전에 머리 노드에 대한 포인터 ptr에 대입
		head = crnt = new Node<E>(obj, ptr);
		// 노드의 데이터는 obj가 되고, 뒤쪽 포인터를 가리키는 것은 ptr, 생성한 노드를 참조하게 head와 crnt를 업데이트
		if(empty) tail = crnt;
	}
	
	// 꼬리에 노드 삽입
	public void addLast(E obj) {
		if(head == null)	// 리스트가 비었는지를 확인
			addFirst(obj);	// 비어있을 경우, 머리에 삽입
		else {		// 꼬리 노드를 찾는 구문
			tail.next = crnt = new Node<E>(obj, null);
			tail = crnt;
		}
	}
	
	// 머리 노드를 삭제
	public void removeFirst() {
		if(head != null) {				// 리스트가 비어있지 않으면
			head = crnt = head.next;	// 머리 노드를 다음 것으로 바꿔줌으로써 삭제
			if(head == null) tail = null;
		}
	}
	
	// 꼬리 노드를 삭제
	public void removeLast() {
		if( head != null ) {
			if ( head.next == null )	// 노드가 하나만 있으면 머리 노드를 삭제
				removeFirst();
			else {
				Node<E> ptr = head;		// 스캔중인 노드
				Node<E> pre = head;		// 스캔 중인 노드의 앞쪽(이전) 노드
				
				while (ptr.next != null) {	// while 문을 종료하면, ptr은 꼬리노드를 가리키고
					pre = ptr;				// pre는 그 이전인 꼬리부터 2번째 노드를 가리킨다
					ptr = ptr.next;			// ptr은 꼬리노드를 가리키게 된다
				}
				pre.next = null;		// pre는 삭제 후의 꼬리 노드 ( pre의 next가 null 을 가리키므로 )
				tail = crnt = pre;				// 선택 노드 crnt는 삭제 후의 꼬리 노드를 참조하게 됩니다.
			}
		}
	}
	
	// 노드 p의 삭제
	public void remove(Node<E> p) {
		if (head != null) {
			if ( p == head) {		// p 가 머리노드면 머리노드를 삭제
				removeFirst();
			} else if (p == tail) { // p 가 꼬리 노드에 있으면, 꼬리노드를 삭제
				removeLast(); 
			} else {				// 노드 p의 앞쪽 노드를 찾는 구문입니다.
				Node<E> ptr = head;	// ptr은 머리노드붙터 시작하기 위해 초기회ㅏ				
				while (ptr.next != p) {	// while 문은 선택 노드 ptr의 뒤쪽 포인터 ptr.next가 p와 같을떄까지 반복
					ptr = ptr.next;		// ptr이 계속해서 다음 노드로 이동
					if(ptr == null) return;		// p가 리스트에 없음다면 삭제처리를 하지 않고 반환에 의해서 메서드 식ㄹ행을 마침
				}
				ptr.next = p.next;	// 삭제할 노드의 다음 포인터인 p.next를 그 앞 노드(삭제노드 이전노드)의 뒤쪽 포인터 ptr.next에 대입
				crnt = ptr;			// 삭제 노드 이전 노드의 뒤쪽 포인터가 참조하는 곳을 삭제노드의 다음노드로 업데이트 합니다.
			}
		}
	}
	
	// 선택 노드를 삭제
	public void removeCurrentNode() {	// 선택 노드가 참조하는 곳은 삭제한 노드의 앞쪽 노드로 업데이트 됩니다.
		remove(crnt);
	}
	
	// 모든 노드를 삭제
	public void clear() {
		while (head != null)	// 노드에 아무것도 없을때까지 머리노드를 삭제합니다.
			removeFirst();
		crnt = null;			// 리스트가 비어있게 되서 선택 노드값도 null로 만들어줍니다.
	}
	
	// 선택 노드를 하나 뒤쪽으로 이동
	public boolean next() {
		if( crnt == null || crnt.next == null )		// 리스트가 비었거나, 다음노드가 없다면 이동이 불가능
			return false;		// 이동이 불가능, 선택 노드가 이동하지 않으면 false를 반환
		crnt = crnt.next;		// 그 외에 리스트가 비어있지 않고, 선택노드의 뒤쪽 노드가 있을 경우 하나 이동합니다.
		return true;			// 선택 노드가 이동하면 true
	}
	
	// 선택 노드 출력
	public void printCurrentNode() {
		if(crnt == null)
			System.out.println("선택한 노드가 없습니다.");
		else
			System.out.println(crnt.data.toString());	// 선택 노드가 가리키고있는 데이터를 출력
	}
	
	// 모든 노드를 출력
	public void dump() {
		Node<E> ptr = head;	// 처음 노드부터 시작하기위해서 ptr을 머리노드로 초기화
		
		while ( ptr != null ) {	// null이 아닌동안 반복하므로 꼬리노드까지 반복을 하게 생성
			System.out.println(ptr.data);	// 반복을하면서 해당 ptr의 데이터를 출력
			ptr = ptr.next;					// 출력을하고 ptr을 다음으로 옮김
		}
	}
	
	// 연습문제 9-1
	// comparator c 메서드를 사용해 서로 같은 노드를 찾아 모두 삭제해 다음 메서드를 작성
	public void purge(Comparator<? super E> c) { //자식 클래스가 부모 클래스로부터 상속받은 멤버를 참조할 때 사용하는 참조 변수
		Node<E> ptr = head; // 처음부터 시작하기위해서 ptr을 머리노드로 보내줌
		
		while(ptr != null) { // 끝까지 갈때까지 반복
			int count = 0;	// 같은 노드가 있을 경우를 알기 위해서 선언을 해줌, 반복할때마다 0으로 초기화해줌
			// ptr2, pre 포인터를 생성해서 ptr를 저장해서 마찬가지로 head에 위치함
			Node<E> ptr2 = ptr;	// ptr이 가리키는 데이터와 비교를 하기 위해 사용하는 ptr2
			Node<E> pre = ptr;	// ptr2를 이동시키기 위해서 사용함
			
			while(pre.next != null){	// pre 포인터가 끝까지 이동할때까지 반복
				ptr2 = pre.next;	// 끝이아니라면 ptr2 또한 다음으로 넘겨주기 위해서 대입
				if(c.compare(ptr.data, ptr2.data) == 0) {	//현재 ptr이 가리키는 데이터와 ptr2가 가리키는 데이터가 같은지 비교
					pre.next = ptr2.next;	// ptr과 ptr2가 같을 경우 pre를 ptr2가 가르키는 다음 노드로 이동시키기 위한 구문
					count++;	// 같을경우 아래 if-else 문에서 해당 ptr의 데이터를 저장하기 위해서 1 증가시켜줌
				}else
					ptr=ptr2;	// ptr을 다음으로 이동시켜줌(pre.next 때문에)
			}
			if(count == 0) ptr = ptr.next; // 같은 것이 없을 경우 ptr을 옆으로 이동
			else {	// 같은 것이 있을 경우 아래 구문을 실행
				Node<E> temp = ptr;	// temp라는 노드에 ptr을 대입해서 임시 저장 찾은 위치에서 계속 하기 위해서 저장
				remove(ptr);		// 같은것중 하나를 없에야 하기 때문에 제거해줌
				ptr = temp.next;	// 제거가 끝난후 다음으로 넘거가기 위해서 temp 포인터를 다음으로 옮기고 ptr에 저장해서 계속함
			}
		}
		crnt = head;	// 선택노드를 head로 이동
	}
	// --------------------------------
	
	// 연습문제 9-2
	// E retrieve(int n), 머리부터 n개 뒤의 노드에 대한 참조를 반환하는 다음의 메서드를 작성.
	public E retrieve(int n) {
		Node<E> ptr = head;	// ptr을 머리노드로 이동

		while (n >= 0 && ptr != null) {	// n이 양수이면서 리스트 끝까지 갈 경우까지 반복 
			if (n-- == 0) {			// n이 0이 될때까지 반복하면서 n을 계속해서 1씩 줄여줌
				crnt = ptr;			// 포인터를 선택 노드에 대입
				return ptr.data;	// 검색에 성공을 했을때 포인터가 가리키는 데이터를 반환
			}
			ptr = ptr.next; // 포인터를 다음으로 이동시켜줌
		}
		return (null);	// n이 음수거나 노드 개수보다 크거나 같으면 null을 반환
	}
	// --------------------------------
	
}
