package other.remove.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author qian.pan on 2024/9/10.
 */
public class ArrayRemoveTest {
    @Test
    public void removeArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
            if (arrayList.get(i) == 0) {
                arrayList.remove(i);
            }
        }
    }

    @Test
    public void removeArrayListImprove() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            System.out.println(arrayList.get(i));
            if (arrayList.get(i) == 2) {
                arrayList.remove(i);
            }
        }
    }


    @Test
    public void forEachRemove() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        for (Integer i : arrayList) {
            System.out.println(i);
            if (i == 0) {
                arrayList.remove(i);
            }
        }
    }
}
