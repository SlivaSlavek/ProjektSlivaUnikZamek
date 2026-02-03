package Hra;

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

    //TODO Upravit toString
    @Override
    public String toString() {
        return "Hra.NPC{" +
                "jmeno='" + jmeno + '\'' +
                ", dialog='" + dialog + '\'' +
                ", pridelenaMistnost=" + pridelenaMistnost +
                '}';
    }
}
