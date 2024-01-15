import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bosshealth here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bosshealth extends Actor
{
    /**
     * Act - do whatever the Bosshealth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] healthDisplay = new GreenfootImage[10];
    int speed = 15;
    int slowDown = 0;
    public Bosshealth()
    {
        for(int i = 0; i < 10; i++)
        {
            healthDisplay[i] = new GreenfootImage("images/Enemies/Boss/HealthBar/frame_0" + i + "_delay-0.1s.gif");
        }
    }
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(world.voidBird.introFinished)
        {
            if(getY() > 50)
            {
                setLocation(getX(), getY()-speed);
                if(slowDown == 5)
                {
                    slowDown=0;
                    speed--;
                }
                else
                {
                    slowDown++;
                }
            }
            setImage(healthDisplay[world.voidBird.bHP]);
        }
    }
}
