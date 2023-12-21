import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld1 extends World
{
    int playerHP = 20;
    Label HP;
    
    Box box = new Box();
    int[][] world1 = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0},
        {1,1,1,0,0,0,0,0,0,4,3,3,3,3,3,0,0,0,0,0,0,1,1,1},
        {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {2,0,0,0,0,1,1,0,0,0,0,0,4,0,0,0,0,1,1,0,0,0,0,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    int[][] world2 = {
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
        {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0},
        {2,0,0,0,0,1,1,0,0,0,0,0,0,0,3,0,0,1,1,0,0,0,0,0},
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
        buildWorld(world1);
        HP = new Label(0,40);
        addObject(HP, 50, 30);
    }
    public void act()
    {
        HP.setValue(playerHP);
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
                else if(world[j][i] == 2)
                {
                    addObject(player,i*50+25,j*50+25);
                }
                else if(world[j][i] == 3)
                {
                    Enemy enemy = new Enemy();
                    addObject(enemy,i*50+25,j*50+26);
                }
                else if(world[j][i] == 4)
                {
                    AEnemy advancedenemy = new AEnemy();
                    addObject(advancedenemy,i*50+25,j*50+26);
                }
            }
        }
    }
    
    public boolean boxAtLocation(int x,int y)
    {
        return getObjectsAt(x, y, Box.class).isEmpty();
    }
    
    public void respawn()
    {
        
    }

}
