package edu.sungshin.anaegyung;

public class TestAccount {
    private int bool;
    private int index;
    private int direct;

    public TestAccount(){

    };

    public TestAccount(int bl, int idx,int dir){
        this.bool = bl;
        this.index=idx;
        this.direct=dir;
    };

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
    public int getDirect() { return direct; }
    public void setDirect(int direct) { this.direct = direct; }
    public int getBool() {
        return bool;
    }
    public void setBool(int bool) {
        this.bool = bool;
    }
}
