import java.util.ArrayList;
import java.util.Scanner;
public class Question26 {

	public static void main(String[] args) {
		
		int num;
		ArrayList<Integer> div3 = new ArrayList<Integer>();
		
		//keyboard scanner for input
		Scanner Keyboard = new Scanner(System.in);
		
		//receive input for array size
		System.out.println("Please Enter a number for array size ");
		num = Keyboard.nextInt();
		int[] InputArray = new int[num];
		System.out.println("Please enter numbers for array ");
		//loop to add numbers to array
		for(int i = 0; i < InputArray.length; i++) {
			num = Keyboard.nextInt();
			InputArray[i] = num;

			//determine if num is divisible by 3
			if(num %3 == 0) {
				div3.add(num);
			}else {}
		}
		
		//print out divisible numbers
		System.out.println("numbers that are divisible by 3");
		for(int i : div3){
			System.out.println(i);
		}
		
		

	}

}
