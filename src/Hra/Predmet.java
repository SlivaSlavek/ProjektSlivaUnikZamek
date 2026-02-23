package Hra;

/**
 * Třída předmětů, co hráč například může brát do inventáře, či některé používat.
 * @author Slávek Slíva
 */
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

    @Override
    public String toString() {
        return nazev;
    }
}
