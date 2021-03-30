
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public ArrayList<QuakeEntry> onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for (int i = 0 ; i < quakeData.size() - numSorted -1 ; i ++){
            if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()){
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qii = quakeData.get(i + 1);
                quakeData.set(i,qii);
                quakeData.set(i + 1,qi);
            }
        }
        return quakeData;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }
        
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
       ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
       for (int i=0; i< in.size()-1; i++) {
            result = onePassBubbleSort(in, i);
            in = result;
            in = onePassBubbleSort(in, 0);
            System.out.println("Printing Quakes after pass " + i);
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
                } 
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean x = true;
        for(int i=0; i< quakes.size()-1; i++){
            if(quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()){
                x = false;
            }
        }
        return x;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
       ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
       System.out.println("Before sorting");
       for (QuakeEntry qe: in) { 
           System.out.println(qe);
           } 
       for (int i=0; i< in.size()-1; i++) {
            result = onePassBubbleSort(in, i);
            in = result;
            System.out.println("Printing Quakes after pass " + (i+1));
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
                } 
            if(checkInSortedOrder(in)){break;}
        }
    }
    
    public void  sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       System.out.println("Before sorting");
        for (QuakeEntry qe: in) { 
           System.out.println(qe);
           } 
           
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            System.out.println("Printing Quakes after pass " + (i+1));
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
                } 
            if(checkInSortedOrder(in)){break;}
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println( qe);
         } 
        
    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
