import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by pwg on 06.06.17.
 */
public class BloomFilter {
  private int m;
  private int k;
  private HashFunction[] hashFunctions;
  private int[] arr;
  private ArrayList<String> data = new ArrayList<>();


  /*
  creates a new Bloomfilter with m-Bits as guard array and k Hashfunctions
   */
  public BloomFilter(int m, int k) throws Throwable {
    this.m = m;
    this.k = k;
    hashFunctions = new HashFunction[k];
    System.out.println();
    for (int i = 0; i < k; i++) {

      hashFunctions[i] = Hashing.murmur3_128((int) (System.currentTimeMillis()));
      String teststring = "Dist2017FHNW";
      System.out.println("Testing Hashfunction " + i + " with" + teststring + hashFunctions[i].hashString(teststring,Charset.defaultCharset()));
          Thread.sleep((int) (Math.random() * 100));
    }
    System.out.println();
    this.arr = new int[m];
  }

  /*
  reads in the data from the file with the given name.
   */

  public int readData(String dataSet) throws IOException {

    try (InputStream input = getClass().getResourceAsStream(dataSet)) {

      BufferedReader br = new BufferedReader(new InputStreamReader(input));

      String inputData;
      while ((inputData = br.readLine()) != null) {
        bloomAdd(inputData);
        data.add(inputData);
      }
    }
    System.out.println("----------------------------------------------");

    System.out.println("added " + data.size() + " Entries to dataSet");
    System.out.println("----------------------------------------------");
    return data.size();
  }

  /*
  Does the filtering: Checks if the Fingerprint of the word matches the guardArray
   */

  public boolean bloomContains(String str) {
    int count = 0;
    for (int i = 0; i < hashFunctions.length; i++) {
      int index = calculateIndex(str, hashFunctions[i]);
      if (arr[index] == 0) {
        return false;
      }
    }
    // System.out.println("This dataset probably " + (count == k ? "contains" : "does not contain") + " the word " + "\"" + str + "\"");
    return true;
  }

  /*
  calculates the index of a word with the
   */
  private int calculateIndex(String str, HashFunction hashf) {
    return Math.abs(hashf.hashString(str, Charset.defaultCharset()).asInt()) % m;


  }

  /*
  counts how many ones are contained in the Guard-Array
   */
  public void printArr() {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += this.arr[i];
    }
    System.out.println(sum + "/" + arr.length + " of the array are filled with \"1\"");
  }

  /*
  Adds the fingerprint of str in the Guard-Array
   */

  public void bloomAdd(String str) {
    for (int i = 0; i < hashFunctions.length; i++) {
      int index = calculateIndex(str, hashFunctions[i]);
      // System.out.println("added fingerprint for word: " + str + " at index:" + index);
      arr[index] = 1;
    }
  }

  /*
  Checks if a string is really in the dataset
   */
  public boolean contains(String str) {
    return this.data.contains(str);
  }
}
