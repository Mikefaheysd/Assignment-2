package fileSystem;

import data_structures.orderedlist;
import fileSystem.FileException;

/**
 * The file system class is used to store a list of directories, sub-directories and files in sorted order.
 * This simulation behaves as a regular file system would without reading or writing to disk. The file system
 * is responsible for catching exceptions if the file does not exist or if a duplicate file or directory is 
 * being created. This file system begins at root level and does not count the root file as being a member 
 * of the total number of items within the file system.
 * @author Michael Fahey
 * @version $Id: FileSystem.java,v 1.8 2013/04/16 05:24:17 masc0674 Exp $
 *
 */

public class FileSystem {
	
	protected class FileNode implements Comparable<FileNode>{
		String Name;
		String Content;

		public FileNode() {
			Name = "";
			Content = "";
		}
		public FileNode(String fileName, String fileContent) {
			Name = fileName;
			Content = fileContent;
		}
		
		@Override
		public int compareTo(FileNode o) {
			// TODO Auto-generated method stub
			
			return Name.compareTo(o.Name);
		}
	}//end node

	protected class DirectoryNode implements Comparable<DirectoryNode>{
		String Name;
		orderedlist<DirectoryNode> childDirectories;
		orderedlist<FileNode> Files;
		//TODO add parent reference
		DirectoryNode Parent;
		
		public DirectoryNode() {
			Name = "";
			Files = new orderedlist<FileNode>();
			childDirectories = new orderedlist<DirectoryNode>();
			
		}
		
		public DirectoryNode(String directoryName) {
			Name = directoryName;
			Files = new orderedlist<FileNode>();
			childDirectories = new orderedlist<DirectoryNode>();
			
		}

		@Override
		public int compareTo(DirectoryNode o) {
			return Name.compareTo(o.Name);
		}

		@Override
		public String toString() {
			return Name;
		}
		
		
	
	}//end node

	
	public int currentSize;
	DirectoryNode currentDirectory;
	orderedlist<DirectoryNode> root;
	
	private static final String DEFAULT_NAME = "root"; 
	

	/**
	 * Constructor, creates a FileSystem instance, 
	 * with the root directory given the name
	 * of the single parameter. 
	 * @param rootDirectory
	 */
	public FileSystem(String rootDirectory) { 
		root = new orderedlist<DirectoryNode>();
		DirectoryNode tmp  = null;
		if(rootDirectory.isEmpty()){
			tmp = new DirectoryNode(DEFAULT_NAME);
		}
		else{
			 tmp = new DirectoryNode(rootDirectory);
		}
		root.add(tmp);
		currentDirectory = tmp;
	} 

	/**
	 * Constructor, creates a FileSystem instance,  
	 * with the root directory given the 
	 * default name DEFAULT_NAME. 
	 */
	public FileSystem() { 
		root = new orderedlist<DirectoryNode>();
		DirectoryNode tmp = new DirectoryNode(DEFAULT_NAME);
			root.add(tmp);
			currentDirectory = tmp;
		
	} 

	/**
	 * Creates a new subdirectory in the current directory 
	 * with the directory name given as a parameter
	 * @param dirName The name of the directory to make
	 * @throws FileException Throws a FileException if a
	 * subdirectory with the same name or a file with the
	 * same name already exists in the current directory. 
	 */
	public void makeDirectory(String dirName) throws FileException { 
		DirectoryNode tmp = new DirectoryNode(dirName);
		tmp.Parent = currentDirectory;
		if(currentDirectory.childDirectories.find(tmp)!=null){
			throw new FileException("Directory already exist");
		}
		currentDirectory.childDirectories.add(tmp);
		currentSize++;
	} 

	/**
	 * Changes the current working directory to the directory
	 * specified in the newDir string. Changes at only one 
	 * level at a time (up or down) are supported. If the user 
	 * specifies the string "..", then the current working 
	 * directory moves upward one level. If any other string 
	 * parameter is given, then the current working directory 
	 * moves down one level to the directory specified.  
	 * If the directory indicated does not exist, then a 
	 * FileException is thrown.
	 * 
	 * @param newDir the name of the directory to move to
	 * @throws FileException an exception is thrown if the
	 * directory does not exist.
	 */
	public void changeDirectory(String newDir) throws FileException { 
		DirectoryNode tmp = new DirectoryNode(newDir);
		DirectoryNode found = currentDirectory.childDirectories.find(tmp);

		if(newDir.equals("..")){
			if(currentDirectory.Parent != null)
			currentDirectory = currentDirectory.Parent;
			else throw new FileException("Parent equals null");
				
		}
		else
			if(found!=null){
			currentDirectory = found;
		}else
		throw new FileException("FileException");
	} 

	/**
	 * Creates a new file in the current working subdirectory. 
	 * If a file or directory already exists with that name, 
	 * then a FileException is thrown. 
	 * @param fName the name of the file to be created.
	 * @param contents the contents of the file.
	 * @throws FileException The exception thrown if the 
	 * file can not be created.
	 */
	public void createFile(String fName, String contents) throws FileException  { 
		FileNode tmpfile = new FileNode(fName, contents);
		
		DirectoryNode tmpdir = new DirectoryNode(fName);
		if(currentDirectory.childDirectories.find(tmpdir)!=null){
			throw new FileException("Directory already exist");
		}
		if(currentDirectory.Files.find(tmpfile)!=null){
			throw new FileException("Directory already exist");
		}
		
		currentDirectory.Files.add(tmpfile);
		currentSize++;
	} 


