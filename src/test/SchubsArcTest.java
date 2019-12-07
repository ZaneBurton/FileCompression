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
public class SchubsArcTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SchubsArcTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws IOException
    {
        return new TestSuite( SchubsArcTest.class );
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
                  BufferedReader reader1 = new BufferedReader(new FileReader("src" + File.separator + "compressed"
                  + File.separator+ "OriginalcompressedFile" + i + ".txt.hh"));

                  BufferedReader reader2 = new BufferedReader(new FileReader("src" + File.separator + "Unarchived" +
                  File.separator + "compressedFile" + i + ".txt.hh"));


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
              }
              //File deleteUnarch = new File("src" + File.separator + "Unarchived");
              //deleteUnarch.delete();

                if(areEqual)
                {
                    return true;
                }
                else
                {
                    return false;
                }


            }




    public static void untarring(String args4) throws IOException {
      BinaryIn in = new BinaryIn( args4 );
      //File deleteMe = new File(args4);
      BinaryOut out = null;
      String line = "";
      new File("src" + File.separator + "Unarchived").mkdirs();
      char sep =  (char) 255;  // all ones 11111111


      while(!in.isEmpty()) {
      try {
              //in = new BinaryIn( args[0]);
              int filenamesize = in.readInt();
              sep = in.readChar();
              String filename="";//"src" + File.separator + "Unarchived" + File.separator;
              //Char theFileNumber = "";
              String filename3 = "";
              boolean digitFound = false;
              int h = 0;
              for (int i = 0; i<filenamesize; i++ ) {
                  filename += in.readChar();
                  h++;
                  for(int g = 0; g<=i; g++) {
                      if(Character.isDigit(filename.charAt(g)) && (digitFound == false)) {
                        filename3 = "src" + File.separator + "Unarchived" + File.separator +
                                    "compressedFile" + filename.charAt(g) + ".txt.hh";
                        digitFound = true;
                    }

                }

              }

              sep = in.readChar();
              long filesize = in.readLong();
              sep = in.readChar();
              System.out.println("Extracting file: " + filename + " (" + filesize + "). )");

              out = new BinaryOut( filename3 );
              for (int i=0; i<filesize; i++)
                  out.write( in.readChar() );
              //deleteMe.delete();

      } finally {
          if (out != null)
              out.close();
        }
    }

    }
    /**
     * Rigourous Test :-)
     */
    public void testSchubsArc() throws IOException
    {
        String[] args = {"src" + File. separator + "archive.zh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile0.txt.hh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile1.txt.hh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile2.txt.hh",
                        "src" + File.separator + "compressed" + File.separator + "compressedFile3.txt.hh"};

        String[] args2 = { "src" + File.separator + "archive.zh", "src" + File.separator + "resources" + File.separator + "blee0.txt",
                        "src" + File.separator + "resources" + File.separator + "blee1.txt",
                        "src" + File.separator + "resources" + File.separator + "blee2.txt",
                        "src" + File.separator + "resources" + File.separator + "blee3.txt"};
        String[] args3 = {"src" + File.separator + "resources" + File.separator + "blee0.txt",
                        "src" + File.separator + "resources" + File.separator + "blee1.txt",
                        "src" + File.separator + "resources" + File.separator + "blee2.txt",
                        "src" + File.separator + "resources" + File.separator + "blee3.txt"};
        //SchubsH.main(args)
        SchubsH.main(args3);
        //SchubsL.main(args2);
        SchubsArc.main(args);
        //Deschubs.main(args[0]);
        //UnTarsn.main(stringFromTarsn);
        //String string = args[0]; //"src/resources/test1.txt";
        untarring(args[0]);

        assertTrue( compareFiles() );

    }
}
