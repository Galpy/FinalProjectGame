import greenfoot.*;

/*
 * Trail.java: Acts as a simple image that fades over time, creating
 * a "trail". The constructer is called by the player and enemies.
 */

public class Trail extends Actor {

    /*
     * Data: A single GreenfootImage, an array of points
     * for drawing a triangle, a counter for the fade over
     * time, and falloff/initial opacity variables.
     */
    private GreenfootImage trail = new GreenfootImage(32, 32);    
    private int[] xPoints = {0, 16, 32};
    private int[] yPoints = {32, 0, 32};
    private int counter = 0;
    private int falloff = 7;
    private int initOpacity;

    /*
     * Contructor called with just a color variable. Sets the color
     * to the given variable, an opacity of roughly 50%, and fills the image
     * as a square.
     */
    public Trail(Color color) {
        trail.setColor(color);
        initOpacity = 125;
        trail.fill();
        setImage(trail);
     }
    
    /*
     * Contructor that accepts more detailed parameters: The shape, color,
     * rotation, and initial opacity.
     */
    public Trail(String Shape, Color color, int rotation, int initOpacity) {
        setRotation(rotation);
        trail.setColor(color);
        this.initOpacity = initOpacity;
        if (Shape == "square") {
            trail.fill();
        }
        else if (Shape == "circle") {
            trail.fillOval(0, 0, 32, 32);
        }
        else if (Shape == "triangle") {
            trail.fillPolygon(xPoints, yPoints, 3);
        }
        setImage(trail);
    }

    // Every act cycle, fade the image.
    public void act() {
        fade();
    }
    
    /*
     * Fades the trail image over time. Starts at a given opacity, and
     * fades to almost 0% opacity over a short period of time. Once it
     * is nearly transparent, remove this object.
     */
    private void fade() {
        if (trail.getTransparency() <= falloff) {
            getWorld().removeObject(this);
        }
        else {
            trail.setTransparency(initOpacity - (counter * falloff));
            counter++;
        }
        
    }
}
