import greenfoot.*;

/*
 * Health.java: Acts as a "lives icon" so the user knows how many
 * lives are remaining. When removed, fades over time.
 */

public class Health extends Actor {
    private GreenfootImage health = new GreenfootImage("heart.png");
    boolean removed = false;
    int timer = 0;

    // Sets the image to be a heart icon
    public Health() {
        setImage(health);
    }

    /*  If the variable 'removed' is true, fade the icon over a short
     *  duration. When the image is close to 100% transparent, remove
     *  this object.
     */
    public void act() {
        if (removed) {
            health.setTransparency(health.getTransparency() - 5);
            if (health.getTransparency() < 5) {
                getWorld().removeObject(this);
            }
        }
    }

    /* When this method is called, sets the removed variable to true,
     * which starts the removal process during the next act cycle.
     */
    public void removeHealth() {
        removed = true;
    }
}