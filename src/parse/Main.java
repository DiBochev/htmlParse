package parse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {
	    StringBuilder html = new StringBuilder();
	    BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("E:\\СУ\\2 година\\2 семестър\\www web\\site\\61550.html"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	    String line = null;
	    try {
			while ((line = reader.readLine()) != null) {
			    html.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    Parser p = new Parser(html.toString());
	    if (!p.isEmpty()) {
	    	p.removeTags();
		}
	    System.out.println(p.toString());
	}

}
