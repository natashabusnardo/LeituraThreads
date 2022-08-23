package dna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GeraFitaSerial {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            for (int i = 0; i <= 9; i++) {
                int arquivo = i;

                BufferedReader ler = new BufferedReader(new FileReader("files/dna/dna-" + arquivo + ".txt"));

                String dna = ler.readLine();

                FileWriter arq = new FileWriter("files/dna/saida/fitacomplementarDna-" + arquivo + "serial.txt");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long fim = System.currentTimeMillis() - start;

        System.out.println("Código executado em " + fim + " segundos");
    }
}
