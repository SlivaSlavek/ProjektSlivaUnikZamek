package commands;

import Hra.SystemHry;

/**
 * Hlavní příkazová třída, od které dědí všechny příkazy.
 */
public abstract class Prikaz {
    public abstract String provedeniPrikazu(String string, SystemHry hra);
}
