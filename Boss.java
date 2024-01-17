import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The boss fight in the game, fought at the end of the game
 * 
 * @author Andy
 * @version 2
 */
public class Boss extends SmoothMover
{
    int bHP = 27;
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
    
    GreenfootImage[] deathR = new GreenfootImage[11];
    GreenfootImage[] deathL = new GreenfootImage[11];
    
    GreenfootSound dash = new GreenfootSound("BossDash.wav");
    GreenfootSound blade = new GreenfootSound("BossSword.wav");
    GreenfootSound stomp = new GreenfootSound("BossStomp.wav");
    GreenfootSound landI = new GreenfootSound("Land2.wav");
    GreenfootSound landS = new GreenfootSound("Land.wav");
    GreenfootSound death = new GreenfootSound("BossDeath.wav");
    
    public Boss()
    {
        //this took so long, you better like the animations or I'm gonna cry
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
        for(int i = 0; i < 11; i++)
        {
            //renaming files is pain, so I didn't
            deathR[i] = new GreenfootImage("images/Enemies/Boss/death/pixil-frame-" + i + ".png");
            deathL[i] = new GreenfootImage("images/Enemies/Boss/death/pixil-frame-" + i + ".png");
            deathL[i].mirrorHorizontally();
        }
        bHP = 18;
    }
    /**
     * Boss AI
     */
    public void act()
    {
        GameWorld world = (GameWorld) getWorld();
        //volume setting
        dash.setVolume(world.maxV);
        blade.setVolume(world.maxV);
        stomp.setVolume(world.maxV);
        landI.setVolume(world.maxV);
        landS.setVolume(world.maxV);
        death.setVolume(world.maxV);
        
        if(!world.paused)
        {
            if(!introFinished)
            {
                introFall();
            }
            else
            {
                if(currAttack == 0)
                {
                    //turns to player and chooses an attack
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
                if(isGrounded&&bHP >= 0)
                {
                    //attacks
                    if(currAttack == 1)
                    {
                        if(bHP > 10)
                        {
                            dash(100);
                        }
                        else
                        {
                            dash(50);
                        }
                    }
                    else if(currAttack == 2)
                    {
                        if(bHP > 10)
                        {
                            stomp(50);
                        }
                        else
                        {
                            stomp(25);
                        }
                    }
                    else if(currAttack == 3)
                    {
                        if(bHP > 10)
                        {
                            blade(100);
                        }
                        else
                        {
                            blade(50);
                        }
                    }
                    else if(currAttack == 4)
                    {
                        fallLocationFound = false;
                        if(bHP > 10)
                        {
                            teleport(100);
                        }
                        else
                        {
                            teleport(50);
                        }
                    }
                    else if(currAttack == 5)
                    {
                        if(bHP > 10)
                        {
                            fall(50);
                        }
                        else
                        {
                            fall(40);
                        }
                    }
                }
                else if(bHP >= 0)
                {
                    fallUntilGround();
                }
                if(bHP >= 0)
                {
                    bounding();
                    iframeB++;
                }
                if(bHP < 0)
                {
                    death();
                }
            }
        }
    }
    
    //Most attacks are just animations and moving towards the player, as well as turning on collision.
    
    int fallIndex = 0;
    int landIndex = 0;
    /**
     * Falls from the sky
     */
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
            landI.play();
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
    /**
     * Drops down, doing contact damage
     */
    public void fall(int speed)
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < speed)
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
                Wind wind = new Wind();
                world.addObject(wind, getX(), getY());
                WindL windL = new WindL();
                world.addObject(windL, getX(), getY());
            }
        }
        else if(landIndex < land.length)
        {
            if(landIndex == 0)
            {
                landS.play();
            }
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
    /**
     * Moves in the player's general direction quickly
     */
    public void dash(int speed)
    {
        if(animationTimer.millisElapsed() < speed)
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
            else if(dashable < 5)
            {
                dash.play();
                damaging = true;
                setImage(dashR[dashIndex]);   
                dashIndex = (dashIndex + 1) % dashR.length;
                dashable++;
                move(50);
            }
            if(dashable >= 5)
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
            else if(dashable < 5)
            {
                dash.play();
                damaging = true;
                setImage(dashL[dashIndex]);   
                dashIndex = (dashIndex + 1) % dashL.length;
                dashable++;
                move(-50);
            }
            if(dashable >= 5)
            {
                damaging = false;
                dashable = 0;
                dashPIndex = 0;
                attack = false;
                currAttack = 0;
            }
        }
    }
    /**
     * Similar to dash, but can rotate, and goes further, but has longer telegraph
     */
    int bladeIndex;
    public void blade(int speed)
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < speed)
        {
            return;
        }
        if(direction == "right")
        {
            if(bladeIndex < bladeR.length)
            {
                animationTimer.mark();
                setImage(bladeR[bladeIndex]);
                bladeIndex++;
            }
            else if(dashable < 1)
            {
                damaging = true;
                setImage(bladeR[14]);
                dashable++;
                animationTimer.mark();
                turnTowards(world.player.getX(), world.player.getY());
            }
            else if(dashable < 30)
            {
                blade.play();
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
                animationTimer.mark();
                setImage(bladeL[bladeIndex]);     
                bladeIndex++;
            }
            else if(dashable < 1)
            {
                damaging = true;
                dashable++;
                setImage(bladeR[14]); 
                animationTimer.mark();
                turnTowards(world.player.getX(), world.player.getY());
            }
            else if(dashable < 30)
            {
                blade.play();
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
    /**
     * Attack that spawns two wind projectiles
     */
    int stompIndex;   
    public void stomp(int speed)
    {
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < speed)
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
                if(stompIndex == 7)
                {
                    stomp.play();
                    Wind wind = new Wind();
                    world.addObject(wind, getX(), getY());
                    WindL windL = new WindL();
                    world.addObject(windL, getX(), getY());
                }
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
                if(stompIndex == 7)
                {
                    stomp.play();
                    Wind wind = new Wind();
                    world.addObject(wind, getX(), getY());
                    WindL windL = new WindL();
                    world.addObject(windL, getX(), getY());
                }
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
            stompIndex = 0;
            attack = false;
            damaging = false;
            currAttack = 0;
        }
    }
    /**
     * An attack that moves the boss from anywhere, to above you, before using the fall attack
     */
    int tpIndex;
    boolean tp = false;
    public void teleport(int speed)
    {
        GameWorld world = (GameWorld) getWorld();
        damaging = false;
        if(animationTimer.millisElapsed() < speed)
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
                if(bHP > 10)
                {
                    fall(100);
                }
                else
                {
                    fall(50);
                }
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
                if(bHP > 10)
                {
                    fall(100);
                }
                else
                {
                    fall(50);
                }
                tp = false;
            }
            else
            {
                tpIndex = 0;
            }
        }
    }
    /**
     * Death animation and sound
     */
    int deathIndex;
    public void death()
    {
        setRotation(0);
        damaging = false;
        GameWorld world = (GameWorld) getWorld();
        if(animationTimer.millisElapsed() < 250)
        {
            return;
        }
        animationTimer.mark();
        if(deathIndex == 0)
        {
            death.play();
        }
        if(direction.equals("right"))
        {
            setImage(deathR[deathIndex]);     
            deathIndex = (deathIndex + 1) % deathR.length;
        }
        else
        {
            setImage(deathL[deathIndex]);
            deathIndex = (deathIndex + 1) % deathR.length;        
        }
        if(deathIndex == 10)
        {
            world.bossEnd = true;
            world.removeObject(this);
        }
    }
    /**
     * Checks if Boss is touching the boundries, and then stops it from moving further
     */
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
    /**
     * Checks if Boss is touching Player
     */
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
    /**
     * Falls until it touches the ground
     */
    public void fallUntilGround()
    {
        //Drops straight to the ground.
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
