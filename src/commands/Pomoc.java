package commands;

import Hra.SystemHry;

/**
 * Příkaz pomoc vypisuje aktuální úkol, co hráč má.
 */
public class Pomoc extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return hra.getDalsiUkol();
    }
}
