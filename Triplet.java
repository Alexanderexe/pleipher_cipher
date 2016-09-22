import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ЦифроваяКомпания on 21.09.2016.
 */
public class Triplet {

    Character first, second, third;

    Triplet(BigramPair wonderPair) {
        if (wonderPair.open.first == wonderPair.cipher.second) {
            first = wonderPair.open.second;
            second = wonderPair.open.first;
            third = wonderPair.cipher.first;
        }
        if (wonderPair.open.second == wonderPair.cipher.first) {
            first = wonderPair.open.first;
            second = wonderPair.open.second;
            third = wonderPair.cipher.second;
        }
    }

    public static Triplet[] fromPairsToTriplets(HashSet<BigramPair> wonderPairs) {
        Triplet[] triplets = new Triplet[wonderPairs.size()];

        Iterator<BigramPair> iterator = wonderPairs.iterator();

        for (int i = 0; i < wonderPairs.size(); i++) {
            triplets[i] = new Triplet(iterator.next());
        }
        return triplets;
    }


}
