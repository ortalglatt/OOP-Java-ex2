public class SpaceShipFactory {
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShipsArr = new SpaceShip[args.length];
        for (int i=0 ; i < args.length ; i++) {
            spaceShipsArr[i] = shipKind(args[i]);
        } return spaceShipsArr;
    }

    private static SpaceShip shipKind(String arg) {
        if (arg.equals("h")) return new HumanSpaceShip();
        else if (arg.equals("r")) return new RunnerSpaceShip();
        else if (arg.equals("b")) return new BasherSpaceShip();
        else if (arg.equals("a")) return new AggressiveSpaceShip();
        else if (arg.equals("d")) return new DrunkardSpaceShip();
        else if (arg.equals("s")) return new SpecialSpaceShip();
        return null;
    }
}
