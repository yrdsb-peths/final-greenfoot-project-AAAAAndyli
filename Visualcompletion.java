import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Visualcompletion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Visualcompletion extends Actor
{
    GreenfootImage[] status = new GreenfootImage[2];
    public Visualcompletion(int checkOrX)
    {
        status[0] = new GreenfootImage("images/Achievements/Fail.png");
        status[1] = new GreenfootImage("images/Achievements/Complete.png");
        setImage(status[checkOrX]);
    }
    /**
     * Act - do whatever the Visualcompletion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
