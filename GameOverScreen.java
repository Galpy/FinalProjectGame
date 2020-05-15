import greenfoot.*;

/*
 * GameOverScreen.java: Displayed at the end of game.
 * Creates an image telling the user what their score was.
 */

public class GameOverScreen extends Actor {

    public GameOverScreen(int score) {
        drawImage("Congratulations! \n", "Your score: ", score);
    }

    /*
     * Creates two GreenfootImages- one for the background, and one for the text.
     * The background size is based on the text size with a margin, and the text
     * is centered over the background image.
     */
    private void drawImage(String title, String prefix, int score) {
        GreenfootImage text = new GreenfootImage(title + prefix + score, 48, Area.getColor("white"), null);
        int width = text.getWidth() + 300;
        int height = text.getHeight() + 300;
        int textX = (width / 2) - (text.getWidth() / 2);
        int textY = (height / 2) - (text.getHeight() / 2);
        GreenfootImage gameOver = new GreenfootImage(width, height);
        gameOver.setColor(Area.getColor("white"));
        gameOver.fillRect(0, 0, width, height);
        gameOver.setColor(Area.getColor("background"));
        gameOver.fillRect(5, 5, width-10, height-10);
        gameOver.drawImage(text, textX, textY);
        setImage(gameOver);
    }
}