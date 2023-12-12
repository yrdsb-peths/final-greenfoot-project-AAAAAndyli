import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean isPlayerAbove;
    
    public void act()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(isTouching(Player.class))
        {
            if(world.player.getY() < getY())
            {
                isPlayerAbove = true;
                world.player.gravityModifier = 0;
                world.player.jumpHeight = 0;
                world.player.isGrounded = true;
                world.player.peakJump = false;
                world.player.setLocation(world.player.getX(), getY()-2*getImage().getHeight()/3);
            }
            else if(!world.player.isGrounded)
            {
                isPlayerAbove = false;
                world.player.setLocation(world.player.getX(), world.player.getY()+10);
            }
        }
    
        // Add your action code here.
    }
}
