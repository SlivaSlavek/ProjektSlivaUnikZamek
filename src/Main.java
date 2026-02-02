import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //Testování:
        SystemHry systemHry=new SystemHry();
        systemHry.vygenerujStartHry();
        for (int i=0;i<8;i++){
            systemHry.vypis(i+"");
            systemHry.vypis(systemHry.getMistnosti()[i].getNazev());
            systemHry.vypis(systemHry.getMistnosti()[i].getPopis());
        }

        System.out.println();
        System.out.println("aaa");
        System.out.println();
        for (int i=0;i<systemHry.getNpccka().size();i++){
            System.out.println(systemHry.getNpccka().get(i));
        }
        System.out.println("Konec zkoušky z NPC generace..");
        for (int i=0;i<systemHry.getMoznePredmety().size();i++){
            System.out.println(systemHry.getMoznePredmety().get(i));
        }
    }
}