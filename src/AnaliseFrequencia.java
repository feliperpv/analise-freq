import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnaliseFrequencia {

    public static char frequenciaMinuscula[] = {'a', 'e', 'o', 's', 'r', 'i', 'n', 'd',
        'm', 'u', 't', 'c', 'l', 'p', 'v', 'g',
        'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k',
        'y', 'w'};

    public static void main(String[] args) {
            
        //String textoCifrado = "z z z z z z d d d d d n n n n r r r q q h";
        String textoCifrado = "n n n n n n r r r r r b b b b f f f e e v";
        //System.out.println(textoCifrado);
        
        /*
        BufferedReader buff = 
                new BufferedReader(new InputStreamReader(System.in));
        
        String textoCifrado = "";
        String str = null;
        try{
            while ((str = buff.readLine()) != null) {
                textoCifrado = textoCifrado + str;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
*/
        decrypt(textoCifrado);
        
    }

    public static void decrypt(String originalText) {

        String result = "";
        String text = originalText.toLowerCase();
        int length = text.length();
        List<String> lista = countFrequency(text);
        int total = 0;
        int divisor = 0;

        for (int i = 0; i < length; i++) {

            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {

                for (int j = 0; j < lista.size(); j++) {

                    char chMap = lista.get(j).charAt(0);

                    if (ch == chMap) {
                        
                        int a = Character.getNumericValue(frequenciaMinuscula[j]);
                        int b = Character.getNumericValue(ch);
                        
                        if(a == 10){
                            total = total + (b-a);
                            divisor++;
                        }
                        
                        result += frequenciaMinuscula[j];
                        break;
                    }

                }
            } else {
                result += ch;
            }
        }
        
        System.out.println("Final: " + result);
        System.out.println("Chave: " + Math.round(total/divisor));

    }

    public static List countFrequency(String plainText) {

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char ch;
        int count = 0;

        int length = plainText.length();

        for (int i = 0; i < length; i++) {

            ch = plainText.charAt(i);
            count++;

            Integer countForCharacter = 0;

            if (map.containsKey(ch)) {
                countForCharacter = map.get(ch);
                countForCharacter++;
            } else {
                countForCharacter = 1;
            }

            if (Character.isLetter(ch)) {
                map.put(ch, countForCharacter);
            }
        }

        return sortByValue(map);
    }

    public static List<String> sortByValue(Map map) {
        Map sortedMap = new TreeMap(new ValueComparator(map));
        sortedMap.putAll(map);

        List<String> array = new ArrayList();

        Set set = sortedMap.entrySet();
        Iterator iterator = set.iterator();

        Map.Entry me = null;

        while (iterator.hasNext()) {
            me = (Map.Entry) iterator.next();
            array.add(String.valueOf(me.getKey()));
        }

        return array;
    }

    public static class ValueComparator implements Comparator {

        Map map;

        public ValueComparator(Map map) {
            this.map = map;
        }

        @Override
        public int compare(Object keyA, Object keyB) {

            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);
            int i = valueB.compareTo(valueA);

            if (i == 0) {
                return 1;
            } else {
                return i;
            }

        }
    }

}
