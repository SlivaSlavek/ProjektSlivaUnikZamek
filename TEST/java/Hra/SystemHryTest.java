package Hra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SystemHryTest {

    private SystemHry hra;

    @BeforeEach
    void setUp() {
        hra = new SystemHry();
    }

    @Test
    void vypis_vypise() {
        assertDoesNotThrow(() -> hra.vypis("Test zprava"));
    }

    @Test
    void npcRotace_nevyhodiVyjimku() {
        hra.vygenerujStartHry();
        assertDoesNotThrow(() -> hra.npcRotace());
    }


    @Test
    void konec_lzeNastavit() {
        hra.setKonec(true);
        assertTrue(hra.isKonec());
    }

    @Test
    void ulozeniHry_vytvoriSoubor() {
        hra.vygenerujStartHry();
        hra.ulozeniHry();

        File f = new File("ulozeni.txt");
        assertTrue(f.exists());
    }
}