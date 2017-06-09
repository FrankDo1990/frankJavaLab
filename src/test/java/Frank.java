import com.google.common.collect.Sets;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Created by dufeng on 2017/3/8.
 */
public class Frank {
    public static void main(String...args) throws Exception {
        String res = calBigX("99999999999999999999999999", "111111111121111111111112371291910000111111111223288888812777777771199");
        BigInteger x = new BigInteger("99999999999999999999999999");
        BigInteger y = new BigInteger("111111111121111111111112371291910000111111111223288888812777777771199");
        System.out.println(x.multiply(y).toString().equals(res));
    }

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
    public static void getExtension(byte[] bytes) {
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(new ByteArrayInputStream(bytes));
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if(iter.hasNext()){
                System.out.println("扩展名:"+iter.next().getFormatName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(iis!=null){
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 把一个文件转化为字节
     * @param file
     * @return   byte[]
     * @throws Exception
     */
    public static byte[] getByte(File file) throws Exception
    {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        System.out.println("Available bytes:" + in.available());
        byte[] temp = new byte[1024];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        in.close();

        byte[] content = out.toByteArray();
        return content;
    }
}
