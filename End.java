import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The end goal of every level
 * 
 * @author Andy 
 * @version 2
 */
public class End extends Actor
{
    GreenfootImage[] star = new GreenfootImage[6];
    SimpleTimer animationTimer = new SimpleTimer();
    public End()
    {
        for(int i = 0; i < 5; i++)
        {
            //renaming files is pain, so I didn't
            star[i] = new GreenfootImage("images/star/frame_" + i + "_delay-0.1s.gif");
            star[i].scale(50,50);
        }
    }
    /**
     * Checks if the player is touching it, and then changes the Level
     */
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!world.paused)
        {
            if(isTouching(Player.class))
            {
                world.worldNum++;
                world.changeWorld(world.worlds[world.worldNum]);
            }
            animate();
        }

    }
    int imageIndex;
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        setImage(star[imageIndex]);     
        imageIndex = (imageIndex + 1) % star.length;
    }
}

