package edu.depaul.rogue.monsters;

public enum MonsterType {
    // (name, carry%, bool flag (0 - mean or 1 - flying), exp, lvl, armr, hpt, dmg, lvlsFound)
    A("Aquator", 0, 0, 20, 5, 9, new int[]{5, 8}, new int[]{0,0}, new int[]{1, 6}),
    B("Bat", 0, 1, 1, 1, 8, new int[]{1,8}, new int[]{1,2}, new int[]{1, 6}),
    C("Centaur", 15, 0, 25, 4, 7, new int[]{1,6}, new int[]{1,6}, new int[]{7,7}),
    D("Dragon", 100, 0, 6800, 10, 1, new int[]{10, 8}, new int[]{1,8}, new int[]{8, 8}),
    E("Emu", 0, 0, 2, 1, 10, new int[]{1, 8}, new int[]{1,2}, new int[]{1, 6});

    private final String name;
    private final int carry;
    private final int flag;
    private final int exp;
    private final int lvl;
    private final int armr;
    private final int[] hpt;
    private final int[] dmg;
    private final int[] lvlsFound;

    MonsterType(String name, int carry, int flag, int exp, int lvl, int armr, int[] hpt, int[] dmg, int[] lvlsFound) {
        this.name = name;
        this.carry = carry;
        this.flag = flag;
        this.exp = exp;
        this.lvl = lvl;
        this.armr = armr;
        this.hpt = hpt;
        this.dmg = dmg;
        this.lvlsFound = lvlsFound;
    }

    public String getName() {
        return name;
    }

    public int getCarry() {
        return carry;
    }

    public int getFlag() {
        return flag;
    }

    public int getExp() {
        return exp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getArmr() {
        return armr;
    }

    public int[] getHpt() {
        return hpt;
    }

    public int[] getDmg() {
        return dmg;
    }

    public int[] getLvlsFound() {
        return lvlsFound;
    }
}
