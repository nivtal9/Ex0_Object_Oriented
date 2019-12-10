package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
    private ArrayList<Monom> Polynom=null;

    /**
     * Zero (empty polynom)
     */
    public Polynom() {
        Polynom=new ArrayList<Monom>();
    }
    public function initFromString(String str){
          function fun = new Polynom(str);
          return fun;
    }
    /**
     * init a Polynom from a String such as:
     *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
     * @param s: is a string represents a Polynom
     */
    public Polynom(String s) {
        String t="";
        s=s.replaceAll(" ", "");
        if(s.charAt(0)=='+'){
            s=s.substring(1,s.length()-1);
        }
        for (int j = 0; j < s.length(); j++) {
            if(s.charAt(j)!=' '){
                t+=s.charAt(j);
            }
        }s=t;
        int i=0; int lastindex=0;String temp="";
        Polynom = new ArrayList<Monom>();
        try {
            while (i < s.length()) {
                if((s.charAt(i)=='-')&&(i==0)){
                    i++;
                }
                if (s.charAt(i) == '+'||s.charAt(i) =='-'||i==s.length()-1) {
                    if(i==s.length()-1){
                        temp=s.substring(lastindex,i+1);
                    }
                    else{
                        temp= s.substring(lastindex, i);
                    }
                    Monom temp2 = new Monom(temp);
                    Polynom.add(temp2);
                    lastindex = i;
                    i++;
                } else {
                    i++;
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("Not a valid Polynom!");
        }
    }
    @Override
    public double f(double x) {
        double ans=0;
        for (int i = 0; i <Polynom.size() ; i++) {
            Monom monom=Polynom.get(i);
            ans+=monom.f(x);
        }
        // TODO Auto-generated method stub
        return ans;
    }

    @Override
    public void add(Polynom_able p1) {
        // TODO Auto-generated method stub
        Iterator< Monom >ite=p1.iteretor( );
        while(ite.hasNext()){
            Monom t = ite.next();
            Monom temp=new Monom(t);
            add(temp);
        }
    }

    @Override
    public void add(Monom m1) {
        Polynom_able temp=new Polynom();
        for (int i = 0; i < Polynom.size(); i++) {
            if (m1.get_power() == Polynom.get(i).get_power()) {
                Polynom.get(i).add(m1);break;
            }
            if (Polynom.size()-1==i) {
                Polynom.add(m1);
                break;
            }
        }
        if(Polynom.size()==0){
            Polynom.add(m1);
        }
        Org();
    }
    // TODO Auto-generated method stub

    @Override
    public void substract(Polynom_able p1) {
        Polynom_able temp=new Polynom();
        temp=p1.copy();
        temp.multiply(Monom.MINUS1);
        // TODO Auto-generated method stub
/*        Iterator< Monom >ite=temp.iteretor( );
        while(ite.hasNext()){
            ite.next().multipy(Monom.MINUS1);
        }*/
        add(temp);
    }

    @Override
    public void multiply(Polynom_able p1) {
        // TODO Auto-generated method stub
        Iterator<Monom>ite=p1.iteretor();
        Polynom temp=new Polynom() ;

        while(ite.hasNext()) {
            Polynom_able temp_2=this. copy() ;

            Monom m=ite.next();
            temp_2. multiply(m) ;temp. add(temp_2) ;
        }
            Polynom=temp. Polynom ;
    }

    @Override
    public boolean equals(Object p1) {
        if(p1 instanceof Polynom_able) {
            Polynom_able copy2=(Polynom_able) p1;
            Polynom_able copy = this.copy();
            copy.substract(copy2);
            if (!copy.isZero()) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isZero() {
        if(Polynom.size()==0) return true;
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double root(double x0, double x1, double eps) {
        double ans=x0;
        while (eps<=(  x1-x0  )) {
            ans = 0.5*( x1+x0 );
            if (0.0==f( ans )) break;
            else if ( 0 > f(x0) * f(ans) ) x1=ans;
            else x0=ans;
        }
        return ans;
    }
    // TODO Auto-generated method stub

    @Override
    public Polynom_able copy() {
        Polynom_able newP= new Polynom();
        for (int i = 0; i <Polynom.size() ; i++) {
            Monom temp=new Monom(Polynom.get(i));
            newP.add(temp);
        }
        // TODO Auto-generated method stub
        return newP;
    }

    @Override
    public Polynom_able derivative() {
        Iterator<Monom> ite=Polynom.iterator();
        Polynom_able ans=new Polynom();
        while (ite.hasNext()){
            ans.add(new Monom(ite.next().derivative()));
        }
        // TODO Auto-generated method stub
        return ans;
    }
    @Override
    public double area(double x0, double x1, double eps) {
        double ans=0;
        while(0<x1-x0){
            if(0<f(x0)){
                ans=(f(x0)*eps)+ans;
            }
            x0=eps+x0;
        }
        // TODO Auto-generated method stub
        return ans;
    }
    @Override
    public Iterator<Monom> iteretor() {
        Iterator<Monom> ite= this.Polynom.iterator();
        return ite;
    }
    @Override
    public void multiply(Monom m1) {
        Iterator<Monom> ite=Polynom.iterator();
        Monom temp=new Monom(m1);
        while(ite.hasNext()){
            ite.next().multipy(temp);
        }
        // TODO Auto-genthiserated method stub
    }
    private Monom_Comperator mc=new Monom_Comperator();
    public String toString(){
        Polynom.sort(mc);
        Iterator<Monom> ite= Polynom.iterator();String ans="";
        while(ite.hasNext()){
            Monom temp=ite.next();
            if(ans!=""&&temp.get_coefficient()>=0){
                ans+="+";
            }
            ans+=temp.toString();
        }
        return ans;
    }
    public void Org(){
        for (int i = 0; i <Polynom.size() ; i++) {
            if(Polynom.get(i).get_coefficient()==0){
                Polynom.remove(i);
            }
        }
    }
}