package commands;

import Hra.SystemHry;

/**
 * Vypisuje nápovědu.
 * @author Slávek Slíva
 */
public class Napoveda extends Prikaz{
    @Override
    public String provedeniPrikazu(String prikaz, SystemHry hra) {
        return " === Nápověda === \n \n Příkazy:    - Píší se přesně, jak jsou zde napsané + případně přesný název - např. ´´jdi Hlavní sál´´ či ´´mluv´´" +
                " \n (příkaz   <parametry>          -popis příkazu) \n \n" +
                " jdi       <název místnosti>    - Příkaz pro chůzi, píše se za něj přesný název místnosti. \n" +
                " mluv      <>                   - Příkaz pro mluvení s NPC v dané místnosti, nepíše se parametr. \n" +
                " prohledej <>                   - Příkaz pro prohledání místnosti (zjištění popisu), nepíše se parametr. \n" +
                " akce      <název předmětu/nic> - Příkaz pro vyvolání akce. Pokud se nenapíše žádný parametr, příkaz otevře truhlu. Jinak je parametr přesný název předmětu, co chce hráč použít. \n" +
                " vezmi     <název předmětu>     - Příkaz pro braní předmětů z truhel, parametrem je přesný název předmětu, co chce člověk vzít. \n" +
                " zahod     <název předmětu>     - Příkaz pro zahození předmětu z inventáře, parametr je přesný název předmětu. \n" +
                " napoveda  <>                   - Příkaz pro vypsání této nápovědy, bez parametru. \n" +
                " pomoc     <>                   - Příkaz pro zobrazení aktuálního úkolu a situace hráče, je bezparamatrový. \n" +
                " konec     <true, pravda,...>   - Příkaz pro ukončení hry. Pokud hru chci uložit, jako parametr napíšu ´´ulozit´´ nebo ´´true´´ nebo ´´pravda´´ nebo ´´ano´´, pokud nechci uložit, nepíšu nic. \n \n" +
                " === konec nápovědy === \n";

    }
}
