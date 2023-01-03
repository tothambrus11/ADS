package sorting;

import org.junit.jupiter.api.Test;
import sorting.keybased.BucketSort;
import sorting.keybased.KeyedInt;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KBSortingTest {
    @Test
    public void bucketSortTest() {

        var list = new ArrayList<>(List.of(
                new KeyedInt(5),
                new KeyedInt(4),
                new KeyedInt(10),
                new KeyedInt(23),
                new KeyedInt(2),
                new KeyedInt(1)
        ));

        BucketSort<KeyedInt> bs = new BucketSort<>();
        bs.sort(list);

        assertEquals(List.of(
                new KeyedInt(1),
                new KeyedInt(2),
                new KeyedInt(4),
                new KeyedInt(5),
                new KeyedInt(10),
                new KeyedInt(23)
        ), list);
    }
}
