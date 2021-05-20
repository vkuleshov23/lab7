package prog.lab7;
import prog.lab7.settings.*;
import prog.lab7.graph.*;

public class main {
	public static void main(String[] args){  //load txt , save txt , save bin, load bin

		String repos = "/home/vadim/Labs/prog/lab7/";

		Settings set = new Settings();
		set.put("volume", 10);
		set.put("brightness", 20);
		System.out.println("set: \n" + set);

		set.loadFromTextFile(repos + "load.txt");
		System.out.println("set + load.txt:\n" + set);
		set.put("senciviry", 50);
		set.put("configuring", 50);
		
		set.saveToTextFile(repos + "save.txt");
		set.saveToBinaryFile(repos + "save.bin");
		System.out.println("set + load.txt + put 2 elements:\n" + set);
		
		Settings set1 = new Settings();
		set1.loadFromTextFile(repos + "save.txt");
		set1.put("graphics", 666);
		set1.put("video", 777);
		set1.put("color", 333);
		System.out.println("set1 + put 3 element:\n" + set1);
		set1.loadFromBinaryFile(repos + "save.bin");
		System.out.println("set1:\n" + set1);
		
		set1.saveToBinaryFile(repos + "load.bin");
		set1.saveToTextFile(repos + "load.txt");
		
		Settings set2 = new Settings();
		set2.loadFromBinaryFile(repos + "load.bin");
		System.out.println("set2:\n" + set2);

		//----------dop lab checking---------------//


		StoringGraph graph = new StoringGraph();
		StoringGraph graph1 = new StoringGraph();
		
		graph.loadFromTextFile(repos + "graphLoad.txt");
		
		graph.saveToBinFile(repos + "graphLoad.bin");
		System.out.println("graph:\n" + graph);
		graph.set(0, 1);
		System.out.println("graph:\n" + graph);
		graph.saveToTextFile(repos + "graphSave.txt");

		graph1.loadFromBinFile(repos + "graphLoad.bin");
		System.out.println("graph1:\n" + graph1);
		graph1.set(2, 3);
		System.out.println("graph1:\n" + graph1);
		graph1.saveToBinFile(repos + "graphSave.bin");
	}
}