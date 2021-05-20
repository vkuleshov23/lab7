package prog.lab7.graph;

import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import prog.lab6.excpt.FormattedInputException;
import prog.lab6.finput.*;

public class StoringGraph{
	protected int[][] graph;
	protected int size;

	public StoringGraph(){
		this.size = 0;
		this.graph = new int[0][0];
	}
	public StoringGraph(int[][] graph){
		this.size = graph[0].length;
		this.graph = graph;
	}
	public void set(int x, int y){
		if((x < this.size && x >= 0) && (y < this.size && y >= 0) && (x != y)){
			this.graph[x][y] = 1;
			this.graph[y][x] = 1;
		}
	}
	public void loadFromBinFile(String filename){
		try{
			DataInputStream file = new DataInputStream(new FileInputStream(filename));
			this.size = file.readInt();
			this.graph = new int[this.size][this.size];
			for(int i = 0; i < this.size; i++){
				for(int j = 0; j < this.size; j++){
					graph[i][j] = file.readInt();
				}
			}
			file.close();
		} catch(IOException err){
        	err.printStackTrace();
			System.out.println(err.getMessage());
		}
	}
	public void saveToBinFile(String filename){
		try{
			DataOutputStream file = new DataOutputStream(new FileOutputStream(filename));
			file.writeInt(this.size);
			for(int i = 0; i < this.size; i++){
				for(int j = 0; j < this.size; j++){
					file.writeInt(graph[i][j]);
				}
			}
			file.close();
		} catch(IOException err){
			System.out.println(err.getMessage());
		}
	}
	public void loadFromTextFile(String filename){
		try{
			Scanner scan = new Scanner(new File(filename));
			scan.useDelimiter("\n");
			String format = "";
			if(scan.hasNext()){
				try{
					this.size = Integer.parseInt((String)scan.nextLine());
				} catch (NumberFormatException nfe) {};
				for(int i = 0; i < this.size; i++){
					format += "%d";
				}
			}
			this.graph = new int[this.size][this.size];
			for(int x = 0; x < this.size; x++){
				if(scan.hasNext()){
					try{
						Object[] data = FormattedInput.sscanf(format, scan.nextLine());
						for(int y = 0; y < this.size; y++){
							this.graph[x][y] = (int)data[y];
						}
					} catch(FormattedInputException err){
						err.getMassage();
						err.printStackTrace();
						java.lang.System.exit(1);
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
        	e.printStackTrace();
		}
	}
	public void saveToTextFile(String filename){
		try{
			FileOutputStream file = new FileOutputStream(filename);
			file.write((this.size + "\n").getBytes());
			for(int i = 0; i < this.size; i++){
				for(int j = 0; j < this.size; j++){					
					file.write(("" + graph[i][j]).getBytes());
					if(j != this.size -1){
						file.write((" ").getBytes());
					}
				}
				file.write("\n".getBytes());
			}
			file.close();
		} catch(IOException err){
			System.out.println(err.getMessage());
		}
	}
	@Override
	public String toString(){
		StringBuilder str = new	StringBuilder();	
		for(int i = 0; i < this.size; i++){
			for(int j = 0; j < this.size; j++){
				str.append(this.graph[i][j]);
				str.append(" ");
			}
			str.append("\n");
		}
		return str.toString(); 
	}
}