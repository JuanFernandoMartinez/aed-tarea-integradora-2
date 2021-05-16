package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.Function;

import au.com.bytecode.opencsv.CSVReader;
import structs.AVL;
import structs.AVLNode;
import structs.BST;
import structs.RedBlackTree;

public class MasterClass {
    private AVL<Double,ArrayList<Long>> ts;
    private AVL<Double,ArrayList<Long>> ftr;
    private AVL<Double,ArrayList<Long>> trb;
    private AVL<Double,ArrayList<Long>> orb;
    private BST<Double,ArrayList<Long>> blk;
    private File file;
    private String[] cell;
    
    public final static int TS = 0;
    public final static int FTR = 1;
    public final static int TRB = 2;
    public final static int ORB = 3;
    public final static int BLK = 4;
    

    public MasterClass() {
        ts = new AVL<>();
        ftr = new AVL<>();
        trb = new AVL<>();
        orb = new AVL<>();
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
                    ts.add(Double.parseDouble(cell[7]), new ArrayList<Long>());
                }
                ts.search(Double.parseDouble(cell[7])).add(pos);

            }

            if (cell[9].equals("") == false) {

                if (ftr.keyExists(Double.parseDouble(cell[9]))) {
                    ftr.search(Double.parseDouble(cell[9])).add(pos);

                } else {
                    ftr.add(Double.parseDouble(cell[9]), new ArrayList<Long>());
                    ftr.search(Double.parseDouble(cell[9])).add(pos);

                }

            }

            if (cell[12].equals("") == false) {

                if (trb.keyExists(Double.parseDouble(cell[12]))) {
                    trb.search(Double.parseDouble(cell[12])).add(pos);

                } else {
                    trb.add(Double.parseDouble(cell[12]), new ArrayList<Long>());
                    trb.search(Double.parseDouble(cell[12])).add(pos);

                }

            }

            if (!cell[10].equals("")) {

                if (orb.keyExists(Double.parseDouble(cell[10]))) {
                    orb.search(Double.parseDouble(cell[10])).add(pos);

                } else {
                    orb.add(Double.parseDouble(cell[10]), new ArrayList<Long>());

                    orb.search(Double.parseDouble(cell[10])).add(pos);

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
    
    public ArrayList<String> searchByRange(int tree, double start, double end) throws IOException
    {
    	ArrayList<String> result = new ArrayList<String>();
    	
    	LinkedList<ArrayList<Long>> positions = new LinkedList<>();
    	
    	switch(tree) {
        case TS:
            positions = ts.searchByRange(start,end);
            break;
            
        case FTR:
            positions = ftr.searchByRange(start,end);
            break;
            
        case TRB:
            positions = trb.searchByRange(start,end);
            break;
            
        case ORB:
            positions = orb.searchByRange(start,end);
            break;
            
        case BLK:
        	positions = blk.searchByRange(start,end);
        	break;
        }
    	
    	if (!positions.isEmpty()) {
    		
    		CSVReader reader = new CSVReader(new FileReader(file));
    		ArrayList<String[]> aux = (ArrayList<String[]>)reader.readAll();
    		
    		for(ArrayList<Long> pos:positions) {
        		
        		for (int i = 0; i < pos.size(); i++) {
        			String a = Arrays.toString(aux.get(pos.get(i).intValue()));
        			a.replace("[", "");
        			a.replace("]", "");
        			result.add(a);
        		}
    		}

    	}
    	
    	return result;
    }

    public ArrayList<String> search(int tree, double p) throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Long> position = new ArrayList<Long>();
        
        switch(tree) {
        case TS:
            position = ts.search(p);
            break;
            
        case FTR:
            position = ftr.search(p);
            break;
            
        case TRB:
            position = trb.search(p);
            break;
            
        case ORB:
            position = orb.search(p);
            break;
            
        case BLK:
        	position = blk.search(p);
        	break;
        }

        if (position != null) {
        	
        	CSVReader reader = new CSVReader(new FileReader(file));
        	Collections.sort(position);
            ArrayList<String[]> aux = (ArrayList<String[]>)reader.readAll();
            for (int i = 0; i < position.size(); i++) {
            	String a = Arrays.toString(aux.get(position.get(i).intValue()));
            	a.replace("[", "");
            	a.replace("]", "");
            	result.add(a);
 
            }
            
        }

        return result;
    }


	public  AVL<Double, ArrayList<Long>> getFtr() {
		return ftr;
	}

	public  AVL<Double, ArrayList<Long>> getTrb() {
		return trb;
	}

	public  AVL<Double, ArrayList<Long>> getOrb() {
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

	public AVL<Double, ArrayList<Long>> getTs() {
		return ts;
	}

	public void setTs(AVL<Double, ArrayList<Long>> ts) {
		this.ts = ts;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String[] getCell() {
		return cell;
	}

	public void setCell(String[] cell) {
		this.cell = cell;
	}

	

	public void setFtr(AVL<Double, ArrayList<Long>> ftr) {
		this.ftr = ftr;
	}

	public void setTrb(AVL<Double, ArrayList<Long>> trb) {
		this.trb = trb;
	}

	public void setOrb(AVL<Double, ArrayList<Long>> orb) {
		this.orb = orb;
	}

	public void setBlk(BST<Double, ArrayList<Long>> blk) {
		this.blk = blk;
	}
     
     

}