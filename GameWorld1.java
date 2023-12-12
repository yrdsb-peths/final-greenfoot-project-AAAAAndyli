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
        addObject(platform,72,574);
        Platform platform2 = new Platform();
        addObject(platform2,251,575);
        Platform platform3 = new Platform();
        addObject(platform3,442,575);
        Platform platform4 = new Platform();
        addObject(platform4,633,575);
        Platform platform5 = new Platform();
        addObject(platform5,824,575);
        Platform platform6 = new Platform();
        addObject(platform6,1014,575);
        Platform platform7 = new Platform();
        addObject(platform7,1187,575);
        Wall wall = new Wall();
        addObject(wall,172,434);
        Wall wall2 = new Wall();
        addObject(wall2,1064,437);
        addObject(player,609,175);
    }
}
