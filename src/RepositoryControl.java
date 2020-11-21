import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RepositoryControl implements Runnable{
    private List<Integer> shortestInex;
    private Pauser pauser;
    public RepositoryControl(Pauser p) {
    	this.pauser = p;
    }
    
	public void run(){
		Repository repo = Repository.getInstance();
        while(true) {
        	pauser.look();
        	if(repo.getFirstCaculation()) {
        		shortestInex = sortByValue(repo.getDistance());
        		repo.updateShortest(shortestInex);
        	}
        	else {
        		HashMap<Integer,Double> temp = new HashMap<Integer,Double>();
        		for(Map.Entry<Integer, Double> entry : repo.getDistance().entrySet()) {
        		    int key = entry.getKey();
        		    double value = entry.getValue();
        		    if(repo.getResult().get(key).size() == repo.getData().size()) {
            			temp.put(key, value);
            		}
        		}
        		shortestInex = sortByValue(temp);
        		if(!shortestInex.equals(repo.getShortestIndex())) {
        		    repo.updateShortest(shortestInex);
    		    }
        	}
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
	
    private List<Integer> sortByValue(HashMap<Integer, Double> hm) 
    { 
        List<Map.Entry<Integer, Double> > list = 
               new LinkedList<Map.Entry<Integer, Double> >(hm.entrySet()); 
  
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double> >() { 
            public int compare(Map.Entry<Integer, Double> o1,  
                               Map.Entry<Integer, Double> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
        List<Integer> l = new ArrayList<>();
        HashMap<Integer, Double> temp = new LinkedHashMap<Integer, Double>();
        int index = 0;
        for (Map.Entry<Integer, Double> aa : list) {
        	if(index < 3) {
            	l.add(aa.getKey());
            	index++;
        	}
        	else {
        		break;
        	}
        } 
        return l; 
    }
}