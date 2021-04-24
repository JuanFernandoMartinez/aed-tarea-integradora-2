package model;
import exceptions.RepeatedElementException;
import structs.NodeTree;

import java.io.*;
import java.util.ArrayList;

public class MasterClass {
    private NodeTree<Double,ArrayList<Long>> ts;
    private NodeTree<Double,ArrayList<Long>> ftr;
    private NodeTree<Double,ArrayList<Long>> trb;
    private NodeTree<Double,ArrayList<Long>> orb;
    private NodeTree<Double,ArrayList<Long>> blk;
    private File file;

    public MasterClass() {
        ts = new NodeTree<>();
        ftr = new NodeTree<>();
        trb = new NodeTree<>();
        orb = new NodeTree<>();
        blk = new NodeTree<>();
    }

    public void readFiles(File file) throws IOException, RepeatedElementException {
        this.file = file;
        RandomAccessFile raf = new RandomAccessFile(file, "r");

        raf.readLine();
        String temp;
        String[] cell;
        long pos;

        do {
            pos = raf.getFilePointer();
            temp = raf.readLine();
            cell = temp.split(",");

            if(ts.search(Double.parseDouble(cell[8])) == null){
                ts.add(Double.parseDouble(cell[8]), new ArrayList<Long>());
            }
            ts.search(Double.parseDouble(cell[8])).add(pos);

            if(ftr.search(Double.parseDouble(cell[10])) == null){
                ftr.add(Double.parseDouble(cell[10]), new ArrayList<Long>());
            }
            ftr.search(Double.parseDouble(cell[10])).add(pos);

            if(trb.search(Double.parseDouble(cell[13])) == null){
                trb.add(Double.parseDouble(cell[13]), new ArrayList<Long>());
            }
            trb.search(Double.parseDouble(cell[13])).add(pos);

            if(orb.search(Double.parseDouble(cell[11])) == null){
                orb.add(Double.parseDouble(cell[11]), new ArrayList<Long>());
            }
            orb.search(Double.parseDouble(cell[11])).add(pos);

            if(blk.search(Double.parseDouble(cell[16])) == null){
                blk.add(Double.parseDouble(cell[16]), new ArrayList<Long>());
            }
            blk.search(Double.parseDouble(cell[16])).add(pos);


        }while(temp != null);




        raf.close();


    }

    public ArrayList<String> searchByts(double p) throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Long> position =  ts.search(p);

        if (position.size() != 0){
            RandomAccessFile raf = new RandomAccessFile(file, "r");

            for (int i = 0; i < position.size(); i++) {
                raf.seek(position.get(i));
                result.add(raf.readLine());
                raf.seek(0);

            }
            raf.close();
        }

        return result;
    }
}
