package org.neu.alg.hw.hw6;

import org.neu.alg.hw.*;

/**
 * File Name: BigNumberTester.java
 * This clas tests BigNumber
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: BigNumberTester.java BigNumber.java  CharArray.java CString.java IntUtil.java
 */

import java.util.Random;

class BigNumberTester {
  static IntUtil u = new IntUtil();

  private static void testBasic() {
    BigNumber a = new BigNumber(789);
    a.pLn("a = ");
    BigNumber b = new BigNumber('7');
    b.pLn("b = ");
    BigNumber c = new BigNumber("123456789012345678901234567890123456789012345678901234567890");
    c.pLn("c = ");
    BigNumber d = c.clone();
    d.pLn("d = ");
  }

  private static void testAdd() {
    BigNumber a = new BigNumber(9789);
    BigNumber b = new BigNumber(100000);
    a.pLn("a = ");
    b.pLn("b = ");
    BigNumber c = a.add(b);
    c.pLn("a + b =  c = ");
    a = a.add(b);
    a.pLn("a = a + b = ");
    BigNumber d = new BigNumber("3490529510847650949147849619903898133417764638493387843990820577");
    BigNumber e = new BigNumber("32769132993266709549961988190834461413177642967992942539798288533");
    BigNumber f = d.add(e);
    BigNumber g = new BigNumber("36259662504114360499109837810738359546595407606486330383789109110");
    u.myassert(f.isEqual(g));
    d.pLn("d = ");
    e.pLn("e = ");
    f.pLn("f = ");
  }

  private static void testMult() {
    {
      BigNumber a = new BigNumber(9789);
      BigNumber zero = new BigNumber(0);
      BigNumber one = new BigNumber(1);
      a.pLn("a = ");
      zero.pLn("zero = ");
      one.pLn("one = ");
      BigNumber b = a.mult(zero);
      b.pLn("b = a*0 =");
      BigNumber c = zero.mult(a);
      c.pLn("c = 0*a = ");
      BigNumber d = a.mult(one);
      d.pLn("d = a*1 = ");
      BigNumber e = one.mult(a);
      e.pLn("e = 1*a =");
    }
    {
      BigNumber a = new BigNumber(9789);
      BigNumber b = new BigNumber(9);
      BigNumber c = a.mult(b);
      a.pLn("a = ");
      b.pLn("b = ");
      c.pLn("c = ");
      u.myassert(c.isEqual("88101"));
      BigNumber d = b.mult(a);
      d.pLn("d = ");
      u.myassert(d.isEqual("88101"));
    }
    {
      int[] t = {18379, 14180, 23697, 2688, 19137, 26732, 12925, 5367};
      for (int i = 0; i < t.length; i = i + 2) {
        BigNumber a = new BigNumber(t[i]);
        BigNumber b = new BigNumber(t[i + 1]);
        BigNumber c = b.mult(a);
        a.pLn("a = ");
        b.pLn("b = ");
        c.pLn("c = ");
        BigNumber d = new BigNumber(t[i] * t[i + 1]);
        d.pLn("d = ");
        u.myassert(d.isEqual(c));
      }
    }
    {
      BigNumber a = new BigNumber("3490529510847650949147849619903898133417764638493387843990820577");
      BigNumber b = new BigNumber("32769132993266709549961988190834461413177642967992942539798288533");
      BigNumber c = new BigNumber("114381625757888867669235779976146612010218296721242362562561842935706935245733897830597123563958705058989075147599290026879543541");
      BigNumber d = b.mult(a);
      a.pLn("a = ");
      b.pLn("b = ");
      c.pLn("c = ");
      d.pLn("d = ");
      u.myassert(d.isEqual(c));
      System.out.println("Number of digits in a b and d = " + a.size() + "  " + b.size() + "   " + c.size());
    }
  }

  private static void testRandom() {
    int m = 1000;
    int max = (1 << 15);
    Random r = new Random();
    for (int i = 0; i < m; ++i) {
      //System.out.println("i = " + i) ;
      int a = RandomInt.getRandomInt(r, 0, max);
      int b = RandomInt.getRandomInt(r, 0, max);
      BigNumber ba = new BigNumber(a);
      BigNumber bb = new BigNumber(b);
      BigNumber ma = ba.add(bb);
      u.myassert(ma.isEqual(a + b));
      BigNumber ms = ba.sub(bb);
      int aminusb = a - b;
      //System.out.print("a = " + a + " b = " + b + " aminusb = " + aminusb + " ") ;
      //ms.pLn("ms = ");
      u.myassert(ms.isEqual(aminusb));
      BigNumber mm = ba.mult(bb);
      u.myassert(mm.isEqual(a * b));
    }
    System.out.println("Random addition and multiplication on " + m + " numbers passed");
  }

