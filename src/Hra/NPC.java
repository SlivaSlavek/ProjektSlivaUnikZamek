package Hra;

/**
 * Třída nehratelných postav hry.
 * @author Slávek Slíva
 */
public class NPC {
    private String jmeno;
    private String dialog;
    private Mistnost pridelenaMistnost;

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public Mistnost getPridelenaMistnost() {
        return pridelenaMistnost;
    }

    public void setPridelenaMistnost(Mistnost pridelenaMistnost) {
        this.pridelenaMistnost = pridelenaMistnost;
    }

    @Override
    public String toString() {
        return jmeno;
    }
}
