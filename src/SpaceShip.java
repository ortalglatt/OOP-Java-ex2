import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public class SpaceShip{
    private int health = 22;
    protected int maxEnergy = 210;
    protected int curEnergy = 190;
    protected boolean isShieldOn = false;
    protected int firingCoolDown = 7;
    protected SpaceShipPhysics physics = new SpaceShipPhysics();

   
    /**
     * Does the common actions for all the ships on this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (this.firingCoolDown < 7){
            this.firingCoolDown ++;
        } if (this.curEnergy < this.maxEnergy) this.curEnergy ++;

    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if (this.isShieldOn) {
            this.maxEnergy += 18;
            this.curEnergy += 18;
        } else collidedOrHitWithoutShield();
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.health = 22;
        this.maxEnergy = 210;
        this.curEnergy = 190;
        this.isShieldOn = false;
        this.firingCoolDown = 7;
        this.physics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead(){
        return (this.health == 0);
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!this.isShieldOn) collidedOrHitWithoutShield();
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (this.isShieldOn) return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        else return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.curEnergy >= 19 && this.firingCoolDown == 7) {
            game.addShot(this.physics);
            this.curEnergy -= 19;
            this.firingCoolDown = 0;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.curEnergy >= 3) {
            this.isShieldOn = true;
            this.curEnergy -= 3;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.curEnergy >= 140){
            this.physics = new SpaceShipPhysics();
            this.curEnergy -= 140;
        }
    }

    /**
     * This method is called whenever the ship collided with another ship or got hit by another ship, and
     * doesn't have it's shield on.
     */
    private void collidedOrHitWithoutShield(){
        this.health --;
        if (this.maxEnergy >= 10) this.maxEnergy -= 10;
        else this.maxEnergy = 0;
        if (this.curEnergy > this.maxEnergy){
            this.curEnergy = this.maxEnergy;
        }
    }
    
}
