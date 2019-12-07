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
import java.io.*;

public class Deschubs
{
    public static void mainDeschubs(String[] args5) throws IOException {
      BinaryIn in = new BinaryIn( args5[0] );
      //File deleteMe = new File(args5[0]);
      String[] expandMe = new String[256];
      int expandMeCount = 0;
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
                        expandMe[expandMeCount] = filename3;
                        //System.out.println(expandMe[expandMeCount]);
                        expandMeCount++;
                        digitFound = true;
                    }

                }

              }

              sep = in.readChar();
              long filesize = in.readLong();
              sep = in.readChar();
              //System.out.println("Extracting file: " + filename + " (" + filesize + "). )");

              out = new BinaryOut( filename3 );
              for (int i=0; i<filesize; i++)
                  out.write( in.readChar() );
              //deleteMe.delete();

      } finally {
          if (out != null)
              out.close();
        }
    }
    if(expandMeCount > 1) {
    String[] expandThisArray = new String[expandMeCount-1];
    for(int k = 0; k<expandMeCount-1; k++) {
        expandThisArray[k] = expandMe[k];
      }
    }
    //main(expandThisArray);
    //for(int e = 0; e <expandMeCount-1; e++){
      //  in = new BinaryIn(expandThisArray[e+1]);
        //expandHuffman(e+1);
      //}




  }


    private static BinaryIn in;
    private static BinaryOut out;
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width

  private static class Node implements Comparable<Node> {
          private final char ch;
          private final int freq;
          private final Node left, right;

          Node(char ch, int freq, Node left, Node right) {
              this.ch    = ch;
              this.freq  = freq;
              this.left  = left;
              this.right = right;
          }

          // is the node a leaf node?
          private boolean isLeaf() {
              assert (left == null && right == null) || (left != null && right != null);
              return (left == null && right == null);
          }

          // compare, based on frequency
          public int compareTo(Node that) {
              return this.freq - that.freq;
          }
  }
  public static void expandHuffman(int number) {

          out = new BinaryOut("src" + File.separator + "decompressed" + File.separator + "Decompressed " + number + ".txt");
          // read in Huffman trie from input stream
          Node root = readTrie();

          // number of bytes to write
          int length = in.readInt();

          // decode using the Huffman trie
          for (int i = 0; i < length; i++) {
              Node x = root;
              while (!x.isLeaf()) {
                  boolean bit = in.readBoolean();
                  if (bit) x = x.right;
                  else     x = x.left;
              }
              out.write(x.ch);
          }

          out.flush();

  }





  private static Node readTrie() {
      boolean isLeaf = in.readBoolean();
      if (isLeaf) {
    char x = in.readChar();
    //err_println("t: " + x );
          return new Node(x, -1, null, null);
      }
      else {
    //err_print("f");
          return new Node('\0', -1, readTrie(), readTrie());
      }
  }

  public static void expandLzw(int number) {

        out = new BinaryOut("src" + File.separator + "decompressed" + File.separator + "Decompressed " + number + ".txt");
        String[] st = new String[L];
        int i; // next available codeword value

        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF

        int codeword = in.readInt(W);
        String val = st[codeword];

        while (true) {
            out.write(val);
            codeword = in.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        out.close();
    }

  private static String getFileExtension(File file) {
        String name = file.getName();
        String[] extension = {"0"};
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        extension[0] = name.substring(lastIndexOf);
        //System.out.println(extension[0]);
        return extension[0];
    }

  public static void main(String[] args) throws IOException {

      new File("src" + File.separator + "decompressed").mkdirs();

      //System.out.println(args.length);
      int count = 0;
      for(int i = 0; i<args.length; i++) {
          File sendFile = new File(args[i]);
          String string = getFileExtension(sendFile);

          if(string.equals(".hh")) {
              String Filename = args[i];
              String Filename2 = "src" + File.separator + "compressed" + File.separator + "compressedFile" + i +  ".txt.hh";
              in = new BinaryIn(Filename);
              out = new BinaryOut(Filename2);

              System.setIn(new FileInputStream( Filename));
              System.setOut(new PrintStream(new FileOutputStream( Filename2)));
              expandHuffman(i);

          }
          else if(string.equals(".ll")) {
              String Filename = args[i];
              String Filename2 = "src" + File.separator + "compressed" + File.separator + "compressedFile" + i +  ".txt.ll";
              in = new BinaryIn(Filename);
              out = new BinaryOut(Filename2);

              System.setIn(new FileInputStream( Filename));
              System.setOut(new PrintStream(new FileOutputStream( Filename2)));


              expandLzw(i);
          }
          else if(string.equals(".zh")) {
              mainDeschubs(args);
          }
          else
              System.out.println("File type not supported.");




      }
    /*  File getEx = new File(args[0]);
      String yelo = getFileExtension(getEx);
      System.out.println(yelo);
      if(yelo.equals(".zh")) {
          mainDeschubs(args);
      }*/




  }




}
