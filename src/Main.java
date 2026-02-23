import Hra.SystemHry;

public class Main {
    public static void main(String[] args) {
        SystemHry systemHry=new SystemHry();
        systemHry.hrani();

        //Testování:
        //SystemHry systemHry=new SystemHry();
        //systemHry.vygenerujStartHry();
        /*
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
        System.out.println("Konec zkoušky z Hra.NPC generace..");
        for (int i=0;i<systemHry.getMoznePredmety().size();i++){
            System.out.println(systemHry.getMoznePredmety().get(i));
        }
        */
        System.out.println("testinginging");
        //systemHry.getHrac().getInv()[0]=systemHry.getMoznePredmety().get(1);
        systemHry.getHrac().getInv()[1]=systemHry.getMoznePredmety().get(0);
        systemHry.getHrac().setPoloha(systemHry.getMistnosti()[7]);
        systemHry.setDalsiUkol("Nyní je potřeba přinést " + systemHry.getMoznePredmety().get(0) + " a " + systemHry.getMoznePredmety().get(1) + " pro " + systemHry.getMistnosti()[7].getNpc().getJmeno() + ".");
        systemHry.vypis(systemHry.getDalsiUkol());
        systemHry.provedPrikaz();
        systemHry.provedPrikaz();
        systemHry.provedPrikaz();
        systemHry.provedPrikaz();
        systemHry.provedPrikaz();
    }
}