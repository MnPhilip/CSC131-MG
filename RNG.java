class RNG extends GM
{
    //dx is number of sides on dice
    //amt is number of dice
    public int diceRoll(int dx, int amt) {
        int total = 0;
        for (int i = 0; i < amt; i++)
        {
            total += (int) (Math.random() * dx) + 1;
        }
        return total;
    }
}