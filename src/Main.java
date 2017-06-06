import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Created by pwg on 24.05.17.
 */

public class Main {

  public static void main(String[] args) {
    System.out.println("Dist Bonusaufgabe");
    HashFunction hf=Hashing.murmur3_32((int)Math.random()*100);
    System.out.println(hf.hashInt(34));

  }


}
