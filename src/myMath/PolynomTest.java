package myMath;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * This class is for testing "polynom" methods JUNIT STYLE
 */

public class PolynomTest {
    public static boolean compDouble(double a , double b) {
        double diff = a-b;
        if (a == b || (diff < 0.01 && diff > -0.01)) return true;
        return false; //niv tal
}
    @Test
    void f() {
        String[] polyonoms = {"2x^3+x^2"};
        String[] polyonoms2 = {"6x^2-2x"};
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            Polynom p2 = new Polynom(polyonoms2[i]);
            p.f(0);
            p2.f(0);
            assertEquals(p, 0);
            assertEquals(p2, 0);
        }
    }

    @Test
    void add() {
        String[] polyonoms = {"2x^3+x^2"};
        String[] polyonoms2 = {"4x^3+2x^2"};
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            Polynom p2 = new Polynom(polyonoms2[i]);
            p.add(p);
            assertEquals(p, p2);
        }
    }

    @Test
    void substract() {
        String[] polyonoms = {"2x^3+x^2"};
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            p.substract(p);
            assertEquals(p, 0);
        }
    }

    @Test
    void multiply() {
        String[] polyonoms = {"2x^3+x^2"};
        String[] polyonoms2 = {"4x^6+x^4"};
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            Polynom p2 = new Polynom(polyonoms2[i]);
            p.multiply(p);
            assertEquals(p, p2);
        }
    }

    @Test
    void testEquals() {
        String[] polyonoms = {"2x^3+x^2"};
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            assertEquals(p, new Polynom("2x^3+x^2"));
        }
    }

    @Test
    void isZero() {
        String[] polyonoms = {"2x^3+x^2"};
        int c=0;
        for (int i = 0; i < polyonoms.length; i++) {
            Polynom p = new Polynom(polyonoms[i]);
            p.substract(p);
            if (p.isZero()) {
                c++;
            }
            assertEquals(c, 1);
        }
    }

    @Test
    void root() {
        String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
                {"x","5x","0","-5"},
                {"4x^6", "-5x^5", "1"}};
        int c=0;
        double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}};
        for (int i = 0; i < polynoms.length; i++) {
            Polynom p1 = new Polynom();
            for (int j = 0; j < polynoms[i].length; j++) {
                Monom temp = new Monom(polynoms[i][j]);
                p1.add(temp);
            }
            if (!compDouble(p1.root(0, 1, 0.0001), res[i][1])) {
                c++;
            }
        }
        assertEquals(c,0);
    }

    @Test
    void copy() {
        int c=0;
        Polynom_able p1 = new Polynom("-4.7x^2-1.0x+6.0");
        Polynom_able p2 = p1.copy();
        if ( !p1.equals(p2)) {
            c++;
        }
        p2.add(new Monom("x^2"));
        if ( p1.equals(p2)) {
            c++;
        }
        assertEquals(c,0);
    }

    @Test
    void derivative() {
    }

    @Test
    void area() {
        String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
                {"x","5x","0","-5"},
                {"4x^6", "-5x^5", "1"}};
        int c=0;
        double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}};
        for (int i = 0; i < polynoms.length; i++) {
            Polynom p1 = new Polynom();
            for (int j = 0; j < polynoms[i].length; j++) {
                Monom temp = new Monom(polynoms[i][j]);
                p1.add(temp);
            }
            if (!compDouble(p1.area(-1, 0, 0.0001), res[i][0]) ) {
                c++;
            }
        }
        assertEquals(c,0);
    }
}