  private static void testFact() {
    BigNumber fact100 = new BigNumber("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000");
    BigNumber b = BigNumber.factorial(100);
    fact100.pLn("a = ");
    b.pLn("!100 = ");
    u.myassert(b.isEqual(fact100));
    System.out.println("Number of digits in !100 = " + b.size());

    BigNumber fact1000 = new BigNumber("402387260077093773543702433923003985719374864210714632543799910429938512398629020592044208486969404800479988610197196058631666872994808558901323829669944590997424504087073759918823627727188732519779505950995276120874975462497043601418278094646496291056393887437886487337119181045825783647849977012476632889835955735432513185323958463075557409114262417474349347553428646576611667797396668820291207379143853719588249808126867838374559731746136085379534524221586593201928090878297308431392844403281231558611036976801357304216168747609675871348312025478589320767169132448426236131412508780208000261683151027341827977704784635868170164365024153691398281264810213092761244896359928705114964975419909342221566832572080821333186116811553615836546984046708975602900950537616475847728421889679646244945160765353408198901385442487984959953319101723355556602139450399736280750137837615307127761926849034352625200015888535147331611702103968175921510907788019393178114194545257223865541461062892187960223838971476088506276862967146674697562911234082439208160153780889893964518263243671616762179168909779911903754031274622289988005195444414282012187361745992642956581746628302955570299024324153181617210465832036786906117260158783520751516284225540265170483304226143974286933061690897968482590125458327168226458066526769958652682272807075781391858178889652208164348344825993266043367660176999612831860788386150279465955131156552036093988180612138558600301435694527224206344631797460594682573103790084024432438465657245014402821885252470935190620929023136493273497565513958720559654228749774011413346962715422845862377387538230483865688976461927383814900140767310446640259899490222221765904339901886018566526485061799702356193897017860040811889729918311021171229845901641921068884387121855646124960798722908519296819372388642614839657382291123125024186649353143970137428531926649875337218940694281434118520158014123344828015051399694290153483077644569099073152433278288269864602789864321139083506217095002597389863554277196742822248757586765752344220207573630569498825087968928162753848863396909959826280956121450994871701244516461260379029309120889086942028510640182154399457156805941872748998094254742173582401063677404595741785160829230135358081840096996372524230560855903700624271243416909004153690105933983835777939410970027753472000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    long startTime = System.nanoTime();
    BigNumber c = BigNumber.factorial(1000);
    long endTime = System.nanoTime();
    double d = u.timeInSec(endTime, startTime);
    System.out.println("Run time for !1000 = " + d + " secs");
    System.out.println("Number of digits in !1000 = " + c.size());
    //Run time for !1000 = 0.225868438 secs
    //Number of digits in !1000 = 2568
    fact1000.pLn("!1000 = ");
    c.pLn("b = ");
    u.myassert(c.isEqual(fact1000));
    System.out.println("RUN time for !1000 = " + d);
  }

  /*----------------------------------------------------------------
  subtract one test
  -----------------------------------------------------------------*/
  private static void subtract1(String t, String a1, String b1, String e1) {
    BigNumber a = new BigNumber(a1);
    BigNumber b = new BigNumber(b1);
    BigNumber c = a.sub(b);
    System.out.println("-------------------- " + t + " ------------------- ");
    a.pLn("a = ");
    b.pLn("b = ");
    c.pLn("c = ");
    u.myassert(c.isEqual(e1));
  }

  /*----------------------------------------------------------------
simpletest
-----------------------------------------------------------------*/
  private static void simpleSubTests() {
    String a[] = {"5992", "69", "69", "0", "85", "69", "92", "8", "739268", "29649", "31916", "20452", "16367", "20569", "19996"};
    String b[] = {"5994", "69", "0", "69", "69", "85", "8", "92", "268495", "739260", "7503", "16367", "20452", "19996", "20569"};
    String e[] = {"-2", "0", "69", "-69", "16", "-16", "84", "-84", "470773", "-709611", "24413", "4085", "-4085", "573", "-573"};
    int sa = a.length;
    int sb = b.length;
    int se = e.length;
    u.myassert(sa == sb);
    u.myassert(sb == se);
    for (int i = 0; i < sa; ++i) {
      String a1 = a[i];
      String b1 = b[i];
      String e1 = e[i];
      String t = a1 + " - " + b1;
      subtract1(t, a1, b1, e1);
    }
  }


  /*----------------------------------------------------------------
  rsa
  https://defuse.ca/big-number-calculator.htm
  -----------------------------------------------------------------*/
  private static void rsaSubTests() {
    {
      String a = new String("3490529510847650949147849619903898133417764638493387843990820577");
      String b = new String("32769132993266709549961988190834461413177642967992942539798288533");
      String e = new String("-29278603482419058600814138570930563279759878329499554695807467956");
      subtract1("RSA", a, b, e);
    }
    {
      String a = new String("3490529510847650949147849619903898133417764638493387843990820577");
      String b = new String("32769132993266709549961988190834461413177642967992942539798288533");
      String e = new String("29278603482419058600814138570930563279759878329499554695807467956");
      subtract1("RSA", b, a, e);
    }
    {
      String a = new String("0");
      String b = new String("32769132993266709549961988190834461413177642967992942539798288533");
      String e = new String("-32769132993266709549961988190834461413177642967992942539798288533");
      subtract1("RSA", a, b, e);
    }
    {
      String a = new String("3490529510847650949147849619903898133417764638493387843990820577");
      String b = new String("3490529510847650949147849619903898133417764638493387843990820577");
      String e = new String("0");
      subtract1("RSA", a, b, e);
    }
    {
      String a = new String("1");
      String b = new String("3490529510847650949147849619903898133417764638493387843990820577");
      String e = new String("-3490529510847650949147849619903898133417764638493387843990820576");
      subtract1("RSA", a, b, e);
    }
  }

  private static void testSub() {
    simpleSubTests();
    rsaSubTests();
  }

  private static void testBench() {
    System.out.println("-----------Basic----------------");
//    testBasic();
    System.out.println("-----------Addition----------------");
    testAdd();
    System.out.println("-----------subtraction----------------");
    testSub();
    System.out.println("-----------mult----------------");
    testMult();
    System.out.println("-----------random----------------");
    testRandom();
    System.out.println("-----------Factorial----------------");
    testFact();
  }

  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Java version used for this program is " + version);
    System.out.println("BigNumberTester.java starts");
    testBench();
    System.out.println("BigNumberTester.java ends");
  }

}