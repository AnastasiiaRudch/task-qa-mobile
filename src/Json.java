import java.util.HashMap;

public class Json<K, V> {

    private HashMap<K, V> data;

    public HashMap<K, V> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Json)) return false;

        Json<?, ?> json = (Json<?, ?>) o;

        return data != null ? data.equals(json.data) : json.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
