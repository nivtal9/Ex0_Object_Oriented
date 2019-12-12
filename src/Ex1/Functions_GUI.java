package Ex1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is for presenting Complex Functions/Functions on a GUI window and can be saved (and load) to file.
 */
public class Functions_GUI implements functions {
    ArrayList<function> func=new ArrayList<>();
    Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};
    @Override
    /**
     * further description are in functions class
     */
    public void initFromFile(String file) throws IOException {
        String l = "";
        FileReader fr=new FileReader(file);
        try {
            BufferedReader br = new BufferedReader(fr);
            while ((l = br.readLine( )) != null) {
                String str = l;
                function f = new ComplexFunction().initFromString(str);

                add(f);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();

            System.out.println("could not read file");
        }

    }

    /**
     * further description are in functions class
     */
    @Override
    public void saveToFile(String file) throws IOException {
        try {
            File f=new File(file );
            StringBuilder sb = new  StringBuilder();
            PrintWriter pw = new  PrintWriter(f);
            for (int i=0;i<this.size( );i++) {
                String string=func.get( i).toString();
                sb.append(string);sb.append("\n");
            }
            pw.write(sb.toString( ));

            pw.close( );
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not save file");
        }
    }
    public void Setters(Range rx,Range ry,int height,int width,int resolution) {
        StdDraw.setCanvasSize(width, height);
        StdDraw.setPenRadius(0.0045);
        StdDraw.setFont();
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());
        for (int j = (int)rx.get_min(); j < rx.get_max(); j=j+1) {
            if(0!=j) {

                StdDraw.line(j, -0.2, j, 0.2);

                StdDraw.text(j, -0.5, j+"");
            }
        }
        for (int i = (int)ry.get_min(); i < ry.get_max(); i=i+1) {
            if(0!=i) {

                StdDraw.text(0.4, i, i+"");

                StdDraw.line(-0.2, i, 0.2, i);
            }
        }
    }
    /**
     * further description are in functions class
     */
    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        Setters(rx,ry,height,width,resolution);
        for (int i=0; i <this.size() ;i++) {
            StdDraw.setPenColor(Colors[i % Colors.length]);
            for(double x = rx.get_min(); x < rx.get_max(); x+=( (rx.get_max() - rx.get_min() ) /resolution) ) {
                StdDraw.line(x, func.get(i).f(x), x+( (rx.get_max() - rx.get_min() ) /resolution)
                        , func.get(i).f(x+( (rx.get_max() - rx.get_min( ) ) /resolution) ));
            }
        }
    }
    /**
     * further description are in functions class
     */
    @Override
    public void drawFunctions(String json_file) {
        Gson gs = new Gson();
        try {
            FileReader fr=new FileReader(json_file);
            BufferedReader br = new BufferedReader(fr);
            Json_Helper(br,gs);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot process Json File");
        }

    }
    private void Json_Helper(BufferedReader br,Gson gs){
        JsonObject JO = gs.fromJson(br, JsonObject.class);
        int width = JO.get("Width").getAsInt();
        int r = JO.get("Resolution").getAsInt();
        int height = JO.get("Height").getAsInt();
        JsonArray RangeParameters = JO.get("Range_X").getAsJsonArray();
        int rx_left=RangeParameters.get(0).getAsInt(); int rx_right=RangeParameters.get(1).getAsInt();
        Range rx = new Range(rx_left,rx_right);
        RangeParameters = JO.get("Range_Y").getAsJsonArray();
        int ry_left=RangeParameters.get(0).getAsInt(); int ry_right=RangeParameters.get(1).getAsInt();
        Range ry = new Range(ry_left,ry_right);

        drawFunctions(width, height, rx, ry, r);
    }

    /**
     * all below Method are simple Method that their name describe the mention of the Method
     * all of this Methods are implements of class Collection.
     *
     */
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