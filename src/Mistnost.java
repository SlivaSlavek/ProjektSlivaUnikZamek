import java.util.ArrayList;

public class Mistnost {
    private String nazev;
    private String popis;
    private ArrayList<Mistnost>sousediciMistnosti;
    private boolean maPovolenySpawnNPC;
    private NPC npc;
    private boolean maTruhlu;
    private Predmet predmet;


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

    public Mistnost() {
    }
}
