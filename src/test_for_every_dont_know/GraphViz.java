package test_for_every_dont_know;
//GraphViz.java - a simple API to call dot from Java programs
/*$Id$*/


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader; 

/**
* 
*
* @author 
*/
public class GraphViz {
/**
* The dir. where temporary files will be created.
*/
//private static String tEMPDIR = "/tmp"; // Linux
private static String tEMPDIR = "d:/temp"; // Windows

/**
* Where is your dot program located? It will be called externally.
*/
//private static String dOT = "/usr/bin/dot"; // Linux
private static String 
dOT = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

/**
* The source of the graph written in dot language.
*/
private StringBuilder graph = new StringBuilder();

/**
* Constructor: creates a new GraphViz object that will contain
* a graph.
*/
public GraphViz() {
}

/**
* Returns the graph's source description in dot language.
* @return Source of the graph in dot language.
*/
public String getDotSource() {
 return graph.toString();
}

/**
* Adds a string to the graph's source (without newline).
*@throws line if has error
*@param line line
*/
public void add(final String line) {
 graph.append(line);
}

/**
* Adds a string to the graph's source (with newline).
*@param line line
*/
public void addln(final String line) {
 final String str= line + "\n";
 graph.append(str);
}
/**
* Adds a string to the graph's source (with newline).
*@param line line
*@param label label
*/
public void addlnlabel(final String line, final String label) {
 final String str=line + "[label= " + label + "]" + "\n";
 graph.append(str);
}

/**
* Adds a newline to the graph's source.
*/
public void addln() {
 graph.append('\n');
}

/**
* Returns the graph as an image in binary format.
* @param dotsource Source of the graph to be drawn.
* @param type Type of the output image to be produced,
* @return A byte array containing the image of the graph.
*/
public byte[] getGraph(final String dotsource, final String type) {
 File dot;
 byte[] imgstream = null;

 try {
    dot = writeDotSourceToFile(dotsource);
    if (dot != null) {
       imgstream = getimgstream(dot, type);
       if (!dot.delete()) {
       System.err.println("Warning: " + dot.getAbsolutePath());
       System.err.println(" could not be deleted!");
       }
       return imgstream;
    }
    return null;
 } catch (java.io.IOException ioe) {
 return null;
 }
}

/**
* Writes the graph's image in a file.
* @param img   A byte array containing the image of the graph.
* @param file  Name of the file to where we want to write.
* @return Success: 1, Failure: -1
*/
public int writeGraphToFile(final byte[] img, final String file) {
 File tofile = new File(file);
 return writeGraphToFile(img, tofile);
}

/**
* Writes the graph's image in a file.
* @param img   A byte array containing the image of the graph.
* @param tofile    A File object to where we want to write.
* @return Success: 1, Failure: -1
*/
public int writeGraphToFile(final byte[] img, final File tofile) {
 try {
    FileOutputStream fos = new FileOutputStream(tofile);
    //System.out.println(img);
    fos.write(img);
    fos.close();
} catch (java.io.IOException ioe) {
 ioe.printStackTrace();
 return -1;
 }
 return 1;
}

/**
* It will call the external dot program, and return the image in
* binary format.
* @param dot Source of the graph (in dot language).
* @param type Type of the output image to be produced,
* @return The image of the graph in .gif format.
*/
private byte[] getimgstream(final File dot, final String type) {
 File img;
 byte[] imgstream = null;

try {
    img = File.createTempFile("graph_", "." + type, new File(GraphViz.tEMPDIR));
    Runtime runtime = Runtime.getRuntime();
    // patch by Mike Chenault
    String[] args =
    {dOT, "-T" + type, dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
    Process process = runtime.exec(args);
    process.waitFor();

    FileInputStream fileinstream = new FileInputStream(img.getAbsolutePath());
    imgstream = new byte[fileinstream.available()];
    fileinstream.read(imgstream);
    // Close it if we need to
    if (fileinstream != null) {
    fileinstream.close();
    }
if (!img.delete()) {
       System.err.println("Warning: "
+ img.getAbsolutePath() + " could not be deleted!");
}
} catch (java.io.IOException ioe) {
    System.err.println("Error:    in I/O processing of tempfile in dir "
+ GraphViz.tEMPDIR + "\n");
    System.err.println("       or in calling external command");
    ioe.printStackTrace();
     
 } catch (java.lang.InterruptedException ie) {
    System.err.println("Error: the execution of "
    + "the external program was interrupted");
    ie.printStackTrace();
 }

return imgstream;   }
/**
* Writes the source of the graph in a file, and returns the written file
* as a File object.
* @throws java.io.IOException if has error
* @param str Source of the graph (in dot language).
* @return The file (as a File object) that contains the source of the graph.
*/
public File writeDotSourceToFile(final String str) throws java.io.IOException {
 File temp;
 try {
    temp = File.createTempFile("graph_",
    ".dot.tmp", new File(GraphViz.tEMPDIR));
    FileWriter fout = new FileWriter(temp);
    fout.write(str);
    fout.close();
 } catch (Exception e) {
    System.err.println("Error: I/O error while"
    + " writing the dot source to temp file!");
    return null;
 }
 //System.out.println(temp);
 return temp;
}

/**
* Returns a string that is used to start a graph.
* @return A string to open a graph.
*/
public String startgraph() {
 return "digraph G {";
}

/**
* Returns a string that is used to end a graph.
* @return A string to close a graph.
*/
public String endgraph() {
 return "}";
}

/**
* Read a dOT graph from a text file.
* @param input Input text file containing the dOT graph
* source.
*/
public void readSource(final String input) {
    StringBuilder stringbuilder = new StringBuilder();
    try {
    FileInputStream fis = new FileInputStream(input);
    DataInputStream dis = new DataInputStream(fis);
    BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(dis));
    String line;
    line = bufferedreader.readLine();
    while (line != null) {
     stringbuilder.append(line);
     line = bufferedreader.readLine();
    }
    dis.close();
    } catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
    }
    this.graph = stringbuilder;
    }

}