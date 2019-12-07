//package com.lynda;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;
import java.io.File;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * Unit test for simple App.
 */
public class SchubsHTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SchubsHTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws IOException
    {
        return new TestSuite( SchubsHTest.class );
    }

    public static boolean compareFiles() throws IOException
              {
                    //Tarsn idk = new Tarsn();
                    //ars2 idk2 = new Tarsn();
                    //BufferedReader reader4 = new BufferedReader(new FileReader("src/resources/dum.txt"));
                    boolean areEqual = true;
                    int lineNum = 1;
                    int lineNum2 = 1;

                    for(int i = 0; i < 4; i++) {
                          BufferedReader reader1 = new BufferedReader(new FileReader("src" + File.separator + "resources"
                          + File.separator+ "blee" + i + ".txt"));

                        //  File deleteMe = new File("src" + File.separator + "resources"
                        //  + File.separator+ "Decompressed " + i + ".txt");

                          BufferedReader reader2 = new BufferedReader(new FileReader("src" + File.separator + "decompressed" +
                          File.separator + "Decompressed " + i + ".txt"));


                        //BufferedReader reader3 = new BufferedReader(new FileReader("src/resources/OtherOriginal1.txt"));



                        String line1 = reader1.readLine();

                        String line2 = reader2.readLine();

                        //String line3 = reader3.readLine();

                        //String line4 = reader4.readLine();



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
                        /*
                        while (line3 != null || line4 != null)
                        {
                            if(line3 == null || line4 == null)
                            {
                                areEqual = false;

                                break;
                            }
                            else if(! line4.equalsIgnoreCase(line3))
                            {
                                areEqual = false;

                                break;
                            }

                            line3 = reader3.readLine();

                            line4 = reader4.readLine();

                            lineNum2++;
                        }*/
                        //deleteMe.delete();
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
    public void testSchubsH() throws IOException
    {
        String[] args = {"src" + File.separator + "resources" + File.separator + "blee0.txt",
                              "src" + File.separator + "resources" + File.separator + "blee1.txt",
                        "src" + File.separator + "resources" + File.separator + "blee2.txt",
                        "src" + File.separator + "resources" + File.separator + "blee3.txt"};

        String[] args2 = {"src" + File.separator + "compressed" + File.separator + "compressedFile0.txt.hh",
                              "src" + File.separator + "compressed" + File.separator + "compressedFile1.txt.hh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile2.txt.hh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile3.txt.hh"};

      /*  String[] args = {"DeschubsH", "src" + File.separator + "resources" + File.separator + "compressedFile1.txt.hh",
        "src" + File.separator + "resources" + File.separator + "compressedFile2.txt.hh",
        "src" + File.separator + "resources" + File.separator + "compressedFile3.txt.hh",
        "src" + File.separator + "resources" + File.separator + "compressedFile4.txt.hh"};
*/
        SchubsH.main(args);
        //for(int i = 0; i<args.length; i++) {
          //  expand(i);
        //}
        Deschubs.main(args2);
        assertTrue( compareFiles() );

    }
}
