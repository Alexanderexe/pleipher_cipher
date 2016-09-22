import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class Main {

    public static void main(String[] args) {


        String a = "For a long time it puzzled me how something so expensive so leading edge could be so useless And then it occurred to me that a computer is a stupid machine with the ability to do incredibly smart things while computer programmers are smart people with the ability to do incredibly stupid things They are in short a perfect match";
        Bigram[] s = ClearText.TextToBigrams(a);
        Key k = new Key();
        String aa = Cipher.openedBigramsToCiphered(s,k);


        Bigram[][] bigrams = Key.openAndCipheredBigrams(a,aa);

        BigramPair[] pairs = Key.makePair(bigrams);

        HashSet<BigramPair> upairs = Key.analysePairs(pairs);



        HashSet<BigramPair> aaa = Key.analyseUnusualPairs(upairs);

        HashSet<Quarter> quarters = Quarter.fromTripletsToQuartets(  Triplet.fromPairsToTriplets(upairs),bigrams);

        HashSet<Quintet> quintets = Quintet.fromQuartetsToQuintets(quarters,bigrams);

        HashSet<BigramPair> edges = Quintet.edgesBigrams(upairs);

        HashSet<Cross> croses = Key.crossQuntets(quintets);

        Iterator<Cross> iter = croses.iterator();

        Cross cross =  iter.next();

        Cross retCross = Key.turnCrossQuintet(cross,0,cross.both);

        Character[][] kkey = Key.placeCross(retCross,3,2);














    }



}
