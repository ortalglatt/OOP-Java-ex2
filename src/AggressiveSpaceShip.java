public class AggressiveSpaceShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        this.move(closestShip);
        double angle = this.getPhysics().angleTo(closestShip.getPhysics());
        double maxAngle = 0.21 * 180 / Math.PI;
        if (-maxAngle < angle && angle < maxAngle) this.fire(game);
        super.doAction(game);
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
}
