import java.util.*;

public class MasonFormula {
    private final List<List<Integer>>adjList;
    private final List<List<Double>>value;
    private final List<List<Integer>>ForwardPath;
    private final List<List<Integer>>Loop;
    private final List<Double>PathGain;
    private final List<Double>LoopGain;

    public MasonFormula(List<List<Integer>> paths,List<List<Double>> values)
    {
        this.adjList=paths;
        this.value=values;
        this.ForwardPath=new ArrayList<>();
        this.Loop=new ArrayList<>();
        this.PathGain=new ArrayList<>();
        this.LoopGain=new ArrayList<>();
    }
    void solve()
    {
        GetForwardPath();
        GetLoop();
        Gain(this.ForwardPath,this.PathGain);
        Gain(this.Loop,this.LoopGain);
        double Numerator=NumeratorCalculation();
        double Denominator=DenominatorCalculation();
        System.out.println(Numerator+"/"+Denominator+"="+Numerator/Denominator);
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
        this.Loop.sort(Comparator.comparingInt(List::size));
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

    private void Gain(List<List<Integer>>original,List<Double>ToBeAdded)
    {
        for(List<Integer>arr:original)
        {
            double gain=1;
            for(int i=0;i<arr.size()-1;++i)
                gain*=value.get(arr.get(i)).get(adjList.get(arr.get(i)).indexOf(arr.get(i+1)));
            ToBeAdded.add(gain);
        }
    }

    private double NumeratorCalculation()
    {
        double answer=0;
        int PathIndex=0;
        for(List<Integer>path:this.ForwardPath)
        {
            Set<Integer> set = new HashSet<>(path);
            double NonTouching=1;
            int Loopindex=0;
            for(List<Integer>LoopVertices:this.Loop)
            {
                boolean duplicate=false;
                for(Integer NodeNumber:LoopVertices)
                {
                    if(set.contains(NodeNumber))
                    {
                        duplicate=true;
                        break;
                    }
                }
                if(!duplicate)
                    NonTouching-=this.LoopGain.get(Loopindex);
                ++Loopindex;
            }
            answer+=this.PathGain.get(PathIndex)*NonTouching;
            ++PathIndex;
        }
        return answer;
    }

    private double DenominatorCalculation()
    {
        double answer=1;
        for(Double d:LoopGain)
            answer-=d;
        for(int i=0;i<this.Loop.size();++i)
        {
            Set<Integer>set=new HashSet<>(this.Loop.get(i));
            for(int j=i+1;j<this.Loop.size();++j)
            {
                boolean touched=false;
                for(Integer s:this.Loop.get(j))
                {
                    if(set.contains(s))
                    {
                        touched=true;
                        break;
                    }
                }
                if(!touched)
                    answer+=(this.LoopGain.get(j)*this.LoopGain.get(i));
            }
        }
        return answer;
    }

}