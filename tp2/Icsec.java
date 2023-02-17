// package Ift3913_Tp1;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class Icsec {

    // create a list that will contain the content of CVS file
    public static List<String[]> cvsContent = new ArrayList<String[]>();
    private static File cvsFileIcsec = new File("Icsec.csv");
    public static File csvFile;

    public static void main(String[] args) throws IOException {

        // File ifExist = new File("Jls.csv");
        // if(ifExist.exists()){
        // ifExist.delete();
        // }
        // Jls jlsFile = new Jls();
        // jlsFile.jlsSearch(args);
        // csvFile = jlsFile.getFile();
        csvFile = new File(args[1]);

        // public static void IcsecSearch() throws IOException {

        Scanner sc = new Scanner(csvFile);
        sc.useDelimiter("\n"); // sets the delimiter pattern

        while (sc.hasNext()) // returns a boolean value
        {
            String[] tmp = sc.next().split(", ");

            // ordre tmp = [path, package, name]
            cvsContent.add(tmp);

        }
        String[] pathNames = new String[cvsContent.size()];
        String[] pakgNames = new String[cvsContent.size()];
        String[] classNames = new String[cvsContent.size()];
        int[] classCounts = new int[cvsContent.size()];

        for (int i = 0; i < cvsContent.size(); i++) {
            {
                // cvsContent.
                classNames[i] = cvsContent.get(i)[2];
                classCounts[i] = 0;

                // tmp= [path, package, name]
                pathNames[i] = cvsContent.get(i)[0];
                pakgNames[i] = cvsContent.get(i)[1];
            }
        }

        sc.close();

        for (String[] strings : cvsContent) {
            countPublic(strings[0], classNames, classCounts);
        }

        PrintWriter pw = new PrintWriter(new FileOutputStream(cvsFileIcsec, false));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < classCounts.length; i++) {
            // System.out.println(
            // cvsContent.get(i)[0] + ", " +
            // pckName + ", " +
            // listElemPathAndName.get(i)[1] + "\n");
            sb.append(pathNames[i]);
            sb.append(", ");
            sb.append(pakgNames[i]);
            sb.append(", ");
            sb.append(classNames[i]);
            sb.append(", ");
            sb.append(classCounts[i]);
            sb.append('\n');

        }
        pw.write(sb.toString());
        pw.close();

        // for size of cvsContent, five countPublic

        // for side classCounts, print it
        // for (int i = 0; i < classCounts.length; i++) {
        // System.out.println(pathNames[i] + ", " + pakgNames[i] + ", " + classNames[i]
        // + ", " + classCounts[i]);
        // }

    }

    // create a function that reads a file and
    // count the number of time a certain word appears
    public static int[] countPublic(String path, String[] className, int nameCount[]) throws IOException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\n");

        while (sc.hasNext()) {
            String tmp = sc.next();
            for (int i = 0; i < className.length; i++) {
                if (tmp.contains(className[i])) {
                    nameCount[i] += 1;
                }
            }
        }
        sc.close();
        return nameCount;
    }

    public File getFile() {
        return cvsFileIcsec;
    }
}