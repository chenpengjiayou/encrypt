package cp;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenpeng on 2017/5/25.
 */
public class DES {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        AlgorithmParameters ap = AlgorithmParameters.getInstance("DES");
        ap.init(new BigInteger("19050619766489163472469").toByteArray());
        byte[] b = ap.getEncoded();
        System.out.println(new BigInteger(b).toString());
    }


}
