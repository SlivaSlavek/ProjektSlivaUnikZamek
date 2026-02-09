package Hra;

import commands.*;

import java.io.*;
import java.util.*;

public class SystemHry {
    private Mistnost[] mistnosti = new Mistnost[8];
    private ArrayList<NPC> npccka = new ArrayList<>();
    private Hrac hrac=new Hrac();
    private ArrayList<Predmet> moznePredmety = new ArrayList<>();
    private HashMap<String, Prikaz>mapaPrikazu=new HashMap<>();
    private String dalsiUkol = "Zatím nemáte žádný úkol.";
    private boolean konec=false;



    public void hrani(){
        vygenerujStartHry();
        hlavniLoop();
    }


    private void hlavniLoop(){
        while (!isKonec()){
            provedPrikaz();
        }
    }


    public void vypis(String text){
        System.out.println(text);
    }


    public void vygenerujStartHry(){
        vygenerujMistnosti();
        vygenerujNPC();
        vygenerujPredmety();
        if (jeSave()) {
            Scanner scannerr=new Scanner(System.in);
            vypis("Bylo nalezeno uložení hry. Pokud chcete novou hru, napiště 0 jinak jakékoliv jiné ČÍSLO pro načtění uložení.");
            if (scannerr.nextInt()!=0){
                nacteniUlozeneHry();
                return;
            }
        }
        hrac.setPoloha(mistnosti[0]);
        //TODO Generování předmětů do místností
    }



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

    public ArrayList<NPC> nestatickychNPC(){
        ArrayList<NPC> pohyblivychNPC=null;
        for (int i=0;i<npccka.size();i++){
            if (npccka.get(i).getPridelenaMistnost()==null){
                pohyblivychNPC.add(npccka.get(i));
            }
        }
        return pohyblivychNPC;
    }

    public void odendejNPCZMistnosti(){
        for (int i =0;i< mistnosti.length;i++){
            if (mistnosti[i].getNpc().getPridelenaMistnost()==null){
                mistnosti[i].setNpc(null);
            }
        }
    }

    public ArrayList<Mistnost> misnostiProNPC(){
        ArrayList<Mistnost>msnsti=new ArrayList<>();
        for (int i=0;i<mistnosti.length;i++) {
            if (mistnosti[i].isMaPovolenySpawnNPC()){
                msnsti.add(mistnosti[i]);
            }
        }
        return msnsti;
    }

    public void ulozeniHry(){
        FileWriter fw = null;
        try {
            fw = new FileWriter("ulozeni.txt");
            fw.write("--V tomto souboru nic neupravovat, pouze pro smazání uložení změntě na 2. řádku ´´uloženo´´ na ´´neuloženo´´ (dělejte například u změn ve vstupních souborech)--");
            fw.write("ulozeno");
            fw.write(dalsiUkol);
            fw.write(hrac.getPoloha().getNazev());
            fw.write(hrac.getInv()[0].getNazev());
            fw.write(hrac.getInv()[1].getNazev());
            for (int i =0;i<mistnosti.length;i++){
                if (mistnosti[i].getPredmet()!=null) {
                    fw.write(mistnosti[i].getPredmet().getNazev());
                } else {
                    fw.write("null");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
                    for (int j=0;i<moznePredmety.size();j++){
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
}
