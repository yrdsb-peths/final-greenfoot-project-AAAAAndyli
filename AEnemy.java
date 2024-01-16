import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AdvancedEnemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AEnemy extends Enemy
{
    /**
     * Act - do whatever the AdvancedEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //state 0 = patrolling
    //state 1 = hunting
    
    boolean isGrounded = true;
    int state = 0;
    double jumpHeight = 0;
    GreenfootImage[] floatRight = new GreenfootImage[5];
    GreenfootImage[] floatLeft = new GreenfootImage[5];
    GreenfootSound death = new GreenfootSound("HunterDeath.wav");
    public AEnemy()
    {
        speed = 2;
        for(int i = 0; i < 5; i++)
        {
            floatRight[i] = new GreenfootImage("images/Enemies/advancedRobot/Abot" + i + ".png");
            floatLeft[i] = new GreenfootImage("images/Enemies/advancedRobot/Abot" + i + ".png");
            floatLeft[i].mirrorHorizontally();
        }
        animationTimer.mark();
    }
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!world.paused)
        {
            if(health > 0)
            {
                if(!world.boxAtLocation(getX(), getY()+26))
                {
                    isGrounded = true;
                }
                else
                {
                    isGrounded = false;
                }
                if(getY() > 700)
                {
                    health = -99;
                }
                if(state == 0)
                {
                    if(isGrounded)
                    {
                        if(direction == "right")
                        {
                            if(!world.boxAtLocation(getX()+26, getY()+30))
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
                            if(!world.boxAtLocation(getX()-26, getY()+30))
                            {
                                move(-1*speed);
                            }
                            else
                            {
                                direction = "right";
                                move(speed);
                            }
                        }
                    }
                    else
                    {
                        if(direction == "right")
                        {
                            move(speed);
                            if(isTouching(Box.class)||getX() > 1200)
                            {
                                direction = "left";
                                move(-1*speed);
                                setLocation(getX(), getY()+Greenfoot.getRandomNumber(10));
                            }
                        }
                        else if(direction == "left")
                        {
                            move(-1*speed);
                            if(isTouching(Box.class)||getX() < 0)
                            {
                                direction = "right";
                                move(speed);
                                setLocation(getX(), getY()+Greenfoot.getRandomNumber(10));
                            }
                        }
                    }
                }
                else
                {
                    if(world.player.getX() > getX())
                    {
                        direction = "right";
                        if(!world.boxAtLocation(getX(), getY()+26)||(!world.boxAtLocation(getX()+26, getY()-25))&&!world.boxAtLocation(getX(), getY()+25))
                        {
                            move(speed+2);
                        }
                        if(((world.player.getY() < getY())||(!world.boxAtLocation(getX()+26, getY()-25)))&&world.boxAtLocation(getX(), getY()-25))
                        {
                            setLocation(getX()+((speed+2)/2),getY()-3);
                        }
                        else if(((world.player.getY() > getY()))&&world.boxAtLocation(getX(), getY()+25))
                        {
                            setLocation(getX()+((speed+2)/2),getY()+3);
                        }
                    }
                    else if(world.player.getX() < getX())
                    {
                        direction = "left";
                        if(!world.boxAtLocation(getX(), getY()+26)||!world.boxAtLocation(getX(), getY()-25)||!world.boxAtLocation(getX(), getY()+25))
                        {
                            move(-1*speed-2);
                        }
                        if(((world.player.getY() < getY())||(!world.boxAtLocation(getX()-26, getY()-25)))&&world.boxAtLocation(getX(), getY()-25))
                        {
                            setLocation(getX()+((-1*speed-2)/2),getY()-3);
                        }
                        else if(((world.player.getY() > getY()))&&world.boxAtLocation(getX(), getY()+25))
                        {
                            setLocation(getX()+((-1*speed-2)/2),getY()+3);
                        }
                    }
                }
                if(300>Math.sqrt((world.player.getY() - getY()) * (world.player.getY() - getY()) + (world.player.getX() - getX()) * (world.player.getX() - getX())))
                {
                    state = 1;
                }
                else
                {
                    state = 0;
                }
                if(!world.boxAtLocation(getX()-26, getY()))
                {
                    direction = "right";
                    move(5);
                }
                else if(!world.boxAtLocation(getX()+26, getY()))
                {
                    direction = "left";
                    move(-5);
                }
                if(!world.boxAtLocation(getX(), getY()-25))
                {
                    setLocation(getX(),getY()+3);
                }
                if(!world.boxAtLocation(getX(), getY()+25))
                {
                    setLocation(getX(),getY()-3);
                }
                iFrames++;
                death();
                death = new GreenfootSound("HunterDeath.wav");
                animate(floatRight, floatLeft);
            }
            else
            {
                death();
            }
        }
    }
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
            if(deathTime < 100)
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
}
