import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class Main {

    public static void main(String[] args) {


        try {
            Scanner sc = new Scanner(new File("C:"+File.separator+"1.txt"));



        String a = sc.nextLine();
        Bigram[] s = ClearText.TextToBigrams(a);
        Key k = new Key();
        String aa = Cipher.openedBigramsToCiphered(s, k);


        Bigram[][] bigrams = Key.openAndCipheredBigrams(a, aa);// разбили по открытому и шифру

        BigramPair[] pairs = Key.makePair(bigrams); // create pairs

        HashSet<BigramPair> upairs = Key.analysePairs(pairs);// find lineBigrams bigrams


        HashSet<BigramPair> aaa = Key.analyseUnusualPairs(upairs); // analyse lineBigrams to create Lines

        HashSet<Quarter> quarters = Quarter.fromTripletsToQuartets(Triplet.fromPairsToTriplets(upairs), bigrams);

        HashSet<Quintet> quintets = Quintet.fromQuartetsToQuintets(quarters, bigrams);

        HashSet<BigramPair> edges = Quintet.edgesBigrams(upairs);

        HashSet<Cross> croses = Key.crossQuntets(quintets);

        Iterator<Cross> iter = croses.iterator();

        Cross cross = iter.next();

        Cross retCross = Key.turnCrossQuintet(cross, 0, cross.both);

            MarkOfResult[] marks = new MarkOfResult[25];
            int aaafs = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    Character[][] key1 = Key.placeCross(retCross, i, j);

                    Character[][] key2 = makekeyiter(key1,bigrams,cross);
                    Character[][] key3 = makekeyiter(key2,bigrams,cross);
                    Character[][] key4 = makekeyiter(key3,bigrams,cross);

                    int aaaaa = markOftry(key4);

                    MarkOfResult m = new MarkOfResult();
                    m.mark = aaaaa;
                    m.key = key1;
                    marks[aaafs] = m;
                    aaafs++;
                }
            }

            MarkOfResult nnn = MarkOfResult.comparemarks(marks,2);




            printdoublemassive(nnn.key);










        }
        catch (FileNotFoundException e){
            System.out.println("12312");
        }

    }


    public static void printdoublemassive(Character[][] nextkey){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (nextkey[i][j] == null) {
                    nextkey[i][j] = 0;
                }
            }
        }

        for (Character[] ker : nextkey) {

            System.out.println(Arrays.toString(ker));
        }
    }


    public static Character[][] makekeyiter(Character[][] ch,Bigram[][] bigrams,Cross cross){
        HashSet<KeyBigram> keyBigrams = Key.findPlacesofBigrams(ch);

        HashSet<KeyBigram> crossbigrams = Key.crossBigrams(keyBigrams);
        HashSet<KeyBigram> linebigrams = Key.lineBigrams(keyBigrams);



        HashSet<KeyBigramPair> keyBigramspairs = Key.defineBigram(bigrams, crossbigrams);
        HashSet<KeyBigramPair> keyLineBigramspairs = Key.defineBigram(bigrams, linebigrams);

        Character[][] nextkey = Key.makeKey(cross.both, ch, keyBigramspairs);
        nextkey = Key.makelineKey(nextkey,keyLineBigramspairs);

        return nextkey;
    }


    public static int markOftry(Character[][] key){
        int mark = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(key[i][j] == null){
                    mark++;
                }
            }
        }
        return mark;
    }
}
