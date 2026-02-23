package commands;

import Hra.SystemHry;

/**
 * Příkaz konec řeší ukončení hry a případné uložení.
 * @author Slávek Slíva
 */
public class Konec extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (prikaz.toLowerCase().equals("true") || prikaz.toLowerCase().equals("pravda") || prikaz.toLowerCase().equals("ano") || prikaz.toLowerCase().equals("ulozit")) {
            hra.ulozeniHry();
            hra.vypis("Hra uložena.");
        }
        hra.setKonec(true);
        return "Hra je nyní ukončena.";
    }
}