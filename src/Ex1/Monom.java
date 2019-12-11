
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 * @author Boaz
 *
 */
public class Monom implements function{
    public static final Monom ZERO = new Monom(0,0);
    public static final Monom MINUS1 = new Monom(-1,0);
    public static final double EPSILON = 0.0000001;
    public static final Comparator<Monom> _Comp = new Monom_Comperator();
    public static Comparator<Monom> getComp() {return _Comp;}
    public Monom(double a, int b){
        this.set_coefficient(a);
        this.set_power(b);
    }

    /**
     * This Method builds a Monom From a Given Monos ot
     * @param ot - a Monom that have coefficient and Power
     */
    public Monom(Monom ot) {
        this(ot.get_coefficient(), ot.get_power());
    }

    /**
     * getters
     */
    public double get_coefficient() {
        return this._coefficient;
    }
    public int get_power() {
        return this._power;
    }

    /**
     * given a string, this Method build a Monom from it
     * implements function class
     * @param str - given string that contains Monom
     * @return a new Monom
     */
    public function initFromString(String str){
        function fun = new Monom(str);
        return fun;
    }
    /**
     * this method returns the derivative monom of this.
     * @return the derivative monom of this
     */
    public Monom derivative() {
        if(this.get_power()==0) {return getNewZeroMonom();}
        Monom m1=new Monom(this.get_coefficient()*this.get_power(),this.get_power()-1);
        return m1;
    }

    /**
     * This Method gets an x dot and giving the outcome of placing the x dot in the Monom
     * @param x - double number
     * @return the outcome of placing the x dot in the Monom
     */
    public double f(double x) {
        double ans=0;
        double p = this.get_power();
        ans = this.get_coefficient()*Math.pow(x, p);
        return ans;
    }
    @Override
    /**
     * This Method returns True if Our Monom Equals to Monom m by subtracting m from our Monom and check if the Monom isZero.
     */
    public boolean equals(Object m){
        if(m instanceof Monom){
            Monom m2=(Monom) m;
            Monom m3=this;
            if(m3.isZero()&&m2.isZero()) return true;
            m3.multipy(MINUS1);
            m2.add(m3);
            return m2.isZero();
        }
        return false;
    }
    @Override
    /**
     * This Method returns a Copy of Our Monom in the formation of function
     * This Method implements function class
     */
    public function copy() {
        Monom m=new Monom(this);
        return m;
    }

    /**
     * This Method Check if given Monom is zero.
     * @return True if our Monom is zero, false if it doesnt
     */
    public boolean isZero() {return this.get_coefficient() == 0;}
    // ***************** add your code below **********************

    /**
     * This Method gets a String and building from it a Monom
     * @param s - a String that Contains a Monom
     */
    public Monom(String s) {
        try {
            int i=-1;boolean minus=false;
            while(i!=(s.length()-1)) {
                i++;
                if (s.charAt(i)=='+'){
                    s=s.substring(i+1,s.length());
                }
                if(s.charAt(i)=='-'){
                    s=s.substring(i+1,s.length());
                    minus=true;
                }
                if(s.charAt(i)=='x'){
                    break;
                }
            }
            if(s.charAt(i)=='x') {
                if(i==0) {
                    this.set_coefficient(1);
                    if(minus) this.set_coefficient(-1);
                }
                else {
                    Double d = Double.parseDouble(s.substring(0,i));
                    this.set_coefficient(d);
                    if(minus) this.set_coefficient(-1*d);
                }
                if(i==s.length()-1) {
                    this.set_power(1);
                }
                else {
                    String temp=s.substring(i+2,s.length());
                    int d = Integer.parseInt(temp);
                    this.set_power(d);
                }
            }
            else {
                Double d = Double.parseDouble(s);
                this.set_coefficient(d);
                this.set_power(0);
                if(minus) this.set_coefficient(-1*d);
            }
        }
        catch(Exception e) {
            throw new RuntimeException("Not a valid Monom!");
        }
    }

    /**
     * This Method adds to our Monom a Given Monom m.
     * it will be given us a msg if Monom m is not in the same power of our Monom
     * @param m - a Given Monom
     */
    public void add(Monom m) {
        if(m.get_power()==this.get_power()) {
            this.set_coefficient(this.get_coefficient()+m.get_coefficient());
        }
        else {
            System.out.println("Not same power Monom's!");
        }
    }

    /**
     * This Method Multiply our Monom by a given Monom d
     * @param d - Given Monom
     */
    public void multipy(Monom d) {
        this.set_coefficient(this.get_coefficient()*d.get_coefficient());
        this.set_power(this.get_power()+d.get_power());
    }

    /**
     * a Simple toString Method
     * @return String that describe our Monom
     */
    public String toString() {
        String ans="";
        if(this.get_power()==0) {
            ans = this.get_coefficient()+"";
        }
        else {
            ans=this.get_coefficient()+"x^"+this.get_power();
        }
        return ans;
    }
    // you may (always) add other methods.

    /**
     * Private Methods and Data
     *
     */

    private void set_coefficient(double a){
        this._coefficient = a;
    }
    private void set_power(int p) {
        if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
        this._power = p;
    }
    private static Monom getNewZeroMonom() {return new Monom(ZERO);}
    private double _coefficient;
    private int _power;
}
