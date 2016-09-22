/**
 * Created by ЦифроваяКомпания on 21.09.2016.
 */
public class BigramPair {


    Bigram open,cipher;

    public Bigram getOpen() {
        return open;
    }

    public void setOpen(Bigram open) {
        this.open = open;
    }

    public Bigram getCipher() {
        return cipher;
    }

    public void setCipher(Bigram cipher) {
        this.cipher = cipher;
    }

    public boolean isMatch(Character a,Character b,Character c){
        if (open.first == a || open.second == a || open.first == b || open.second == b || open.first == c || open.second == c)
            return true;
            return false;
    }
}
