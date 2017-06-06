/**
 * Created by pwg on 24.05.17.
 */

public class Main {

  private int m = 0;
  private int k = 0;

  public static void main(String[] args) throws Throwable{
    System.out.println("Dist Bonusaufgabe");


    BloomFilter bf = computeOptimalBloomFilter(390,0.01);
    bf.readWords();


  }


  /*
  1. For a given number n of elements that are expected to be
  stored in the data structure and for a given error probability p,
  compute a suitable size for the filter and the optimal number k of
  hash functions.
   */
  public static BloomFilter computeOptimalBloomFilter(int n, double p) {
    double dm = -n * Math.log(p) / (Math.pow(Math.log(2), 2));
    double dk = dm / n * Math.log(2);
    int m = (int)(dm+1);
    int k= (int)(dk+1);



    System.out.println("Probablity: " + p + ", Elements: " + n);

    System.out.println("Filtersize:" + m);
    System.out.println("Hash Functions:" + k);

    return new BloomFilter(m, k);
  }


}
