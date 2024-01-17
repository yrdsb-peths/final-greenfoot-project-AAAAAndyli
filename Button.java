import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Button
 * 
 * @author Andy
 * @version 1
 */
public class Button extends Actor
{
    GreenfootImage[] button = new GreenfootImage[3];
    boolean hovering;
    int buttonType;
    int mx;
    int my;
    
    public Button(int buttonType)
    {
        this.buttonType = buttonType;
        for(int i = 0; i < 2; i++)
        {
            if(buttonType == 0)
            {
                button[i] = new GreenfootImage("images/Pbutton/pixil-frame-" + i + ".png");
                //play button
            }
            else if(buttonType == 1)
            {
                button[i] = new GreenfootImage("images/Rbutton/pixil-frame-" + i + ".png");
                //resume button
            }
            else if(buttonType == 2)
            {
                button[i] = new GreenfootImage("images/Qbutton/pixil-frame-" + i + ".png");
                //quit button
            }
        }
    }
    /**
     * Detects mouse inputs
     */
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
           mx = mouse.getX();
           my = mouse.getY();
        }
        if(buttonType == 1)
        {
            GameWorld world = (GameWorld) getWorld();
            if(Greenfoot.mouseClicked(this))
            {
                setImage(button[2]);
                world.paused = false;
                world.removeObject(this);
            }
        }
        else if(Greenfoot.mouseClicked(this)){
            setImage(button[2]);
            if(buttonType==0)
            {
                Mainmenu world = (Mainmenu) getWorld();
                GameWorld newWorld = new GameWorld();
                Greenfoot.setWorld(newWorld);
                world.mm.stop();
            }
            else if(buttonType==2)
            {
                if(getWorld().getClass().getName()=="GameWorld")
                {
                    GameWorld world = (GameWorld) getWorld();
                }
                if(getWorld().getClass().getName()=="Gameover")
                {
                    Gameover world = (Gameover) getWorld();
                    world.go.stop();
                }
                Mainmenu m = new Mainmenu();
                Greenfoot.setWorld(m);
            }
        }
        else if(mx > getX()-128&&mx<getX()+128&&my>getY()-128&&my<getY()+128)
        {
            setImage(button[1]);
        }
        else
        {
            setImage(button[0]);
        }
    }
}
