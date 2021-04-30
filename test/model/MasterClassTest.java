package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MasterClassTest {
	MasterClass mc = new MasterClass();
	
	void setupStage1() throws IOException {
		File f = new File("test/data/testData.csv");
		mc.readFiles(f);
	}
	
	@Test
	void testReadFile() throws IOException {
		setupStage1();
		assertNotNull(mc.getTs().getRoot().getLeft());
		assertNotNull(mc.getFtr().getRoot().getLeft());
		assertNotNull(mc.getTrb().getRoot().getLeft());
		assertNotNull(mc.getOrb().getRoot().getLeft());
		assertNotNull(mc.getBlk().getRoot().getLeft());
		
		assertNotNull(mc.getTs().getRoot().getRight());
		assertNotNull(mc.getFtr().getRoot().getRight());
		assertNotNull(mc.getTrb().getRoot().getRight());
		assertNotNull(mc.getOrb().getRoot().getRight());
		assertNotNull(mc.getBlk().getRoot().getRight());
	}
	
	@Test
	void testSearch() throws IOException {
		setupStage1();
		ArrayList<String> result = mc.search(MasterClass.TRB, 7);
		assertTrue(result.size() > 0);
	}
}
