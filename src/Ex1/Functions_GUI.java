package Ex1;

import com.google.gson.Gson;

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
    public void Setters(Range x,Range y,int h,int w,int r){

        StdDraw.setCanvasSize(w, h);
        StdDraw.setPenRadius(0.00345);
        StdDraw.setFont();
        StdDraw.setXscale(x.get_min(), x.get_max());
        StdDraw.setYscale(y.get_min(), y.get_max());
        StdDraw.line(x.get_min(), 0, x.get_max(), 0);
        StdDraw.line(0, y.get_min(), 0, y.get_max());
/*        StdDraw.setPenColor(Color.BLACK);
        for (int i = (int)x.get_min(); i <=x.get_max() ; i++) {
            StdDraw.line(i,y.get_min(),i,y.get_max());
        }
        for (int i = (int)y.get_min(); i < y.get_max(); i++) {
            StdDraw.line(x.get_min(),i,x.get_max(),i);
        }
        StdDraw.setPenRadius(0.007);
        StdDraw.line(x.get_min(),0,x.get_max(),0);
        Font f =new Font("TimesNewRoman",Font.BOLD,10);
        StdDraw.setFont(f);
        for (int i = (int)x.get_min(); i <x.get_max() ; i++) {
            StdDraw.text(i-0.05,-0.5,Integer.toString(i));
        }
        StdDraw.line(0,y.get_min(),0,y.get_max());
        for (int i = (int)y.get_min(); i < y.get_max(); i++) {
            StdDraw.text(x[r/2]-0.3,i-0.05,Integer.toString(i));
        }*/
/*        int n = 100;
        double maxY = 2.0, minY = -2.0;
// the function y = sin(4x), sampled at n+1 points
// between x = 0 and x = pi
        double[] arrx = new double[n+1];
        double[] arry = new double[n+1];
        for (int i = 0; i <= n; i++) {
            arrx[i] = Math.PI * i / n;
            arry[i] = Math.sin(4*arrx[i]);
        }
// rescale the coordinate system
        StdDraw.setXscale(0, Math.PI);
        StdDraw.setYscale(minY, maxY);
//////// vertical lines
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.line(arrx[i], minY, arrx[i], maxY);
        }
//////// horizontal lines
        for (double i = minY; i <= maxY; i=i+0.5) {
            StdDraw.line(0, i, Math.PI, i);
        }
//////// x axis
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, arry[n/2], Math.PI, arry[n/2]);
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.text(arrx[i]-0.07, -0.07, Integer.toString(i-n/2));
        }
//////// y axis
        StdDraw.line(arrx[n/2], minY, arrx[n/2], maxY);
        for (double i = minY; i <= maxY; i=i+0.5) {
            StdDraw.text(arrx[n/2]-0.07, i+0.07, Double.toString(i));
        }
// plot the approximation to the function
        for (int i = 0; i < n; i++) {
            StdDraw.line(arrx[i], arry[i], arrx[i+1], arry[i+1]);
        }
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(arrx[n/2], 1);*/
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
        try
        {
            Gson g = new Gson();
            FileReader r = new FileReader(json_file);
            GUI_params p = g.fromJson(r , GUI_params.class);
            drawFunctions(p.Width, p.Height, p.Range_X, p.Range_Y, p.Resolution);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("404 - File Not Found");
        }
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