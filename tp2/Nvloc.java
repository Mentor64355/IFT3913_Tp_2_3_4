import java.io.*;

class Nvloc {

    // Fonctionne en COMPTANT les commentaire
    // reste a enlever les commentaires
    private static String filename;
    private static int count;

    // constructor
    // public Nvloc(String[] args) {
    // filename = args[0];
    // count = 0;
    // }

    public static void main(String[] args) throws IOException {
        filename = args[0];
        count = 0;
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;

        int tmp = 0;
        while ((line = reader.readLine()) != null) {
            if (line.trim().length() > 0 && !(line.trim().startsWith("//"))) {

                if (line.trim().startsWith("/*")) {
                    tmp = 1;
                }
                if (line.trim().endsWith("*/")) {
                    tmp = 0;
                }
                if (tmp == 0) {
                    count++;
                }
            }
        }
        System.out.println(count);
        reader.close();
    }

    public static int getCount() {
        return count;
    }
}
