/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class ClearText {

    private static String deleteSpace(String txt){
        txt = txt.replaceAll(" ","");
        txt = txt.replaceAll("J","I");
        return txt;
    }
    private static String editDoubleLetter(String txt){

        StringBuilder stb = new StringBuilder(txt);


        for (int i = 0; i < stb.length()-1; i = i+2 ) {
            if (stb.charAt(i) == stb.charAt(i+1)){
                stb.insert(i+1,'X');
            }
        }

        return stb.toString();
    }
    private static String editParity(String txt){
        if(txt.length() % 2 == 1) {
            StringBuilder stb = new StringBuilder(txt);
            stb.append('X');
            return  stb.toString();
        }
        return txt;
    }

    public static Bigram[] splitToBigrams(String txt){
        Bigram[] bigrams = new Bigram[txt.length()/2];
        int j = 0;
        for (int i = 0; i < txt.length() ; i = i + 2) {
            bigrams[j] = new Bigram();
            bigrams[j].first = txt.charAt(i);
            bigrams[j].second = txt.charAt(i + 1);
            j++;
        }
        return bigrams;
    }

    public static Bigram[] TextToBigrams(String txt){
        txt = txt.toUpperCase();
        String textWithoutSpaces = deleteSpace(txt);
        String textWithoutRepiats = editDoubleLetter(textWithoutSpaces);
        String ParityText = editParity(textWithoutRepiats);

        return splitToBigrams(ParityText);
    }

}
