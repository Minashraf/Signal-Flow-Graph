
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MainScreen extends BasicGameState {
    int posx;
    int posy;
    boolean flag=false;



    public MainScreen(int state) throws SlickException {

    }

    public void init(GameContainer gc, StateBasedGame sbg )throws SlickException {


    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.fillRect(posx,posy,100,100);
        g.drawString(posx+","+posy,200,200);
    }


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

         flag=Mouse.isButtonDown(0);
        if(Mouse.isButtonDown(0)){
            posx=Mouse.getX();
            posy=820-Mouse.getY();
        }


    }

    public void window(String window, int delta, StateBasedGame sbg) {}

    public int getID() {return 0;}

}
