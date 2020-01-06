public class Tile {
    private int x;
    private int y;
    private boolean cityBuilt;
    private boolean improvedTile;

    // A ListOfUnits containing all the units positioned on this tile.
    private ListOfUnits allUnits;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.cityBuilt = false;
        this.improvedTile = false;
        this.allUnits = new ListOfUnits();
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean isCity(){
        return this.cityBuilt;
    }

    public boolean isImproved(){
        return this.improvedTile;
    }

    public void foundCity(){
        this.cityBuilt = true;
    }

    public void buildImprovement(){
        this.improvedTile = true;
    }

    public boolean addUnit(Unit u){
        if (u instanceof MilitaryUnit){
             for (MilitaryUnit mil: this.allUnits.getArmy()){
                 if (!mil.getFaction().equals(u.getFaction())){
                     return false;
                 }
             }
        }
        this.allUnits.add(u);
        return true;
    }

    public boolean removeUnit(Unit u){
         return this.allUnits.remove(u);
    }

    public Unit selectWeakEnemy(String s){
        Unit weak  = null;
        Unit[] u = this.allUnits.getUnits();
        double min = Double.MAX_VALUE;
        for (int i = 0; i < allUnits.size(); i++){
            if (!u[i].getFaction().equals(s));{
                if (min > u[i].getHP()){
                    min = u[i].getHP();
                    weak = u[i];
                }
            }
        }
        return weak;

    }

    public static double getDistance(Tile t1, Tile t2){
        double distance = Math.sqrt(Math.pow((t1.getX()-t2.getX()),2)+Math.pow((t1.getY()-t2.getY()),2));
        return distance;
    }

}
