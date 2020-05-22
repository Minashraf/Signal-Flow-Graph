import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class BackEnd extends BasicGameState {
    private int id=1;
    private boolean clicked=false;
    private List<Shape>ShapeList;
    private List<Shape>ClickedShapes;
    private Map<Point,Double>gain;
    private boolean finished=false;
    private String answer="";
    private List<List<Integer>>Path;
    private List<List<Double>>Gain;

    BackEnd()
    {
        this.ShapeList =new ArrayList<>();
        this.ClickedShapes=new ArrayList<>();
        this.gain=new LinkedHashMap<>();
        this.Path=new ArrayList<>();
        this.Gain=new ArrayList<>();
    }

    public void init(GameContainer gc, StateBasedGame sbg ){
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,1280,600);
        for(Shape s: ShapeList)
        {
            g.setColor(Color.black);
            g.drawOval(s.getX(),s.getY(),40,40);
            g.drawString(s.getId(),s.getX(),s.getY());
        }
        StringBuilder FromTo= new StringBuilder();
        for(Map.Entry<Point,Double>mp:gain.entrySet())
        {
            FromTo.append((int)mp.getKey().getX()).append("      ").append((int)mp.getKey().getY()).append("        ").append(mp.getValue());
            FromTo.append("\n");
        }
        g.setColor(Color.red);
        g.drawString(FromTo.toString(),800,185);
        g.setColor(Color.black);
        g.drawString("1.Left Click to create a node\n" +
                          "2.Click on 2 nodes to enter the gain\n"+
                          "3.Right click to solve\n"+
                          "NOTE: this program works only with Node 1 as \n" +
                            "the starting node and Last Node MUST be a sink node\n"+
                            "and the one with the highest number",800,50);
        g.drawString("From"+" "+"  To       Gain",800,170);
        g.drawString("Answer: "+answer,1000,500);
        if(finished)
            g.drawString("Press anywhere to restart",1000,550);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta){
        if(!finished&&Mouse.getX()<800)
        {
            Shape exist = ShapeExists(Mouse.getX(), 600 - Mouse.getY());
            if (Mouse.isButtonDown(0) && !clicked) {
                if (exist == null) {
                    clicked = true;
                    int PosX = Mouse.getX();
                    int PosY = 600 - Mouse.getY();
                    ShapeList.add(new Shape(PosX, PosY, id++));
                } else {
                    clicked = true;
                    ClickedShapes.add(exist);
                    if (ClickedShapes.size() == 2) {
                        try {
                            double valueOfGain=Double.parseDouble(JOptionPane.showInputDialog("Please inert the gain from node " + ClickedShapes.get(0).getId() + " to " + ClickedShapes.get(1).getId()));
                            if(valueOfGain!=0)
                                gain.put(new Point(Integer.parseInt(ClickedShapes.get(0).getId()), Integer.parseInt(ClickedShapes.get(1).getId())), valueOfGain);
                            else
                                gain.remove(new Point(Integer.parseInt(ClickedShapes.get(0).getId()), Integer.parseInt(ClickedShapes.get(1).getId())));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Enter the gain value\n (Must be a number)", "Failure", JOptionPane.ERROR_MESSAGE);
                        }
                        ClickedShapes = new ArrayList<>();
                    }
                }
            } else if (!Mouse.isButtonDown(0))
                clicked = false;
            if (Mouse.isButtonDown(1))
            {
                finished = true;
                sort();
                Build();
                MasonFormula Mason=new MasonFormula(Path,Gain);
                this.answer=Mason.solve();
            }
        }
        else if (Mouse.isButtonDown(0)&&finished)
        {
            this.ShapeList =new ArrayList<>();
            this.ClickedShapes=new ArrayList<>();
            this.gain=new LinkedHashMap<>();
            this.Path=new ArrayList<>();
            this.Gain=new ArrayList<>();
            id=1;
            clicked=false;
            finished=false;
            answer="";
        }
    }

    private Shape ShapeExists(int x,int y)
    {
        for(Shape s:ShapeList)
        {
            if(s.getX()<=x&&x<=s.getX()+40&&s.getY()<=y&&y<=s.getY()+40)
                return s;
        }
        return null;
    }

    private void sort()
    {
        List<Map.Entry<Point,Double>> list = new LinkedList<>(gain.entrySet());
        list.sort((o1, o2) -> (int) (o1.getKey().getX() - o2.getKey().getX()));
        gain=new LinkedHashMap<>();
        for(Map.Entry<Point,Double> entry:list)
            gain.put(entry.getKey(),entry.getValue());
    }

    private void Build()
    {
        Path.add(new ArrayList<>());
        Gain.add(new ArrayList<>());
        for(int i=0;i<id-2;++i)
        {
            Path.add(new ArrayList<>());
            Gain.add(new ArrayList<>());
        }
        for(Map.Entry<Point,Double> entry:gain.entrySet())
        {
            Path.get((int) entry.getKey().getX()).add((int) entry.getKey().getY());
            Gain.get((int) entry.getKey().getX()).add(entry.getValue());
        }
    }

    public int getID() {return 0;}

}