package Ex1Testing;
import Ex1.Monom;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class JUNIT.
 */
public class MonomTest {
    @Test
    void derivative() {
        String[] monoms = {"2x^3", "x^2", "-x", "-3.2x^2", "2"};
        String[] monoms2 = {"6x^2", "2x", "-1", "-6x", "0"};
        for (int i = 0; i < monoms.length; i++) {
            Monom m = new Monom(monoms[i]);
            m.derivative();
            Monom m2 = new Monom(monoms2[i]);
            assertEquals(m, m2);
        }
    }

    @Test
    void f() {
        String[] monoms = {"2x^3", "x^2", "-x", "-3.2x^2", "2"};
        String[] monoms2 = {"0", "0", "0", "0", "2"};
        for (int i = 0; i < monoms.length; i++) {
            Monom m = new Monom(monoms[i]);
            m.f(0);
            Monom m2 = new Monom(monoms2[i]);
            assertEquals(m, m2);
        }
    }

    @Test
    void isZero() {
        String[] monoms = {"2x^3", "x^2", "-x", "-3.2x^2", "2"};
        String[] monoms2 = {"-2x^3", "-x^2", "x", "3.2x^2", "-2"};
        int c = 0;
        for (int i = 0; i < monoms.length; i++) {
            Monom m = new Monom(monoms[i]);
            Monom m2 = new Monom(monoms2[i]);
            m.add(m2);
            if (m.isZero()) {
                c++;
            }
            assertEquals(c, 5);
        }

    }
    @Test
    void add() {
        String[] monoms = {"2x^3", "x^2", "-x", "-3x^2", "-2"};
        String[] monoms2 = {"4x^3", "2x^2", "-2x", "-6x^2", "-4"};
        for (int i = 0; i < monoms.length; i++) {
            Monom m = new Monom(monoms[i]);
            Monom m2 = new Monom(monoms2[i]);
            m.add(m);
            assertEquals(m, m2);
        }
    }
        @Test
        void multipy () {
            String[] monoms = {"2x^3", "x^2", "-x", "-3x^2", "-2"};
            String[] monoms2 = {"4x^6", "x^4", "x^2", "9x^4", "4"};
            for (int i = 0; i < monoms.length; i++) {
                Monom m = new Monom(monoms[i]);
                Monom m2 = new Monom(monoms2[i]);
                m.multipy(m);
                assertEquals(m, m2);
            }
        }

        @Test
        void testEquals () {
            String[] monoms = {"4x^3", "2x^2", "-2x", "-6x^2", "-4"};
            String[] monoms2 = {"4x^3", "2x^2", "-2x", "-6x^2", "-4"};
            int c=0;
            for (int i = 0; i < monoms.length; i++) {
                Monom m = new Monom(monoms[i]);
                Monom m2 = new Monom(monoms2[i]);
                if(m.equals(m2)){
                    c++;
                }
                assertEquals(c, 5);
            }
        }
    }