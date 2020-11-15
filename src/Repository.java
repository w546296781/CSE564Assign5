import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Repository extends Observable{
	
	private static Repository instance; 
	private List<int[]> data;
	
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
		
	}
	
	public void saveFile(String filePath) {
		
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
