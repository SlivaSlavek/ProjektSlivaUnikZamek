package commands;

import Hra.SystemHry;

/**
 * Vypisuje nápovědu.
 */
public class Napoveda extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return "Napoveda";
        //TODO Vypsat nápovědu příkazů a k čemu jaký slouží
    }
}
