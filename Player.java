import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends SmoothMover
{
    int x = 0;
    int y = 0;
    double hMovement = 0;
    double jumpHeight = 0;
    double gravityModifier = 0;
    int dashable = 0;
    int jumpIndex = 0;
    int fallIndex = 0; 
    int landingIndex = 0;
    boolean isGrounded = true;
    boolean peakJump = false;
        
    SimpleTimer animationTimer = new SimpleTimer();
    
    //direction elephant is facing
    String facing = "right";
    /**
     * constructor creates image for animation
     */
    public Player()
    {
    }
    
    /**
     * Movement code, responds to keyboard inputs.
     */
    public void act()
    {
        //movement
        if(isAtEdge()) 
        {
            
        }
        if(Greenfoot.isKeyDown("left"))
        {
            hMovement -= 1;
            facing = "left";
        }
        if(Greenfoot.isKeyDown("right"))
        {
            hMovement += 1;
            facing = "right";
        }
        if(Greenfoot.isKeyDown("up"))
        {
            isGrounded = false;
            jump();
            gravityModifier-=0.05;
        }
        //jump code
        if((peakJump&&!isGrounded)&&gravityModifier < 2)
        {
            gravityModifier+=0.7;
        }
        if(jumpHeight > 10)
        {
            gravityModifier = 0.3;
        }
        if(Greenfoot.isKeyDown("space"))
        {
            dash();
        }
        setLocation(getX()+(int)hMovement, getY()+jumpHeight);
        bounding();
        if(!isGrounded)
        {
            if(!Greenfoot.isKeyDown("up")&&!peakJump)
            {
                peakJump = true;
                gravityModifier = 0;
                jumpHeight = 0;
                jumpHeight += gravityModifier;
            }
            else
            {
                jumpHeight += gravityModifier;
            }
        }
        isGrounded = isTouching(Platform.class);
        if(hMovement < 0)
        {
            hMovement+=0.8;
        }
        else if (hMovement > 0)
        {
            hMovement-=0.8;
        }
        dashable++;
    }
    /**
     * Moves the elephant vertically, and controls how high the elephant can jump
     */
    public void jump()
    {
        if(peakJump == false)
        {
            isGrounded = false;
            jumpHeight = -8;
            jumpHeight -= gravityModifier;
        }
        if(isGrounded == false&&gravityModifier < -1)
        {
            peakJump = true;
        }
    }
    /**
     * Increases speed for a short amount of time
     */
    public void dash()
    {
        if(dashable > 100)
        {
            if(!isTouching(Platform.class))
            {
                jumpHeight = 0;
                gravityModifier = 0;
                peakJump = true;
                isGrounded = false;
            }
            if(facing == "left")
            {
                hMovement -= 20;
            }
            else
            {
                hMovement += 20;
            }
            dashable = 0;
        }
    }
    public void bounding()
    {
        if(getX()>1195)
        {
            setLocation(1195, getY());
            hMovement = 0;
        }
        else if(getX() < 5)
        {
            setLocation(5, getY());
            hMovement = 0;
        }

    }
}
