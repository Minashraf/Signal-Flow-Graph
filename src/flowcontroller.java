
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
//-Djava.library.path=lib/natives/windows

public class     flowcontroller extends StateBasedGame {
    private static final String appName="test";
    private static final int menu=0;
    private flowcontroller(String appName) throws SlickException {
        super(appName);
        this.addState(new MainScreen(menu));
        this.enterState(menu);
    }

    public void initStatesList(GameContainer gc )throws SlickException {
        this.getState(menu).init(gc,this);
        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);

    }

    public static void main(String[] args) {
        try {
            AppGameContainer appc = new AppGameContainer(new flowcontroller(appName));

            appc.setDisplayMode(1280, 820, false);
            appc.start();
            appc.setTargetFrameRate(30);


        } catch (SlickException e) {
            System.out.println("Slick Exception Found");
        }

    }

}