import java.util.ArrayList;
import java.util.List;

public class MasonFormula {
    private final List<List<Integer>>adjList;
    private final List<List<Integer>>value;
    private List<List<Integer>>ForwardPath;

    public MasonFormula(List<List<Integer>> paths,List<List<Integer>> values)
    {
        this.adjList=paths;
        this.value=values;
        this.ForwardPath=new ArrayList<>();
    }
    String solve()
    {
        GetForwardPath();
        return null;
    }

    private void GetForwardPath()
    {
            boolean[]visited=new boolean[adjList.size()+1];
            visited[1]=true;
            DFS(new ArrayList<>(),1,1,visited,this.ForwardPath);
            System.out.println(this.ForwardPath.size());
    }

    private void DFS(List<List<Integer>>arr,int source,int current,boolean[]visited,List<List<Integer>>container)
    {
        if(current==adjList.size()&&arr.size()!=0)
        {
            container.add(new ArrayList<>(arr.get(arr.size()-1)));
            return;
        }
        for(int i=0;i<adjList.get(current).size();++i)
        {
            if(current==source)
            {
                arr.add(new ArrayList<>());
                arr.get(arr.size()-1).add(current);
            }
            if(!visited[adjList.get(current).get(i)])
            {
                visited[adjList.get(current).get(i)]=true;
                arr.get(arr.size()-1).add(adjList.get(current).get(i));
                DFS(arr,source,adjList.get(current).get(i),visited,container);
                arr.get(arr.size()-1).remove(adjList.get(current).get(i));
                visited[adjList.get(current).get(i)]=false;
            }
        }
    }
}
