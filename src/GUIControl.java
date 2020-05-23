import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GUIControl extends StateBasedGame {
    private static final String appName="Signal Flow Graph";
    private static final int menu=0;

    private GUIControl()
    {
        super(GUIControl.appName);
        this.addState(new BackEnd());
        this.enterState(menu);
    }

    public void initStatesList(GameContainer gc )throws SlickException
    {
        this.getState(menu).init(gc,this);
        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);

    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new GUIControl());
            app.setDisplayMode(1350, 650, false);
            app.start();
            app.setTargetFrameRate(30);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}