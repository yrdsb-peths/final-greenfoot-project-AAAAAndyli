import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SmoothMover
{
    long lastAdded = System.currentTimeMillis();
    boolean turnable = false;
    int timesTurned = 0;
    String direction = "right";
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    
    public void act()
    {
        long curTime  = System.currentTimeMillis();
        GameWorld1 world = (GameWorld1) getWorld();
        if(direction == "right")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()+25, getY()+28)&&world.boxAtLocation(getX()+25, getY()))
            {
                move(5);
            }
            else
            {
                direction = "left";
                move(-5);
            }
        }
        else if(direction == "left")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()-25, getY()+28)&&world.boxAtLocation(getX()-25, getY()))
            {
                move(-5);
            }
            else
            {
                direction = "right";
                move(5);
            }
        }
        if (curTime >= lastAdded + 10)
        {
            turnable = true;
            lastAdded  = curTime;
        }
    }
}
