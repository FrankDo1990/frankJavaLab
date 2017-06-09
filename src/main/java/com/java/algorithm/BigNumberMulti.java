package com.java.algorithm;

/**
 * Created by dufeng on 2017/6/9.
 */
public class BigNumberMulti {
    public static String ZERO = "0";
    public static String ONE = "1";

    public static void main(String...args){
        String a = "9999";
        String b = "9999";
        int[] arr = new int[a.length() + b.length()];
        for (int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
        bigAdd(a, b ,arr);
        System.out.println(bigAdd(a, b ,arr));

    }
    public static String calBigXYDC(String a, String b){
        int[] arr = new int[a.length() + b.length()];
        for (int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
        return calBigXWithDC(a, b, arr);
    }
    public static String calBigXWithDC(String a, String b, int[] tempArr){
        StringBuilder sb = new StringBuilder();
        if (a.equals(ZERO) || b.equals(ZERO) ) return ZERO;
        if (a.equals(ONE)) return b;
        if (b.equals(ONE)) return a;
        if (a.length() == 1 && b.length() == 1){

        }
        return sb.toString();
    }

    public static String bigAdd(String a, String b, int[] tempArr){
        int maxI = Math.min(a.length() - 1, b.length() - 1);
        int fromLow = 0;
        int j = tempArr.length - 1, i = 0;
        for (; i <= maxI; i++){
            int bita = a.charAt(a.length() - 1 - i) - '0';
            int bitb = b.charAt(b.length() - 1 - i) - '0';
            int add = bita + bitb + fromLow;
            tempArr[j--] = add % 10;
            fromLow = add / 10;
        }
        if (a.length() != b.length()){
            String c = a.length() > b.length()? a : b;
            for (int k = c.length() - 1 - i; k >= 0; k--){
                int bit = c.charAt(k) - '0';
                int add = bit + fromLow;
                tempArr[j--] = add % 10;
                if (add >= 10){
                    fromLow = add / 10;
                }else {
                    break;
                }
            }
        }
        tempArr[j] = fromLow;

        StringBuilder sb = new StringBuilder();
        int start = tempArr.length  - 1 - Math.max(a.length(), b.length());
        if (tempArr[start] ==  0){
            start++;
        }
        while (start < tempArr.length){
            sb.append(tempArr[start]);
            tempArr[start] = 0;
            start++;
        }
        return sb.toString();
    }















    //普通进位乘法
    public static String calBigX(String a, String b){
        String ra = reverse(a);
        String rb = reverse(b);
        int[] res = new int[ra.length() + rb.length()];
        for (int i = 0; i< res.length; i++){
            res[i] = 0;
        }
        for (int i = 0; i < ra.length(); i++){
            for (int j = 0; j < rb.length(); j ++){
                res[i + j] += (ra.charAt(i) - '0') * (rb.charAt(j) - '0');
                int next = res[i + j] / 10, addIndex = 1;
                res[i + j]  %= 10;
                while (next > 0){
                    int curIndexNumber = res[i + j + addIndex] + (next % 10);
                    res[i + j + addIndex++] = curIndexNumber % 10;
                    next = curIndexNumber /10;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        for (int i = res.length -1 ; i >=0 ; i--){
            if (res[i] != 0) start = true;
            if (start){
                sb.append(res[i]);
            }
        }
        return sb.toString();
    }

    public static String reverse(String original){
        if (original == null || original.length() <= 0) return null;
        int i = 0;
        char[] charArr = new char[original.length()];
        while (i <= original.length() / 2){
            int maxIndex = original.length() - 1;
            char mid = original.charAt( maxIndex - i );
            charArr[maxIndex - i] = original.charAt(i);
            charArr[i] = mid;
            i++;
        }
        return String.valueOf(charArr);
    }
}
