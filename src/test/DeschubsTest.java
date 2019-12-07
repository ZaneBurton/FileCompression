//package com.lynda;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Unit test for simple App.
 */
public class DeschubsTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DeschubsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws IOException
    {
        return new TestSuite( DeschubsTest.class );
    }
    public static boolean compareUnarchivedFiles() throws IOException
        {
            //Tarsn idk = new Tarsn();
            //ars2 idk2 = new Tarsn();
            //BufferedReader reader4 = new BufferedReader(new FileReader("src/resources/dum.txt"));
            boolean areEqual = true;
            int lineNum = 1;
            int lineNum2 = 1;

            for(int i = 0; i < 4; i++) {
              BufferedReader reader1 = new BufferedReader(new FileReader("src" + File.separator + "Unarchived" + File.separator +
                                                                         "compressedFile" + i + ".txt.hh"));

              BufferedReader reader2 = new BufferedReader(new FileReader("src" + File.separator + "resources" + File.separator +
                                                                          "blee" + i + ".txt"));




            String line1 = reader1.readLine();

            String line2 = reader2.readLine();




            while (line1 != null || line2 != null)
            {
                if(line1 == null || line2 == null)
                {
                    areEqual = false;

                    break;
                }
                else if(! line1.equalsIgnoreCase(line2))
                {
                    areEqual = false;

                    break;
                }

                line1 = reader1.readLine();

                line2 = reader2.readLine();

                lineNum++;
            }

          }

            if(areEqual)
            {
                return true;
            }
            else
            {
                return false;
            }


        }

    public static boolean compareFiles() throws IOException
        {

            boolean areEqual = true;
            int lineNum = 1;
            int lineNum2 = 1;

            for(int i = 0; i < 4; i++) {
              BufferedReader reader1 = new BufferedReader(new FileReader("src" + File.separator + "decompressed" + File.separator +
                                                                         "Decompressed " + i + ".txt"));

              BufferedReader reader2 = new BufferedReader(new FileReader("src" + File.separator + "resources" + File.separator +
                                                                          "blee" + i + ".txt"));




            String line1 = reader1.readLine();

            String line2 = reader2.readLine();





            while (line1 != null || line2 != null)
            {
                if(line1 == null || line2 == null)
                {
                    areEqual = false;

                    break;
                }
                else if(! line1.equalsIgnoreCase(line2))
                {
                    areEqual = false;

                    break;
                }

                line1 = reader1.readLine();

                line2 = reader2.readLine();

                lineNum++;
            }

          }

            if(areEqual)
            {
                return true;
            }
            else
            {
                return false;
            }


        }



    /**
     * Rigourous Test :-)
     */
     public void testHuffmanExpand() throws IOException {
            String[] huffmanTest = { "src" + File.separator + "compressed" + File.separator + "compressedFile0.txt.hh",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile1.txt.hh",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile2.txt.hh",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile3.txt.hh"};
            String[] args8 = { "src" + File.separator + "resources" + File.separator + "blee0.txt",
                         "src" + File.separator + "resources" + File.separator + "blee1.txt",
                         "src" + File.separator + "resources" + File.separator + "blee2.txt",
                         "src" + File.separator + "resources" + File.separator + "blee3.txt"};
            SchubsH.main(args8);
            Deschubs.main(huffmanTest);
            assertTrue( compareFiles() );

     }

     public void testLzw() throws IOException {
            String[] lzwTest = { "src" + File.separator + "compressed" + File.separator + "compressedFile0.txt.ll",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile1.txt.ll",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile2.txt.ll",
                         "src" + File.separator + "compressed" + File.separator + "compressedFile3.txt.ll"};
            String[] args3 = { "src" + File.separator + "resources" + File.separator + "blee0.txt",
                         "src" + File.separator + "resources" + File.separator + "blee1.txt",
                         "src" + File.separator + "resources" + File.separator + "blee2.txt",
                         "src" + File.separator + "resources" + File.separator + "blee3.txt"};

            SchubsL.main(args3);
            Deschubs.main(lzwTest);
            assertTrue( compareFiles() );

     }

     public void testDeschubs() throws IOException
        {
            String[] huffmanTest = { "src" + File.separator + "compressed" + File.separator + "compressedFile0.txt.hh",
                            "src" + File.separator + "compressed" + File.separator + "compressedFile1.txt.hh",
                            "src" + File.separator + "compressed" + File.separator + "compressedFile2.txt.hh",
                            "src" + File.separator + "compressed" + File.separator + "compressedFile3.txt.hh"};
            String[] zhTest = {"src" + File.separator + "archive.zh"};

            SchubsArc.main(zhTest);
            Deschubs.main(zhTest);


            assertTrue( compareUnarchivedFiles() );

        }
}
