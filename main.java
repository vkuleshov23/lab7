package prog.lab7;
import prog.lab7.settings.*;

public class main {
	public static void main(String[] args){  //load txt , save txt , save bin, load bin

		String repos = "/home/vadim/Labs/prog/lab7/";

		Settings set = new Settings();
		set.put("#_#", 10);
		set.put("@_@", 20);
		set.put("o_o", 30);
		set.put("O_o", 40);
		set.put("x_x", 50);
		System.out.println("set: \n" + set);

		set.loadFromTextFile(repos + "load.txt");
		System.out.println("set + load.txt:\n" + set);
		set.put(">_<", 50);
		set.put("*-*", 50);
		
		set.saveToTextFile(repos + "save.txt");
		set.saveToBinaryFile(repos + "save.bin");
		System.out.println("set + load.txt + put 2 elements:\n" + set);
		
		Settings set1 = new Settings();
		set1.loadFromTextFile(repos + "save.txt");
		set1.put("]_[", 666);
		set1.put(">_<", 777);
		set1.put("<_>", 333);
		System.out.println("set1 + put 3 element:\n" + set1);
		set1.loadFromBinaryFile(repos + "save.bin");
		System.out.println("set1:\n" + set1);
		
		set1.saveToBinaryFile(repos + "load.bin");
		
		Settings set2 = new Settings();
		set2.loadFromBinaryFile(repos + "load.bin");
		System.out.println("set2:\n" + set2);
	}
}