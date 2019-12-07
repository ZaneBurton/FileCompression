/**
 * Zane Burton
 * Software Engineering II
 * Fall 2019
 * Brent Reeves
 *
 * begin to copy many files to one, long file
 * execute: java Tars archive-name file1 [file2...]
 */

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

public class SchubsArc
{
    public static void main(String[] args) throws IOException {

      String[] args2 = { "src" + File.separator + "archive.zh", "src" + File.separator + "resources" + File.separator + "blee0.txt",
                                    "src" + File.separator + "resources" + File.separator + "blee1.txt",
                                    "src" + File.separator + "resources" + File.separator + "blee2.txt",
                                    "src" + File.separator + "resources" + File.separator + "blee3.txt"};
      for(int i = 0; i<args.length; i++) {
          args[i] = args2[i];
      }
    	File in1 = null;
      File in2 = null;
      int numberOfFiles = args.length;
      String[] original = new String[256];
      String[] pickMe = new String[256];
      String[] arrayNew = new String[args.length-1];
      for(int j = 0; j<args.length-1; j++)
          arrayNew[j] = args[j+1];
      SchubsH.main(arrayNew);


      for(int i = 0; i < numberOfFiles-1; i++) {
          original[i] = arrayNew[i];
          BinaryIn source = new BinaryIn(original[i]);


      String original2 = arrayNew[2];


      BinaryIn source2 = new BinaryIn(original2);
      BinaryOut out2 = null; //BinaryOut(original);
      BinaryOut out3 = null;//BinaryOut(original2);
      out2 = new BinaryOut("src" + File.separator +"compressed" + File.separator + "OriginalCompressedFile" + i + ".txt.hh");
      //out3 = new BinaryOut("src/resources/OtherOriginal" + i +".txt");

      //while(!source.isEmpty()) {
        try {
            while(!source.isEmpty()){
            out2.write(source.readChar());
          }
        } finally {
            if (out2 != null)
                out2.close();
            ;
        }
    //  }
    /*
        try {
            while(!source2.isEmpty()) {
            out3.write(source2.readChar());
          }
        } finally {
          if (out3 != null)
              out3.close();
            ;
        }*/
      }



    	BinaryIn bin1 = null;
    	BinaryOut out = null;

      //BinaryIn bin2 = new BinaryIn(args[1])
      //BinaryIn bin3 = new BinaryIn(args[2])
      //String stringFromSchubsArc = args[0];

    	char separator =  (char) 255;  // all ones 11111111

    	try {
          //
          out = new BinaryOut(args2[0]);
    	    // notice the input files start at arg[1], not arg[0]
          //String[] arrayNew = new String[args.length-1];
          for(int j = 0; j<args.length-1; j++)
              arrayNew[j] = args[j+1];
          for(int i = 0; i<numberOfFiles-1; i++) {
        	    in1 = new File(arrayNew[i]);
        	    if (!in1.exists() || !in1.isFile()) return;


        	    long filesize = in1.length();
        	    int filenamesize = arrayNew[i].length();




        	    // archive file is at args[0]
        	    // layout: file-name-length, separator, filename, file-size, file


        	    out.write(filenamesize);
        	    out.write(separator);

        	    out.write(arrayNew[i]);
        	    out.write(separator);

              out.write(filesize);
              out.write(separator);

              bin1 = new BinaryIn(arrayNew[i]);
              while(!bin1.isEmpty())
                out.write(bin1.readChar());

              //in1.delete();
          //in2.delete();
        }
       // write the file size
      // write the separator
       // now copy the input file to the output, one character at a time

    	} finally {
    	    if (out != null)
    		out.close();
    	}
        }

}
