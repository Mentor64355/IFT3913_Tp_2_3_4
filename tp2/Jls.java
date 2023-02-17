import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

public class Jls {
    public static List<String[]> listElemPathAndName = new ArrayList<String[]>();
    public static String pckName = "";
    public static int trackPackage = 10000;
    private static File cvsFile = new File("jls.csv");
    private static File folder;

    // public Jls(String[] arg) {
    // folder = new File(arg[0]);
    // }
    // public static void main(String[] args) {
    // System.out.println("rien");
    // }

    public static void main(String[] arg) throws IOException {

        folder = new File(arg[0]);
        // public void jlsSeach() throws IOException {
        // File folder = new File(args[0]);

        File[] listOfFiles = folder.listFiles();
        pckName = folder.getName();
        pckName.replace('\\', '.');
        pckName.replace('/', '.');

        diveInto(listOfFiles, 0, 0);

        String[] tmp = pckName.split("\\.");
        pckName = "";

        for (int i = 0; i <= trackPackage; i++) {
            pckName = pckName + "." + tmp[i];

        }
        pckName = pckName.substring(1);

        PrintWriter pw = new PrintWriter(new FileOutputStream(cvsFile, false));
        StringBuilder sb = new StringBuilder();

        for (String[] strings : listElemPathAndName) {
            System.out.println(
                    strings[0] + ", " +
                            pckName + ", " +
                            strings[1] + "\n");
            sb.append(strings[0]);
            sb.append(", ");
            sb.append(pckName);
            sb.append(", ");
            String changeName = strings[1].replace(".java", "");
            changeName = changeName.replace(".Java", "");
            sb.append(changeName);
            sb.append('\n');

        }
        pw.write(sb.toString());
        pw.close();

        // return cvsFile;

    }

    // visit recursively all the files in the folder and its subfolders

    // Code inspire de :
    // https://www.geeksforgeeks.org/java-program-to-list-all-files-in-a-directory-and-nested-sub-directories/
    public static void diveInto(File[] listOfFiles, int index, int level) throws IOException {

        if (index == listOfFiles.length) {
            return;
        }

        if (listOfFiles[index].isFile()) {
            // System.out.println(listOfFiles[index].getName() + " " +
            // listOfFiles[index].toPath());

            String[] element = { listOfFiles[index].toPath().toString(), listOfFiles[index].getName() };
            listElemPathAndName.add(element);

            if (trackPackage > level) {
                trackPackage = level;
            }

        }

        // for sub-directories
        else if (listOfFiles[index].isDirectory()) {

            pckName = pckName + "." + listOfFiles[index].getName();
            // recursion for sub-directories
            diveInto(listOfFiles[index].listFiles(), 0,
                    level + 1);

        }

        // recursion for main directory
        diveInto(listOfFiles, ++index, level);
    }

    public File getFile() {
        return cvsFile;
    }
}