public class Predmet {
    public String nazev;
    public boolean jenJednou;

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isJenJednou() {
        return jenJednou;
    }

    public void setJenJednou(boolean jenJednou) {
        this.jenJednou = jenJednou;
    }

    public Predmet(String nazev, boolean jenJednou) {
        this.nazev = nazev;
        this.jenJednou = jenJednou;
    }

    //TODO Upravit toString
    @Override
    public String toString() {
        return "Predmet{" +
                "nazev='" + nazev + '\'' +
                ", jenJednou=" + jenJednou +
                '}';
    }
}
