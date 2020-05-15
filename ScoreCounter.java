import greenfoot.*; 

/*
 * ScoreCounter.java: A simple score counter. Adds an image
 * with the user's current score, and adds new enemies at certain
 * thresholds.
 */

public class ScoreCounter extends Actor {
    private GreenfootImage scoreCounter = new GreenfootImage(150,32);
    private int score;
    
    /*
     * Sets the scorecounter's font to be 24pt, and the font color
     * to white.
     */
    public ScoreCounter() {
        score = 0;
        scoreCounter.setFont(new Font(24));
        scoreCounter.setColor(Area.getColor("white"));
        updateImage();
    }

    /*
     * Increase the score every act cycle, and update the image
     * to display the new score. If the score is a multiple of 250,
     * call the world's levelUp() method which adds an enemy.
     */
    public void act() {
        score++;
        updateImage();
        if (score % 250 == 0) {
           Area area = (Area) getWorld();
           area.levelUp();
        }
    }

    public int getScore() {
        return score;
    }

    // Update the score on screen.
    private void updateImage() {
        scoreCounter.clear();
        scoreCounter.drawString("Score: " + score, 0, 32);
        setImage(scoreCounter);
    }   
}