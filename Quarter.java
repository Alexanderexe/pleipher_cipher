import java.util.HashSet;

/**
 * Created by ЦифроваяКомпания on 21.09.2016.
 */
public class Quarter {
    Character first,second,third,fourth;
    
    public static Quarter quartetFromTriplet(Triplet triplet,Bigram[][] bigrams){


        Character first = triplet.first;
        Character second = triplet.third;

        String s =  new String(""+first+second);


        Bigram bigram = Cipher.findBigram(bigrams,s);

        if (bigram != null){

            Quarter quarter = new Quarter();
            quarter.first = triplet.first;
            quarter.second = triplet.second;
            quarter.third = triplet.third;
            quarter.fourth = bigram.second;
            return quarter;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quarter quarter = (Quarter) o;

        if (!first.equals(quarter.first)) return false;
        if (!second.equals(quarter.second)) return false;
        if (!third.equals(quarter.third)) return false;
        return fourth.equals(quarter.fourth);

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        result = 31 * result + third.hashCode();
        result = 31 * result + fourth.hashCode();
        return result;
    }

    public static HashSet<Quarter> fromTripletsToQuartets(Triplet[] triplets, Bigram[][] bigrams){

        HashSet<Quarter> quartets = new HashSet<>();

        for (int i = 0; i < triplets.length; i++) {
            Quarter quartet = quartetFromTriplet(triplets[i],bigrams);
            if (quartet != null){
                quartets.add(quartet);
            }
        }
        return quartets;
    }
}
