package Hra;

import commands.*;

import java.io.*;
import java.util.*;

/**
 * Hlavní třída. Obsahuje hlavní metody a vlastnosti pro funkčnost hry. Celá hra běží na věcech z této třídy.
 * @author Slávek Slíva
 */
public class SystemHry {
    private Mistnost[] mistnosti = new Mistnost[8];
    private ArrayList<NPC> npccka = new ArrayList<>();
    private Hrac hrac=new Hrac();
    private ArrayList<Predmet> moznePredmety = new ArrayList<>();
    private HashMap<String, Prikaz>mapaPrikazu=new HashMap<>();
    private String dalsiUkol = "Zatím nemáte žádný úkol.";
    private boolean jeMainPredmet1 = false;
    private boolean jeMainPredmet2 = false;
    private boolean konec=false;


    /**
     * Metoda hraní obsahuje celou hru - vygeneruje vše a poté zahájí hlavní smyčku, co bude trvat až do konce.
     */
    public void hrani(){
        vygenerujStartHry();
        hlavniLoop();
    }


    /**
     * Hlavní loop hry je smyčka, co celou hru opakuje dokola, dokud neskončí.
     */
    private void hlavniLoop(){
        while (!isKonec()){
            provedPrikaz();
        }
    }


    /**
     * Metoda sloužící k vypisování.
     * @param text Text, co se má vypsat
     */
    public void vypis(String text){
        System.out.println(text);
    }


    /**
     * Zavolá metody pro vygenerování jednotlivých částí hry. Pokud existuje save, pak vyřeší s hráčem, zda hru chce nevou nebo uloženou.
     */
    public void vygenerujStartHry(){
        vypis("Vítejte ve hře!");
        vypis("Načítání...");
        vygenerujMistnosti();
        vygenerujNPC();
        vygenerujPredmety();
        if (jeSave()) {
            Scanner scannerr=new Scanner(System.in);
            vypis("Bylo nalezeno uložení hry. Pokud chcete novou hru, napiště 0 jinak jakékoliv jiné ČÍSLO či něco pro načtění uložené hry.");
            if (!Objects.equals(scannerr.nextLine(), "0")){
                nacteniUlozeneHry();
                return;
            }
        }
        vypis("\n\nÚnik z prokletého zámku\n\n" +
                "Hlavní postava, obyčejný mladík, Pepa Bojka se jednoho dne vydal do temného\n" +
                "lesa, cestou narazil na velký tmavý zámek. A protože byl člověk zvídavý, vešel\n" +
                "dovnitř. Jen co vešel dovnitř, dveře se za ním zabouchly a zamkly.\n" +
                "Pepa se ne a ne dostat ven, když v tom uslyší za svými zády zakvákání. Byla tam\n" +
                "malá ropucha, co opakovala stále dokola ”Čaroděj je ve věži, kváky kvák.”\n" +
                "V Prokletém zámku bude hrdina moci projít několik místností, až narazí na\n" +
                "Temnou věž. V Temné věži se ukrývá čaroděj, kterému tento Prokletý zámek\n" +
                "patří. Protože má dobrou náladu, řekne, jak se lze dostat pryč. Hlavní dveře se\n" +
                "musí otevřít velkým starým klíčem, avšak to nestačí, protože čaroděj musí ještě\n" +
                "přerušit kouzlo, které zabraňuje lidem utéci. Přeruší to pouze tehdy, když mu\n" +
                "Pepa donese Starý čarovný lexikon, který je schovaný na zámku.\n" +
                "Předměty jsou schované na zámku, neznámo kde, ale při prohledávání truhel se\n" +
                "jistě brzy objeví. Cílem hry je utéct ze Strašidelného zámku, tak že hráč odemkne hlavní dveře\n" +
                "Velkým starým klíčem, který je skrytý na zámku.\n\n" +
                "Hra začíná....   (pro zobrazení příkazů napiště ´´napoveda´´ (takto bez diakritiky jen to slovo)) \n");
        hrac.setPoloha(mistnosti[0]);
        rozmistovaniPredmetu();
    }


