package commands;

import Hra.SystemHry;

/**
 * Příkaz pomoc vypisuje aktuální úkol, co hráč má. Dále vypisuje aktuální "situaci" hráče (poloha, inventář, apod...)
 * @author Slávek Slíva
 */
public class Pomoc extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return "Aktuální úkol: " + hra.getDalsiUkol() +
                "\nAktuální poloha: " + hra.getHrac().getPoloha() +
                "\nInventář: " + hra.getHrac().getInv()[0] + "; " + hra.getHrac().getInv()[1];
    }
}
