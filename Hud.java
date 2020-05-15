import greenfoot.*; 

/*
 * Hud.java: A simple "hud" bar. All it does is create a partially 
 * transparent bar at the bottom of the screen.
 */

public class Hud extends Actor {
    
    public Hud() {
        drawImage();
    }
    
    public void act() {
    }

    private void drawImage() {
        GreenfootImage hud = new GreenfootImage(1280, 64);
        hud.setColor(Area.getColor("white"));
        hud.setTransparency(60);
        hud.fill();
        setImage(hud);
    }   
}

