import java.util.List;
import java.util.Stack;

public class TspShortest implements Runnable{
    private int[] SolutionPath;
    private double[] solutionDistance;
    private int startIndex;
    public TspShortest(int s){
        startIndex = s;
    }
    @Override
    public void run() {
        Repository repository = Repository.getInstance();
        List<double[]> country = repository.getCountry();
        int numberOfNodes = country.size();
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[numberOfNodes];
        SolutionPath = new int[country.size()+1];
        solutionDistance = new double[country.size()];
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
                            = repository.caculateDis(country.get(i)[0],country.get(element)[0],country.get(i)[1],country.get(element)[1]);
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
                        = repository.caculateDis(country.get(dst)[0],country.get(last)[0],country.get(dst)[1],country.get(last)[1]);
                solutionDistanceIndex++;
                disantce += repository.caculateDis(country.get(dst)[0],country.get(last)[0],country.get(dst)[1],country.get(last)[1]);
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
                = repository.caculateDis(country.get(startIndex-1)[0],country.get(last)[0],country.get(startIndex-1)[1],country.get(last)[1]);
        disantce += repository.caculateDis(country.get(startIndex-1)[0],country.get(last)[0],country.get(startIndex-1)[1],country.get(last)[1]);
        this.SolutionPath = SolutionPath;
        this.solutionDistance = solutionDistance;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}