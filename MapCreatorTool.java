import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MapCreatorTool here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapCreatorTool extends World
{
    int mode = 0;
    /**
     * Constructor for objects of class MapCreatorTool.
     * 
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
            x = Math.round(x/49)*50+25;
            y = Math.round(y/49)*50+25;
            if(mode == 1)
            {
                placeBox(x,y);
            }
            else if(mode == 2)
            {
                placeEnemy(x,y);
            }
            else if(mode == 3)
            {
                placeAdvancedEnemy(x,y);
            }
        }
        select();
    }
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
    public void placeBox(int x, int y)
    {
        VisualBox box = new VisualBox();
        addObject(box,x,y);
    }
    public void placeEnemy(int x, int y)
    {
        VisualEnemy enemy = new VisualEnemy();
        addObject(enemy,x,y);
    }
    public void placeAdvancedEnemy(int x, int y)
    {
        VAEnemy aEnemy = new VAEnemy();
        addObject(aEnemy,x,y);
    }
}
