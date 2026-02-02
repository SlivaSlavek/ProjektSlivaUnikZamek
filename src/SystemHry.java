import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SystemHry {
    private Mistnost[] mistnosti = new Mistnost[8];
    private ArrayList<NPC> npccka = new ArrayList<>();
    private Hrac hrac;

    public void vypis(String text){
        System.out.println(text);
    }



    public void vygenerujStartHry(){
        vygenerujMistnosti();
        vygenerujNPC();

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
                                vypis("V mistnosti " + mistnosti[i].getNazev() + " nemůže být NPC " + npcTemp.getJmeno() + ", protože tam již je NPC " + mistnosti[i].getNpc().getJmeno() + ".");
                            } else {
                                npcTemp.setPridelenaMistnost(mistnosti[i]);
                                mistnosti[i].setNpc(npcTemp);
                                mistnostReady = true;
                            }
                        } else if (radekMist.toLowerCase().equals(mistnosti[i].getNazev().toLowerCase())) {
                            if (mistnosti[i].getNpc()!=null){
                                vypis("V mistnosti " + mistnosti[i].getNazev() + " nemůže být NPC " + npcTemp.getJmeno() + ", protože tam již je NPC " + mistnosti[i].getNpc().getJmeno() + ".");
                            } else {
                                npcTemp.setPridelenaMistnost(mistnosti[i]);
                                mistnosti[i].setNpc(npcTemp);
                                mistnostReady = true;
                            }
                        }
                    }
                    if (mistnostReady=false){
                        npcTemp.setPridelenaMistnost(null);
                        vypis("Přidělená místnost " + radekMist + " NPC " + npcTemp.getJmeno() + " nenalezena. Přidělená místnost nebyla nastavená.");
                    }
                }
                npccka.add(npcTemp);
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
}
