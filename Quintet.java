import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ЦифроваяКомпания on 22.09.2016.
 */
public class Quintet {

    Character first, second, third, fourth, fiveth;


    Quintet() {
    }

    Quintet(String str) {
        first = str.charAt(0);
        second = str.charAt(1);
        third = str.charAt(2);
        fourth = str.charAt(3);
        fiveth = str.charAt(4);
    }

    public static Quintet fromQuartettoQuintet(Quarter quarter, Bigram[][] bigrams) {

        Character first = quarter.second;
        Character second = quarter.fourth;

        String bigramtxt = "" + first + second;

        Quintet quintet = new Quintet();
        Bigram bigram = Cipher.findBigram(bigrams, bigramtxt);

        if (bigram != null) {
            quintet.first = quarter.first;
            quintet.second = quarter.second;
            quintet.third = quarter.third;
            quintet.fourth = quarter.fourth;
            quintet.fiveth = bigram.second;
            return quintet;
        }
        return null;
    }


    public static HashSet<Quintet> fromQuartetsToQuintets(HashSet<Quarter> quarters, Bigram[][] bigrams) {

        HashSet<Quintet> quintets = new HashSet<>();

        Iterator<Quarter> iterator = quarters.iterator();

        for (int i = 0; i < quarters.size(); i++) {
            Quintet quintet = fromQuartettoQuintet(iterator.next(), bigrams);
            if (quintet != null) {
                quintets.add(quintet);
            }
        }
        return quintets;
    }

    public static HashSet<BigramPair> edgesBigrams(HashSet<BigramPair> unusualPairs) {

        HashSet<BigramPair> edgesBigrams = new HashSet<>();

        //Iterator<BigramPair> iterator = unusualPairs.iterator();

        for (BigramPair pair : unusualPairs) {


            if (pair.getOpen().second == pair.getCipher().first) {
                edgesBigrams.add(pair);
            }

        }
        return edgesBigrams;
    }

    public static String turnString(String string, int pos, Character ch) {
        int currentpos = string.indexOf(ch);

        char[] chatac = string.toCharArray();


        for (int i = 0; ; i++) {
            if ((chatac[pos] == ch)) {
                StringBuilder stb = new StringBuilder();
                stb.append(chatac[0]);
                stb.append(chatac[1]);
                stb.append(chatac[2]);
                stb.append(chatac[3]);
                stb.append(chatac[4]);

                return stb.toString();
            }
            char bubble = chatac[0];
            chatac[0] = chatac[1];
            chatac[1] = chatac[2];
            chatac[2] = chatac[3];
            chatac[3] = chatac[4];
            chatac[4] = bubble;


        }
    }

    public boolean isHave(Character ch) {
        if (first == ch || second == ch || third == ch || fourth == ch || fiveth == ch) {
            return true;
        } else return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quintet quintet = (Quintet) o;

        if (!first.equals(quintet.first)) return false;
        if (!second.equals(quintet.second)) return false;
        if (!third.equals(quintet.third)) return false;
        if (!fourth.equals(quintet.fourth)) return false;
        return fiveth.equals(quintet.fiveth);

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        result = 31 * result + fourth.hashCode();
        result = 31 * result + fiveth.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "" + first + second + third + fourth + fiveth
                ;
    }

    //public static Quintet determineEdgesOfQuientet(){


}
