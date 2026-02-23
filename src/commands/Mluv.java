package commands;

import Hra.SystemHry;

import java.util.Objects;

/**
 * Příkaz mluv řeší mluvu s NPC. V případě, že hráč od NPC dosten úkol, řeší i to.
 * @author Slávek Slíva
 */
public class Mluv extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (hra.getHrac().getPoloha().getNpc()!=null){
            String ukol1 = "Jdi najít " + hra.getMistnosti()[7].getNpc().getJmeno() + " do " + hra.getMistnosti()[7].getNazev() + ".";
            String ukol2 = "Nyní je potřeba přinést " + hra.getMoznePredmety().get(0) + " a " + hra.getMoznePredmety().get(1) + " pro " + hra.getMistnosti()[7].getNpc().getJmeno() + ".";
            String ukol3 = "Nyní jdi směrem na " + hra.getMistnosti()[0].getNazev() + " a tam použij předmět, díky kterému budeš moci odejít.";
            if (hra.getHrac().getPoloha()==hra.getMistnosti()[0]){
                if (!Objects.equals(hra.getDalsiUkol(), ukol2) && !Objects.equals(hra.getDalsiUkol(), ukol3)){
                    hra.setDalsiUkol(ukol1);
                }
            }
            if (hra.getHrac().getPoloha()==hra.getMistnosti()[7]){
                if (!Objects.equals(hra.getDalsiUkol(), ukol3)) {
                    hra.setDalsiUkol(ukol2);
                }
            }
            return hra.getHrac().getPoloha().getNpc().getJmeno() + ": " + hra.getHrac().getPoloha().getNpc().getDialog();
        } else {
            return "V místnosti se nenachází žádné NPC.";
        }
    }
}
