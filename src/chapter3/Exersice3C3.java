package chapter3;

public class Exersice3C3 {
	static class GenericClass<T>{	// 제너릭 클래스의 파라미터는 일반적으로 T 라고 작성합니다.
		private T xyz;
		GenericClass(T t){
			this.xyz = t;
		}
		T getXyz() {
			return xyz;
		}
	}
	public static void main(String[] args) {
		GenericClass<String> s = new GenericClass<String>("ABC");
		GenericClass<Integer> n = new GenericClass<Integer>(15);
		
		System.out.println(s.getXyz());
		System.out.println(n.getXyz());
	}

}
