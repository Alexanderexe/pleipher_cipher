import java.util.HashSet;

/**
 * Created by ЦифроваяКомпания on 26.09.2016.
 */
public class MarkOfResult {

    Character[][] key;
    Integer mark;


    public static MarkOfResult comparemarks(MarkOfResult[] markOfResultHashSet,int ia){


        MarkOfResult[] arr = markOfResultHashSet;


        MarkOfResult top = arr[ia];


        for (int i = ia; i < 25; i++) {
            if(arr[i].mark < top.mark){

                top = arr[i];
            }
        }
        return top;
    }

}
