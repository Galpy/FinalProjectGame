import greenfoot.*;

/*
 * BasicEnemy.java: A basic enemy
 * The enemy has a given speed, and the shape and color is chosen randomly.
 */

public class BasicEnemy extends Actor {
    private int speed;
    private String shape;
    private String color;
    
    /*
     * Sets the speed of the enemy, the shape, and a random rotation.
     */
    public BasicEnemy (int speed) {
        this.speed = speed;
        setShape();
        setRotation(Greenfoot.getRandomNumber(15));
    }
    
    /*
     * Every act cycle, moves the number of cells dictated by the speed variable,
     * adds a trail, and checks whether the enemy needs to bounce of a wall.
     */
    public void act() {
        move(speed);
        getWorld().addObject(new Trail(shape, Area.getColor(color), getRotation(), 90), getX(), getY());
        bounceOffWall();
    }    
    
    // If the enemy is at the world's edge, turn a random number of degrees.
    public void bounceOffWall() {
            if (getX() == 0 || getX() == getWorld().getWidth() - 1){
                turn(Greenfoot.getRandomNumber(180));
            }
            if (getY() == 0 || getY() == getWorld().getHeight() - 1){
                turn(Greenfoot.getRandomNumber(180));
            }
    }
    
    /*
     * Sets the shape randomly among 3 possible variations:
     * triangle, circle, square. Also randomly selects 1 of 6 possible colors.
     */
    private void setShape() {
        GreenfootImage enemy = new GreenfootImage(32,32);
        switch(Greenfoot.getRandomNumber(6)) {
            case 0:
                color = "white";
                break;
            case 1:
                color = "red";
                break;
            case 2:
                color = "orange";
                break;
            case 3:
                color = "yellow";
                break;
            case 4:
                color = "purple";
                break;
            case 5:
                color = "green";
                break;
            default:
                color = "white";
                break;
        }
        enemy.setColor(Area.getColor(color));
        switch(Greenfoot.getRandomNumber(3)) {
            case 0:
                int[] xPoints = {0, 16, 32};
                int[] yPoints = {32, 0, 32};
                enemy.fillPolygon(xPoints, yPoints, 3);
                shape = "triangle";
                break;
            case 1:
                enemy.fillOval(0, 0, 32, 32);
                shape = "circle";
                break;
            default:
                enemy.fill();
                shape = "square";
                break;
        }
        setImage(enemy);
    }
}