package commands;

import Hra.SystemHry;
import Hra.Hrac;
import Hra.Predmet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZahodTest {

    private Zahod zahod;
    private SystemHry hra;
    private Hrac hrac;

    @BeforeEach
    void setUp() {
        zahod = new Zahod();
        hra = new SystemHry();
        hrac = new Hrac();
        hra.setHrac(hrac);
        hrac.getInv()[0] = null;
        hrac.getInv()[1] = null;
        hra.getMoznePredmety().add(new Predmet("Main1", true));
        hra.getMoznePredmety().add(new Predmet("Main2", true));
    }

    @Test
    void testVyhodPredmet0() {
        hrac.getInv()[0] = new Predmet("A", false);
        assertDoesNotThrow(() -> zahod.provedeniPrikazu("A", hra));
    }

    @Test
    void testVyhodPredmet1() {
        hrac.getInv()[1] = new Predmet("B", false);
        assertDoesNotThrow(() -> zahod.provedeniPrikazu("B", hra));
    }

    @Test
    void testVyhodNicVPrazdnemInv() {
        String result = zahod.provedeniPrikazu("C", hra);
        assertEquals("V inventáři nic není.", result);
    }

    @Test
    void testVyhodNeexistujiciPredmet() {
        hrac.getInv()[0] = new Predmet("Neco", false);
        hrac.getInv()[1] = new Predmet("Jineho", false);
        String result = zahod.provedeniPrikazu("X", hra);
        assertEquals("V inventáři není žádný předmět s názvem X.", result);
    }
}