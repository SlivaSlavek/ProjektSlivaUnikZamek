package commands;

import Hra.SystemHry;

/**
 * Příkaz prohledej vypíše popis místnosti.
 */
public class Prohledej extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return hra.getHrac().getPoloha().getPopis();
    }
}
