public class RunnerSpaceShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        if (this.toClose(closestShip)) this.teleport();
        this.move(closestShip);
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
        if (0 < angle && angle <= Math.PI) turn = -1;
        else if (-Math.PI < angle && angle < 0) turn = 1;
        this.physics.move(true, turn);

    }

    /**
     * Checks if the ship is too close to other ship, so that it need to teleport.
     * @param closestShip the SpaceShip object of the closest ship to this ship.
     * @return true if this ship is too close to the closest ship, false otherwise.
     */
    private boolean toClose(SpaceShip closestShip){
        double angle = this.getPhysics().angleTo(closestShip.getPhysics());
        double maxAngle = 0.23 * 180 / Math.PI;
        return  ((closestShip.getPhysics().distanceFrom(this.getPhysics()) < 0.25) &&
                (-maxAngle < angle && angle < maxAngle));
    }

}
