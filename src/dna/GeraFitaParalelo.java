package dna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GeraFitaParalelo {
    public static void main(String[] args) {
        long star = System.currentTimeMillis();

        ArrayList<Thread> tds = new ArrayList<>();

        try {
            for (int i = 0; i <= 9; i++) {
                int arquivo = i;

                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader ler = new BufferedReader(new FileReader("files/dna/dna-" + arquivo + ".txt"));

                            String dna = ler.readLine();

                            FileWriter arq = new FileWriter("files/dna/saida/fitacomplementarDna-" + arquivo + "paralelo.txt");
                            PrintWriter gravarArq = new PrintWriter(arq);

                            while (dna != null) {
                                for (int j = 0; j < dna.length(); j++) {
                                    if ('A' == dna.charAt(j))
                                        gravarArq.print('T');
                                    else if ('T' == dna.charAt(j))
                                        gravarArq.print('A');
                                    else if ('C' == dna.charAt(j))
                                        gravarArq.print('G');
                                    else if ('G' == dna.charAt(j))
                                        gravarArq.print('C');
                                }

                                gravarArq.print("\n");
                                dna = ler.readLine();
                            }

                            arq.close();
                            ler.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                tds.add(t1);
            }

            long fim = System.currentTimeMillis() - star;

            System.out.println("Demorou: " + fim + " segundos");

            for (Thread thread : tds) {
                thread.start();
            }

            for (Thread thread : tds) {
                thread.join();
            }

            fim = System.currentTimeMillis() - star;

            System.out.println("Demorou: " + fim + " segundos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
