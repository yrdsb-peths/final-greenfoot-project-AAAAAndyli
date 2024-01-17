import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main menu, should be the first thing loading in
 * 
 * @author Andy
 * @version 1
 */
public class Mainmenu extends World
{
    GreenfootSound mm = new GreenfootSound("mm.wav");
    /**
     * Constructor for objects of class Mainmenu.
     * Main menu
     */
    public Mainmenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 600, 1); 
        prepare();
        mm.playLoop();
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
