import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
            }
            else if(buttonType == 1)
            {
                button[i] = new GreenfootImage("images/Rbutton/pixil-frame-" + i + ".png");
            }
            else if(buttonType == 2)
            {
                button[i] = new GreenfootImage("images/Qbutton/pixil-frame-" + i + ".png");
            }
        }
    }
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
                GameWorld newWorld = new GameWorld();
                Greenfoot.setWorld(newWorld);
            }
            else if(buttonType==2)
            {
                if(getWorld().getClass().getName()=="GameWorld")
                {
                    GameWorld world = (GameWorld) getWorld();
                    world.calm.stop();
                    world.combat.stop();
                    world.boss.stop();
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
