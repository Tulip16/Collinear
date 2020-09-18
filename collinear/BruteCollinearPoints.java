/* *****************************************************************************
 *  Name: Tulip Pandey
 *  Date: 11th April 2020
 *  Description: Assignment 3 - part 2
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Point min(Point a1,Point a2,Point a3,Point a4){
        Point res = a1;
        if(a2.compareTo(res)<0) res = a2;
        if(a3.compareTo(res)<0) res = a3;
        if(a4.compareTo(res)<0) res = a4;
        return res;
    }

    private Point max(Point a1,Point a2,Point a3,Point a4){
        Point res = a1;
        if(a2.compareTo(res)>0) res = a2;
        if(a3.compareTo(res)>0) res = a3;
        if(a4.compareTo(res)>0) res = a4;
        return res;
    }

    private void resize(int n){
        LineSegment[] copy = new LineSegment[n];
        for(int i=0;i<lines.length;i++){
            copy[i] = lines[i];
        }
        lines = copy;

    }

    private int number=0;
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) {
        if(points==null) throw new IllegalArgumentException();
        lines = new LineSegment[0];
        for(int p=0;p<points.length;p++){
            if(points[p]==null) throw new IllegalArgumentException();
            for(int q=p+1;q<points.length;q++){
                if(points[q]==null) throw new IllegalArgumentException();
                if(points[q].compareTo((points[p]))==0) throw new IllegalArgumentException();
                for(int r=q+1;r<points.length;r++){
                    if(points[r]==null) throw new IllegalArgumentException();
                    if(points[r].compareTo((points[q]))==0) throw new IllegalArgumentException();
                    if(points[r].compareTo((points[p]))==0) throw new IllegalArgumentException();
                    if(!(points[p].slopeTo(points[q])==points[q].slopeTo(points[r]))) continue;
                    for(int s=r+1;s<points.length;s++){
                        if(points[s]==null) throw new IllegalArgumentException();
                        if(points[s].compareTo((points[p]))==0) throw new IllegalArgumentException();
                        if(points[s].compareTo((points[q]))==0) throw new IllegalArgumentException();
                        if(points[s].compareTo((points[r]))==0) throw new IllegalArgumentException();
                        if(points[r].slopeTo(points[s])==points[q].slopeTo(points[r])){
                            resize(number+1);
                            lines[number] = new LineSegment(min(points[p],points[q],points[r],points[s]),max(points[p],points[q],points[r],points[s]));
                            number++;
                        }
                    }
                }
            }
        }

    }   // finds all line segments containing 4 points
    public int numberOfSegments() {
        return number;
    }      // the number of line segments
    public LineSegment[] segments()  {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();


    }
}
