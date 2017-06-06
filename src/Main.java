/**
 * Created by pwg on 24.05.17.
 */

public class Main {


  static final double AIMEDPROBABILITY = 0.01;
  static final int MINWORDSIZE = 3;
  static final int SAMPLESIZE = 1000;
  static final int EXPECTEDSIZE = 60000;

  public static void main(String[] args) throws Throwable {
    System.out.println("Dist Bonusaufgabe");


    BloomFilter bf = createOptimalBloomFilter(EXPECTEDSIZE, AIMEDPROBABILITY);
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
    System.out.println("got " + (double) count / (double) SAMPLESIZE + " wrong matches. Aimed value was: " + AIMEDPROBABILITY
    );
  }


  /*
  creates a Bloomfilter with n = num expected Elements, p = expected Probability for false matches
   */
  public static BloomFilter createOptimalBloomFilter(int n, double p) throws Throwable {
    double dm = -n * Math.log(p) / (Math.pow(Math.log(2), 2));
    double dk = dm / n * Math.log(2);
    int m = (int) (dm + 1);
    int k = (int) (dk + 1);

    System.out.println("Probablity: " + p + ", Elements: " + n);
    System.out.println("Filtersize:" + m);
    System.out.println("Hash Functions:" + k);

    return new BloomFilter(m, k);
  }

  /*
  Creates random words
   */
  public static String wordGenerator() {
    char[] word = new char[(int) (Math.random() * 7) + MINWORDSIZE];
    for (int i = 0; i < word.length; i++) {
      word[i] = (char) (97 + (int) (Math.random() * 26));
    }
    //  System.out.println("generated word " + "\"" + new String(word) + "\"");
    return new String(word);
  }


}
