/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisefrequencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Felipe
 */
public class AnaliseFrequencia {

    public static char frequenciaMaiuscula[] = {'A', 'E', 'O', 'S', 'R', 'I', 'N', 'D',
        'M', 'U', 'T', 'C', 'L', 'P', 'V', 'G',
        'H', 'Q', 'B', 'F', 'Z', 'J', 'X', 'K',
        'Y', 'W'};

    public static char frequenciaMinuscula[] = {'a', 'e', 'o', 's', 'r', 'i', 'n', 'd',
        'm', 'u', 't', 'c', 'l', 'p', 'v', 'g',
        'h', 'q', 'b', 'f', 'z', 'j', 'x', 'k',
        'y', 'w'};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String texto = "Um texto cuja modalidade se define pela natureza argumentativa representa, sobretudo, aquele texto em que se atesta a capacidade de o emissor discorrer, defender seu ponto de vista acerca deste ou daquele assunto.";
        System.out.println(texto);

        String textoCifrado = "Dv cngcx ldsj vxmjurmjmn bn mnorwn ynuj wjcdanij japdvnwcjcrej anyanbnwcj, bxkancdmx, jzdnun cngcx nv zdn bn jcnbcj j ljyjlrmjmn mn x nvrbbxa mrblxaana, mnonwmna bnd yxwcx mn erbcj jlnalj mnbcn xd mjzdnun jbbdwcx.";
        System.out.println(textoCifrado);

        String textoDecifrado = decrypt(textoCifrado);
        System.out.println(textoDecifrado);
    }

    public static String decrypt(String originalText) {

        String result = "";
        String text = originalText.toLowerCase();
        int length = text.length();

        List<String> lista = countFrequency(text);

        for (int i = 0; i < length; i++) {

            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                
                for (int j = 0; j < lista.size(); j++) {

                    char chMap = lista.get(j).charAt(0); 

                    if (ch == chMap) {
                        result += frequenciaMinuscula[j];
                        break;
                    }
                    
                }
            } else {
                result += ch;
            }
        }

        return result;

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

}
