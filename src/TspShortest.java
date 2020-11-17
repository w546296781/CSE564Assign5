import java.util.List;
import java.util.Stack;

public class TspShortest implements Runnable{
    private int[] SolutionPath;
    private double[] solutionDistance;
    private int startIndex;
    Pauser pauser;
    public TspShortest(int s,Pauser pauser){
        startIndex = s;
        this.pauser = pauser;
    }
    @Override
    public void run() {
    	while(true) {
    		pauser.look();
	        Repository repository = Repository.getInstance();
	        List<int[]> data = repository.getData();
	        int numberOfNodes = data.size();
	        Stack<Integer> stack = new Stack<>();
	        int[] visited = new int[numberOfNodes];
	        SolutionPath = new int[data.size()+1];
	        solutionDistance = new double[data.size()];
	        visited[startIndex-1] = 1;
	        stack.push(startIndex-1);
	        SolutionPath[0] = startIndex;
	        repository.addPath(startIndex, startIndex);
	        int element;
	        int dst = 0, i;
	        double min = Double.MAX_VALUE;
	        boolean minFlag = false;
	        double disantce = 0.0;
	        int last = 0;
	        int SolutionPathIndex = 1;
	        int solutionDistanceIndex = 0;
	        while (!stack.isEmpty())
	        {
	            element = stack.peek();
	            i = 1;
	            min = Integer.MAX_VALUE;
	            double tempDistance = 0.0;
	            while (i < numberOfNodes)
	            {
	                if (i != element && visited[i] == 0)
	                {
	                    tempDistance
	                            = repository.caculateDis(data.get(i)[0],data.get(element)[0],data.get(i)[1],data.get(element)[1]);
	                    if (min > tempDistance)
	                    {
	                        min = (int)tempDistance;
	                        dst = i;
	                        minFlag = true;
	                    }
	                }
	                i++;
	            }
	
	            if (minFlag)
	            {
	                repository.addPath(startIndex,dst+1);
	                visited[dst] = 1;
	                stack.push(dst);
	                SolutionPath[SolutionPathIndex] = dst+1;
	                SolutionPathIndex++;
	                solutionDistance[solutionDistanceIndex]
	                        = repository.caculateDis(data.get(dst)[0],data.get(last)[0],data.get(dst)[1],data.get(last)[1]);
	                solutionDistanceIndex++;
	                disantce += repository.caculateDis(data.get(dst)[0],data.get(last)[0],data.get(dst)[1],data.get(last)[1]);
	                last = dst;
	                minFlag = false;
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                continue;
	            }
	            stack.pop();
	        }
	        repository.addPath(startIndex,startIndex);
	        SolutionPath[SolutionPath.length-1] = startIndex;
	        solutionDistance[solutionDistance.length-1]
	                = repository.caculateDis(data.get(startIndex-1)[0],data.get(last)[0],data.get(startIndex-1)[1],data.get(last)[1]);
	        disantce += repository.caculateDis(data.get(startIndex-1)[0],data.get(last)[0],data.get(startIndex-1)[1],data.get(last)[1]);
	        this.SolutionPath = SolutionPath;
	        this.solutionDistance = solutionDistance;
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}
    }
}