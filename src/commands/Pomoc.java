package commands;

import Hra.SystemHry;

public class Pomoc extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return hra.getDalsiUkol();
    }
}
