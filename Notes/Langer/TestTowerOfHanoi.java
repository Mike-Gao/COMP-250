package demos.recursion;

public class TestTowerOfHanoi {

	static void tower(int n, String start, String finish, String other){
		if (n > 0){
			tower(n-1, start, other, finish);
			System.out.println("move from " + start + " to " + finish);
			tower(n-1, other, finish, start);
		}
	}

	public static void main(String[] args) {

		tower(4, "A", "B", "C");

	}

}
