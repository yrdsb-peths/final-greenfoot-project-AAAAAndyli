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
            setLocation(getX(),getY()-0.5);
        }
        if(!world.boxAtLocation(getX(), getY()+20))
        {
            isGrounded = true;
        }
        else
        {
            setLocation(getX(),getY()+0.1);
            isGrounded = false;
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
                    setLocation(getX()+speed,getY()-24+jumpHeight);
                    jumpHeight++;
                    isGrounded = false;
                }
                else if(!isGrounded)
                {
                    setLocation(getX()+speed,getY()-24+jumpHeight);
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
                    setLocation(getX()-speed,getY()-24+jumpHeight);
                    jumpHeight++;
                    isGrounded = false;
                }
                else if(!isGrounded)
                {
                    setLocation(getX()-speed,getY()-24+jumpHeight);
                    jumpHeight++;
                }
            }
        }
        if(!isGrounded||300>Math.sqrt((world.player.getY() - getY()) * (world.player.getY() - getY()) + (world.player.getX() - getX()) * (world.player.getX() - getX())))
        {
            state = 1;
        }
        else
        {
            state = 0;
        }
        if(jumpHeight > 27)
        {
            jumpHeight = 27;
        }
        iFrames++;
        touchingPlayer();
    }
}
