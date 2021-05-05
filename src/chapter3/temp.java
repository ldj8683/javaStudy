package chapter3;

public class temp {

	public static void main(String[] args) {
		int[] x = { 1, 2, 3, 4, 5, 6, 7 };
		int key = 5;
		
		System.out.print("   |");
		for(int i = 0 ; i < x.length; i++) {
			System.out.printf("%3d",i);
		}
		System.out.print("\n---+");
		for(int i = 0 ; i < x.length; i++) {
			System.out.print("---");
		}
		System.out.println("--");
		for(int i = 0 ; i < x.length; i++) {
			System.out.print("   |  ");
			for(int j = 0 ; j < i ; j++) {
				System.out.print("   ");
			}
			System.out.print("*\n");
			System.out.printf("%3d|",i);
			for(int j = 0 ; j < x.length; j++) {
				System.out.printf("%3d",x[j]);
			}
			System.out.println();
			if(x[i] == key) break;
		}
	}

}
