package edu.sungshin.anaegyung;

public class UserAccount {
    private int obBool;
    private int obIndex;
    private int obDirect;
    private int obSec;

    public UserAccount(){ }
    public UserAccount(int bool,int index,int direct,int os){
        this.obBool=bool;
        this.obIndex=index;
        this.obDirect=direct;
        this.obSec=os;
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
    public int getObSec() { return obSec; }
    public void setObSec(int obSec) { this.obSec = obSec; }
}

