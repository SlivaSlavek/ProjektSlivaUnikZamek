import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemHry {
    private Mistnost[] mistnosti = new Mistnost[8];
    private Hrac hrac;

    public void vypis(String text){
        System.out.println(text);
    }



    public void vygenerujStartHry(){
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
}
