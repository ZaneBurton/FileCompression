/**
 * Software Engineering II
 * Fall 2019
 * Brent Reeves
 *
 * begin to copy many files to one, long file
 * execute: java SchubsL archive-name file1 [file2...]
 */

import java.io.IOException;
import java.io.File;
import java.io.*;

public class SchubsL
{
    private static BinaryIn in;
    private static BinaryOut out;
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width

    public static void compress() {

        //String g = in.readString();
        //char[] input = g.toCharArray();
        String input = in.readString();
        //System.out.println("         here         ");

        TST<Integer> st = new TST<Integer>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        //System.out.println("         here before the While loop        ");

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            out.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);            // Scan past s in input.
        }
        out.write(R, W);
        out.close();
    }


    public static void expand() {
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



    public static void main(String[] args) throws IOException {
      int numberOfFiles = args.length;
      for(int i = 0; i<numberOfFiles; i++) {
          String Filename = args[i];
          String Filename2 = "src" + File.separator + "compressed" + File.separator + "compressedFile" + i +  ".txt.ll";
          in = new BinaryIn(Filename);
          out = new BinaryOut(Filename2);

          System.setIn(new FileInputStream( Filename));
          System.setOut(new PrintStream(new FileOutputStream( Filename2)));

          compress();


      }
    //  System.out.println("          " + "hersdlfkhasd;fle" + "         ");

      //  if      (args[0].equals("-")) compress();
      //  else if (args[0].equals("+")) expand();
      //  else throw new RuntimeException("Illegal command line argument");
    }

}
