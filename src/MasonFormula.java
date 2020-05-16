import java.util.*;

public class MasonFormula {
    private final List<List<Integer>>adjList;
    private final List<List<Integer>>value;
    private List<List<Integer>>ForwardPath;
    private List<List<Integer>>Loop;

    public MasonFormula(List<List<Integer>> paths,List<List<Integer>> values)
    {
        this.adjList=paths;
        this.value=values;
        this.ForwardPath=new ArrayList<>();
        this.Loop=new ArrayList<>();
    }
    String solve()
    {
        GetForwardPath();
        GetLoop();
        return null;
    }

    private void GetForwardPath()
    {
            boolean[]visited=new boolean[adjList.size()+1];
            visited[1]=true;
            DFS(new ArrayList<>(),1,1,adjList.size(),visited,this.ForwardPath);
    }

    private void DFS(List<List<Integer>>arr,int source,int current,int destination,boolean[]visited,List<List<Integer>>container)
    {
        if(current==destination&&arr.size()!=0&&visited[destination])
        {
            container.add(new ArrayList<>(arr.get(arr.size()-1)));
            return;
        }
        if(current==adjList.size())
            return;
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
                DFS(arr,source,adjList.get(current).get(i),destination,visited,container);
                arr.get(arr.size()-1).remove(arr.get(arr.size()-1).size()-1);
                visited[adjList.get(current).get(i)]=false;
            }
        }
    }

    private void GetLoop()
    {
        for(int i=1;i<adjList.size();++i)
        {
            boolean[]visited=new boolean[adjList.size()+1];
            DFS(new ArrayList<>(),i,i,i,visited,this.Loop);
        }
        this.Loop.sort(new Comparator<>() {
            public int compare(List a1, List a2) {
                return a1.size() - a2.size();
            }
        });
        DuplicateRemoval(this.Loop);
    }

    private void DuplicateRemoval(List<List<Integer>>arr)
    {
        for(int i=0;i<arr.size()-1;++i)
        {
            int k=i+1;
            while(k<arr.size()&&arr.get(k).size()==arr.get(i).size())
            {
                Set<Integer> set = new HashSet<>(arr.get(i));
                boolean all=true;
                for(int j=0;j<arr.get(k).size();++j)
                {
                    if(!set.contains(arr.get(k).get(j)))
                    {
                        all=false;
                        break;
                    }
                }
                if(all)
                    arr.remove(k);
                else
                    ++k;
            }
        }
    }

}

