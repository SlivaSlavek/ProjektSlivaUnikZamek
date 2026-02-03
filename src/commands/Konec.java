package commands;

import Hra.SystemHry;

public class Konec extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        hra.setKonec(true);
        return "Hra je nyní ukončena.";
    }
}