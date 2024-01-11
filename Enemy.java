import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends SmoothMover
{
    int health = 30;
    String direction = "right";
    int iFrames = 0;
    int speed = 3;
    SimpleTimer animationTimer = new SimpleTimer();
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage[] moveRight = new GreenfootImage[7];
    GreenfootImage[] moveLeft = new GreenfootImage[7];
    public Enemy()
    {
        for(int i = 0; i < 7; i++)
        {
            moveRight[i] = new GreenfootImage("images/Enemies/simpleRobot/Sbot" + i + ".png");
            moveLeft[i] = new GreenfootImage("images/Enemies/simpleRobot/Sbot" + i + ".png");
            moveLeft[i].mirrorHorizontally();
        }
        animationTimer.mark();
    }
    
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(direction == "right")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()+25, getY()+28)&&world.boxAtLocation(getX()+25, getY()))
            {
                move(speed);
            }
            else
            {
                direction = "left";
                move(-1*speed);
            }
        }
        else if(direction == "left")
        {
            if(isTouching(Box.class)&&!world.boxAtLocation(getX()-25, getY()+28)&&world.boxAtLocation(getX()-25, getY()))
            {
                move(-1*speed);
            }
            else
            {
                direction = "right";
                move(speed);
            }
        }
        iFrames++;
        animate();
        death();
    }
    public void death()
    {
        GameWorld world = (GameWorld) getWorld();
        if(isTouching(Player.class)&& world.player.dashable < 10&&iFrames>10)
        {
            if(world.player.hMovement>0)
            {
                health-=(int)world.player.hMovement;
            }
            else
            {
                health+=(int)world.player.hMovement;
            }
            iFrames = 0;
        }
        if(health <=0)
        {
            world.removeObject(this);
            world.playerHP++;
        }
    }
    int imageIndex = 0;
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(direction.equals("right"))
        {
            setImage(moveRight[imageIndex]);     
            imageIndex = (imageIndex + 1) % moveRight.length;
        }
        else
        {
            setImage(moveLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % moveLeft.length;        
        }
    }
}
