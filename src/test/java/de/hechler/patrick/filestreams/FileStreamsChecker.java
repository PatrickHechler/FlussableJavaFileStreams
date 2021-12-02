package de.hechler.patrick.filestreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import de.hechler.patrick.zeugs.check.Checker;
import de.hechler.patrick.zeugs.check.anotations.Check;
import de.hechler.patrick.zeugs.check.anotations.CheckClass;
import de.hechler.patrick.zeugs.check.anotations.Start;

@CheckClass
public class FileStreamsChecker extends Checker {
	
	@Start(onlyOnce = true)
	private void init() throws IOException {
		Files.createDirectories(Paths.get("./testfiles/output/"));
	}
	
	@Check
	public static void checkWriteAndRead1() throws IOException {
		try (OutputStream pfos = new PatrNativeFileOutputStream("./testfiles/output/out0.txt")) {
			try (PrintStream out = new PrintStream(pfos, true)) {
				out.println("hello file");
				out.println("this is a simple test file");
				out.print("[TEST]: {T, LS, E}");
				out.print(System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T + LS, E}" + System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS + E}");
				out.print(System.lineSeparator() + "[TEST_END]");
				out.print("[TEST]: {T + LS + E}" + System.lineSeparator() + "[TEST_END]");
				out.print("[TEST]: {T, LS + LS + LS, E}");
				out.print(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS[0], LS[1], E}");
				out.print(System.lineSeparator().charAt(0));
				out.print(System.lineSeparator().charAt(1));
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS[0], FLUSH, LS[1], E}");
				out.print(System.lineSeparator().charAt(0));
				out.flush();
				out.print(System.lineSeparator().charAt(1));
				out.print("[TEST_END]");
				out.print("[END]");
			}
		}
		try (InputStream pfis = new PatrNativeFileInputStream("testfiles/output/out0.txt")) {
			try (Scanner in = new Scanner(pfis)) {
				assertEquals("hello file", in.nextLine());
				assertEquals("this is a simple test file", in.nextLine());
				assertEquals("[TEST]: {T, LS, E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T + LS, E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS + E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T + LS + E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS + LS + LS, E}", in.nextLine());
				assertEquals("", in.nextLine());
				assertEquals("", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS[0], LS[1], E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS[0], FLUSH, LS[1], E}", in.nextLine());
				assertEquals("[TEST_END][END]", in.nextLine());
				assertFalse(in.hasNextLine());
				assertFalse(in.hasNext());
			}
		}
	}
	
	@Check
	public static void checkWriteAndRead2() throws IOException {
		try (OutputStream pfos = new PatrFileOutputStream("./testfiles/output/out0.txt")) {
			try (PrintStream out = new PrintStream(pfos, true)) {
				out.println("hello file");
				out.println("this is a simple test file");
				out.print("[TEST]: {T, LS, E}");
				out.print(System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T + LS, E}" + System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS + E}");
				out.print(System.lineSeparator() + "[TEST_END]");
				out.print("[TEST]: {T + LS + E}" + System.lineSeparator() + "[TEST_END]");
				out.print("[TEST]: {T, LS + LS + LS, E}");
				out.print(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS[0], LS[1], E}");
				out.print(System.lineSeparator().charAt(0));
				out.print(System.lineSeparator().charAt(1));
				out.print("[TEST_END]");
				out.print("[TEST]: {T, LS[0], FLUSH, LS[1], E}");
				out.print(System.lineSeparator().charAt(0));
				out.flush();
				out.print(System.lineSeparator().charAt(1));
				out.print("[TEST_END]");
				out.print("[END]");
			}
		}
		try (InputStream pfis = new PatrNativeFileInputStream("testfiles/output/out0.txt")) {
			try (Scanner in = new Scanner(pfis)) {
				assertEquals("hello file", in.nextLine());
				assertEquals("this is a simple test file", in.nextLine());
				assertEquals("[TEST]: {T, LS, E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T + LS, E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS + E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T + LS + E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS + LS + LS, E}", in.nextLine());
				assertEquals("", in.nextLine());
				assertEquals("", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS[0], LS[1], E}", in.nextLine());
				assertEquals("[TEST_END][TEST]: {T, LS[0], FLUSH, LS[1], E}", in.nextLine());
				assertEquals("[TEST_END][END]", in.nextLine());
				assertFalse(in.hasNextLine());
				assertFalse(in.hasNext());
			}
		}
	}
	
}
