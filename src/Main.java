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
    }
}