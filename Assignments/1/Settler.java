public class Settler extends Unit {

    public Settler(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Settler) {
            return super.equals(o);
        }
        return false;
    }

    @Override
    public void takeAction(Tile t){
        if(!this.getPosition().isCity() && this.getPosition().equals(t)) {
            t.foundCity();
            t.removeUnit(this);
        }
    }
}
