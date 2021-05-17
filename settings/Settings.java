package prog.lab7.settings;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

public class Settings{
	protected HashMap<String, Integer> settings;
	public Settings(){
		settings = new HashMap<>();
	}
	public void put(String name, int param){
		try{
			this.settings.put(name, param);
		} catch(NullPointerException err){
			err.printStackTrace();
			System.out.println(err.getMessage());
			java.lang.System.exit(1);
		}
	}
	public int get(String name){
		try{
			return this.settings.get(name);
		} catch(NullPointerException err){
        	err.printStackTrace();
			System.out.println(err.getMessage());
			java.lang.System.exit(1);
			return 0;
		}
	}
	public void delete(String name){
		settings.remove(name);
	}
	public void loadFromBinaryFile(String filename){
		try{
			DataInputStream file = new DataInputStream(new FileInputStream(filename));
			int size = file.readInt();
			String key; int param;
			for(int i = 0; i < size; i++){
				int len = file.readInt();
				key = ""; param = 0;
				for(int j = 0; j < len; j++){
					key += file.readChar();
				}
				param = file.readInt();
				this.put(key, param);
			}
			file.close();
		} catch(IOException err){
        	err.printStackTrace();
			System.out.println(err.getMessage());
		}
	}
	public void saveToBinaryFile(String filename){
		try{
			DataOutputStream file = new DataOutputStream(new FileOutputStream(filename));
			file.writeInt(settings.size());
			for(Map.Entry<String, Integer> item : settings.entrySet()){
				file.writeInt((item.getKey()).length());
				for(char c : item.getKey().toCharArray()){
					file.writeChar(c);
				}
				file.writeInt(item.getValue());
			}
		} catch(IOException err){
			System.out.println(err.getMessage());
		}
	}
	public void loadFromTextFile(String filename){
		try{
			Scanner scan = new Scanner(new File(filename));
			scan.useDelimiter(System.getProperty("line.separator"));
			while(scan.hasNext()){
				try{
					Object[] data = FormattedInput.sscanf("%s %d", (String)scan.next());
					this.put((String)data[0], (int)data[1]);
				} catch(FormattedInputException err){
					err.getMassage();
					err.printStackTrace();
					java.lang.System.exit(1);
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
			for(Map.Entry<String, Integer> item : settings.entrySet()){
				file.write(("" + item.getKey() + " " + item.getValue() + "\n").getBytes());
			}
			file.close();
		} catch(IOException err){
			System.out.println(err.getMessage());
		}
	}
	@Override
	public String toString(){

		StringBuilder str = new	StringBuilder();
		for(Map.Entry<String, Integer> item : settings.entrySet()){
			str.append(item.getKey()); 
			str.append(" ");
			str.append(item.getValue());
			str.append('\n');
		}
		return str.toString(); 
	}
	@Override
	public boolean equals(Object tmp){
		return true;
	}

}