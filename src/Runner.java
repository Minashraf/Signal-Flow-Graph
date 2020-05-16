import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        //Assuming the last node (Destination node is always a sink)
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter the number of edges:");
        int edges;
        while(true)
        {
            try{
                edges=input.nextInt();
                break;
            }catch (Exception e)
            {
                input.nextLine();
                System.out.println("Please enter a digit");
            }
        }
        List<List<Integer>>paths=new ArrayList<>();
        paths.add(new ArrayList<>());
        List<List<Integer>>gain=new ArrayList<>();
        gain.add(new ArrayList<>());
        input.nextLine();
        for(int i=0;i<edges-1;++i)
        {
            System.out.println("Please enter the neighbour vertices to "+(i+1)+" separated by a comma:");
            String[]split=input.nextLine().split(",");
            boolean error=false;
            for(String s:split)
            {
                int value=0;
                try{
                    value=Integer.parseInt(s);
                }catch (Exception e)
                {
                    System.out.println("Please enter a number");
                    error=true;
                }
                if(!error&&value>edges)
                {
                    System.out.println("Please enter a valid number less than the number of edges");
                    error=true;
                }
                if(error)
                    break;
            }
            if(error)
            {
                --i;
                continue;
            }
            paths.add(new ArrayList<>());
            for(String s:split)
                paths.get(paths.size()-1).add(Integer.parseInt(s));
        }
        for(int i=1;i<edges;++i)
        {
            gain.add(new ArrayList<>());
            for(int j=0;j<paths.get(i).size();++j)
            {
                System.out.println("Please enter the gain from "+i+" to "+paths.get(i).get(j)+":");
                try {
                    gain.get(gain.size()-1).add(Integer.parseInt(input.nextLine()));
                }catch (Exception e)
                {
                    System.out.println("Please enter a number");
                    gain.remove(gain.size()-1);
                    --j;
                }
            }
        }
        MasonFormula obj=new MasonFormula(paths,gain);
        System.out.println(obj.solve());
    }
}
