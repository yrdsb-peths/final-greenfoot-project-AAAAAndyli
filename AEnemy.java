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
    public AEnemy()
    {
        speed = 3;
    }
    public void act()
    {
        GameWorld1 world = (GameWorld1) getWorld();
        if(isTouching(Box.class))
        {
            setLocation(getX(),getY()-0.1);
        }
        if(!world.boxAtLocation(getX(), getY()+27))
        {
            isGrounded = true;
        }
        else if((world.boxAtLocation(getX(), getY()+27)))
        {
            isGrounded = false;
            setLocation(getX(),getY()+10);
        }
        if(state == 0)
        {
            if(direction == "right")
            {
                if(isTouching(Box.class)&&!world.boxAtLocation(getX()+26, getY()+28)&&world.boxAtLocation(getX()+26, getY()))
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
                if(isTouching(Box.class)&&!world.boxAtLocation(getX()-26, getY()+28)&&world.boxAtLocation(getX()-26, getY()))
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
            if(world.player.getX() > getX())
            {
                if(isTouching(Box.class)&&!world.boxAtLocation(getX()+26, getY()+28)&&world.boxAtLocation(getX()+26, getY())&&!world.boxAtLocation(getX(), getY()+26))
                {
                    move(speed+2);
                    jumpHeight = 0;
                }
                else if((world.player.getY() < getY())||(!world.boxAtLocation(getX()+26, getY()-26))&&isGrounded)
                {
                    setLocation(getX()+1,getY()-25+jumpHeight);
                    jumpHeight++;
                }
                else if(!isGrounded)
                {
                    setLocation(getX()+1,getY()-25+jumpHeight);
                    jumpHeight++;
                }
            }
            else if(world.player.getX() < getX())
            {
                if(isTouching(Box.class)&&!world.boxAtLocation(getX()-26, getY()+28)&&world.boxAtLocation(getX()-26, getY())&&!world.boxAtLocation(getX(), getY()+26))
                {
                    move(-1*(speed+2));
                    jumpHeight = 0;
                }
                else if((world.player.getY() < getY())||(!world.boxAtLocation(getX()-26, getY()-26))&&isGrounded)
                {
                    setLocation(getX()-1,getY()-25+jumpHeight);
                    jumpHeight++;
                }
                else if(!isGrounded)
                {
                    setLocation(getX()-1,getY()-25+jumpHeight);
                    jumpHeight++;
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
        if(jumpHeight > 30)
        {
            jumpHeight = 30;
        }
        if(!world.boxAtLocation(getX()-26, getY()))
        {
            direction = "right";
            move(speed);
        }
        else if(!world.boxAtLocation(getX()+26, getY()))
        {
            direction = "left";
            move(-1*speed);
        }
        iFrames++;
        touchingPlayer();
    }
}