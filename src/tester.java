import fileSystem.FileSystem;
import data_structures.orderedlist;
import fileSystem.FileException;

public class tester {
	
public static void main(String[] args) {
		
		System.out.println("Testing the linked list");
		try {
		FileSystem test = new FileSystem("root");
		test.makeDirectory("test");
		test.createFile("home", "yes");
		test.createFile("bome", "yes");
		test.createFile("hme", "yes");
		test.makeDirectory("s");
		test.createFile("ome", "yes");
		test.createFile("bme", "yes");
		test.createFile("he", "yes");
		test.makeDirectory("b");
		
		test.changeDirectory("test");
		System.out.println(test.getCurrentDirectory());
		test.printDirectoryContents();
		System.out.println(test.currentSize);
		test.changeDirectory("..");
		System.out.println("PWD = " + test.getCurrentDirectory());

		test.deleteDirectory("test");
		System.out.println(test.currentSize);
		System.out.println(test.getCurrentDirectory());
		test.printAll();
		System.out.println("PWD = " + test.getCurrentDirectory());
		System.out.println(test.getContents("home"));

}
		catch (FileException e){
		System.out.println("Error");
		e.printStackTrace();
		}
}
}