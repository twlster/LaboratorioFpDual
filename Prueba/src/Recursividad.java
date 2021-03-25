
public class Recursividad {

	public static void main(String[] args) {
		System.out.println(new Recursividad().bunnyEars2(6));
	}

	public int bunnyEars2(int bunnies) {
		return bunnies == 0 ? 0 : bunnies % 2 == 0 ? 3 + bunnyEars2(bunnies - 1) : 2 + bunnyEars2(bunnies - 1);
	}

}