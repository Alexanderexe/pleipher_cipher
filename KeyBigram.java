/**
 * Created by ЦифроваяКомпания on 26.09.2016.
 */
public class KeyBigram {

    Bigram bigram;
    IndexOfCharacter firstindex;
    IndexOfCharacter secondindex;

    KeyBigram(Bigram bigram,IndexOfCharacter index,IndexOfCharacter index2){
        this.bigram = bigram;
        firstindex = index;
        secondindex = index2;
    }


}
