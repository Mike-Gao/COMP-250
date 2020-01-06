public class Worker extends Unit{
    private int jobsPerformed;

    public Worker(Tile position, double hp, String faction) {
        super(position, hp, 2, faction);
        this.jobsPerformed = 0;
    }

    @Override
    public void takeAction(Tile t){
        if (!getPosition().isImproved() && getPosition().equals(t)) {
            t.buildImprovement();
            this.jobsPerformed++;
            if (this.jobsPerformed == 10){
                t.removeUnit(this);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Worker) {
            if(super.equals(o)){
                if (this.jobsPerformed == ((Worker) o).jobsPerformed){
                    return true;
                }
            }
        }
        return false;
    }
}
