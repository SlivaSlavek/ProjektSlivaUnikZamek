package commands;

import Hra.SystemHry;

public class Mluv extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (hra.getHrac().getPoloha().getNpc()!=null){
            return hra.getHrac().getPoloha().getNpc().getJmeno() + ": " + hra.getHrac().getPoloha().getNpc().getDialog();
        } else {
            return "V místnosti se nenachází žádné NPC.";
        }
    }
}
