import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class BackEnd extends BasicGameState {
    private int posx;
    private int posy;
    private int id=1;
    private boolean clicked=false;
    private List<Shape>container;

    public BackEnd()
    {
        this.container=new ArrayList<>();
    }
    public void init(GameContainer gc, StateBasedGame sbg )throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.gray);
        g.fillRect(0,0,1280,820);
        for(Shape s:container)
        {
            g.setColor(Color.white);
            g.fillOval(s.getX(),s.getY(),40,40);
            g.setColor(Color.black);
            g.drawString(s.getId(),s.getX(),s.getY());
        }
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(Mouse.isButtonDown(0)&&!clicked)
        {
            clicked=true;
            posx=Mouse.getX();
            posy=820-Mouse.getY();
            container.add(new Shape(posx,posy,id++));
        }
        else
            clicked=false;
    }

    public void window(String window, int delta, StateBasedGame sbg) {}

    public int getID() {return 0;}

}