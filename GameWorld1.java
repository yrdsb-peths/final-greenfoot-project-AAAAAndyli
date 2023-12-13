import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld1 extends World
{
    int[][] world1 = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    
    Player player = new Player();
    /**
     * Constructor for objects of class GameWorld1.
     * 
     */
    public GameWorld1()
    {    
        super(1200, 600, 1); 
        //prepare();
        addObject(player,609,175);
        buildWorld(world1);
    }
    
    private void buildWorld(int[][] world)
    {
        for(int j = 0; j < 12; j++)
        {
            for(int i = 0; i < 24; i++)
            {
                if(world[j][i] == 1)
                {
                    Box box = new Box();
                    addObject(box,i*50+25,j*50+25);
                }
            }
        }
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
        addObject(wall,1064,437);
        addObject(player,609,175);
    }
}
