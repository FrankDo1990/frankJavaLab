package com.java.algorithm;

/**
 * Created by dufeng on 2017/6/9.
 */
public class BigNumberMulti {
    public static String ZERO = "0";
    public static String ONE = "1";

    public static void main(String...args){
        String a = "12312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423";
        String b = "1231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423123128788812312313131131313124234242342312312878881231231313113131312423424234231231287888123123131311313131242342423423";
        String res = calBigXWithDC(a, b);
        System.out.println(res);
        System.out.println(res.length());
        String res1 = calBigX(a, b);
        System.out.print(res.equals(res1));

    }
    public static String sameLen(String x, String y){
        if (x.length() >= y.length()) return x;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y.length() - x.length(); i ++){
            sb.append('0');
        }
        sb.append(x);
        return sb.toString();
    }
    public static String calBigXWithDC(String a, String b){
        boolean neg = false;
        if (a.charAt(0) == '-' && b.charAt(0) != '-'){
            neg = true;
            a = a.substring(1);
        }else if (b.charAt(0) == '-' && a.charAt(0) != '-'){
            neg = true;
            b = b.substring(1);
        }
        a = sameLen(a, b);
        b = sameLen(b, a);
        int len = a.length();
        if (len == 1){
            return neg ? "-" + String.valueOf((a.charAt(0) - '0') * (b.charAt(0) - '0')) : String.valueOf((a.charAt(0) - '0') * (b.charAt(0) - '0'));
        }
        int mid = len / 2;
        String x0 = a.substring(0, mid);
        String y0 = b.substring(0, mid);
        String x1 = a.substring(mid, len);
        String y1 = b.substring(mid, len);
        String z0 = calBigXWithDC(x0, y0);
        String z2 = calBigXWithDC(x1, y1);
        String z1 = bigSub(calBigXWithDC(bigAdd(x0, x1), bigAdd(y0, y1)), bigAdd(z0, z2));
        String res = bigAdd(bigAdd(leftShiftX(z0, 2*(len - mid)), leftShiftX(z1, len - mid)), z2);
        int k = 0;
        while (k < res.length() && res.charAt(k) == '0'){
            k++;
        }
        res = res.substring(k);
        if (neg){
            res = "-" + res;
        }
        return res;
    }

    public static String leftShiftX(String orig, int x){
        StringBuilder sb = new StringBuilder();
        if (orig != null && x > 0){
            sb.append(orig);
            while (x-- > 0){
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public static String bigSub(String a, String b){
        if (a == null || a.length() == 0 ){
            a = "0";
        }
        if (b == null || b.length() == 0){
            b = "0";
        }
        if (a.charAt(0) == '-'){
            if (b.charAt(0) == '-'){
                return bigSub(b.substring(1), a.substring(1));
            }else {
                return "-" + bigAdd(b, a.substring(1));
            }
        }else {
            if (b.charAt(0) == '-'){
                return bigAdd(a, b.substring(1));
            }
        }

        int lowBorrow = 0;
        a = sameLen(a, b);
        b = sameLen(b, a);
        int i = 0;
        boolean aBigger = false;
        for (; i < a.length(); i++){
            if (a.charAt(i) != b.charAt(i)){
                aBigger = a.charAt(i) > b.charAt(i);
                break;
            }
        };
        if (i == a.length()){
            return "0";
        }
        if (!aBigger){
            return "-" + bigSub(b, a);
        }
        i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        for (;i >=0 && j >= 0; i--, j--){
            int bita = a.charAt(i) - '0' - lowBorrow;
            int bitb = b.charAt(j) - '0';
            int bit = bita >= bitb ? bita - bitb : 10 + bita - bitb;
            lowBorrow = bita >= bitb ? 0 : 1;
            sb.insert(0, bit);
        }
        if (lowBorrow > 0){
            int index = 0;
            while (index < sb.length()  && sb.charAt(index) == '0'){
                index++;
            }
            if (index == sb.length()){
                return "0";
            }else {
                String str  = sb.substring(index);
                sb.delete(0, sb.length());
                sb.append("-");
                sb.append(str);
                return sb.toString();
            }
        }
        return sb.toString();
    }
    /**
     * 大数相加
     * @param a
     * @param b
     * @return
     */
    public static String bigAdd(String a, String b){
        int fromLow = 0;
        a = sameLen(a, b);
        b = sameLen(b, a);
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        for (;i >=0 && j >= 0; i--, j--){
            int bita = a.charAt(i) - '0';
            int bitb = b.charAt(j) - '0';
            int add = bita + bitb + fromLow;
            fromLow = add / 10;
            sb.insert(0, add % 10);
        }
        if(fromLow > 0){
            sb.insert(0, fromLow);
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
