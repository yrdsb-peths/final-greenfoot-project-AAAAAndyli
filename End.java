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

