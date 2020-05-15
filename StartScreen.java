import greenfoot.*;

/*
 * StartScreen.java: Displays instructions at the start of
 * the game, and waits until the user is ready before gameplay
 * begins.
 */

public class StartScreen extends Actor {
        
    public StartScreen() {
        drawImage();
    }

    /* 
     * Checks whether the user presses the spacebar. If they do,
     * start the game and remove the startscreen.
     */
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Area area = (Area) getWorld();
            area.startGame();
            area.removeObject(this);
        }
    }

    /*
     * Creates two greenfoot images- one with the text, and the other as
     * a background. The background's size is based on the text size with
     * an additional margin, and the text is centered.
     */
    private void drawImage() {
        String instructions = "Controls: \n Move with the WASD keys \n";
        instructions += "Hold shift to move faster \n \n";
        instructions += "Stay away from the other shapes \n";
        instructions += "You get three extra lives- good luck! \n \n \n \n";
        instructions += "(Press space to start playing)";
        GreenfootImage text = 
            new GreenfootImage(instructions, 32, Area.getColor("white"), null);
        
        int width = text.getWidth() + 300;
        int height = text.getHeight() + 300;
        int textX = (width / 2) - (text.getWidth() / 2);
        int textY = (height / 2) - (text.getHeight() / 2);
        GreenfootImage startScreen = new GreenfootImage(width, height);
        startScreen.setColor(Area.getColor("white"));
        startScreen.fillRect(0, 0, width, height);
        startScreen.setColor(Area.getColor("background"));
        startScreen.fillRect(5, 5, width-10, height-10);
        startScreen.drawImage(text, textX, textY);
        setImage(startScreen);
    }
}