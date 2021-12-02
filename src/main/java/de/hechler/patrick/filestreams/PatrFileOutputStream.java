package de.hechler.patrick.filestreams;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PatrFileOutputStream extends FileOutputStream {

	public PatrFileOutputStream(File file, boolean append) throws FileNotFoundException {
		super(file, append);
	}

	public PatrFileOutputStream(File file) throws FileNotFoundException {
		super(file);
	}

	public PatrFileOutputStream(FileDescriptor fdObj) {
		super(fdObj);
	}

	public PatrFileOutputStream(String name, boolean append) throws FileNotFoundException {
		super(name, append);
	}

	public PatrFileOutputStream(String name) throws FileNotFoundException {
		super(name);
	}
	
	@Override
	public void flush() throws IOException {
		super.getFD().sync();
	}
	
}
