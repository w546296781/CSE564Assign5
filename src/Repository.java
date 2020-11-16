import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class Repository extends Observable{
	private static Repository instance; 
	private List<int[]> data;
	private List<double[]> tspCountry;
	private double smallestX;
	private double biggestX ;
	private double smallestY;
	private double biggestY;
	public Repository() {
		data = new ArrayList<int[]>();
	}
	
    public static Repository getInstance(){  
    	if (instance == null) {  
    			instance = new Repository();  
    	}  
    	return instance;  
    }
	
	public void notifyCanvas() {
		setChanged();
		notifyObservers();
	}
	
	public List<int[]> getData(){
		return data;
	}
	
	public void openFile(String filePath) {
		tspCountry = new ArrayList<>();
		try{
			File input = new File(filePath);
			Scanner myReader = new Scanner(input);
			while(myReader.hasNextLine()) {
				String[] temp = myReader.nextLine().split(" ");
				if (temp[0].charAt(0) - '0' >= 0 && temp[0].charAt(0) - '0' <= 9) {
					tspCountry.add(new double[]{Double.parseDouble(temp[1]),Double.parseDouble(temp[2])});
				}
			}
			myReader.close();
			findSmallestBiggest();
			transferData();
		}catch (Exception e){
			e.printStackTrace();
		}
		notifyCanvas();
	}
	
	public void saveFile(String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			int index = 1;
			for (int[] item:data) {
				writer.write(index+ " " + item[0]+ " " + item[1] + "\r\n");
				index++;
			}
			writer.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private void findSmallestBiggest(){
		smallestX = Double.MAX_VALUE;
		biggestX = Double.MIN_VALUE;
		smallestY = Double.MAX_VALUE;
		biggestY = Double.MIN_VALUE;
		for (double[] item:tspCountry) {
			if (smallestX > item[0]) {
				smallestX = item[0];
			}
			if (smallestY > item[1]) {
				smallestY = item[1];
			}
			if (biggestX < item[0]) {
				biggestX = item[0];
			}
			if (biggestY < item[1]) {
				biggestY = item[1];
			}
		}
	}

	private void transferData(){
		data.clear();
		double length = biggestX - smallestX;
		double height = biggestY - smallestY;
		int ansX = 0;
		int ansY = 0;
		for (double[] item:tspCountry) {
			ansX = (int)((item[0] - smallestX) / length * 500);
			ansY = (int)((item[1] - smallestY) / height * 500 );
			data.add(new int[]{ansX,ansY});
		}
	}

	public void addPoint(int x, int y) {
		data.add(new int[] {x, y});
		notifyCanvas();
	}
	
	public void clearData() {
		data.clear();
		notifyCanvas();
	}
	
	public void run() {
		
	}
	
	public void stop() {
		
	}
}
