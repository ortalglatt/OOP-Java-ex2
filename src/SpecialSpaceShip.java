public class SpecialSpaceShip extends SpaceShip{

    /**
     * Does the actions of this ship for this round.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        this.move(closestShip);
        if (this.getPhysics().distanceFrom(closestShip.getPhysics()) < 0.19) {
            this.shieldOn();
            this.fire(game);
        } else this.isShieldOn = false;
        if (this.curEnergy < this.maxEnergy) this.curEnergy ++;
    }

    /**
     * Check where this ship need to move according to it's closest ship, and move it
     *
     * @param closestShip the SpaceShip object of the closest ship to this ship.
     */
    private void move(SpaceShip closestShip){
        int turn = 0;
        double angle = this.getPhysics().angleTo(closestShip.getPhysics());
        if (0 < angle && angle <= Math.PI) turn = 1;
        else if (-Math.PI < angle && angle < 0) turn = -1;
        this.physics.move(true, turn);
    }

    /**
     * Attempts to turn on the shield (without taking any energy).
     */
    @Override
    public void shieldOn() {
        this.isShieldOn = true;
        }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    @Override
    public void fire(SpaceWars game) {
        if (this.curEnergy >= 19) {
            game.addShot(this.physics);
            this.curEnergy -= 19;
        }
    }


}
