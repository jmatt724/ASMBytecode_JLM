package asm;

public class Tests {
	//place to put java code to decompile or asmifiy
	public static void main(String args[]) {
		long i = 56;
		long j = 55;
		
		if(i > j) {
			System.out.println("Second is larger");
		}
		else {
			System.out.println("First is larger");
		}
	}
}
