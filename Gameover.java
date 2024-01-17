import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * World when you lose
 * 
 * @author Andy
 * @version 2
 */
public class Gameover extends World
{
    GreenfootSound go = new GreenfootSound("go.mp3");
    /**
     * Constructor for objects of class Gameover.
     * 
     */
    public Gameover()
    {    
        // GameOver world, rotissere chicken in background
        super(600, 400, 1); 
        prepare();
        go.playLoop();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     * this is intentionally ugly
     */
    private void prepare()
    {
        Label label = new Label("Game Over", 120);
        addObject(label,269,145);
        label.setLocation(440,169);
        label.setLocation(412,116);
        Button button = new Button(2);
        addObject(button,439,375);
        label.setLocation(279,197);
        label.setLocation(351,174);
    }
}
