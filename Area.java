import greenfoot.*;

/*
 * Area.java: The game world.
 * Sets up the game, contains a variety of private data for global use
 * with applicable getters/setters, and various methods for manipulating
 * gameplay.
 */

public class Area extends World {
    
    //Private references to a background image, scorecounter, and music.
    private GreenfootImage background;
    private ScoreCounter sc;
    private GreenfootSound gameSound = new GreenfootSound("GameSound.mp3");

    /*
     * Variables indicating whether sound should play, and
     * the number of lives.
     */
    private boolean muted = false;
    private int lives = 3;
    
    // Static colors defined for use through the game.
    private static Color bgColor = new Color(46,52,64);
    private static Color white = new Color(216,222,233);
    private static Color grey = new Color(117,117,128);
    private static Color red = new Color(191,97,106);
    private static Color orange = new Color(208,135,112);
    private static Color yellow = new Color(235,203,139);
    private static Color purple = new Color(180,142,173);
    private static Color green = new Color(163,190,140);
    
    /*
     * Set the background color, and add a grey bar ("hud") at the bottom of
     * the screen, and a start screen.
     */
    public Area() {
        super(1280, 960, 1);
        background = getBackground();
        background.setColor(getColor("background"));
        background.fill();
        
        addObject(new Hud(), 640, 928);
        addObject(new StartScreen(), getWidth()/2, getHeight()/2);        
    }

    // Returns static color objects listed above.
    public static Color getColor(String color) {
        switch(color) {
            case "background":
                return bgColor;
            case "white":
                return white;
            case "grey":
                return grey;
            case "red":
                return red;
            case "orange":
                return orange;
            case "yellow":
                return yellow;
            case "purple":
                return purple;
            case "green":
                return green;
            default:
                return bgColor;
        }
    }

    public boolean getMuted() {
        return muted;
    }
    
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
        
    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    
    /* Adds the player object, a scorecounter, a mute button, several enemies, 
     * and starts the music.
    */
    public void startGame() {
        addObject(new Player(), getWidth()/2, getHeight()/2);
        addHealth(lives);
        sc = new ScoreCounter();
        addObject(sc, 1180, 920);        
        addEnemies(4, 6);
        addObject(new SoundButton(), 1060, 928);
        gameSound.play();
    }
    
    // Adds the specified number of enemies at a random location with the given
    // speed.
    private void addEnemies(int count, int speed) {
        for(int i = 0; i < count; i++)
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(new BasicEnemy(speed), x, y);
        }
    }
    
    /*
     * Adds the "health" icons at the bottom of the screen, at regular intervals.
     */
    public void addHealth(int count) {
        int j = 0;
        for(int i = 0; i < count; i++)
        {
            int x = 40 + j;
            int y = 928;
            addObject(new Health(), x, y);
            j += 60;
        }
    }
    
    /*
     * When called, adds an enemy to the screen with a slightly random
     * speed. There is a 50% chance of the speed being higher than
     * average, and a 50% chance of the speed being lower.
     */
    public void levelUp() {
        int speed = 6;
        int rand = Greenfoot.getRandomNumber(4);
        if (Greenfoot.getRandomNumber(100) > 50) {
            speed += rand;
        }
        else {
            speed -= rand;
        }
        addEnemies(1, speed);
    }
    
    // Plays/pauses the music based on the "music" variable.
    public void toggleMusic() {
        if (muted) {
            gameSound.pause();
        }
        
        else {
            gameSound.play();
        }
    }
    
    /*
     * On game over, removes the last health icon, the enemies, and creates
     * a game over screen. Only stops the music if the music isn't paused:
     * calling stop() when paused appears to play the music for a short period
     * of time inconsistently.
     */
    public void gameOver() {
        removeObjects(getObjects(BasicEnemy.class));
        removeObjects(getObjects(Trail.class));
        removeObjects(getObjects(Health.class));
        addObject(new GameOverScreen(sc.getScore()), getWidth()/2, getHeight()/2);
        
        if (!muted) {
            gameSound.stop();
        }
        Greenfoot.stop();
    }
}