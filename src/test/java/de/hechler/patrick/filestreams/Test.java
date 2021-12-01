package de.hechler.patrick.filestreams;

import de.hechler.patrick.zeugs.check.BigCheckResult;
import de.hechler.patrick.zeugs.check.Checker;

public class Test {
	
	public void testname() throws Exception {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		BigCheckResult bcr = Checker.checkAll(true, FileStreamsChecker.class);
		bcr.print();
		if (bcr.wentUnexpected()) {
			bcr.detailedPrintUnexpected(System.err);
			throw new AssertionError(bcr.toString());
		}
	}
	
}
