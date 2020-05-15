import greenfoot.*; 

/*
 * SoundButton.java: A simple mute/play button for the music
 * and sound effects. Toggles sound when the button is clicked.
 */

public class SoundButton extends Actor {
    private GreenfootImage playing;
    private GreenfootImage paused;
    private boolean muted = false;
    
    /*
     * Creates 2 GreenfootImages to indicate whether the sound is
     * playing.
     */
    public SoundButton() {
        playing = new GreenfootImage("playing.png");
        paused = new GreenfootImage("muted.png");
        updateImage();
    }

    /*
     * If the player clicks the icon, toggle the "muted" variable,
     * synchronize it with the world's variable, toggle the music
     * state, and switch the image.
     */
    public void act() {
        if(Greenfoot.mouseClicked(this)) {
            Area area = (Area) getWorld();
            muted = !muted;
            updateImage();
            area.setMuted(muted);
            area.toggleMusic();
        }
    }
    
    private void updateImage() {
        if (muted) {
            setImage(paused);
        }
        else {
            setImage(playing);
        }
    }   
}