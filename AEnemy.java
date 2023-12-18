import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AdvancedEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AEnemy extends Enemy
{
    /**
     * Act - do whatever the AdvancedEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //state 0 = patrolling
    //state 1 = hunting
    int state = 0;
    public void act()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(state == 0)
        {
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
        }
        else
        {
            
        }
    }
    public void patrolState()
    {
        
    }
}
