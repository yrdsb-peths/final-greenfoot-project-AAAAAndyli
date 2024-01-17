import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays Achievements after you beat the game
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Achievements extends Actor
{
    int type;
    GreenfootImage[] achievementImages = new GreenfootImage[3];
    GreenfootImage[] status = new GreenfootImage[2];
    boolean doOnce = true;
    public Achievements(int achievementType)
    {
        achievementImages[0] = new GreenfootImage("images/Achievements/KEE.png"); //Kill every enemy
        achievementImages[1] = new GreenfootImage("images/Achievements/BUF.png"); //Beat under 5
        achievementImages[2] = new GreenfootImage("images/Achievements/NH.png"); //No Hit
        setImage(achievementImages[achievementType]);
        type = achievementType;
    }
    /**
     * Act - do whatever the Achievements wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act()
    {
        if(doOnce)
        {
            GameWorld world = (GameWorld) getWorld();
            if(type == 0)
            {
                if(world.killAll)
                {
                    Visualcompletion check = new Visualcompletion(1);
                    world.addObject(check, getX(), getY());   
                }
                else
                {
                    Visualcompletion check = new Visualcompletion(0);
                    world.addObject(check, getX(), getY());   
                }
            }
            else if(type == 1)
            {
                if(world.completeUnder5)
                {
                    Visualcompletion check = new Visualcompletion(1);
                    world.addObject(check, getX(), getY());   
                }
                else
                {
                    Visualcompletion check = new Visualcompletion(0);
                    world.addObject(check, getX(), getY());   
                }
            }
            else
            {
                if(world.noHit)
                {
                    Visualcompletion check = new Visualcompletion(1);
                    world.addObject(check, getX(), getY());   
                }
                else
                {
                    Visualcompletion check = new Visualcompletion(0);
                    world.addObject(check, getX(), getY());   
                }
            }
            doOnce = false;
        }
    }
}
