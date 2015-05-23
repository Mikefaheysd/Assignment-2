package fileSystem;

public class FileException extends Exception {
	
	/**
	 * 
	 * creates a new exception called FileSystemException, when this exception is raised it will return
	 * "FileSystemException" this is used to inform the user that the operation is not supported.
	 * The FileException can be raised from many instances, such as trying to remove a file that does not exist
	 * or trying to make directories with the same name.
	 */
	private static final long serialVersionUID = -2081922331823382817L;

		
	      public FileException() {}

	      //Constructor that accepts a message
	      public FileException(String message)
	      {
	         super("FileSystemException");
	      }

 }

