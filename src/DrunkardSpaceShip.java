import java.util.Random;


public class DrunkardSpaceShip extends SpaceShip{
    private Random rand = new Random();
    private int minRounds = 150;
    private int curRound = 0;
    private int roundLen = rand.nextInt(this.minRounds) + this.minRounds;
    private int changeDirection = rand.nextInt(roundLen / 2) + (roundLen / 4);


    /**
     * Does the actions of this ship for this round.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        this.move();
        if (rand.nextBoolean()) this.fire(game);
        super.doAction(game);
    }

    /**
     * Check where this ship need to move according to it's curRound, changeDirection and roundLen - constants
     * that changes randomly during the game.
     **/
    private void move(){
        int turn = 1;
        if (this.curRound <= this.changeDirection) turn = -1;
        else if (curRound > roundLen) {
            this.roundLen = rand.nextInt(this.minRounds) + this.minRounds;
            this.changeDirection = rand.nextInt(roundLen / 2) + (roundLen / 4);
            this.curRound = 0;
        } this.curRound ++;
        this.physics.move(true, turn);
    }
}
