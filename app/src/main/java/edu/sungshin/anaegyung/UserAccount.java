package edu.sungshin.anaegyung;

public class UserAccount {
    private int obBool;
    private int obIndex;
    private int obDirect;

    public UserAccount(){ }
    public UserAccount(int bool,int index,int direct){
        this.obBool=bool;
        this.obIndex=index;
        this.obDirect=direct;
    }

    public int getObBool() {
        return obBool;
    }

    public void setObBool(int obBool) {
        this.obBool = obBool;
    }

    public int getObIndex() {
        return obIndex;
    }

    public void setObIndex(int obIndex) {
        this.obIndex = obIndex;
    }

    public int getObDirect() {
        return obDirect;
    }

    public void setObDirect(int obDirect) {
        this.obDirect = obDirect;
    }
}

