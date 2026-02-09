package commands;

import Hra.SystemHry;

public class Mluv extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (hra.getHrac().getPoloha().getNpc()!=null){
            String ukol1 = "Jdi najít " + hra.getMistnosti()[7].getNpc().getJmeno() + " do " + hra.getMistnosti()[7].getNazev() + ".";
            String ukol2 = "Nyní je potřeba přinést " + hra.getMoznePredmety().get(0) + " a " + hra.getMoznePredmety().get(1) + " pro " + hra.getMistnosti()[7].getNpc().getJmeno() + ".";
            String ukol3 = "Nyní jdi směrem na " + hra.getMistnosti()[0].getNazev() + " a tam použij předmět, díky kterému budeš moci odejít.";
            if (hra.getHrac().getPoloha()==hra.getMistnosti()[0]){
                if (hra.getDalsiUkol()!=ukol2||hra.getDalsiUkol()!=ukol3){
                    hra.setDalsiUkol(ukol1);
                }
            }
            if (hra.getHrac().getPoloha()==hra.getMistnosti()[7]){
                if (hra.getDalsiUkol()!=ukol3) {
                    hra.setDalsiUkol(ukol2);
                }
            }
            return hra.getHrac().getPoloha().getNpc().getJmeno() + ": " + hra.getHrac().getPoloha().getNpc().getDialog();
        } else {
            return "V místnosti se nenachází žádné NPC.";
        }
    }
}
