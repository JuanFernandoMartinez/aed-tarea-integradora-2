package model;
import exceptions.RepeatedElementException;

import java.io.*;
import java.util.ArrayList;
import structs.BST;

public class MasterClass {
    private BST<Double,ArrayList<Long>> ts;
    private BST<Double,ArrayList<Long>> ftr;
    private BST<Double,ArrayList<Long>> trb;
    private BST<Double,ArrayList<Long>> orb;
    private BST<Double,ArrayList<Long>> blk;
    private File file;

    public MasterClass() {
        ts = new BST<>();
        ftr = new BST<>();
        trb = new BST<>();
        orb = new BST<>();
        blk = new BST<>();
    }

    public void readFiles(File file) throws IOException {
        this.file = file;
        RandomAccessFile raf = new RandomAccessFile(file, "r");

        raf.readLine();

        long pos = raf.getFilePointer();
        String temp = raf.readLine();

        String[] cell;

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

    public ArrayList<String> searchByts(double p) throws IOException {
        ArrayList<String> result = new ArrayList<String>();

        ArrayList<Long> position = ts.search(p);

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


}