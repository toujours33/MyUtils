package cst.wyz.array;

public class ArrayOperation<T>{

    public void printArray(T[] t, String... strings) {
        if (strings.length != 0) {
            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }
        }
        System.out.print("[ ");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
            if (i < t.length - 1)
                System.out.print(" , ");
        }
        System.out.println(" ]");
    }

    /**
     * 交换数组中特定位置的值
     * @param t 包装类型数组引用
     * @param left 待交换的元素地址
     * @param right 待交换的元素地址
     * @throws IndexOutOfBoundsException 元素地址超出界限(left||right < 0 || left||right > t.length)
     */
    public void swapTwo(T[] t, int left, int right) {
        if(left < 0 || left >= t.length || right < 0 || right >= t.length)
            throw new IndexOutOfBoundsException();
        T temp = t[left];
        t[left] = t[right];
        t[right] = temp;
    }

    public T[] arrayCopy(T[] t) {
        return t.clone();
    }

    /**
     *
     * @方法名：insertInnerArray @描述： 数组内进行插入操作，数组长度不变，整体后移
     * @param t
     *            源数组
     * @param source
     *            悬空的元素下表
     * @param target
     *            要插入的位置下标。如果source<taeget 则target往左直到source 整体左移；若source>target
     *            则target 往右知道 source 整体右移
     * @return
     *
     * @author:Jerry
     * @Time: 2018-03-12 14:51:17
     */
    public void insertInnerArray(T[] t, int source, int target) {
        T temp = t[source];
        if (source < target) {
            for (int i = source; i < target; i++) {
                t[i] = t[i + 1];
            }
            t[target] = temp;
        } else {
            for (int i = source; i > target; i--) {
                t[i] = t[i - 1];
            }
            t[target] = temp;
        }
    }

    /**
     * 数组内容转换特定的字符串"[ elem , elem , elem ]"
     * @param t 包装类型数组
     * @return
     */
    public String toString(T[] t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (int i = 0; i < t.length; i++) {
            stringBuilder.append(t[i]);
            if (i < t.length - 1)
                stringBuilder.append(" , ");
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

}
