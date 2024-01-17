import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wind
 * 
 * @author Andy
 * @version 1
 */
public class Wind extends SmoothMover
{
    int transparency = 255;
    /**
     * Act - do whatever the Wind wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveUntilOut();
    }
    /**
     * Moves until outside screen
     */
    public void moveUntilOut()
    {
        GameWorld world = (GameWorld) getWorld();
        if(getX() > 0 || getX() < getWorld().getWidth())
        {
            move(10);
            getImage().setTransparency(transparency-=3);
            if(transparency < 6)
            {
                world.removeObject(this);
            }
        }
        else
        {
            world.removeObject(this);
        }
    }
}
