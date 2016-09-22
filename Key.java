import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class Key {

    public Character[][] key;
    Alphabet alphabet;

    Key(){
        key = new Character[5][5];
        alphabet = new Alphabet();
        Iterator<Character> iterator = alphabet.shortAlphabet.iterator();

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                key[i][j] = iterator.next();
            }
        }
    }

    public IndexOfCharacter determineCharacterIndex(Character ch){
        IndexOfCharacter index = new IndexOfCharacter();
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                if (key[i][j] == ch){
                    index.setI(i);
                    index.setJ(j);
                    return index;
                }
            }
        }
        if (index == null){
            throw new ArithmeticException();
        }
        return index;
    }


    public static Bigram[][] openAndCipheredBigrams (String OpenedTxt, String cipheredTxt){

        Bigram[] openBigrams = ClearText.TextToBigrams(OpenedTxt);
        Bigram[] cipheredBigrams = ClearText.TextToBigrams(cipheredTxt);

        Bigram[][] compareBigrams = new Bigram[openBigrams.length][2];

        ClearText.TextToBigrams(cipheredTxt);
        for (int i = 0; i < openBigrams.length; i++) {
            for (int j = 0; j < 3; j++) {
                if(j==0)
                compareBigrams[i][j] = openBigrams[i];
                if (j==1)
                compareBigrams[i][j] = cipheredBigrams[i];
            }
        }
        return compareBigrams;
    }

    public static BigramPair[] makePair(Bigram[][] bigrams){
        BigramPair[] pairs = new BigramPair[bigrams.length];
        int dd = bigrams.length;
        int k = 0;

            for (int j = 0;  j < bigrams.length; j++) {
                pairs[k] = new BigramPair();
                pairs[k].setOpen(bigrams[j][0]);
                pairs[k].setCipher(bigrams[j][1]);
                k++;
            }

        return  pairs;
    }

    public static HashSet<BigramPair> analysePairs(BigramPair[] pairs ){

        HashSet<BigramPair> unusualPairs = new HashSet();

        for (int i = 0; i < pairs.length; i++) {

            if (pairs[i].getOpen().first == pairs[i].getCipher().second){
                unusualPairs.add(pairs[i]);
            }
            if (pairs[i].getOpen().second == pairs[i].getCipher().first){
                unusualPairs.add(pairs[i]);
            }

        }
        return unusualPairs;
    }


    public static HashSet<BigramPair> analyseUnusualPairs(HashSet<BigramPair> upairs){

        HashMap<Character,Integer> alphabeticCard = new HashMap<>();

        Alphabet alpha = new Alphabet();
        for (Character ch: alpha.shortAlphabet) {
            alphabeticCard.put(ch,0);
        }


        for (BigramPair pair : upairs) {

            Character first = pair.getOpen().first;
            Character second = pair.getOpen().second;
            Character third = pair.getCipher().first;
            Character fourth = pair.getCipher().second;

            alphabeticCard.put(first,alphabeticCard.get(first)+1);
            alphabeticCard.put(second,alphabeticCard.get(second)+1);
            alphabeticCard.put(third,alphabeticCard.get(third)+1);
            alphabeticCard.put(fourth,alphabeticCard.get(fourth)+1);

        }


        Iterator<Character> iterator = alpha.shortAlphabet.iterator();

        int first = 0;
        int second = 0;
        int third = 0;

        Character firstCh = ' ';
        Character secondCh = ' ';
        Character thirdCh  = ' ';

        for (int i = 0; i < alphabeticCard.size() ; i++) {
            char currentCh = iterator.next();
             int  current = alphabeticCard.get(currentCh);
            if(current >= first ){

                thirdCh = secondCh;
                secondCh = firstCh;
                firstCh = currentCh;


                third = second;
                second = first;
                first = current;

            }
        }

        HashSet<BigramPair> wonderPairs = new HashSet<>();

        for (BigramPair pair : upairs) {
            if (pair.isMatch(firstCh,secondCh,thirdCh)){
                wonderPairs.add(pair);
            }
        }

        return wonderPairs;

    }

    public static HashSet<Cross> crossQuntets(HashSet<Quintet> quintets){

        HashSet<Cross> croses = new HashSet<>();



        for (Quintet quintet: quintets ) {

            int flag = 0;
            Character ch = ' ';

            for (Quintet pairQuintet: quintets) {

                if(!quintet.equals(pairQuintet)){



                    if (quintet.isHave(pairQuintet.first)){
                        flag++;
                        ch = pairQuintet.first;
                    }
                    if (quintet.isHave(pairQuintet.second)){
                        flag++;
                        ch = pairQuintet.second;
                    }
                    if (quintet.isHave(pairQuintet.third)){
                        flag++;
                        ch = pairQuintet.third;
                    }
                    if (quintet.isHave(pairQuintet.fourth)){
                        flag++;
                        ch = pairQuintet.fourth;
                    }
                    if (quintet.isHave(pairQuintet.fiveth)){
                        flag++;
                        ch = pairQuintet.fiveth;
                    }
                    if (flag == 1) {

                        croses.add(new Cross(quintet, pairQuintet,ch));
                    }

                }

            }

        }

        return croses;
    }

    public static Cross turnCrossQuintet(Cross cross,int posInString,Character both){
        String first = cross.first.toString();
        String second = cross.second.toString();

        String crossedFirst = Quintet.turnString(first,posInString,both);
        String crossedSecond = Quintet.turnString(second,posInString,both);

        cross.first = new Quintet(crossedFirst);
        cross.second = new Quintet(crossedSecond);

        return cross;
    }

    public static Character[][] placeCross(Cross cross,int posx,int posy){
        Character[][] crosskey = new Character[5][5];
        char[] first = cross.first.toString().toCharArray();
        char[] second = cross.second.toString().toCharArray();

        int x = posx;
        int y = posy;

        for (int j = 0; j < 5; j++) {

            if(x == 5){x = 0;};

            crosskey[x][y] = first[j];

            x++;
        }

        x = posx;
        y = posy;

        for (int j = 0; j < 5; j++) {

            if(y == 5){y = 0;};

            crosskey[x][y] = second[j];

            y++;
        }


        return crosskey;
    }

    public static IndexOfCharacter findRightIndex(Bigram[][] bigrams,Cross cross){

        Character[][] table = new Character[5][5];
        IndexOfCharacter[] indexes =  new IndexOfCharacter[25];
        int n = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                indexes[n].setI(i);
                indexes[n].setJ(j);
                n++;
            }
        }

        int i = 0;
        int j = 0;

        table = placeCross(cross,indexes[i].getX(),indexes[i].getY());
        Bigram first = new Bigram();
        Bigram second = new Bigram();
        Bigram third = new Bigram();
        Bigram fourth = new Bigram();

        first.first = table[indexes[i].getX()+1][indexes[i].getY()];
        first.second = table[indexes[i].getX()][indexes[i].getY()-1];

        second.first = table[indexes[i].getX()-1][indexes[i].getY()];
        second.second = table[indexes[i].getX()][indexes[i].getY()-1];

        third.first = table[indexes[i].getX()][indexes[i].getY()+1];
        third.second = table[indexes[i].getX()-1][indexes[i].getY()];

        third.first = table[indexes[i].getX()-1][indexes[i].getY()];
        third.second = table[indexes[i].getX()][indexes[i].getY()+1];

        Bigram firstBigram = Cipher.findBigram(bigrams,first.toString());
        Bigram secondBigram = Cipher.findBigram(bigrams,second.toString());
        Bigram thirdBigram = Cipher.findBigram(bigrams,third.toString());
        Bigram fourthBigram = Cipher.findBigram(bigrams,fourth.toString());

        table[i+1][j+1] = firstBigram.second;
        table[i-1][j-1] = secondBigram.first;
        table[i+1][j+1] = third.second;
        table[i+1][j+1] = firstBigram.second;

        ///поставили пару точек и скобок


        return null;
    }













}
