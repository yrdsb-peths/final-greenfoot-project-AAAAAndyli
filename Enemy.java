import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SmoothMover
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        
        GameWorld1 world = (GameWorld1) getWorld();
        if(getRotation() == 0)
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()-25, getY()+27))
            {
                move(5);
            }
            else
            {
                turn();
            }
        }
        else if(getRotation() == 90)
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()-25, getY()-25))
            {
                move(5);
            }
            else
            {
                turn();
            }
        }
        else if(getRotation() == 180)
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()+25, getY()-25))
            {
                move(5);
            }
            else
            {
                turn();
            }
        }
        else if(getRotation() == 270)
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()+25, getY()+25))
            {
                move(5);
            }
            else
            {
                turn();
            }
        }
    }
    public void turn()
    {
        turn(90);
        for(int i = 0; i < 25; i++)
        {
            move(2);      
        }
        turn(90);
        move(2);      
        turn(-90);
    }
}
