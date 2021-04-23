package model;
import exceptions.RepeatedElementException;
import structs.NodeTree;

import java.io.*;
import java.util.ArrayList;

public class MasterClass {
    private NodeTree<Double,ArrayList<Long>> points;
    private NodeTree<Integer,ArrayList<Long>> f;
    private NodeTree<Integer,ArrayList<Long>> a;
    private NodeTree<Integer,ArrayList<Long>> l;
    private NodeTree<Integer,ArrayList<Long>> z;
    private File file;

    public MasterClass() {
        points = new NodeTree<>();
        f = new NodeTree<>();
        a = new NodeTree<>();
        l = new NodeTree<>();
        z = new NodeTree<>();
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

            if(points.search(Double.parseDouble(cell[7])) == null){
                points.add(Double.parseDouble(cell[7]), new ArrayList<Long>());
            }
            points.search(Double.parseDouble(cell[7])).add(pos);

        }while(temp != null);




        raf.close();


    }
}
