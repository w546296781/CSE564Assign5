import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class TspShortest implements Runnable{
    private int[] SolutionPath;
    private double[] solutionDistance;
    private List<Integer> startIndexList;
    private int startIndex;
    private int startListIndex;
    private double disantce = 0.0;
    private int last = 0;
    private List<int[]> data;
    private int numberOfNodes;
    private int[] visited;
    private int dst = 0;
    private Pauser pauser;
    
    private boolean exit = false;
    public TspShortest(List<Integer> s,Pauser pauser){
    	startListIndex = 0;
    	startIndexList = s;
    	switchStartIndex();
        this.pauser = pauser;
    }
    
    @Override
    public synchronized void run() {
    	Repository repository = Repository.getInstance();
    	data = repository.getData();
    	numberOfNodes = data.size();
    	firstCityUpdate();
    	while(!exit) {
    		if(repository.getData().size() == 0) {
    			break;
    		}
    		pauser.look();
    		double min = Double.MAX_VALUE;
            double tempDistance = 0.0;
            if(contains(visited)) {
            	if(startListIndex < startIndexList.size() - 1) {
            		startListIndex++;
            		switchStartIndex();
            		firstCityUpdate();
            		repository.firstCaculationSwitch(false);
            	}
            	else {
                	end();
            	}
            	continue;
            }
            for(int i = 0; i < numberOfNodes; i++) {
            	if (visited[i] == 0)
                {
                    tempDistance
                        = repository.caculateDis(data.get(i)[0],data.get(last)[0],data.get(i)[1],data.get(last)[1]);
                    if (min > tempDistance)
                    {
                        min = (int)tempDistance;
                        dst = i;
                    }
                 }
            }
            visited[dst] = 1;
            disantce += repository.caculateDis(data.get(dst)[0],data.get(last)[0],data.get(dst)[1],data.get(last)[1]);
            last = dst;
            repository.addPath(startIndex,dst+1,disantce);
    		try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println(startIndex);
			}
    	}
    }
    
    private void firstCityUpdate() {
    	Repository repository = Repository.getInstance();
    	visited = new int[numberOfNodes];
    	visited[startIndex-1] = 1;
    	last = startIndex -1;
    	disantce = 0.0;
    	repository.addPath(startIndex, startIndex,disantce);
    }
    
    private boolean contains(int[] array) {
    	for(int j:array){
    		if(j==0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private void end() {
    	exit = true;
    }
    
    private void switchStartIndex() {
    	startIndex = startIndexList.get(startListIndex);
    }
}