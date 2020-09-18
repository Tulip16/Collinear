/* *****************************************************************************
 *  Name: Tulip Pandey
 *  Date: 11th April 2020
 *  Description: Assignment 3 - part3
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

    private int number=0;
    private LineSegment[] lines;

    private void resize(int n){
        LineSegment[] copy = new LineSegment[n];
        for(int i=0;i<lines.length;i++){
            copy[i] = lines[i];
        }
        lines = copy;

    }

    private Point min(Point a1,Point a2){
        Point res = a1;
        if(a2.compareTo(res)<0) res = a2;
        return res;
    }

    private Point max(Point a1,Point a2){
        Point res = a1;
        if(a2.compareTo(res)>=0) res = a2;
        return res;
    }



    public FastCollinearPoints(Point[] points) {
        if(points==null) throw new IllegalArgumentException();
        lines  = new LineSegment[0];
        Point[] b = new Point[points.length];
        for(int z=0; z<points.length;z++){
            if(points[z]==null) throw new IllegalArgumentException();
            b[z] = points[z];
        }

        for(int i=0;i<points.length;i++){
            Arrays.sort(points, b[i].slopeOrder());
            int k;
            for(int j = 0; j<points.length; j+=k){
                Point p = points[j];
                Point q = points[j];

                k = 1;
                if(j+k>=points.length) break;

                while(b[i].slopeTo(points[j+k])==b[i].slopeTo(points[j])){
                    if(points[j].slopeTo(points[j+k])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
                    p = min(p,points[j+k]);
                    q = max(q,points[j+k]);

                    k++;
                    if(j+k>=points.length) break;
                }

                if(k>2){
                    p = min(p,b[i]);
                    q = max(q,b[i]);
                    int cd = 0;
                    for(int w=0;w<i;w++){
                        if((p.slopeTo(b[w])==q.slopeTo(b[w]))||(p.slopeTo(b[w])==Double.NEGATIVE_INFINITY)||(q.slopeTo(b[w])==Double.NEGATIVE_INFINITY)) cd=7;
                    }
                    if(cd!=7) {
                        resize(number+1);
                        lines[number] = new LineSegment(p, q);
                        number++;
                    }
                }

            }

        }
    }    // finds all line segments containing 4 or more points
    public int numberOfSegments(){
        return number;
    }       // the number of line segments
    public LineSegment[] segments(){
        //Arrays.sort(lines);
        /*LineSegment[] copy = new LineSegment[lines.length/4];
        for(int i=0;i<copy.length;i++){
            copy[i] = lines[4*i];
        }*/
        return lines;
    }              // the line segments




    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("input40.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();



    }
}
