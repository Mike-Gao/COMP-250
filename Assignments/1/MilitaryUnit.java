public abstract class MilitaryUnit extends Unit {
    private double attackDamage;
    private int range;
    private int armor;

    public MilitaryUnit(Tile position, double hp, int movingRange, String faction, double attackDamage, int range, int armor) {
        super(position, hp, movingRange, faction);
        this.attackDamage = attackDamage;
        this.range = range;
        this.armor = armor;
    }

    @Override
    public void takeAction(Tile t) {
        if (Tile.getDistance(getPosition(),t) < this.range + 1){
            if (t.selectWeakEnemy(this.getFaction()) != null){
                if (this.getPosition().isImproved()){
                    t.selectWeakEnemy(this.getFaction()).receiveDamage(this.attackDamage*1.05);
                }
                else{
                    t.selectWeakEnemy(this.getFaction()).receiveDamage(this.attackDamage);
                }
            }
        }
    }

    @Override
    public void receiveDamage(double d){
        d = d * (100 / (100 + (double)this.armor));
        super.receiveDamage(d);
    }
}
