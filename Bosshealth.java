import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays the health remaining in the boss fight
 * 
 * @author Andy
 * @version 2
 */
public class Bosshealth extends Actor
{
    GreenfootImage[] healthDisplay = new GreenfootImage[10];
    int deathTime = 0;
    int speed = 15;
    int slowDown = 0;
    public Bosshealth()
    {
        for(int i = 0; i < 10; i++)
        {
            healthDisplay[i] = new GreenfootImage("images/Enemies/Boss/HealthBar/frame_0" + i + "_delay-0.1s.gif");
        }
    }
    /**
     * Displays health of boss
     */
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!world.paused)
        {
            if(world.voidBird.introFinished)
            {
                if(getY() > 50)
                {
                    //moves into position
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
                if(world.voidBird.bHP >=0)
                {
                    //Displays health
                    setImage(healthDisplay[world.voidBird.bHP/2]);
                }
                else if(deathTime < 200)
                {
                    //shakes for cool visual effect
                    setLocation(600+Greenfoot.getRandomNumber(25), 50+Greenfoot.getRandomNumber(25));
                    deathTime++;
                }
                else
                {
                    //die
                    world.removeObject(this);
                }
            }
        }
    }
}
