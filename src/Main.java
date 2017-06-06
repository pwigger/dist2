/**
 * Created by pwg on 24.05.17.
 */

public class Main {

  static final double AIMEDPROBABILITY = 0.005;
  static final int MINWORDSIZE = 4;
  static final int MAXWORDSIZE = 8;
  static final int SAMPLESIZE = 1000;
  static final int EXPECTEDSIZE = 58110;

  public static void main(String[] args) throws Throwable {
    System.out.println("Dist Bonusaufgabe");
    System.out.println();

    BloomFilter bf = createOptimalBloomFilter(EXPECTEDSIZE, AIMEDPROBABILITY);
    bf.readData("words.txt");

    int count = 0;
    System.out.println("Generating "+SAMPLESIZE+" Words");
    System.out.println("----------------------------------------------");


    for (int i = 0; i < SAMPLESIZE; i++) {
      String generatedWord = wordGenerator();
      if (bf.bloomContains(generatedWord)) {
        if (!bf.contains(generatedWord)) {
          System.out.println("WRONG MATCH: This dataset probably contains the word " + "\"" + generatedWord + "\"");
          count++;
        }
        else{
          System.out.println("TRUE MATCH: This dataset probably contains the word " + "\"" + generatedWord + "\"");
        }
      }
    }
    System.out.println("----------------------------------------------");
    bf.printArr();

    double actualValue=(double) count / (double) SAMPLESIZE;
    System.out.println("got " + count+"/"+SAMPLESIZE+" = " +actualValue+ " wrong matches.");
    System.out.println("Aimed value was: " + AIMEDPROBABILITY);
    System.out.println("Difference: " + Math.abs(AIMEDPROBABILITY-actualValue));
    System.out.println("----------------------------------------------");

  }


  /*
  creates a Bloomfilter with n = num expected Elements, p = expected Probability for false matches
   */
  public static BloomFilter createOptimalBloomFilter(int n, double p) throws Throwable {
    double dm = -n * Math.log(p) / (Math.pow(Math.log(2), 2));
    double dk = dm / n * Math.log(2);
    int m = (int) (dm + 1);
    int k = (int) (dk + 1);
    System.out.println("----------------------------------------------");

    System.out.println("Probablity: " + p + ", Elements: " + n);
    System.out.println("Filtersize:" + m);
    System.out.println("Hash Functions:" + k);
    System.out.println("----------------------------------------------");

    return new BloomFilter(m, k);
  }

  /*
  Creates random words
   */
  public static String wordGenerator() {
    char[] word = new char[(int) (Math.random() * MAXWORDSIZE) + MINWORDSIZE];
    for (int i = 0; i < word.length; i++) {
      word[i] = (char) (97 + (int) (Math.random() * 26));
    }
    //  System.out.println("generated word " + "\"" + new String(word) + "\"");
    return new String(word);
  }


}
