package Ex1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.IntFunction;

public class Functions_GUI implements functions {
    ArrayList<function> func=new ArrayList<>();
    @Override
    public void initFromFile(String file) throws IOException {
        String line="";
        try{
            Scanner sc=new Scanner(new File(file));
            while(sc.hasNextLine()){
                line=sc.nextLine();
                Monom m=new Monom(Monom.ZERO);
                ComplexFunction co=new ComplexFunction(m);
                func.add(co.initFromString(line));
            }
        }
        catch (Exception i){
            i.printStackTrace();
            throw new IOException("Read file has been corrupted");
        }
    }

    @Override
    public void saveToFile(String file) throws IOException {

    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

    }

    @Override
    public void drawFunctions(String json_file) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<function> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    @Override
    public boolean add(function function) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}