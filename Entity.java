public class Entity extends GM{
    public int hp;
    public int mp;
    public int dNum;
    public int dType;
    public String name;

    public int HPset(int HP){
        this.hp = HP;
        if (HP <= 0) {
            return 0;
        }
        return hp;
    }

    public int MPset(int MP) {
        this.mp = MP;
        if (MP <= 0) {
            return 0;
        }
        return mp;
    }
}