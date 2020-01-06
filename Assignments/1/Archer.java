public class Archer extends MilitaryUnit {
    private int availableArrows;

    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.availableArrows = 5;

    }

    @Override
    public void takeAction(Tile t){
        if(this.availableArrows>0){
            this.availableArrows--;
            super.takeAction(t);
        } else {
            this.availableArrows = 5;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Archer) {
            if (super.equals(o)){
                if (((Archer) o).availableArrows == this.availableArrows){
                    return true;
                }
            }
        }
        return false;
    }
}
