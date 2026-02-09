package commands;

import Hra.SystemHry;

import java.util.Objects;

public class Vezmi extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        if (hra.getHrac().getPoloha().isTruhlaOtevrena()) {
            if (hra.getHrac().getPoloha().getPredmet() != null) {
                if (hra.getHrac().getInv()[0] == null || hra.getHrac().getInv()[1] == null) {
                    if (Objects.equals(prikaz.toLowerCase(), hra.getHrac().getPoloha().getPredmet().getNazev().toLowerCase())) {
                        if (hra.getHrac().getInv()[0] == null) {
                            hra.getHrac().getInv()[0] = hra.getHrac().getPoloha().getPredmet();
                            hra.getHrac().getPoloha().setPredmet(null);
                        } else {
                            hra.getHrac().getInv()[1] = hra.getHrac().getPoloha().getPredmet();
                            hra.getHrac().getPoloha().setPredmet(null);
                        }
                        return "Předmět " + prikaz + " byl vložen do inventáře.";
                    } else {
                        return "Předmět " + prikaz + " nebyl v místnosti nalezen.";
                    }
                } else {
                    return "Hráč má plný inventář, žádný nový předmět se mu tam nevejde.";
                }
            } else {
                return "V místnosti není žádný předmět.";
                }
        } else {
            return "V místnosti není žádná truhla nebo není otevřená.";
        }
    }
}
