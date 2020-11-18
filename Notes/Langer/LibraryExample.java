package huffman;

import java.util.ArrayList;

/**
 * This class gives an example of how to use the BitSequence and TextSequence
 * classes.   See the main method.
 * 
 * Comp250, assignment3
 * Anton Dubrau, 14.03.2011
 */


public class LibraryExample {

    public static void main(String[] args) {
        //********** Text Sequence **************************
        //we'll create a TextSequence object from a String. We set the size of bytes
        //to 0 since we only really 
        TextSequence text = new TextSequence("<this is a test>", 0);
        
        //store the file
        String fName = "test.txt";
        text.writeToFile(fName);
        
        //read it back into a new Text Seqyence
        TextSequence text2 = TextSequence.readFromFile(fName);

        //print sutff out
        System.out.println("file contents: "+text2.getText()+"\nfile size: "+text2.getByteSize());
        
        
        //********** Bit Sequence *******************************
        BitSequence bits = new BitSequence();
        
        //add bits
        bits.add(true);
        bits.add(false);

        //add a bit Sequence itself - add a copy of the 'bits' variable
        BitSequence tBits = new BitSequence(bits); //create a copy of bits
        bits.add(tBits); //add the bits of tBits to bits

        //add chars and numbers
        bits.add('a');
        bits.add(3);
        
        //add an array
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(4321); //we can add ints to an Integer array
        array.add((int)'b'); //we can add a chars by casting them first to an int
        bits.add(array);

        //print bit Sequence
        System.out.println("bits:  "+bits.toString());
        
        //store bit sequence
        fName = "test.bin";            
        bits.writeToFile(fName);
        
        //load it
        BitSequence bits2 = BitSequence.readFromFile(fName);
        
        //print it again - note how after loading it, there are extra 0 bits at the end
        System.out.println("bits2: "+bits2.toString());
        
        //we have to read back the data in the same order we stored it
        System.out.println(bits.nextBit()); //first two added bits
        System.out.println(bits.nextBit());
        System.out.println(bits.nextBit()); //the copy of the first two bits
        System.out.println(bits.nextBit());
        System.out.println(bits.nextChar()); //get back char
        System.out.println(bits.nextNBitNumber(32)); //an int has 32 bits
        System.out.println(bits.nextList()); //reverse of adding list        
    }

}
