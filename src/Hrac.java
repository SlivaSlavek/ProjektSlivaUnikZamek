public class Hrac {
    private String jmeno;
    private Predmet[] inv = new Predmet[2];
    private Mistnost poloha;

    public Predmet[] getInv() {
        return inv;
    }

    public void setInv(Predmet[] inv) {
        this.inv = inv;
    }

    public Mistnost getPoloha() {
        return poloha;
    }

    public void setPoloha(Mistnost poloha) {
        this.poloha = poloha;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }
}
