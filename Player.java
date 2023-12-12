import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * The player
 * 
 * @author 7678rfyuf
 * @version December 2023
 */
public class Player extends Actor
{
    int x = 0;
    int y = 0;
    double hMovement = 0;
    int jumpHeight = 0;
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
        if(Greenfoot.isKeyDown("left"))
        {
            hMovement -= 1.3;
            facing = "left";
        }
        if(Greenfoot.isKeyDown("right"))
        {
            hMovement += 1.3;
            facing = "right";
        }
        if(Greenfoot.isKeyDown("up"))
        {
            isGrounded = false;
            jump();
            gravityModifier-=0.3;
        }
        //jump code
        if((peakJump&&!isGrounded)&&gravityModifier < 2)
        {
            gravityModifier+=0.5;
        }
        if(Greenfoot.isKeyDown("space"))
        {
            dash();
        }
        setLocation(getX()+(int)hMovement, getY()+jumpHeight);
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
        if(hMovement < 0)
        {
            hMovement++;
        }
        else if (hMovement > 0)
        {
            hMovement--;
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
            jumpHeight = -12;
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
                hMovement = -50;
            }
            else
            {
                hMovement = 50;
            }
            dashable = 0;
        }
    }
  
}