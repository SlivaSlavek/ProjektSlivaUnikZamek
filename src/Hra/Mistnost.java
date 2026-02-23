package Hra;

import java.util.ArrayList;

/**
 * Třída místností.
 * @author Slávek Slíva
 */
public class Mistnost {
    private String nazev;
    private String popis;
    private ArrayList<Mistnost>sousediciMistnosti;
    private boolean maPovolenySpawnNPC;
    private NPC npc;
    private boolean maTruhlu;
    private Predmet predmet;
    private boolean truhlaOtevrena = false;


    @Override
    public String toString() {
        String sousediciMistnostiJmena = "";
        for (int i=0;i<sousediciMistnosti.size();i++){
            if(sousediciMistnostiJmena==""){
                sousediciMistnostiJmena=sousediciMistnosti.get(i).getNazev();
            } else {
                sousediciMistnostiJmena = sousediciMistnostiJmena + "; " + sousediciMistnosti.get(i).getNazev();
            }
        }
        if (maTruhlu && getNpc()!=null){
            return nazev + ", v místnosti je truhla a npc " + npc + ". Sousedící místnosti: " + sousediciMistnostiJmena;
        } else if (maTruhlu&&getNpc()==null){
            return nazev + ", v místnosti je truhla. Sousedící místnosti: " + sousediciMistnostiJmena;
        } else if (!maTruhlu&&getNpc()!=null) {
            return nazev + ", v místnosti je npc " + npc + ". Sousedící místnosti: " + sousediciMistnostiJmena;
        } else {
            return nazev + ", v místnosti není nic zajímavého. Sousedící místnosti: " + sousediciMistnostiJmena;
        }
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public ArrayList<Mistnost> getSousediciMistnosti() {
        return sousediciMistnosti;
    }

    public void setSousediciMistnosti(ArrayList<Mistnost> sousediciMistnosti) {
        this.sousediciMistnosti = sousediciMistnosti;
    }

    public boolean isMaPovolenySpawnNPC() {
        return maPovolenySpawnNPC;
    }

    public void setMaPovolenySpawnNPC(boolean maPovolenySpawnNPC) {
        this.maPovolenySpawnNPC = maPovolenySpawnNPC;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public boolean isMaTruhlu() {
        return maTruhlu;
    }

    public void setMaTruhlu(boolean maTruhlu) {
        this.maTruhlu = maTruhlu;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Mistnost(boolean maPovolenySpawnNPC, boolean maTruhlu,ArrayList<Mistnost> sousedniMistnosti) {
        this.maPovolenySpawnNPC = maPovolenySpawnNPC;
        this.maTruhlu = maTruhlu;
    }

    public boolean isTruhlaOtevrena() {
        return truhlaOtevrena;
    }

    public void setTruhlaOtevrena(boolean truhlaOtevrena) {
        this.truhlaOtevrena = truhlaOtevrena;
    }

    public Mistnost() {
    }
}
