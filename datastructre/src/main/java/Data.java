import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        Object x  = map.put(10,10);
        System.out.println(x);

        List<String> list = new ArrayList<>();
        list.add("null");
        System.out.println(list);

    }
}
