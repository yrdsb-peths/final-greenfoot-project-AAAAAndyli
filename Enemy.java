import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SmoothMover
{
    int health = 30;
    String direction = "right";
    int iFrames = 0;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(direction == "right")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()+25, getY()+28)&&world.boxAtLocation(getX()+25, getY()))
            {
                move(2);
            }
            else
            {
                direction = "left";
                move(-2);
            }
        }
        else if(direction == "left")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()-25, getY()+28)&&world.boxAtLocation(getX()-25, getY()))
            {
                move(-2);
            }
            else
            {
                direction = "right";
                move(2);
            }
        }
        iFrames++;
        touchingPlayer();
    }
    public void touchingPlayer()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(isTouching(Player.class)&& world.player.dashable < 10&&iFrames>10)
        {
            if(world.player.hMovement>0)
            {
                health-=(int)world.player.hMovement;
            }
            else
            {
                health+=(int)world.player.hMovement;
            }
            iFrames = 0;
        }
        if(health <=0)
        {
            world.removeObject(this);
        }
    }
}
