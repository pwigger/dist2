import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pwg on 06.06.17.
 */
public class BloomFilter {
  private int m;
  private int k;
  private HashFunction[] hashFunctions;
  private int[] arr;


  public BloomFilter(int m, int k) {
    this.m = m;
    this.k = k;

    hashFunctions = new HashFunction[k];
    for (int i = 0; i < hashFunctions.length; i++) {
      hashFunctions[i] = Hashing.murmur3_128((int) Math.random() * 100000);
    }
    this.arr = new int[m];
  }

  public void readWords() throws IOException {



    try(InputStream input =  getClass().getResourceAsStream("words.txt")) {

      int data = input.read();
      while(data != -1){
        System.out.print((char) data);
        data = input.read();
      }
    }
  }


}
