package task;

import java.util.ArrayList;
import java.util.Arrays;

import classes.Engine;
import classes.Floor;
import classes.Helper;
import classes.Lift;
import classes.User;

public class App {

	//System.out.println(44);
	//for (int i = 0, len =  - 1; i <= len; i++) {}
	public static void main(String[] args) {
		new Engine().start();
		
//		App app = new App();
//		app.displayMatrix();
	}
	
	private void displayMatrix() {
//		String[][] strings = {{"aaa"}, {"bbb"}, {"ccc"}};
////		String[] first = strings[0];
//		//System.out.println(Arrays.toString(first)); // => [cheeki, breeeki]
//		strings[0][0] = "a222";
		
		
		String s1 = "s1";
		char ch1 = '|';
		StringBuffer b = new StringBuffer();
		b.append(s1);
		b.append(ch1);
		b.append(s1);
		
		System.out.println(b); // => v damki
	}
}

















