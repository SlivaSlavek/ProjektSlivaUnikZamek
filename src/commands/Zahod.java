package commands;

import Hra.SystemHry;

import java.util.Objects;

/**
 * S příkazem zahoď hráč vyhazuje předměty z inventáře pryč.
 * @author Slávek Slíva
 */
public class Zahod extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (hra.getHrac().getInv()[0]==null&&hra.getHrac().getInv()[1]==null){
            return "V inventáři nic není.";
        } else {
            if (hra.getHrac().getInv()[0]!=null){
                if (Objects.equals(prikaz.toLowerCase(), hra.getHrac().getInv()[0].getNazev().toLowerCase())) {
                    if (Objects.equals(hra.getHrac().getInv()[0].getNazev(), hra.getMoznePredmety().get(0).getNazev())) {
                        hra.setJeMainPredmet1(false);
                    } else if (Objects.equals(hra.getHrac().getInv()[0].getNazev(), hra.getMoznePredmety().get(1).getNazev())) {
                        hra.setJeMainPredmet2(false);
                    }
                    hra.getHrac().getInv()[0] = null;
                    return "Předmět " + prikaz + " byl nenávratně zahozen.";
                }
            }
            if (hra.getHrac().getInv()[1]!=null) {
                if (Objects.equals(prikaz.toLowerCase(), hra.getHrac().getInv()[1].getNazev().toLowerCase())) {
                    if (Objects.equals(hra.getHrac().getInv()[1].getNazev(), hra.getMoznePredmety().get(0).getNazev())) {
                        hra.setJeMainPredmet1(false);
                    } else if (Objects.equals(hra.getHrac().getInv()[1].getNazev(), hra.getMoznePredmety().get(1).getNazev())) {
                        hra.setJeMainPredmet2(false);
                    }
                    hra.getHrac().getInv()[1] = null;
                    return "Předmět " + prikaz + " byl nenávratně zahozen.";
                } else {
                    return "V inventáři není žádný předmět s názvem " + prikaz + ".";
                }
            }
        }
        return "Nenalezeno";
    }
}
