package model;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Function;



import structs.AVL;
import structs.BST;
import structs.RedBlackTree;

public class MasterClass {
    private RedBlackTree<Double,ArrayList<Long>> ts;
    private RedBlackTree<Double,ArrayList<Long>> ftr;
    private RedBlackTree<Double,ArrayList<Long>> trb;
    private RedBlackTree<Double,ArrayList<Long>> orb;
    private BST<Double,ArrayList<Long>> blk;
    private File file;
    private String[] cell;
    
    public final static int TS = 0;
    public final static int FTR = 1;
    public final static int TRB = 2;
    public final static int ORB = 3;
    public final static int BLK = 4;
    

    public MasterClass() {
        ts = new RedBlackTree<>();
        ftr = new RedBlackTree<>();
        trb = new RedBlackTree<>();
        orb = new RedBlackTree<>();
        blk = new BST<>();
    }

    public void readFiles(File file) throws IOException {
        this.file = file;
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        //RandomAccessFile raf = new RandomAccessFile(file, "r");

        br.readLine();

        long pos = 0;
        String temp = br.readLine();
        pos = 1;
        while (temp != null)
        	{
        	cell = temp.split(",");

            if (cell[7].equals("") == false) {
                if (!ts.keyExists(Double.parseDouble(cell[7]))) {
                    ts.insert(Double.parseDouble(cell[7]), new ArrayList<Long>());
                }
                ts.searchValue(Double.parseDouble(cell[7])).add(pos);

            }

            if (cell[9].equals("") == false) {

                if (ftr.keyExists(Double.parseDouble(cell[9]))) {
                    ftr.searchValue(Double.parseDouble(cell[9])).add(pos);

                } else {
                    ftr.insert(Double.parseDouble(cell[9]), new ArrayList<Long>());
                    ftr.searchValue(Double.parseDouble(cell[9])).add(pos);

                }

            }

            if (cell[12].equals("") == false) {

                if (trb.keyExists(Double.parseDouble(cell[12]))) {
                    trb.searchValue(Double.parseDouble(cell[12])).add(pos);

                } else {
                    trb.insert(Double.parseDouble(cell[12]), new ArrayList<Long>());
                    trb.searchValue(Double.parseDouble(cell[12])).add(pos);

                }

            }

            if (!cell[10].equals("")) {

                if (orb.keyExists(Double.parseDouble(cell[10]))) {
                    orb.searchValue(Double.parseDouble(cell[10])).add(pos);

                } else {
                    orb.insert(Double.parseDouble(cell[10]), new ArrayList<Long>());

                    orb.searchValue(Double.parseDouble(cell[10])).add(pos);

                }
            }

            if (cell[15].equals("") == false) {

                if (blk.keyExists(Double.parseDouble(cell[15]))) {
                    blk.search(Double.parseDouble(cell[15])).add(pos);

                } else {
                    blk.add(Double.parseDouble(cell[15]), new ArrayList<Long>());
                    blk.search(Double.parseDouble(cell[15])).add(pos);

                }

            }
            //pos = raf.getFilePointer();
            pos++;
            temp = br.readLine();

        	}

        //raf.close();
        br.close();

    }

    public ArrayList<String> search(int tree, double p) throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Long> position = new ArrayList<Long>();
        
        switch(tree) {
        case TS:
            position = ts.searchValue(p);
            break;
            
        case FTR:
            position = ftr.searchValue(p);
            break;
            
        case TRB:
            position = trb.searchValue(p);
            break;
            
        case ORB:
            position = orb.searchValue(p);
            break;
            
        case BLK:
        	position = blk.search(p);
        	break;
        }

        if (position != null) {
        	
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            
            
            for (int i = 0; i < position.size(); i++) {
            	
            	
            	
                raf.seek(35*(position.get(i)));
                result.add(raf.readLine());
            }
            raf.close();
        }

        return result;
    }

<<<<<<< HEAD
    
    
    
    
    public void exportData(String fileName) throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        PrintWriter pw = new PrintWriter(fileName);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        for (int i = 0; i < cell.length; i++) {
            result.add(raf.readLine());
            pw.println(result);

        }
        pw.close();
        raf.close();
    }

	public RedBlackTree<Double, ArrayList<Long>> getTs() {
=======
	public AVL<Double, ArrayList<Long>> getTs() {
>>>>>>> 83ddb8bc8b1ddb39be6e4df8347bd12085c05517
		return ts;
	}

	public  RedBlackTree<Double, ArrayList<Long>> getFtr() {
		return ftr;
	}

	public  RedBlackTree<Double, ArrayList<Long>> getTrb() {
		return trb;
	}

	public  RedBlackTree<Double, ArrayList<Long>> getOrb() {
		return orb;
	}

	public BST<Double, ArrayList<Long>> getBlk() {
		return blk;
	}

	 public boolean removeBytsFile(double p) throws IOException {

         String paramRemove = Double.toString(p);

         File tempFile = new File("myTempFile.txt");

         if (!file.exists()) {
             file.createNewFile();
         }

         if (!tempFile.exists()) {
             tempFile.createNewFile();
         }

         BufferedReader reader = new BufferedReader(new FileReader(file));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

         String currentLine = reader.readLine();

         while ((currentLine) != null) {
             // trim newline when comparing with lineToRemove
             String trimmedLine = currentLine.trim();

             String[] cell = trimmedLine.split(",");

             String temp = reader.readLine();

             if (!cell[7].equals(paramRemove)) {
                 if (temp == null) {
                     writer.write(currentLine);
                 } else {

                     writer.write(currentLine + "\n");
                 }
             }

         }

         reader.close();
         writer.close();
         boolean val = tempFile.renameTo(file);

         readFiles(this.file);
         return val;

     }

     public void removeByts(double p) throws IOException {

        removeBytsFile(p);

        ArrayList<String> result = new ArrayList<>();
         ArrayList<Long> position;
         position = ts.search(p);

         if (position.size() != 0) {
             RandomAccessFile raf = new RandomAccessFile(file, "r");

             for (int i = 0; i < position.size(); i++) {
                 raf.seek(position.get(i));
                 result.remove(raf.readLine());

             }
             raf.close();
         }
     }

}