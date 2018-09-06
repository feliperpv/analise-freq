import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnaliseFrequencia {

    public static char frequenciaMaiuscula[] = {'A', 'E', 'O', 'S', 'R', 'I', 'N', 'D',
        'M', 'U', 'T', 'C', 'L', 'P', 'V', 'G',
        'H', 'Q', 'B', 'F', 'Z', 'J', 'X', 'K',
        'Y', 'W'};

    public static char frequenciaMinuscula[] = {'a', 'e', 'o', 's', 'r', 'i', 'n', 'd',
        'm', 'u', 't', 'c', 'l', 'p', 'v', 'g',
        'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k',
        'y', 'w'};

    public static void main(String[] args) {

        String texto = "A a a a a a a a a a a a aa a a a a a a a a a a a a a a a a a a a a a a a a a a a a E e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e O o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s R r r r r r r r r r r r r r r r r r r r r r r r r r r r r r I i i i i i i i i i i i i i i i i i i i i i i i i i i  i i i i i i i i i i i i i";
        //String texto = "A a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a E e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e O o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s R r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r I i i i i i i i i i i i i i i i i i i i i i i i i i i  i i i i i i i i i i i i i";
        System.out.println(texto);

        String textoCifrado = "F f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f f J j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j j T t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t t X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x X x x W w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w N n n n n n n n n n n n n n n n n n n n n n n n n n n  n n n n n n n n n n n n n";
        System.out.println(textoCifrado);

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
                        total = total + (b - a);
                        divisor++;
                        
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
