import greenfoot.*;

/*
 * Player.java: The player object. Contains methods to move when the user
 * presses a key, checks for collision with enemies, and stops the game
 * when there are no more lives left.
 */

public class Player extends Actor {
    /*
     * Private data: the image to be displayed, the color of the player,
     * the player's invulnerability status, speed, and a timer.
     */
    private GreenfootImage player = new GreenfootImage(32, 32);
    private String color = "white";
    private boolean invulnerable = false;
    private int timer = 0;
    private int speed;

    public Player() {
        drawImage();
    }
    
    /*
     * Check whether the user is pressing a movement key, and whether
     * the player is currently invulnerable. If the player is invulnerable,
     * increment the timer until it reaches 200. Once it reaches 200,
     * reset the timer back to 0, turn off invulnerability, and reset the 
     * player's color back to the default. If the player is not invulnerable,
     * check whether the player is touching an enemy.
     */
    public void act() {
        checkKeys();
        if (invulnerable) {
            if (timer <= 200) {
                timer++;
            }
            else {
                timer = 0;
                invulnerable = false;
                color = "white";
                drawImage();
            }
        }
        else {
            collisionCheck();
        }
    }
    
    /* 
     * Checks what keys the user is pressing, and moves in the direction specified
     * by the keys. If the "shift" key is pressed, move faster.
     */
    private void checkKeys() {
        if (Greenfoot.isKeyDown("shift")) {
            speed = 10;
        }
        else {
            speed = 5;
        }
        if (Greenfoot.isKeyDown("w")) {
            move(0, speed * -1);
        }
        
        if (Greenfoot.isKeyDown("a")) {
            move(speed * -1, 0);
        }
        
        if (Greenfoot.isKeyDown("s")) {
             move(0, speed);
        }
        
        if (Greenfoot.isKeyDown("d")) {
            move(speed, 0);
        }
        
    }
    
    /*
     * Move the player, and add a trail based on the player's
     * color.
     */
    private void move(int x, int y) {
        int xLoc = getX() + x;
        int yLoc = getY() + y;
        setLocation(xLoc, yLoc);
        getWorld().addObject(new Trail(Area.getColor(color)), getX(), getY());
    }
    
    /*
     * If the player is touching an enemy, play a collision sound if the
     * sound is not muted. Make the player invulnerable, sets the player
     * color to grey, and decrease the number of lives. If there are no
     * more lives, the game is over. Otherwise, remove a health icon
     * so the user knows they lost a life.
     */
    private void collisionCheck() {
        if (isTouching(BasicEnemy.class)) {
            Area area = (Area) getWorld();
            if (!area.getMuted()) {
                GreenfootSound collisionSound = new GreenfootSound("Impact.mp3");
                collisionSound.play();
            }
            invulnerable = true;
            color = "grey";
            drawImage();
            int lives = area.getLives()-1;
            area.setLives(lives);
            if (lives == 0) {
                area.gameOver();
            }
            else {
                Health health = 
                    (Health)getWorld().getObjects(Health.class).get(lives);
                health.removeHealth();
            }
        }
    }
    
    /*
     * Sets the player's image to a square, with a color based on the
     * "color" variable (changed to indicate invulnerability).
     */
    private void drawImage() {
        player.clear();
        player.setColor(Area.getColor(color));
        player.fill();
        setImage(player);
    }
}