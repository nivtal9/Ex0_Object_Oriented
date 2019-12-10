package Ex1;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;


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
        if (!file.isEmpty()) {
            String str = "";
            PrintWriter w=null;
            w = new PrintWriter(file);
            for (int j=0; j< size(); j++) {
                str = this.func.get(j).toString();
                w.println(str);
            }
            w.close();
        }
        else{
            throw new IOException("File is Empty");
        }
    }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
    }

    @Override
    public void drawFunctions(String json_file) {
    }

    @Override
    public int size() {
        return func.size();
    }

    @Override
    public boolean isEmpty() {
        return func.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return func.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return func.iterator();
    }

    @Override
    public Object[] toArray() {
        return func.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return func.toArray(a);
    }

    @Override
    public boolean add(function function) {
        return func.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return func.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return func.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends function> c) {
        return func.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return func.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return func.retainAll(c);
    }

    @Override
    public void clear() {
        this.clear();
    }
}