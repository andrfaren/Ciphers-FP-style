package fpstyle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void encryptShift() {
        assertEquals("Ifmmp xpsme", Util.encryptShift("Hello world", 1));
        assertEquals("Boesfx", Util.encryptShift("Andrew", 1));
        assertEquals("Efhp", Util.encryptShift("Zack", 5));
        assertEquals("Bjqhtrj yt mdujwxpnqq!", Util.encryptShift("Welcome to hyperskill!", 5));
    }

    @Test
    void decryptShift() {
        assertEquals("Hello world", Util.decryptShift("Ifmmp xpsme", 1));
        assertEquals("Andrew", Util.decryptShift("Boesfx", 1));
        assertEquals("Zack", Util.decryptShift("Efhp", 5));
        assertEquals("Welcome to hyperskill!", Util.decryptShift("Bjqhtrj yt mdujwxpnqq!", 5));
    }

    @Test
    void decryptUnicode() {
        assertEquals("Hello world", Util.decryptUnicode("Ifmmp!xpsme", 1));
        assertEquals("Andrew", Util.decryptUnicode("Boesfx", 1));
        assertEquals("@ack", Util.decryptUnicode("Efhp", 5));
        assertEquals("Welcome to hyperskill!", Util.decryptUnicode("\\jqhtrj%yt%m~ujwxpnqq&", 5));
    }

    @Test
    void encryptUnicode() {
        assertEquals("Ifmmp!xpsme", Util.encryptUnicode("Hello world", 1));
        assertEquals("Boesfx", Util.encryptUnicode("Andrew", 1));
        assertEquals("_fhp", Util.encryptUnicode("Zack", 5));
        assertEquals("\\jqhtrj%yt%m~ujwxpnqq&", Util.encryptUnicode("Welcome to hyperskill!", 5));
    }
}