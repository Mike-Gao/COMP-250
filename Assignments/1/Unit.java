public abstract class Unit {
    private Tile position;
    private double hp;
    private int movingRange;
    private String faction;

    public Unit(Tile position, double hp, int movingRange, String faction) {
        this.position = position;
        this.hp = hp;
        this.movingRange = movingRange;
        this.faction = faction;
        if (!position.addUnit(this)){
            throw new IllegalArgumentException();
        }
    }

    public final Tile getPosition() {
        return this.position;
    }

    public final double getHP() {
        return this.hp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Double.compare(unit.hp, hp) == 0 &&
                position.equals(unit.position) &&
                faction.equals(unit.faction);
    }

    public final String getFaction() {
        return this.faction;
    }

    public boolean moveTo(Tile t) {
        if (Tile.getDistance(this.getPosition(), t) >= this.movingRange + 1) {
            return false;
        }
        if (t.addUnit(this)) {
            this.position.removeUnit(this);
            this.position = t;
            return true;
        }
        return false;
    }

    public void receiveDamage(double d) {
        if (!getPosition().isCity()) {
            this.hp -= d;
        } else {
            this.hp -= 0.9 * d;
        }
        if (this.hp <= 0) {
            this.position.removeUnit(this);
        }
    }

    public abstract void takeAction(Tile t);

}
