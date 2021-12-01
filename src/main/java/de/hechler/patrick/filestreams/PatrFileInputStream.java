package de.hechler.patrick.filestreams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Native;

public class PatrFileInputStream extends InputStream {
	
	/**
	 * points to the native FILE pointer
	 * 
	 * @implNote {@code -1} when closed
	 */
	@Native
	private final long file;
	
	/**
	 * opens a new file stream
	 * 
	 * @param file
	 *            the name of the file
	 * @return the native FILE pointer
	 * @throws FileNotFoundException
	 *             if the file stream could not be opened
	 */
	private static native long create(String file) throws FileNotFoundException;
	
	public PatrFileInputStream(String file) throws FileNotFoundException {
		this.file = create(file);
	}
	
	
	@Override
	public int read() throws IOException {
		byte[] b = new byte[1];
		int r;
		do {
			r = read(b, 0, 1);
			if (r == -1) {
				return -1;
			}
		} while (r == 0);
		assert r == 1;
		return 0xFF & b[0];
	}
	
	
	@Override
	public native int read(byte[] b, int off, int len) throws IOException;
	
	private native void jump(long pos, int when);
	
	private native long pos();
	
	@Override
	public native void close() throws IOException;
	
	@Override
	public int available() throws IOException {
		long pos = pos();
		jump(0L, 2);
		long end = pos();
		jump(pos, 0);
		long avl = end - pos;
		if (avl <= (long) Integer.MAX_VALUE) {
			return (int) avl;
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
	public long length() {
		long pos = pos();
		jump(0L, 2);
		long end = pos();
		jump(pos, 0);
		return end;
	}
	
	/**
	 * returns <code>true</code> if this stream has been closed and <code>false</code> if not.
	 * 
	 * @return <code>true</code> if this stream has been closed and <code>false</code> if not
	 */
	public boolean isClosed() {
		return this.file == -1;
	}
	
	@Override
	@Deprecated
	protected void finalize() throws Throwable {
		close();
	}
	
}
