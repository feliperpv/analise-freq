/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisefrequencia;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author Felipe
 */
public class ValueComparator implements Comparator {

    Map map;

    public ValueComparator(Map map) {
        this.map = map;
    }

    @Override
    public int compare(Object keyA, Object keyB) {
       
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        int i = valueB.compareTo(valueA);
        
        if(i == 0){
            return 1;
        } else {
            return i;
        }
        
    }

}
