import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Wind
 * 
 * @author Andy
 * @version 1
 */
public class WindL extends Wind
{
    public WindL()
    {
        setRotation(180);
    }
    public void act()
    {
        moveUntilOut();
    }
}
