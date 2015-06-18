package alex.scala.other;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TestSame {

    public <T, R> List<R> flatMap(List<T> sourceList, Function<T, List<R>> function) {
        List<R> result = new ArrayList<>();
        for (T t : sourceList) {
            List<R> rs = function.apply(t);
            result.addAll(rs);
        }
        return result;
    }
}
