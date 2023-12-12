import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean isPlayerAbove;
    
    public void act()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(isTouching(Player.class))
        {
            if(world.player.getY() < getY()-getImage().getHeight()/2)
            {
                isPlayerAbove = true;
                world.player.gravityModifier = 0;
                world.player.jumpHeight = 0;
                world.player.isGrounded = true;
                world.player.peakJump = false;
                world.player.setLocation(world.player.getX(), getY()-2*getImage().getHeight()/3);
            }
            else if(world.player.getX() < getX())
            {
                isPlayerAbove = false;
                world.player.hMovement = -2;
                world.player.setLocation(world.player.getX()-1, world.player.getY());
            }
            else if(world.player.getX() > getX())
            {
                isPlayerAbove = false;
                world.player.hMovement = 2;
                world.player.setLocation(world.player.getX()+1, world.player.getY());
            }
        }
    
        // Add your action code here.
    }
}
