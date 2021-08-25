import java.util.Map;
import java.util.Set;

public class test {

    public static void main(String[] args) {

    }

    public static boolean compareJsons(Json first, Json second) {

//        if(first.getValue() instanceof Json){
//            compareJsons(first.getValue(), second.getValue());
//        }

        Set<Map.Entry<?, ?>> entries = first.getData().entrySet();

        for (Map.Entry<?, ?> entry : entries) {
            if (!second.getData().containsKey(entry.getKey())) {
                return false;
            }
            if (!entry.getValue().equals(second.getData().get(entry.getKey()))) {
                return false;
            }
            if(entry.getValue() instanceof Json){
                compareJsons(entry.getValue(), second.getData().get(entry.getKey())));
            }

        }


        return true;
    }

}
