import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * It's indicating the dash
 * 
 * @author Andy
 * @version 1
 */
public class Dashindicator extends Actor
{
    /**
     * Visually indicates the cooldown for the dash
     */
    GreenfootImage dashIndicator = new GreenfootImage("images/dash.png");
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(world.player.dashable < 50)
        {
            getImage().setTransparency(world.player.dashable*4);
        }
        //ensure the player knows when the dash is fully charged
        else
        {
            getImage().setTransparency(255);
        }
    }
}
