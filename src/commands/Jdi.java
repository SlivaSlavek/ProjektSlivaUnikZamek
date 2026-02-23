package commands;

import Hra.SystemHry;

/**
 * Příkaz jdi řeší pohyb hráče do jiných místností.
 */
public class Jdi extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        for (int i=0;i<hra.getHrac().getPoloha().getSousediciMistnosti().size();i++){
            if (prikaz.toLowerCase().equals(hra.getHrac().getPoloha().getSousediciMistnosti().get(i).getNazev().toLowerCase())){
                hra.getHrac().setPoloha(hra.getHrac().getPoloha().getSousediciMistnosti().get(i));
                hra.npcRotace();
                hra.rozmistovaniPredmetu();
                return "Hráč přešel do místnosti " + hra.getHrac().getPoloha().getNazev() + ".";
            }
        }
        return "Místnost " + prikaz + " neexistuje nebo nesousedí s místností " + hra.getHrac().getPoloha().getNazev() + ", ve které se nyní hráč nachází.";
    }
}