    /**
     * Metoda provede zavolání příkazu, co si napíše hráč, případně vyřeší, když je příkaz neplatný.
     */
    public void provedPrikaz(){
        vlozPrikazyDoMapy();
        Scanner scanner = new Scanner(System.in);
        String napsano = scanner.nextLine();
        if (!napsano.contains(" ")){
            napsano=napsano+"  ";
        }
        String[] prikaz = napsano.split(" ",2);
        if(mapaPrikazu.containsKey(prikaz[0].toLowerCase())){
            vypis(mapaPrikazu.get(prikaz[0]).provedeniPrikazu(prikaz[1], this));
        } else{
            vypis("Příkaz " + prikaz[0] + " nebyl nalezen. Pro nabídku správných příkazů zadejte příkaz ´´napoveda´´ (bez diakritiky).");
        }

    }

    private void vlozPrikazyDoMapy(){
        mapaPrikazu.put("akce", new Akce());
        mapaPrikazu.put("jdi", new Jdi());
        mapaPrikazu.put("konec",new Konec());
        mapaPrikazu.put("mluv",new Mluv());
        mapaPrikazu.put("napoveda",new Napoveda());
        mapaPrikazu.put("pomoc",new Pomoc());
        mapaPrikazu.put("prohledej",new Prohledej());
        mapaPrikazu.put("vezmi",new Vezmi());
        mapaPrikazu.put("zahod",new Zahod());
    }

    /**
     * Metoda mění polohu pohyblivých NPC po místnostech.
     */
    public void npcRotace(){
        Random random=new Random();
        odendejNPCZMistnosti();
        ArrayList<Mistnost>povoleneMistnost=misnostiProNPC();
        ArrayList<NPC> nestatickaNPC=nestatickychNPC();
        for (int i=0;i<nestatickaNPC.size();i++){
            int cislo=random.nextInt(nestatickaNPC.size()+povoleneMistnost.size()+2);
            if (cislo< povoleneMistnost.size()){
                if(povoleneMistnost.get(cislo).getNpc()==null) {
                    povoleneMistnost.get(cislo).setNpc(nestatickaNPC.get(i));
                }
            }
        }
    }

    /**
     * Metoda kontroluje a hleda NPC, co se mohou pohybovat.
     * @return Vrací ArrayList NPC, co se mohou pohybovat.
     */
    public ArrayList<NPC> nestatickychNPC(){
        ArrayList<NPC> pohyblivychNPC=new ArrayList<>();
        for (int i=0;i<npccka.size();i++){
            if (npccka.get(i).getPridelenaMistnost()==null){
                pohyblivychNPC.add(npccka.get(i));
            }
        }
        return pohyblivychNPC;
    }

    /**
     * Odstraní pohyblivá NPCčka z místností.
     */
    public void odendejNPCZMistnosti(){
        for (int i =0;i< mistnosti.length;i++){
            if (mistnosti[i].getNpc()!=null) {
                if (mistnosti[i].getNpc().getPridelenaMistnost() == null) {
                    mistnosti[i].setNpc(null);
                }
            }
        }
    }

    /**
     * Hledá a kontroluje místnosti, kam mohou cestovat NPC.
     * @return Navrací ArryList místností, kde se NPC mohou objevit.
     */
    public ArrayList<Mistnost> misnostiProNPC(){
        ArrayList<Mistnost>msnsti=new ArrayList<>();
        for (int i=0;i<mistnosti.length;i++) {
            if (mistnosti[i].isMaPovolenySpawnNPC()){
                msnsti.add(mistnosti[i]);
            }
        }
        return msnsti;
    }

