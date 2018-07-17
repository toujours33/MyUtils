package cst.wyz.array;

/**
 * 工具类与 ArrayOperation 相关，ArrayOperation 类中传入的是 基本类型的包装类，如 Integer等
 * 但是实际中定义数组类型很少会定义包装类的实际类型，而是基本类型 int，所以此处提供一个 基本类型数组到包装类型数组的转换工具
 * @author user
 *
 */
public class CastBaseTypeArray {

    public static int[] integerCastInt(Integer[] integers) {
        int[] intArray = new int[integers.length];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = integers[i].intValue();
        }

        return intArray;
    }

    public static Integer[] intCastInteger(int[] intArray) {
        Integer[] integers = new Integer[intArray.length];

        for (int i = 0; i < integers.length; i++) {
            integers[i] = intArray[i];
        }

        return integers;
    }

    public static char[] characterCastChar(Character[] characters) {
        char[] charArray = new char[characters.length];

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = characters[i].charValue();
        }

        return charArray;
    }

    public static Character[] charCastCharacter(char[] charArray) {
        Character[] characters = new Character[charArray.length];

        for (int i = 0; i < characters.length; i++) {
            characters[i] = charArray[i];
        }

        return characters;
    }

}
