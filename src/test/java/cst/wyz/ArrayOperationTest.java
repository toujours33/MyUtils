package cst.wyz;

import cst.wyz.array.ArrayOperation;
import org.junit.Assert;
import org.junit.Test;

public class ArrayOperationTest {

    private Integer[] test = {1,2,3,4,5};
    private String testString = "[ 1 , 2 , 3 , 4 , 5 ]";
    private ArrayOperation<Integer> intOperation = new ArrayOperation<>();

    @Test
    public void testPrint(){
        intOperation.printArray(test,"Print Test Array");
    }

    @Test
    public void testToString(){
        Assert.assertEquals(testString,intOperation.toString(test));
    }

    @Test
    public void testSwap(){
        try {
            intOperation.swapTwo(test,1,3);
            intOperation.printArray(test);
            assert true;
        } catch (Exception e) {
            if (e.getClass() == IndexOutOfBoundsException.class)
                assert false;
            e.printStackTrace();
        }
    }


    @Test
    public void testSwapError(){
        try{
            intOperation.swapTwo(test,0,test.length);
        }catch (Exception e){
            if(e.getClass() == IndexOutOfBoundsException.class)
            {
                assert true;
                e.printStackTrace();
                try {
                    intOperation.swapTwo(test,-1,test.length-1);
                } catch (Exception e1) {
                    if (e1.getClass() == IndexOutOfBoundsException.class)
                        assert true;
                    else assert false;
                    e1.printStackTrace();
                }
            }
            else assert false;
        }
    }

    @Test
    public void testArrayclone(){

        Integer[] copy = intOperation.arrayCopy(test);
        intOperation.swapTwo(test,0,1);

        Assert.assertNotEquals(intOperation.toString(copy),intOperation.toString(test));

        //Assert.assertArrayEquals(copy,test);

    }

}
