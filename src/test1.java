import java.util.Arrays;

public class test1 {

/// Given an array of numbers and a target number, find the sum of two numbers
//
//// from the array that is equal to the target number. May not sum the same index twice.
//
//// twoSum([1,2,3], 5) // return the indexes of the numbers that match the result [1, 2]
//

    public static void main(String[] args) {

        int[] arr = {1,2,3,1,4};
//        System.out.println(Arrays.toString(twoSum(arr, 5)));
        twoSum(arr, 5);

    }

    public static int[] twoSum (int[] numArr, int sum) {

        int[] res = new int[2];
        for (int i = 0; i < numArr.length; i++) { //0
            for (int j = i+1; j < numArr.length; j++) { //1 2 3

                if(numArr[i] + numArr[j] == sum){
                    res[0] = i;
                    res[1] = j;
                    System.out.println(Arrays.toString(res));
                }
            }
        }
        return res;
    }

}
