import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mainmenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mainmenu extends World
{

    /**
     * Constructor for objects of class Mainmenu.
     * 
     */
    public Mainmenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 600, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Title title = new Title();
        addObject(title,627,228);
        Button button = new Button(0);
        addObject(button,600,600);
    }
}
