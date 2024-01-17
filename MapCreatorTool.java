import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Assists me in map creation
 * 
 * @author Andy
 * @version 1
 */
public class MapCreatorTool extends World
{
    int mode = 0;
    int[][] worldGen = new int[24][12];
    /**
     * Constructor for objects of class MapCreatorTool.
     * Used for development purposes, not intended for actual gameplay.
     */
    public MapCreatorTool()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 600, 1, false); 

    }
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null&&Greenfoot.mouseClicked(this))
        {
            int x = mouse.getX();
            int y = mouse.getY();
            x = (int)Math.round(x/50)*50+25;
            y = (int)Math.round(y/50)*50+25;
            if(mode == 1)
            {
                placeBox(x,y);
                worldGen[mouse.getX()/50][mouse.getY()/50] = 1;
            }
            else if(mode == 2)
            {
                placeEnemy(x,y);
                worldGen[mouse.getX()/50][mouse.getY()/50] = 3;
            }
            else if(mode == 3)
            {
                placeAdvancedEnemy(x,y);
                worldGen[mouse.getX()/50][mouse.getY()/50] = 4;
            }
        }
        select();
        if(Greenfoot.isKeyDown("enter"))
        {
            printArray(worldGen);
        }
    }
    /**
     * Picks either a tile (1) or a simple enemy (2) or an advanced enemy (3)
     */
    public void select()
    {
        if(Greenfoot.isKeyDown("1"))
        {
            mode = 1;
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            mode = 2;
        }
        else if(Greenfoot.isKeyDown("3"))
        {
            mode = 3;
        }
    }
    /**
     * Places a box
     */
    public void placeBox(int x, int y)
    {
        VisualBox box = new VisualBox();
        addObject(box,x,y);
    }
    /**
     * Places an enemy
     */
    public void placeEnemy(int x, int y)
    {
        VisualEnemy enemy = new VisualEnemy();
        addObject(enemy,x,y);
    }
    /**
     * Places an advanced enemy
     */
    public void placeAdvancedEnemy(int x, int y)
    {
        VAEnemy aEnemy = new VAEnemy();
        addObject(aEnemy,x,y);
    }
    /**
     * Prints out the 2d array of the new world created in terminal
     */
    public void printArray(int[][] arr)
    {
        System.out.println("New world:");
        System.out.print("{");
        for(int j = 0; j < 12; j++)
        {
            System.out.print("{");
            for(int i = 0; i < 24; i++)
            {
                System.out.print(arr[i][j]);
                System.out.print(", ");
            }
            System.out.print("},");
            System.out.println("");
        }
        System.out.print("};");
    }
}
