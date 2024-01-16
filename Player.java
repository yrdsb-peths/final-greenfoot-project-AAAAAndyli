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
    int dashable = 1000;
    int jumpIndex = 0;
    int fallIndex = 0; 
    int landingIndex = 0;
    int iFrames = 0;
    boolean isGrounded = true;
    boolean peakJump = false;
    boolean isWalking = false;
    boolean isDashing = false;
        
    SimpleTimer animationTimer = new SimpleTimer();
    
    GreenfootImage[] idleRight = new GreenfootImage[2];
    GreenfootImage[] idleLeft = new GreenfootImage[2];
    
    GreenfootImage[] walkRight = new GreenfootImage[5];
    GreenfootImage[] walkLeft = new GreenfootImage[5];
    
    GreenfootImage[] dashRight = new GreenfootImage[3];
    GreenfootImage[] dashLeft = new GreenfootImage[3];
    
    GreenfootSound dash = new GreenfootSound("Dash.wav");
    GreenfootSound bossDamage = new GreenfootSound("BossHit.wav");
    
    String facing = "right";
    /**
     * constructor creates image for animation
     */
    public Player()
    {
        for(int i = 0; i < 2; i++)
        {
            idleRight[i] = new GreenfootImage("images/Chicken/Idle/idle" + i + ".png");
            idleLeft[i] = new GreenfootImage("images/Chicken/Idle/idle" + i + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < 5; i++)
        {
            walkRight[i] = new GreenfootImage("images/Chicken/walk/walk" + i + ".png");
            walkLeft[i] = new GreenfootImage("images/Chicken/walk/walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        for(int i = 0; i < 3; i++)
        {
            dashRight[i] = new GreenfootImage("images/Chicken/dash/dash" + i + ".png");
            dashLeft[i] = new GreenfootImage("images/Chicken/dash/dash" + i + ".png");
            dashLeft[i].mirrorHorizontally();
        }
        jumpHeight = 0;
        gravityModifier = 0;
        hMovement = 0;
    }
    
    /**
     * Movement code, responds to keyboard inputs.
     */
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();

        dash.setVolume(world.maxV);
        if(!world.paused)
        {
            touchingEnemy();
            //movement
            isWalking = false;
            if(Greenfoot.isKeyDown("left"))
            {
                isWalking = true;
                hMovement -= 1;
                facing = "left";
            }
            if(Greenfoot.isKeyDown("right"))
            {
                isWalking = true;
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
            if(Greenfoot.isKeyDown("space")||dashable < 21)
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
            isGrounded = isTouching(Box.class);
            if(hMovement < 0)
            {
                hMovement+=0.8;
            }
            else if (hMovement > 0)
            {
                hMovement-=0.8;
            }
            if(hMovement < -10&&dashable > 50)
            {
                hMovement=-10;
            }
            else if (hMovement > 10&&dashable > 50)
            {
                hMovement=10;
            }
            dashable++;
            iFrames++;
            animate();
        }
    }
    /**
     * Moves the elephant vertically, and controls how high the elephant can jump
     */
    public void jump()
    {
        if(peakJump == false)
        {
            isGrounded = false;
            jumpHeight = -9;
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
        if(dashable < 20)
        {
            dash.play();
            isDashing = true;
            if(!isTouching(Box.class))
            {
                jumpHeight = 0;
                gravityModifier = 0;
                peakJump = true;
                isGrounded = false;
            }
        }
        if(dashable > 50)
        {
            if(!isTouching(Box.class))
            {
                jumpHeight = 0;
                gravityModifier = 0;
                peakJump = true;
                isGrounded = false;
            }
            if(facing == "left")
            {
                hMovement = -20;
            }
            else
            {
                hMovement = 20;
            }
            dashable = 0;
        }
        else if(dashable == 20)
        {
            isDashing = false;
            jumpHeight = 0;
            gravityModifier = 0;
            hMovement = 0;
        }
    }
    public void bounding()
    {
        GameWorld world = (GameWorld) getWorld();
        if(getX()>1190)
        {
            setLocation(1190, getY());
            hMovement = 0;
        }
        else if(getX() < 10)
        {
            setLocation(10, getY());
            hMovement = 0;
        }
        if(getY() > 700)
        {
            setLocation(world.playerSpawnX, world.playerSpawnY);
            world.playerHP--;
        }
        if(getY() < 1)
        {
            setLocation(getX(), 2);
        }
    }
    public void touchingEnemy()
    {
        GameWorld world = (GameWorld) getWorld();
        if(!getWorld().getObjects(Boss.class).isEmpty()&&world.voidBird.isTouchingPlayer(false)&& dashable > 20&& iFrames > 50)
        {

            if(world.voidBird.bHP != 18)
            {
                world.voidBird.bHP++;
            }
            world.playerHP -= 2;
            iFrames = 0;
            hMovement = 0;
        }
        else if(!getWorld().getObjects(Boss.class).isEmpty()&&world.voidBird.isTouchingPlayer(true)&& dashable <= 20 &&world.voidBird.iframeB > 25)
        {
            bossDamage.play();
            world.voidBird.bHP--;
            world.voidBird.iframeB=0;
        }

        if(isTouching(Enemy.class)&& dashable > 20&& iFrames > 50)
        {
            world.playerHP --;
            iFrames = 0;
            hMovement = 0;
        }
    }
    int walkIndex = 0;
    int dashIndex = 0;
    int idleIndex = 0;
    public void animate()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(isDashing) 
        {
            if(facing.equals("right"))
            {
                setImage(dashRight[dashIndex]);     
                dashIndex = (dashIndex + 1) % dashRight.length;
            }
            else
            {
                setImage(dashLeft[dashIndex]);
                dashIndex = (dashIndex + 1) % dashLeft.length;        
            }
        }
        else if(isWalking)
        {
            if(facing.equals("right"))
            {
                setImage(walkRight[walkIndex]);     
                walkIndex = (walkIndex + 1) % walkRight.length;
            }
            else
            {
                setImage(walkLeft[walkIndex]);
                walkIndex = (walkIndex + 1) % walkLeft.length;        
            }
        }
        else
        {
            if(facing.equals("right"))
            {
                setImage(idleRight[idleIndex]);     
                idleIndex = (idleIndex + 1) % idleRight.length;
            }
            else
            {
                setImage(idleLeft[idleIndex]);
                idleIndex = (idleIndex + 1) % idleLeft.length;        
            }
        }
    }
}
