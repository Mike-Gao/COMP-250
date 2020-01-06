public class ListOfUnits {
    private Unit[] units;
    private int sizeOfUnits;

    public ListOfUnits() {
        this.sizeOfUnits = 0;
        this.units = new Unit[10];

    }

    public int size(){
        return this.sizeOfUnits;
    }

    public Unit[] getUnits(){
        Unit[] total = new Unit[this.sizeOfUnits];
        for (int i = 0; i < this.sizeOfUnits; i++){
            total[i] = this.units[i];
        }
        return total;
    }

    public Unit get(int a){
        if (a >= this.sizeOfUnits || a < 0 ){
            throw new IndexOutOfBoundsException("Index cannot be less than 0 or greater than the size of the list!");
        }
        else {
            return this.units[a];
        }
    }

    public void add(Unit u){
        if (this.sizeOfUnits == this.units.length){
            int newSize = this.units.length + (this.units.length/2) + 1;
            Unit[] tmpUnit = new Unit[newSize];
            for (int i = 0; i < this.sizeOfUnits; i++){
                tmpUnit[i] = this.units[i];
            }
            this.units = tmpUnit;
        }
        this.units[sizeOfUnits] = u;
        this.sizeOfUnits++;
    }

    public int indexOf(Unit u){
        for (int i = 0; i < this.sizeOfUnits; i++){
            if (this.units[i].equals(u)){
                return i;
            }
        }
        return -1;
    }

    public boolean remove(Unit u){
        if (indexOf(u) != -1) {
            for (int i = indexOf(u); i < this.sizeOfUnits - 1; i++) {
                this.units[i] = this.units[i + 1];
            }
            this.sizeOfUnits--;
            return true;
        }
        return false;
    }

    public MilitaryUnit[] getArmy(){
        int militarySize = 0;
        for (int i = 0; i < this.sizeOfUnits; i++){
            if (this.units[i] instanceof MilitaryUnit){
                militarySize++;
            }
        }
        MilitaryUnit[] military = new MilitaryUnit[militarySize];
        int j = 0;
        for(int i = 0; i < this.sizeOfUnits; i++){
            if (this.units[i] instanceof MilitaryUnit){
                military[j] = (MilitaryUnit) this.units[i];
                j++;
            }
        }
        return military;
    }

}
