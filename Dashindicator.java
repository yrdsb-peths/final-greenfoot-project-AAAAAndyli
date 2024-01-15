import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dashindicator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dashindicator extends Actor
{
    /**
     * Act - do whatever the Dashindicator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage dashIndicator = new GreenfootImage("images/dash.png");
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(world.player.dashable < 100)
        {
            getImage().setTransparency(world.player.dashable*2);
        }
        //ensure the player knows when the dash is fully charged
        else
        {
            getImage().setTransparency(255);
        }
    }
}
