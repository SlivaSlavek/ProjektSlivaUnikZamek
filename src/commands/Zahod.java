package commands;

import Hra.SystemHry;

import java.util.Objects;

public class Zahod extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if(Objects.equals(prikaz.toLowerCase(), hra.getHrac().getInv()[0].getNazev().toLowerCase())){
            hra.getHrac().getInv()[0]=null;
            return "Předmět " + prikaz + " byl nenávratně zahozen.";
        } else if(Objects.equals(prikaz.toLowerCase(), hra.getHrac().getInv()[1].getNazev().toLowerCase())){
            hra.getHrac().getInv()[1]=null;
            return "Předmět " + prikaz + " byl nenávratně zahozen.";
        } else {
            return "V inventáři není žádný předmět s názvem " + prikaz;
        }
    }
}
