import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.net.URL;

class content {
    public static void main(String args[]) {
        LogGet("ログ２", "https://zukan.pokemon.co.jp/detail/", "index.txt");
    }

    public static void LogGet(String logName, String logUrl, String fileName) {
        // File f = new File("html\\" + fileName);
        /*
         * if (f.exists()) { System.out.println("重複したファイル名 " + fileName + " が与えられたため " +
         * logName + " は取得しません。"); return; }
         */
        URL url = null;
        InputStream is = null;
        InputStreamReader isr = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedReader ibr = null;
        try {
            FileWriter filewriter = new FileWriter(new File("html\\" + fileName));
            /*
             * fos = new FileOutputStream("html\\" + fileName); osw = new
             * OutputStreamWriter(fos, "EUC-JP");
             */

            for (int i = 1; i < 1000; i++) {
                url = new URL(logUrl + (i / 100) + "" + ((i / 10) % 10) + "" + (i % 10));
                is = url.openStream();
                isr = new InputStreamReader(is, "EUC-JP");
                ibr = new BufferedReader(isr);

                String test = ibr.readLine();

                while (test != null) {
                    int a = test.indexOf("\"image_m\"");
                    int b = test.indexOf("\"image_s\"");
                    if (a != -1) {
                        System.out.println(i);
                        System.out.println(test.substring(a + 11, b - 2).replace("\\", ""));
                        filewriter.write("[\"" + i / 100 + "." + (i / 10) % 10 + "" + i % 10 + "\", \""
                                + test.substring(a + 11, b - 2).replace("\\", "") + "\"],\n");
                    }
                    test = ibr.readLine();
                }
            }

            filewriter.close();

            /*
             * while (true) { int i = isr.read(); if (i == -1) { break; } osw.write((char)
             * i); } System.out.println(logName + " をファイル名 " + fileName + " で取得しました。");
             */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                isr.close();
                is.close();
                osw.close();
                fos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}