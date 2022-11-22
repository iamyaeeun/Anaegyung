package edu.sungshin.anaegyung;

public class TestAccount {
    private int index;
    private int area;
    private int direct;

    public TestAccount(){

    };

    public TestAccount(int idx,int a,int dir){
        this.index=idx;
        this.area=a;
        this.direct=dir;
    };

    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
    public int getArea() { return area; }
    public void setArea(int area) { this.area = area; }
    public int getDirect() { return direct; }
    public void setDirect(int direct) { this.direct = direct; }
}
