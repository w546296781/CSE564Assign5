import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

public class Repository extends Observable{
	private static Repository instance;
    private List<int[]> data;
    private HashMap<Integer,List> result;
    private HashMap<Integer,Double> distance;
    private List<Integer> shortestIndex;
    private List<double[]> tspCountry;
	private double smallestX;
	private double biggestX ;
	private double smallestY;
	private double biggestY;
	private boolean firstCaculation;
	public Repository() {
		data = new ArrayList<int[]>();
		result = new HashMap<Integer, List>();
		distance = new HashMap<Integer,Double>();
		tspCountry = new ArrayList<double[]>();
		shortestIndex = new ArrayList<>();
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
		result.clear();
		tspCountry.clear();
		shortestIndex.clear();
		distance.clear();
		notifyCanvas();
	}
	
	public synchronized void addPath(int startIndex, int dest, double distance){
        if(!result.containsKey(startIndex)){
            List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
            list.add(dest);
            result.put(startIndex,list);
        }
        else{
            result.get(startIndex).add(dest);
        }
        
        if(!this.distance.containsKey(startIndex)){
            this.distance.put(startIndex,distance);
        }
        else{
            this.distance.put(startIndex, distance);
        } 
        
        if(firstCaculation) {
        	notifyCanvas();
        }
        else {
        	for(int i = 0; i < shortestIndex.size(); i ++) {
            	if(result.get(shortestIndex.get(i)).size() == data.size()) {
            		notifyCanvas();
            	}
            }
        }
    }
	
	public void updateShortest(List<Integer> l) {
		shortestIndex = l;
	}
	
	public HashMap<Integer,Double> getDistance(){
		return this.distance;
	}
	
	public List<Integer> getShortestIndex(){
		return shortestIndex;
	}
	
	public List<double[]> getCountry(){
        return tspCountry;
    }

    public HashMap<Integer,List> getResult(){
        return result;
    }
    
    public synchronized double caculateDis(double endX, double startX, double endY, double startY){
        double distance = 0.0;
        distance = Math.sqrt(Math.pow(endX - startX,2) + Math.pow(endY - startY,2));
        return distance;
    }
    
    public void firstCaculationSwitch(boolean b) {
    	firstCaculation = b;
    }
}