    /**
     * Regeneruje předměty do místností.
     */
    public void rozmistovaniPredmetu(){
        for (int i=0;i< mistnosti.length;i++){
            if (mistnosti[i].isMaTruhlu()) {
                if (mistnosti[i].getPredmet() == null) {
                    Random random = new Random();
                    int cislo = random.nextInt(0, moznePredmety.size() - 1);
                    if (isJeMainPredmet1() && cislo == 0) {
                        cislo = random.nextInt(1, moznePredmety.size() - 1);
                    }
                    if (isJeMainPredmet2() && cislo == 1) {
                        cislo = random.nextInt(2, moznePredmety.size() - 1);
                    }
                    if (cislo == 0) {
                        jeMainPredmet1 = true;
                    } else if (cislo == 1) {
                        jeMainPredmet2 = true;
                    }
                    mistnosti[i].setPredmet(moznePredmety.get(cislo));
                    mistnosti[i].setTruhlaOtevrena(false);
                }
            }
        }
    }

    /**
     * Uloží aktuální stav hry do souboru ulozeni.txt, který při novém spuštění půjde načíst.
     */
    public void ulozeniHry(){
        FileWriter fw0 = null;

        try {
            fw0 = new FileWriter("ulozeni.txt");
            BufferedWriter fw = new BufferedWriter(fw0);
            fw.write("--V tomto souboru nic neupravovat, pouze pro smazání uložení změntě na 2. řádku ´´uloženo´´ na ´´neuloženo´´ (dělejte například u změn ve vstupních souborech)--");
            fw.newLine();
            fw.write("ulozeno");
            fw.newLine();
            fw.write(dalsiUkol);
            fw.newLine();
            fw.write(hrac.getPoloha().getNazev());
            fw.newLine();
            if (jeMainPredmet1){
                fw.write("true");
                fw.newLine();
            } else{
                fw.write("false");
                fw.newLine();
            }
            if (jeMainPredmet2){
                fw.write("true");
                fw.newLine();
            } else{
                fw.write("false");
                fw.newLine();
            }
            if (hrac.getInv()[0]!=null) {
                fw.write(hrac.getInv()[0].getNazev());
                fw.newLine();
            } else {
                fw.write("null");
                fw.newLine();
            }
            if (hrac.getInv()[1]!=null) {
                fw.write(hrac.getInv()[1].getNazev());
                fw.newLine();
            } else {
                fw.write("null");
                fw.newLine();
            }
            for (int i =0;i<mistnosti.length;i++){
                if (mistnosti[i].getPredmet()!=null) {
                    fw.write(mistnosti[i].getPredmet().getNazev());
                    fw.newLine();
                } else {
                    fw.write("null");
                    fw.newLine();
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Kontroluje, zda je nějaké předchozí uložení hry.
     * @return  navrací true pokud je nějaké uložení.
     */
    private boolean jeSave(){
        FileReader fr = null;
        try {
            fr=new FileReader("ulozeni.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br=new BufferedReader(fr);
        try {
            br.readLine();
            if (Objects.equals(br.readLine(), "ulozeno")) {
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Načte uloženou hru.
     */
    private void nacteniUlozeneHry(){
        FileReader fr = null;
        try {
            fr=new FileReader("ulozeni.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br=new BufferedReader(fr);
        try {
            br.readLine();
            if (Objects.equals(br.readLine(), "ulozeno")){
                dalsiUkol=br.readLine();
                String polohaa=br.readLine();
                for (int i=0;i<mistnosti.length;i++){
                    if (Objects.equals(mistnosti[i].getNazev(), polohaa)){
                        hrac.setPoloha(mistnosti[i]);
                    }
                }
                if (hrac.getPoloha()==null){
                    hrac.setPoloha(mistnosti[0]);
                }
                if (Objects.equals(br.readLine(), "true")){
                    jeMainPredmet1=true;
                } else {
                    jeMainPredmet1=false;
                }
                if (Objects.equals(br.readLine(), "true")){
                    jeMainPredmet2=true;
                } else {
                    jeMainPredmet2=false;
                }
                String predmet1=br.readLine();
                for (int i=0;i<moznePredmety.size();i++){
                    if (Objects.equals(moznePredmety.get(i).getNazev(), predmet1)){
                        hrac.getInv()[0]=moznePredmety.get(i);
                    }
                }
                String predmet2= br.readLine();
                for (int i=0;i<moznePredmety.size();i++){
                    if (Objects.equals(moznePredmety.get(i).getNazev(), predmet2)){
                        hrac.getInv()[1]=moznePredmety.get(i);
                    }
                }
                for (int i=0;i<mistnosti.length;i++){
                    String predmett=br.readLine();
                    for (int j=0;j<moznePredmety.size();j++){
                        if (Objects.equals(moznePredmety.get(j).getNazev(), predmett)){
                            mistnosti[i].setPredmet(moznePredmety.get(j));
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Vygeneruje NPC ze souboru generatorNPC.txt.
     */
    private void vygenerujNPC() {
        FileReader fr = null;
        try {
            fr = new FileReader("generatorNPC.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            if (Objects.equals(br.readLine(), "--KONEC!--")) {
                return;
            }
            while (true) {
                if (Objects.equals(br.readLine(), "--KONEC!--")) {
                    return;
                }
                if (Objects.equals(br.readLine(), "--KONEC!--")) {
                    return;
                }
                String radekJmeno =br.readLine();
                if (Objects.equals(radekJmeno, "--KONEC!--")) {
                    return;
                }
                String radekRec=br.readLine();
                if (Objects.equals(radekRec, "--KONEC!--")){
                    return;
                }
                String radekMist= br.readLine();
                if(Objects.equals(radekMist, "--KONEC!--")){
                    return;
                }
                NPC npcTemp = new NPC();
                npcTemp.setJmeno(radekJmeno);
                npcTemp.setDialog(radekRec);
                if (Objects.equals(radekMist, "null") || Objects.equals(radekMist, "") || Objects.equals(radekMist, "nic")){
                    npcTemp.setPridelenaMistnost(null);
                } else {
                    boolean mistnostReady = false;
                    for (int i=0;i< mistnosti.length;i++){
                        if (Objects.equals(radekMist, mistnosti[i].getNazev())){
                            if (mistnosti[i].getNpc()!=null){
                                vypis("V mistnosti " + mistnosti[i].getNazev() + " nemůže být Hra.NPC " + npcTemp.getJmeno() + ", protože tam již je Hra.NPC " + mistnosti[i].getNpc().getJmeno() + ".");
                            } else {
                                npcTemp.setPridelenaMistnost(mistnosti[i]);
                                mistnosti[i].setNpc(npcTemp);
                                mistnostReady = true;
                            }
                        } else if (radekMist.toLowerCase().equals(mistnosti[i].getNazev().toLowerCase())) {
                            if (mistnosti[i].getNpc()!=null){
                                vypis("V mistnosti " + mistnosti[i].getNazev() + " nemůže být Hra.NPC " + npcTemp.getJmeno() + ", protože tam již je Hra.NPC " + mistnosti[i].getNpc().getJmeno() + ".");
                            } else {
                                npcTemp.setPridelenaMistnost(mistnosti[i]);
                                mistnosti[i].setNpc(npcTemp);
                                mistnostReady = true;
                            }
                        }
                    }
                    if (mistnostReady=false){
                        npcTemp.setPridelenaMistnost(null);
                        vypis("Přidělená místnost " + radekMist + " Hra.NPC " + npcTemp.getJmeno() + " nenalezena. Přidělená místnost nebyla nastavená.");
                    }
                }
                npccka.add(npcTemp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Vygeneruje předměty ze souboru predmety.txt.
     */
    private void vygenerujPredmety(){
        FileReader fr = null;
        try {
            fr = new FileReader("predmety.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            if (Objects.equals(br.readLine(), "--KONEC!--")) {
                return;
            }
            while (true) {
                if (Objects.equals(br.readLine(), "--KONEC!--")) {
                    return;
                }
                if (Objects.equals(br.readLine(), "--KONEC!--")) {
                    return;
                }
                String radekJmeno =br.readLine();
                if (Objects.equals(radekJmeno, "--KONEC!--")) {
                    return;
                }
                String radekJedinecnost=br.readLine();
                if (Objects.equals(radekJedinecnost, "--KONEC!--")){
                    return;
                }
                boolean temp;
                if (radekJedinecnost.toLowerCase().equals("true") || radekJedinecnost.toLowerCase().equals("pravda")){
                    temp = true;
                } else {
                    temp=false;
                }
                moznePredmety.add(new Predmet(radekJmeno,temp));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Vygeneruje místnosti ze souboru mistnosti.txt.
     */
    private void vygenerujMistnosti(){
        FileReader fr = null;
        try {
            fr = new FileReader("mistnosti.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            br.readLine();
            for(int i=0;i< mistnosti.length;i++) {
                br.readLine();
                mistnosti[i]=new Mistnost();
                mistnosti[i].setNazev(br.readLine());
                mistnosti[i].setPopis(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mistnosti[0].setMaPovolenySpawnNPC(false); mistnosti[0].setMaTruhlu(false); mistnosti[0].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1])));
        mistnosti[1].setMaPovolenySpawnNPC(true); mistnosti[1].setMaTruhlu(false); mistnosti[1].setSousediciMistnosti( new ArrayList<>(List.of(mistnosti[0],mistnosti[2],mistnosti[3],mistnosti[4],mistnosti[5],mistnosti[6])));
        mistnosti[2].setMaPovolenySpawnNPC(true); mistnosti[2].setMaTruhlu(true); mistnosti[2].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1],mistnosti[3])));
        mistnosti[3].setMaPovolenySpawnNPC(true); mistnosti[3].setMaTruhlu(true); mistnosti[3].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1],mistnosti[2])));
        mistnosti[4].setMaPovolenySpawnNPC(true); mistnosti[4].setMaTruhlu(true); mistnosti[4].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1])));
        mistnosti[5].setMaPovolenySpawnNPC(true); mistnosti[5].setMaTruhlu(true); mistnosti[5].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1])));
        mistnosti[6].setMaPovolenySpawnNPC(false); mistnosti[6].setMaTruhlu(false); mistnosti[6].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[1], mistnosti[7])));
        mistnosti[7].setMaPovolenySpawnNPC(false); mistnosti[7].setMaTruhlu(false); mistnosti[7].setSousediciMistnosti(new ArrayList<>(List.of(mistnosti[6])));
    }

    public Mistnost[] getMistnosti() {
        return mistnosti;
    }

    public Hrac getHrac() {
        return hrac;
    }

    public ArrayList<NPC> getNpccka() {
        return npccka;
    }

    public ArrayList<Predmet> getMoznePredmety() {
        return moznePredmety;
    }

    public String getDalsiUkol() {
        return dalsiUkol;
    }

    public void setDalsiUkol(String dalsiUkol) {
        this.dalsiUkol = dalsiUkol;
    }

    public boolean isKonec() {
        return konec;
    }

    public void setKonec(boolean konec) {
        this.konec = konec;
    }

    public boolean isJeMainPredmet1() {
        return jeMainPredmet1;
    }

    public void setJeMainPredmet1(boolean jeMainPredmet1) {
        this.jeMainPredmet1 = jeMainPredmet1;
    }

    public boolean isJeMainPredmet2() {
        return jeMainPredmet2;
    }

    public void setJeMainPredmet2(boolean jeMainPredmet2) {
        this.jeMainPredmet2 = jeMainPredmet2;
    }

    public void setHrac(Hrac hrac) {
        this.hrac = hrac;
    }

    public void setMistnosti(Mistnost[] mistnosti) {
        this.mistnosti = mistnosti;
    }

    public void setMoznePredmety(List<String> predmety) {
    }
}
