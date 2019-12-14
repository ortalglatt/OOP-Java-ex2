import java.awt.Image;
import oop.ex2.*;


public class HumanSpaceShip extends SpaceShip{

    /**
     * Does the actions of this ship for this round.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        GameGUI GUI = game.getGUI();
        if (GUI.isTeleportPressed()) this.teleport();
        this.move(GUI);
        if (GUI.isShieldsPressed()) this.shieldOn();
        else this.isShieldOn = false;
        if (GUI.isShotPressed()) this.fire(game);
        super.doAction(game);
    }

    /**
     * Checks if the right, left and acceleration buttons are pressed, if they are, it will move the ship
     * according to the pressed buttons.
     *
     * @param GUI the gameGUI object of the game that this ship belongs.
     */
    private void move(GameGUI GUI){
        boolean accel = false;
        int turn = 0;
        if (GUI.isUpPressed()) accel = true;
        if (GUI.isRightPressed() && !GUI.isLeftPressed()) turn = -1;
        else if (GUI.isLeftPressed() && !GUI.isRightPressed()) turn = 1;
        this.physics.move(accel, turn);
    }

    /**
     * Gets the image of this ship with or without the shield.
     *
     * @return the image of this ship.
     */
    @Override
    public Image getImage(){
        if (this.isShieldOn) return GameGUI.SPACESHIP_IMAGE_SHIELD;
        else return GameGUI.SPACESHIP_IMAGE;
    }

}

