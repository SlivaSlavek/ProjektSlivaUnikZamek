package commands;

import Hra.SystemHry;

/**
 * Příkaz prohledej vypíše popis místnosti.
 * @author Slávek Slíva
 */
public class Prohledej extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return hra.getHrac().getPoloha().getPopis();
    }
}
