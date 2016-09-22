/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class Cipher {

    public static Bigram fromOpenedBigramToCipher(String txt, Key key){
        Bigram bigram = new Bigram(txt);
        Bigram sipheredBigram = null;

        int firstI = key.determineCharacterIndex(bigram.first).getX();
        int firstJ = key.determineCharacterIndex(bigram.first).getY();

        int secondI = key.determineCharacterIndex(bigram.second).getX();
        int secondJ = key.determineCharacterIndex(bigram.second).getY();

        if(firstI != secondI && firstJ != secondJ){
            sipheredBigram = new Bigram();
            sipheredBigram.first = key.key[firstI][secondJ];
            sipheredBigram.second = key.key[secondI][firstJ];
        }
        if (firstI == secondI){
            sipheredBigram = new Bigram();
            if(firstJ < 4 && secondJ < 4) {
                sipheredBigram.first = key.key[firstI][firstJ + 1];
                sipheredBigram.second = key.key[firstI][secondJ + 1];
            }
            if (firstJ == 4){
                sipheredBigram.first = key.key[firstI][0];
                sipheredBigram.second = key.key[firstI][secondJ + 1];
            }
            if (secondJ == 4){
                sipheredBigram.first = key.key[firstI][firstJ + 1];
                sipheredBigram.second = key.key[firstI][0];
            }
        }

        if (firstJ == secondJ){
            sipheredBigram = new Bigram();
            if (firstI < 4 && secondI < 4){
                sipheredBigram.first = key.key[firstI + 1][firstJ];
                sipheredBigram.second = key.key[secondI+1][secondJ];
            }
            if (firstI == 4){
                sipheredBigram.first = key.key[0][firstJ];
                sipheredBigram.second = key.key[secondI + 1][firstJ];
            }
            if (secondI == 4){
                sipheredBigram.first = key.key[firstI+1][firstJ];
                sipheredBigram.second = key.key[0][firstJ];
            }
        }

        return sipheredBigram;
    }


    public static String openedBigramsToCiphered(Bigram[] clearTextBigrams,Key key){
        StringBuilder stringbilder = new StringBuilder();
        for (int i = 0; i < clearTextBigrams.length; i++) {

            String stringOpenedBigram = clearTextBigrams[i].toString();
            String stringcipheredBigram = fromOpenedBigramToCipher(stringOpenedBigram,key).toString();

            stringbilder = stringbilder.append(stringcipheredBigram);

        }
        return stringbilder.toString();
    }

    public static String cipheredBigramsToOpened(Bigram[] cipheredBigrams,Key key){
        StringBuilder stringbilder = new StringBuilder();
        for (int i = 0; i < cipheredBigrams.length; i++) {

            String stringcipheredBigram = cipheredBigrams[i].toString();
            String stringOpenedBigram = fromCipheredBigramsToOpened(stringcipheredBigram,key).toString();

            stringbilder = stringbilder.append(stringOpenedBigram);

        }
        return stringbilder.toString();
    }

    public static Bigram fromCipheredBigramsToOpened(String txt,Key key){
        Bigram bigram = new Bigram(txt);
        Bigram sipheredBigram = null;

        int firstI = key.determineCharacterIndex(bigram.first).getX();
        int firstJ = key.determineCharacterIndex(bigram.first).getY();

        int secondI = key.determineCharacterIndex(bigram.second).getX();
        int secondJ = key.determineCharacterIndex(bigram.second).getY();

        if(firstI != secondI && firstJ != secondJ){
            sipheredBigram = new Bigram();
            sipheredBigram.first = key.key[firstI][secondJ];
            sipheredBigram.second = key.key[secondI][firstJ];
        }
        if (firstI == secondI){
            sipheredBigram = new Bigram();
            if(firstJ <= 4 && secondJ <= 4) {
                sipheredBigram.first = key.key[firstI][firstJ - 1];
                sipheredBigram.second = key.key[firstI][secondJ - 1];
            }
            if (firstJ == 0){
                sipheredBigram.first = key.key[firstI][4];
                sipheredBigram.second = key.key[firstI][secondJ + 1];
            }
            if (secondJ == 0){
                sipheredBigram.first = key.key[firstI][firstJ + 1];
                sipheredBigram.second = key.key[firstI][4];
            }
        }

        if (firstJ == secondJ){
            sipheredBigram = new Bigram();
            if (firstI <= 4 && secondI <= 4){
                sipheredBigram.first = key.key[firstI - 1][firstJ];
                sipheredBigram.second = key.key[secondI - 1][secondJ];
            }
            if (firstI == 0){
                sipheredBigram.first = key.key[4][firstJ];
                sipheredBigram.second = key.key[secondI - 1][firstJ];
            }
            if (secondI == 0){
                sipheredBigram.first = key.key[firstI - 1][firstJ];
                sipheredBigram.second = key.key[4][firstJ];
            }
        }

        return sipheredBigram;
    }

    public static Bigram findBigram(Bigram[][] bigrams,String bigram){

        String strait = bigram;
        String reverse = ""+bigram.charAt(1)+bigram.charAt(0);

        for (int i = 0; i < bigrams.length - 1; i++) {
            String currentBigram = bigrams[i][0].toString();
            String hideBigram = bigrams[i][1].toString();
            if (currentBigram.equals(strait)) {
                return new Bigram(hideBigram);
            }
            if (currentBigram.equals(reverse)){
                hideBigram = ""+hideBigram.charAt(1)+hideBigram.charAt(0);
                return new Bigram(hideBigram);
            }

        }
        return null;
    }

}
