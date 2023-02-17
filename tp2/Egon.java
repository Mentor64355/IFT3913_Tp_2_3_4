// package Ift3913_Tp1;

// import Ift3913_Tp1.jls;
// import Ift3913_Tp1.nvloc;
// import Ift3913_Tp1.Icsec;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Egon {

    private static List<String[]> cvsContent = new ArrayList<String[]>();
    private static List<String> nclocFiles = new ArrayList<String>();
    private static List<String> IcsecFiles = new ArrayList<String>();
    private static List<Integer> nclocNum = new ArrayList<Integer>();
    private static List<Integer> IcsecNum = new ArrayList<Integer>();
    private static File cvsFileReturn;

    public static void main(String[] args) throws IOException {
        // take as arg the path of the folder to analyse and a threshold
        // call nvlco and icsec for each file in the folder and subfolders
        // and arrange them in a table in decreasing order

        // Icsec icsecFile = new Icsec();
        // icsecFile.IcsecSearch(args);
        // File csvFile = icsecFile.getFile();
        File csvFile = new File(args[2]);

        int seuil = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(csvFile);
        cvsFileReturn = new File("Egon" + seuil + ".csv");
        sc.useDelimiter("\n"); // sets the delimiter pattern

        while (sc.hasNext()) // returns a boolean value
        {
            String[] tmp = sc.next().split(", ");
            // tmp= [path, package, name, Icsec]
            cvsContent.add(tmp);

        }

        for (String[] strings : cvsContent) {

            // contiendra les nom de fichiers (afin de pouvoir les trier et retrouver
            // ceux qui sont dans le seuil)
            IcsecFiles.add(strings[2]);
            nclocFiles.add(strings[2]);

            // va contenir les valeurs de Icsec et ncloc pour les trier

            // TODO IMPORTANT: mettre la bonne appelation quand commun helper sera fait
            // Nvloc nvloc = new Nvloc();
            String[] str = { strings[0], "" };
            Nvloc.main(str);
            // Nvloc.main(str);

            nclocNum.add(Nvloc.getCount());

            IcsecNum.add(Integer.parseInt(strings[3]));
        }

        int sizeNvloc = nclocFiles.size();
        for (int i = 0; i < sizeNvloc; i++) {
            // System.out.println("here" + i);

            for (int j = 0; j < sizeNvloc; j++) {
                // System.out.println("there" + j);

                if (nclocNum.get(i) < nclocNum.get(j)) {

                    int temp = nclocNum.get(i);
                    nclocNum.set(i, nclocNum.get(j));
                    nclocNum.set(j, temp);

                    String tmp = nclocFiles.get(i);
                    nclocFiles.set(i, nclocFiles.get(j));
                    nclocFiles.set(j, tmp);

                }

                if (IcsecNum.get(i) < IcsecNum.get(j)) {
                    int temp = IcsecNum.get(i);
                    IcsecNum.set(i, IcsecNum.get(j));
                    IcsecNum.set(j, temp);

                    String tmp = IcsecFiles.get(i);
                    IcsecFiles.set(i, IcsecFiles.get(j));
                    IcsecFiles.set(j, tmp);
                }
            }
        }

        // x est l'indice a patir du quel on commence a afficher les fichiers
        int x = nclocFiles.size() * seuil / 100;

        List<String> topNvloc = new ArrayList<String>();
        List<String> topIcsec = new ArrayList<String>();

        // on ajoute les fichiers qui sont dans le seuil
        int size = nclocFiles.size();
        int tmp = (size - x);
        for (int i = tmp; i < size; i++) {
            // System.out.println(nclocFiles.get(i) + " " + nclocNum.get(i) + " " + i);

            topNvloc.add(nclocFiles.get(i));
            topIcsec.add(IcsecFiles.get(i));
        }

        // on stock dans topIcsec les fichiers qui sont dans le seuil
        // pour les 2 valeur (icsec et nvloc)
        if (topNvloc.size() != 0 || topIcsec.size() != 0) {
            topIcsec.retainAll(topNvloc);

            // File cvsFileReturn = new File("egon.csv");
            PrintWriter pw = new PrintWriter(new FileOutputStream(cvsFileReturn, false));
            StringBuilder sb = new StringBuilder();

            for (int i = x; i < nclocFiles.size(); i++) {
                for (String[] strings : cvsContent) {

                    // mettre les infos des classes suspecte etre
                    // divine dans le nouveau fichier excel
                    // System.out.println(strings[2] + " | " + nclocFiles.get(i));
                    // System.out.println(strings[2].equals(nclocFiles.get(i)));
                    // System.out.println(topIcsec);
                    if (strings[2].equals(nclocFiles.get(i)) &&
                            topIcsec.contains(nclocFiles.get(i))) {

                        sb.append(strings[0]);
                        sb.append(',');
                        sb.append(strings[1]);
                        sb.append(',');
                        sb.append(strings[2]);
                        sb.append(',');
                        sb.append(strings[3]);
                        sb.append(',');
                        sb.append(nclocNum.get(i));
                        sb.append('\n');
                    }
                }

            }
            pw.write(sb.toString());
            pw.close();
        } else {
            System.out.println("Aucune classe suspectee divine");
        }
    }

    public File getFile() {
        return cvsFileReturn;
    }
}
