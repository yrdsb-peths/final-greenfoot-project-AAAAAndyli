import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A simple enemy that moves back and forth
 * 
 * @author Andy
 * @version 3
 */
public class Enemy extends SmoothMover
{
    int health = 30;
    String direction = "right";
    int iFrames = 0;
    int speed = 3;
    SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage[] moveRight = new GreenfootImage[7];
    GreenfootImage[] moveLeft = new GreenfootImage[7];
    GreenfootImage[] explosion = new GreenfootImage[12];
    
    GreenfootSound Explosion = new GreenfootSound("Explosion.mp3");
    GreenfootSound metalHit = new GreenfootSound("RHit.mp3");
    GreenfootSound death = new GreenfootSound("DroneDeath.mp3");
    public Enemy()
    {
        for(int i = 0; i < 7; i++)
        {
            moveRight[i] = new GreenfootImage("images/Enemies/simpleRobot/Sbot" + i + ".png");
            moveLeft[i] = new GreenfootImage("images/Enemies/simpleRobot/Sbot" + i + ".png");
            moveLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < 11; i++)
        {
            explosion[i] = new GreenfootImage("images/explosion/boom" + i + ".png");
            explosion[i].scale(100, 100);
        }
        animationTimer.mark();
    }
    
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        Explosion.setVolume(world.maxV);
        metalHit.setVolume(world.maxV);
        death.setVolume(world.maxV);
        if(!world.paused)
        {
            if(health > 0)
            {
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
                animate(moveRight, moveLeft);
                death();
            }
            death();
        }
    }
    int deathTime;
    int deathIndex;
    /**
     * Checks if it is touching player, and check its health.
     * If health is below 0, it dies
     */
    public void death()
    {
        GameWorld world = (GameWorld) getWorld();
        if(isTouching(Player.class)&& world.player.dashable < 20&&iFrames>10)
        {
            health-=10;
            metalHit.play();  
            iFrames = 0;
        }
        if(health <=0)
        {
            if(deathTime < 50)
            {
                death.play();
                setLocation(getX()+Greenfoot.getRandomNumber(10)-5, getY()+Greenfoot.getRandomNumber(10)-5);
                deathTime++;
            }
            else if(animationTimer.millisElapsed() < 99)
            {
                return;
            }
            else if(deathIndex !=11)
            {
                Explosion.play();
                setImage(explosion[deathIndex]);     
                deathIndex = (deathIndex + 1) % explosion.length;
            }
            else
            {
                world.removeObject(this);
                world.playerHP++;
            }
        }
    }
    int imageIndex = 0;
    /**
     * Animate
     */
    public void animate(GreenfootImage[] right, GreenfootImage[] left)
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
