import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld1 extends World
{
    Player player = new Player();
    /**
     * Constructor for objects of class GameWorld1.
     * 
     */
    public GameWorld1()
    {    
        super(1200, 600, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Platform platform = new Platform();
        addObject(platform,145,538);
        Platform platform2 = new Platform();
        addObject(platform2,613,541);
        Platform platform3 = new Platform();
        addObject(platform3,1047,547);
        addObject(player,164,373);
    }
}
