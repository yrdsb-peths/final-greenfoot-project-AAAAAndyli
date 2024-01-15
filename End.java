import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends Actor
{
    /**
     * Act - do whatever the End wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!world.paused)
        {
            if(isTouching(Player.class))
            {
                world.playerHP+=2;
                world.worldNum++;
                world.changeWorld(world.worlds[world.worldNum]);
            }
        }
    }
}
