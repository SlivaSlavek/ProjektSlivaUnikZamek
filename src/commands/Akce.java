package commands;

import Hra.SystemHry;

import java.util.Objects;

public class Akce extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        String ukol2 = "Nyní je potřeba přinést " + hra.getMoznePredmety().get(0) + " a " + hra.getMoznePredmety().get(1) + " pro " + hra.getMistnosti()[7].getNpc().getJmeno() + ".";
        String ukol3 = "Nyní jdi směrem na " + hra.getMistnosti()[0].getNazev() + " a tam použij předmět, díky kterému budeš moci odejít.";
        prikaz=prikaz.toLowerCase();
        if (prikaz==null){
            if (hra.getHrac().getPoloha().isTruhlaOtevrena()!=true){
                if (hra.getHrac().getPoloha().getPredmet()==null){
                    return "V místnosti nejspíše nebude žádná truhla.";
                } else {
                    hra.getHrac().getPoloha().setTruhlaOtevrena(true);
                    return "Truhla byla otevřena.";
                }
            } else {
                return "Truhla v místnosti je již otevřena.";
            }
        } else if (hra.getHrac().getPoloha()==hra.getMistnosti()[7]){
            if (prikaz.equals(hra.getMoznePredmety().get(1).getNazev().toLowerCase())) {
                if (Objects.equals(hra.getDalsiUkol(), ukol2)){
                    if (Objects.equals(hra.getHrac().getInv()[0].getNazev().toLowerCase(), prikaz)){
                        if (hra.getHrac().getInv()[1].getNazev()==hra.getMoznePredmety().get(0).getNazev()){
                            hra.setDalsiUkol(ukol3);
                            hra.getHrac().getInv()[0]=null;
                            return "Děkuji a gratuluji. " + ukol3;
                        } else {
                            return "Ještě ti chybí předmět " + hra.getMoznePredmety().get(0).getNazev() + ".";
                        }
                    } else if (Objects.equals(hra.getHrac().getInv()[1].getNazev().toLowerCase(), prikaz)) {
                        if (hra.getHrac().getInv()[0].getNazev()==hra.getMoznePredmety().get(0).getNazev()){
                            hra.setDalsiUkol(ukol3);
                            hra.getHrac().getInv()[1]=null;
                            return "Děkuji a gratuluji. " + ukol3;
                        } else {
                            return "Ještě ti chybí předmět " + hra.getMoznePredmety().get(0).getNazev() + ".";
                        }
                    } else {
                        return "Požadovaný předmět není v inventáři.";
                    }
                } else {
                    return "Požadovaný úkol nyní nelze splnit.";
                }
            } else if (prikaz.equals(hra.getMoznePredmety().get(0).getNazev())) {
                if (Objects.equals(hra.getHrac().getInv()[0].getNazev().toLowerCase(), prikaz)){
                    return hra.getMistnosti()[7].getNpc().getJmeno() + ": Ano, tento předmět potřebojuš k odchodu, je dobře, že ho máš, ale nedávej mi ho. Já chci " + hra.getMoznePredmety().get(2) + ".";
                } else if (Objects.equals(hra.getHrac().getInv()[1].getNazev().toLowerCase(), prikaz)) {
                    return hra.getMistnosti()[7].getNpc().getJmeno() + ": Ano, tento předmět potřebojuš k odchodu, je dobře, že ho máš, ale nedávej mi ho. Já chci " + hra.getMoznePredmety().get(2) + ".";
                } else {
                    return "Požadovaný předmět není v inventáři.";
                }
            } else {
                return "S předmětem "+ prikaz +" zde nelze žádná akce.";
            }
        } else if (hra.getHrac().getPoloha()==hra.getMistnosti()[0]) {
            if (prikaz==hra.getMoznePredmety().get(0).getNazev()){
                if (hra.getDalsiUkol()!=ukol3){
                    return "Nejdříve je třeba splnit ostatní úkoly.";
                } else {
                    if (hra.getHrac().getInv()[0]==hra.getMoznePredmety().get(0)||hra.getHrac().getInv()[1]==hra.getMoznePredmety().get(0)){
                        hra.setKonec(true);
                        return "";
                    } else {
                        return "Daný předmět není v inventáři.";
                    }
                }
            } else {
                return "Daný předmět zde nelze použít";
            }
        } else {
            return "V této místnosti nelze provést žádnou akci.";
        }
    }
}
