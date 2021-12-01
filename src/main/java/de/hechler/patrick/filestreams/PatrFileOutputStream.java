package de.hechler.patrick.filestreams;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Native;
import java.nio.channels.ClosedChannelException;
import java.nio.file.Paths;

public class PatrFileOutputStream extends OutputStream {
	
	static {
		System.load(Paths.get("./jni-libs/jnifilestreams.dll").toAbsolutePath().toString());
	}
	
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
	 * @param append
	 *            if the new bytes should be appended or not
	 * @return the native FILE pointer
	 * @throws FileNotFoundException
	 *             if the file stream could not be opened
	 */
	private static native long create(String file, boolean append) throws FileNotFoundException;
	
	/**
	 * creates a new {@link PatrFileOutputStream} to the given file the old file will be overwritten if it exists
	 * 
	 * @param file
	 *            the file to be created/overwritten
	 * @throws FileNotFoundException
	 *             if the file-stream could not be opened
	 */
	public PatrFileOutputStream(String file) throws FileNotFoundException {
		this(file, false);
	}
	
	public PatrFileOutputStream(String file, boolean append) throws FileNotFoundException {
		this.file = create(file, append);
	}
	
	
	
	@Override
	public void write(int b) throws IOException, ClosedChannelException {
		write(new byte[] {(byte) b });
	}
	
	@Override
	public void write(byte[] b) throws IOException, ClosedChannelException, IndexOutOfBoundsException {
		write(b, 0, b.length);
	}
	
	@Override
	public native void write(byte[] b, int off, int len) throws IOException, ClosedChannelException;
	
	@Override
	public native void flush() throws IOException, ClosedChannelException;
	
	/**
	 * closes and frees the underling file stream.<br>
	 * and sets {@link #file} to {@code -1}
	 */
	@Override
	public native void close() throws IOException;
	
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
