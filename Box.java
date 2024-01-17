import greenfoot.*;  //  (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tile with collision for most things
 * 
 * @author Andy
 * @version 3
 */
public class Box extends Actor
{
    /**
     * Acts as a collider/obstacle to the player
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
