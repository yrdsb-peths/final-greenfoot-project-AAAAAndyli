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
    int bHP = 9;
    SimpleTimer animationTimer = new SimpleTimer();
    String direction = "right";
    boolean damaging = false;
    boolean introFinished = false;
    boolean fallLocationFound = false;
    
    boolean attack = false;
    int currAttack = 0;
    
    int fallLocation;
    int fallDist;
    int dashable = 0;
    int iframeB = 0;
    
    GreenfootImage[] fall= new GreenfootImage[3];
    GreenfootImage[] land= new GreenfootImage[26];
    
    GreenfootImage[] dashRP = new GreenfootImage[5];
    GreenfootImage[] dashLP = new GreenfootImage[5];
    
    GreenfootImage[] dashR = new GreenfootImage[3];
    GreenfootImage[] dashL = new GreenfootImage[3];
    
    GreenfootImage[] stompR = new GreenfootImage[13];
    GreenfootImage[] stompL = new GreenfootImage[13];
    
    GreenfootImage[] bladeR = new GreenfootImage[15];
    GreenfootImage[] bladeL = new GreenfootImage[15];
    
    GreenfootImage[] TR = new GreenfootImage[12];
    GreenfootImage[] TL = new GreenfootImage[12];
    
    public Boss()
    {
        for(int i = 0; i < 3; i++)
        {
            fall[i] = new GreenfootImage("images/Enemies/Boss/introBird/f" + i + ".png");
        }
        for(int i = -1; i < 25; i++)
        {
            land[i+1] = new GreenfootImage("images/Enemies/Boss/introBird/i" + i + ".png");
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
        for(int i = 0; i < 15; i++)
        {
            bladeR[i] = new GreenfootImage("images/Enemies/Boss/bladeBird/b" + i + ".png");
            bladeL[i] = new GreenfootImage("images/Enemies/Boss/bladeBird/b" + i + ".png");
            bladeL[i].mirrorHorizontally();
        }
        for(int i = 0; i < 12; i++)
        {
            //renaming files is pain, so I didn't
            TR[i] = new GreenfootImage("images/Enemies/Boss/teleport/pixil-frame-" + i + ".png");
            TL[i] = new GreenfootImage("images/Enemies/Boss/teleport/pixil-frame-" + i + ".png");
            TL[i].mirrorHorizontally();
        }
    }
    
    public void act()
    {
        if(!introFinished)
        {
            introFall();
        }
        else
        {
            GameWorld world = (GameWorld) getWorld();
            if(currAttack == 0)
            {
                if(world.player.getX() > getX())
                {
                    direction = "right";
                }
                else
                {
                    direction = "left";
                }
                currAttack = Greenfoot.getRandomNumber(5);
                isGrounded = false;
            }     
            if(isGrounded)
            {
                if(currAttack == 1)
                {
                    dash();
                }
                else if(currAttack == 2 && 200 > Math.sqrt(Math.pow(getX()-world.player.getX(),2)+Math.pow(getY()-world.player.getY(),2)))
                {
                    stomp();
                }
                else if(currAttack == 2)
                {
                    currAttack = 0;
                }
                else if(currAttack == 3)
                {
                    blade();
                }
                else if(currAttack == 4)
                {
                    fallLocationFound = false;
                    teleport();
                }
                else if(currAttack == 5)
                {
                    fall();
                }
            }
            else
            {
                fallUntilGround();
            }
            bounding();
            iframeB++;
        }
    }
    int fallIndex = 0;
    int landIndex = 0;
    public void introFall()
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        if(world.boxAtLocation(getX(), getY()+55))
        {
            animationTimer.mark();
            setImage(fall[fallIndex]);     
            fallIndex = (fallIndex + 1) % fall.length;
            setLocation(getX(), getY() + 50);
        }
        else if(landIndex < land.length)
        {
            setLocation(getX(),600-75);
            animationTimer.mark();
            setImage(land[landIndex]);     
            landIndex++;
        }
        else
        {
            introFinished = true;
            landIndex = 0;
            attack = false;
        }
    }
    
    public void fall()
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < 75)
        {
            return;
        }
        if(!fallLocationFound)
        {
            damaging = true;
            animationTimer.mark();
            setImage(fall[fallIndex]);     
            fallIndex = (fallIndex + 1) % fall.length;
            fallDist+=25;
            setLocation(getX(),getY()+25);
            if(!world.boxAtLocation(getX(), getY()+fallDist))
            {
                fallLocationFound = true;
                fallLocation = fallDist+getY() - getY()%50-25;
                setLocation(getX(),fallLocation);
            }
        }
        else if(landIndex < land.length)
        {
            fallDist = 0;
            damaging = false;
            animationTimer.mark();
            setImage(land[landIndex]);     
            landIndex++;
        }
        else
        {
            fallLocationFound = false;
            landIndex = 0;
            fallIndex = 0;
            attack = false;
            currAttack = 0;
        }
    }
        
    int dashPIndex = 0;
    int dashIndex = 0;
    public void dash()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        if(direction == "right")
        {
            if(dashPIndex < 5)
            {
                animationTimer.mark();
                setImage(dashRP[dashPIndex]);     
                dashPIndex++;
            }   
            else if(dashable < 10)
            {
                damaging = true;
                setImage(dashR[fallIndex]);   
                dashIndex = (dashIndex + 1) % dashR.length;
                dashable++;
                move(25);
            }
            if(dashable >= 10)
            {
                damaging = false;
                dashable = 0;
                dashPIndex = 0;
                attack = false;
                currAttack = 0;
            }
        }
        else
        {
            if(dashPIndex < 5)
            {
                animationTimer.mark();
                setImage(dashLP[dashPIndex]);     
                dashPIndex++;
            }   
            else if(dashable < 10)
            {
                damaging = true;
                setImage(dashL[fallIndex]);   
                dashIndex = (dashIndex + 1) % dashL.length;
                dashable++;
                move(-25);
            }
            if(dashable >= 10)
            {
                damaging = false;
                dashable = 0;
                dashPIndex = 0;
                attack = false;
                currAttack = 0;
            }
        }
    }
    int bladeIndex;
    public void blade()
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        if(direction == "right")
        {
            if(bladeIndex < bladeR.length)
            {
                damaging = true;
                animationTimer.mark();
                setImage(bladeR[bladeIndex]);
                bladeIndex++;
            }
            else if(dashable < 1)
            {
                setImage(bladeR[14]);
                dashable++;
                animationTimer.mark();
                turnTowards(world.player.getX(), world.player.getY());
            }
            else if(dashable < 20)
            {
                move(20);
                dashable++;
            }
            else
            {
                setRotation(0);
                damaging = false;
                dashable = 0;
                bladeIndex = 0;
                attack = false;
                currAttack = 0;
            }
        }
        else
        {
            if(bladeIndex < bladeL.length)
            {
                damaging = true;
                animationTimer.mark();
                setImage(bladeL[bladeIndex]);     
                bladeIndex++;
            }
            else if(dashable < 1)
            {
                dashable++;
                setImage(bladeR[14]); 
                animationTimer.mark();
                turnTowards(world.player.getX(), world.player.getY());
            }
            else if(dashable < 30)
            {
                dashable++;
                move(20);
            }
            else
            {
                setRotation(0);
                damaging = false;
                dashable = 0;
                bladeIndex = 0;
                attack = false;
                currAttack = 0;
            }
        }
    }
    
    int stompIndex;   
    public void stomp()
    {
        if(animationTimer.millisElapsed() < 25)
        {
            return;
        }
        if(direction == "right")
        {
            if(stompIndex != 12)
            {
                animationTimer.mark();
                damaging = true;
                setImage(stompR[stompIndex]);   
                stompIndex = (stompIndex + 1) % stompR.length;
            }
            else
            {
                attack = false;
                damaging = false;
                currAttack = 0;
            }
        }
        else
        {
            if(stompIndex != 12)
            {
                animationTimer.mark();
                damaging = true;
                setImage(stompL[stompIndex]);   
                stompIndex = (stompIndex + 1) % stompR.length;
            }
            else
            {
                attack = false;
                damaging = false;
                currAttack = 0;
            }
        }
        if(stompIndex == 12)
        {
            attack = false;
            damaging = false;
            currAttack = 0;
        }
    }
    int tpIndex;
    boolean tp = false;
    public void teleport()
    {
        GameWorld world = (GameWorld) getWorld();
        damaging = false;
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        if(direction == "right")
        {
            if(tpIndex != 11)
            {
                tp=true;
                animationTimer.mark();
                setImage(TR[tpIndex]);   
                tpIndex = (tpIndex + 1) % TR.length;
            }
            else if(tp)
            {
                currAttack = 5;
                setLocation(world.player.getX(), world.player.getY() - 250);
                fallLocationFound = false;
                fall();
                tp = false;
            }
            else
            {
                tpIndex = 0;
            }
        }
        else
        {
            if(tpIndex != 11)
            {
                tp = true;
                animationTimer.mark();
                setImage(TL[tpIndex]);   
                tpIndex = (tpIndex + 1) % TL.length;
            }
            else if(tp)
            {
                setLocation(world.player.getX(), world.player.getY() - 250);
                currAttack = 5;
                fallLocationFound = false;
                fall();
                tp = false;
            }
            else
            {
                tpIndex = 0;
            }
        }
    }
    
    public void bounding()
    {
        if(getX() < 70)
        {
            setLocation(50, getY());
        }
        if(getX() > 1120)
        {
            setLocation(1125, getY());
        }
        if(getY() < 74&&(getX() < 575||getX() > 775))
        {
            setLocation(getX(), 76);
        }
        if(getY() > 520)
        {
            setLocation(getX(), 525);
        }
    }
    
    public boolean isTouchingPlayer(boolean dashing)
    {
        if(dashing)
        {
            return isTouching(Player.class);
        }
        else
        {
            return isTouching(Player.class)&&damaging;
        }
    }
    boolean isGrounded;    
    public void fallUntilGround()
    {
        setImage(dashRP[1]);
        GameWorld world = (GameWorld) getWorld();        
        if(world.boxAtLocation(getX(), getY()+25))
        {
            setLocation(getX(), getY() + 10);
        }
        else
        {
            setLocation(getX(),getY()+25-getY()%50);
            isGrounded = true;
        }
    }
}
