/**
 * Created by pwg on 24.05.17.
 */

public class Main {


  static final double p = 0.001;
  static final int MINWORDSIZE = 5;
  static final int SAMPLESIZE = 1000;

  public static void main(String[] args) throws Throwable {
    System.out.println("Dist Bonusaufgabe");


    BloomFilter bf = computeOptimalBloomFilter(58110, p);
    bf.readData("words.txt");

    int count = 0;

    for (int i = 0; i < SAMPLESIZE; i++) {
      String generatedWord = wordGenerator();
      if (bf.bloomContains(generatedWord)) {
        if (!bf.contains(generatedWord)) {
          count++;
        }
      }
    }
    System.out.println("----------------------------------------------");
    bf.printArr();
    System.out.println("got " + (double) count / (double) SAMPLESIZE + " wrong matches. Aimed value was: " + p
    );
  }


  /*
  1. For a given number n of elements that are expected to be
  stored in the data structure and for a given error probability p,
  compute a suitable size for the filter and the optimal number k of
  hash functions.
   */
  public static BloomFilter computeOptimalBloomFilter(int n, double p) throws Throwable {
    double dm = -n * Math.log(p) / (Math.pow(Math.log(2), 2));
    double dk = dm / n * Math.log(2);
    int m = (int) (dm + 1);
    int k = (int) (dk + 1);


    System.out.println("Probablity: " + p + ", Elements: " + n);

    System.out.println("Filtersize:" + m);
    System.out.println("Hash Functions:" + k);

    return new BloomFilter(m, k);
  }

  public static String wordGenerator() {
    char[] word = new char[(int) (Math.random() * 7) + MINWORDSIZE];
    for (int i = 0; i < word.length; i++) {
      word[i] = (char) (97 + (int) (Math.random() * 26));
    }
    //  System.out.println("generated word " + "\"" + new String(word) + "\"");
    return new String(word);
  }


}
