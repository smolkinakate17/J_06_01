import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringStreamTest {

    @Test
    void newString() {
        assertEquals("", StringStream.newString(""));
        assertEquals("", StringStream.newString("Да; и; но ((4))"));
        assertEquals("ДЕНЬ ДОБРЫЙ", StringStream.newString("Добрый день!"));
        assertEquals("ЗЕЛЕНЫЙ СИНЕ", StringStream.newString("сине-зеленый"));
        assertEquals("БЕЗ КОФЕ МОЛОКОМ САХАРА ЧАШКА", StringStream.newString("Чашка кофе с молоком без сахара."));
        assertEquals("ЕЩЕ МНОГО РАЗ", StringStream.newString("Эх раз, да еще раз, да еще много-много раз!"));
        assertEquals("ADIPISCING ALIQUA ALIQUIP AMET ANIM AUTE CILLUM COMMODO CONSECTETUR CONSEQUAT CULPA CUPIDATAT DESERUNT" +
                        " DOLOR DOLORE DUIS EIUSMOD ELIT ENIM ESSE EST EXCEPTEUR EXERCITATION FUGIAT INCIDIDUNT IPSUM IRURE LABORE " +
                        "LABORIS LABORUM LOREM MAGNA MINIM MOLLIT NISI NON NOSTRUD NULLA OCCAECAT OFFICIA PARIATUR PROIDENT QUI QUIS REPREHENDERIT " +
                        "SED SINT SIT SUNT TEMPOR ULLAMCO VELIT VENIAM VOLUPTATE",
                StringStream.newString("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut" +
                        " labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                        "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
                        "eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                        "mollit anim id est laborum."));
    }
}