import greenfoot.*;  //  (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Box extends Actor
{
    /**
     * Act - do whatever the Box wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!world.paused)
        {
            if(isTouching(Player.class))
            {
                if(world.player.getY() < getY()-getImage().getHeight()/2&&(world.player.getX()-40 < getX()&&world.player.getX()+40 > getX()))
                {
                    world.player.gravityModifier = 0;
                    world.player.jumpHeight = 0;
                    world.player.isGrounded = true;
                    world.player.peakJump = false;
                    world.player.setLocation(world.player.getX(), getY()-getImage().getHeight()/2-world.player.getImage().getHeight()/2+1);
                }
                else if(world.player.getY() > getY()-getImage().getHeight()/2+10&&(world.player.getX()-40 < getX()&&world.player.getX()+40 > getX()))
                {
                    world.player.peakJump = true;
                    world.player.gravityModifier = 2;
                    world.player.jumpHeight = 0;
                    world.player.setLocation(world.player.getX(), world.player.getY()+10);
                }
                if(world.player.getX() < getX()&&world.player.getY() > getY()-getImage().getHeight()/2&&world.player.getY() < getY()+getImage().getHeight()/2)
                {
                    world.player.hMovement = 0;
                    world.player.setLocation(getX()-getImage().getWidth()+2, world.player.getY());
                }
                else if(world.player.getX() > getX()&&world.player.getY() > getY()-getImage().getHeight()/2&&world.player.getY() < getY()+getImage().getHeight()/2)
                {
                    world.player.hMovement = 0;
                    world.player.setLocation(getX()+getImage().getWidth()-2, world.player.getY());
                } 
            }
        }
    }

}
