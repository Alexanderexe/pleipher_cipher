/**
 * Created by ЦифроваяКомпания on 22.09.2016.
 */
public class Cross {
    Quintet first,second;
    Character both;

    Cross(Quintet first,Quintet second,Character ch){
        this.first = first;
        this.second = second;
        both = ch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cross cross = (Cross) o;

        if (!first.equals(cross.first)) return false;
        return second.equals(cross.second);

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + second.hashCode();
        return result;
    }



}
