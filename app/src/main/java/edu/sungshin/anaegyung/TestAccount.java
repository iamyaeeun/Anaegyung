package edu.sungshin.anaegyung;

public class TestAccount {
    private int bool;
    private int index;
    private int direct;
    private int sec;

    public TestAccount(){

    };

    public TestAccount(int bl, int idx,int dir,int s){
        this.bool = bl;
        this.index=idx;
        this.direct=dir;
        this.sec=s;
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
    public int getSec() { return sec; }
    public void setSec(int sec) { this.sec = sec; }
}