	/**
	 * Returns true if there exists a directory with the given 
	 * name in the current working subdirectory.  Otherwise 
	 * returns false; 
	 * @param dirName the name of the directory to look for
	 * @return true if the directory exists
	 */
	public boolean directoryExists(String dirName) {
		DirectoryNode tmpdir  = null;
		tmpdir = new DirectoryNode(dirName);
		if(currentDirectory.childDirectories.find(tmpdir)!=null){
			return true;
			}
		
		return false; 
	} 

	/**
	 * Returns true if there exists a file with the given 
	 * name in the current working subdirectory.  Otherwise
	 * returns false
	 * @param fName the file name to look for
	 * @return whether the file exists
	 */
	public boolean fileExists(String fName) {
		
		FileNode tmpfile  = null;
		tmpfile = new FileNode(fName, "");
		
		if(currentDirectory.Files.find(tmpfile)!=null){
			return true;
			}
		return false; 
	} 

	/**
	 * Removes the directory with the given name from the 
	 * current working subdirectory, if it exists, and 
	 * is empty.  Throws a FileException otherwise. 
	 * @param dirName the directory to delete
	 * @throws FileException
	 */
	public void deleteDirectory(String dirName) throws FileException { 
		if(directoryExists(dirName)){
			DirectoryNode tmp  = null;
			tmp = new DirectoryNode(dirName);
			DirectoryNode found = currentDirectory.childDirectories.find(tmp);
			
			if(found.childDirectories.size()==0 && found.Files.size()==0){
				currentDirectory.childDirectories.remove(found);
				currentSize--;
			}
			else{
				throw new FileException("Error");
			}
			
		}
		else{
			throw new FileException("Error");
		}
		
	} 

	/**
	 * Deletes the file with the given name from the 
	 * current working subdirectory, if it exists,
	 * otherwise throws a FileException
	 * @param fName the file to delete
	 * @throws FileException
	 */
	public void deleteFile(String fName) throws FileException { 
		if(fileExists(fName)){
			FileNode tmp  = null;
			tmp = new FileNode(fName, "");
			FileNode found = currentDirectory.Files.find(tmp);
				currentDirectory.Files.remove(found);
				currentSize--;
			}
			
		else{
			throw new FileException("Error");
		}
	}

	/**
	 * Prints the directory and file names in the current working 
	 * subdirectory.  All directories should be printed before
	 * files.  Within each group (directories and files) entries
	 * must be in sorted order (dictionary order) 
	 */
	public void printDirectoryContents() { 
		System.out.println("Current Directory : " + currentDirectory.Name);
		for (DirectoryNode i : currentDirectory.childDirectories)
			System.out.println(i.Name + "/ ");
		for (FileNode i : currentDirectory.Files)
			System.out.println(i.Name + " ");
	} 

	/**
	 * prints the entire contents of the file system tree.  For each 
	 * subdirectory starting with the root or top level directory, 
	 * all directories and files are printed.  Levels of nesting should 
	 * indicated by inserting spaces before the nested directory or 
	 * filename.  Use 5 blank spaces for each level of nesting.  Entries 
	 * in a subdirectory must immediately follow that directory name 
	 * in the listing. 
	 */
	public void printAll() { 
		DirectoryNode temp = currentDirectory;
		currentDirectory = root.peekFirst();
		printAll(0);
		currentDirectory = temp;
	} 
	
	public void printAll(int numberOfSpaces) { 
		for(int x = numberOfSpaces; x > 0; x--)
			System.out.print(" ");	
		System.out.println(currentDirectory.Name + "\\");
		for(FileNode f : currentDirectory.Files){
			for(int x = numberOfSpaces; x > 0; x--)
				System.out.print(" ");	
			System.out.println(f.Name);
		}
			
		for(DirectoryNode d: currentDirectory.childDirectories){
			currentDirectory = d;
			printAll(numberOfSpaces += 4);
		}
	}

	/**
	 * Returns the content of a file stored in the current working 
	 * subdirectory identified by the parameter fName.  Throws
	 * a FileException if the file is not found, or it is a
	 * directory instead of a file. 
	 * @param fName the file whose contents to get
	 * @return the contents of the file
	 * @throws FileException
	 */
	public String getContents(String fName) throws FileException  {
		FileNode tmp = new FileNode(fName, "");
		tmp = currentDirectory.Files.find(tmp);
		if(tmp != null){
			return currentDirectory.Files.find(tmp).Content;
		}
		else throw new FileException("File Does Not Exist");
		
	} 

	/**
	 * Returns the String representation of the current working 
	 * directory.
	 * @return the contents of the directory
	 */

	public String getCurrentDirectory() { 
		return currentDirectory.Name;
	} 

	/**
	 * Returns the current number of subdirectories and files in the 
	 * file system.  A FileSystem with only a root directory has
	 * size == 0 (provided that the root directory is empty). 
	 * @return
	 */
	public int size() {
		return currentSize;
	} 

}
