import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends SmoothMover
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int bHP = 400;
    
    GreenfootImage[] fall= new GreenfootImage[4];
    GreenfootImage[] land= new GreenfootImage[25];
    
    GreenfootImage[] dashRP = new GreenfootImage[5];
    GreenfootImage[] dashLP = new GreenfootImage[5];
    
    GreenfootImage[] dashR = new GreenfootImage[3];
    GreenfootImage[] dashL = new GreenfootImage[3];
    
    GreenfootImage[] stompR = new GreenfootImage[13];
    GreenfootImage[] stompL = new GreenfootImage[13];
    
    GreenfootImage[] bladeR = new GreenfootImage[16];
    GreenfootImage[] bladeL = new GreenfootImage[16];
    
    public Boss()
    {
        for(int i = 0; i < 4; i++)
        {
            fall[i] = new GreenfootImage("images/Enemies/Boss/introBird/f" + i + ".png");
        }
        for(int i = 0; i < 25; i++)
        {
            land[i] = new GreenfootImage("images/Enemies/Boss/introBird/i" + i + ".png");
        }
        for(int i = 0; i < 5; i++)
        {
            dashRP[i] = new GreenfootImage("images/Enemies/Boss/dashBird/dp" + i + ".png");
            dashLP[i] = new GreenfootImage("images/Enemies/Boss/dashBird/dp" + i + ".png");
            dashLP[i].mirrorHorizontally();
        }
        for(int i = 0; i < 3; i++)
        {
            dashR[i] = new GreenfootImage("images/Enemies/Boss/dashBird/d" + i + ".png");
            dashL[i] = new GreenfootImage("images/Enemies/Boss/dashBird/d" + i + ".png");
            dashL[i].mirrorHorizontally();
        }
        for(int i = 0; i < 13; i++)
        {
            stompR[i] = new GreenfootImage("images/Enemies/Boss/stompBird/s" + i + ".png");
            stompL[i] = new GreenfootImage("images/Enemies/Boss/stompBird/s" + i + ".png");
            stompL[i].mirrorHorizontally();
        }
        for(int i = 0; i < 16; i++)
        {
            bladeR[i] = new GreenfootImage("images/Enemies/Boss/bladeBird/b" + i + ".png");
            bladeL[i] = new GreenfootImage("images/Enemies/Boss/bladeBird/b" + i + ".png");
            bladeL[i].mirrorHorizontally();
        }

    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void introduction()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(direction.equals("right"))
        {
            setImage(right[imageIndex]);     
            imageIndex = (imageIndex + 1) % right.length;
        }
        else
        {
            setImage(left[imageIndex]);
            imageIndex = (imageIndex + 1) % left.length;        
        }
    }
}
