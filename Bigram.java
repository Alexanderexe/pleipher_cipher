/**
 * Created by ЦифроваяКомпания on 20.09.2016.
 */
public class Bigram {

    public Character first;
    public Character second;

    public Bigram(){};

    public Bigram(String string){
        first = string.charAt(0);
        second = string.charAt(1);
    }

    public Character getFirst() {
        return first;
    }

    public Character getSecond() {
        return second;
    }

    public void setFirst(Character first) {
        this.first = first;
    }

    public void setSecond(Character second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "" + first + second;
    }
}
