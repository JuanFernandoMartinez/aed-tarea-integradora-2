package model;

import java.io.*;
import java.util.ArrayList;

import structs.AVL;
import structs.BST;

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
        RandomAccessFile raf = new RandomAccessFile(file, "r");

        raf.readLine();

        long pos = raf.getFilePointer();
        String temp = raf.readLine();

        do {

            cell = temp.split(",");

            if (cell[7].equals("") == false) {
                if (!ts.keyExists(Double.parseDouble(cell[7]))) {
                    ts.add(Double.parseDouble(cell[7]), new ArrayList<Long>());
                }
                ts.search(Double.parseDouble(cell[7])).add(pos);

            }

            if (cell[10].equals("") == false) {

                if (ftr.keyExists(Double.parseDouble(cell[10]))) {
                    ftr.search(Double.parseDouble(cell[10])).add(pos);

                } else {
                    ftr.add(Double.parseDouble(cell[10]), new ArrayList<Long>());
                    ftr.search(Double.parseDouble(cell[10])).add(pos);

                }

            }

            if (cell[13].equals("") == false) {

                if (trb.keyExists(Double.parseDouble(cell[13]))) {
                    trb.search(Double.parseDouble(cell[13])).add(pos);

                } else {
                    trb.add(Double.parseDouble(cell[13]), new ArrayList<Long>());
                    trb.search(Double.parseDouble(cell[13])).add(pos);

                }

            }

            if (!cell[11].equals("")) {

                if (orb.keyExists(Double.parseDouble(cell[11]))) {
                    orb.search(Double.parseDouble(cell[11])).add(pos);

                } else {
                    orb.add(Double.parseDouble(cell[11]), new ArrayList<Long>());

                    orb.search(Double.parseDouble(cell[11])).add(pos);

                }
            }

            if (cell[16].equals("") == false) {

                if (blk.keyExists(Double.parseDouble(cell[16]))) {
                    blk.search(Double.parseDouble(cell[16])).add(pos);

                } else {
                    blk.add(Double.parseDouble(cell[16]), new ArrayList<Long>());
                    blk.search(Double.parseDouble(cell[16])).add(pos);

                }

            }
            pos = raf.getFilePointer();
            temp = raf.readLine();

        } while (temp != null);

        raf.close();

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
        	position = orb.search(p);
        	break;
        }

        if (position.size() != 0) {
            RandomAccessFile raf = new RandomAccessFile(file, "r");

            for (int i = 0; i < position.size(); i++) {
                raf.seek(position.get(i));
                result.add(raf.readLine());
            }
            raf.close();
        }

        return result;
    }

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
}