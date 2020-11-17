import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class TspShortest implements Runnable{
    private int[] SolutionPath;
    private double[] solutionDistance;
    private int startIndex;
    private Timer timer;
    double min = Double.MAX_VALUE;
    boolean minFlag = false;
    double disantce = 0.0;
    int last = 0;
    List<int[]> data;
    int numberOfNodes;
    int[] visited;
    int element;
    int dst = 0, i;
    Pauser pauser;
    private boolean exit = false;
    public TspShortest(int s,Pauser pauser){
        startIndex = s;
        this.pauser = pauser;
    }
    
    @Override
    public synchronized void run() {
    	Repository repository = Repository.getInstance();
    	data = repository.getData();
    	numberOfNodes = data.size();
    	int[] visited = new int[numberOfNodes];
    	visited[startIndex-1] = 1;
    	last = startIndex -1;
    	repository.addPath(startIndex, startIndex,disantce);
    	while(!exit) {
    		pauser.look();
    		min = Integer.MAX_VALUE;
            double tempDistance = 0.0;
            if(contains(visited)) {
            	repository.addPath(startIndex, startIndex,disantce);
            	end();
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
}