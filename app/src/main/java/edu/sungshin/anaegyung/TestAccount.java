package edu.sungshin.anaegyung;

public class TestAccount {
    private int index;
    private int direct;

    public TestAccount(){

    };

    public TestAccount(int idx,int dir){
        this.index=idx;
        this.direct=dir;
    };

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
    public int getDirect() { return direct; }
    public void setDirect(int direct) { this.direct = direct; }
}
