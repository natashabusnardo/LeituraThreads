package ler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LeituraParalelo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ArrayList<Thread> tds = new ArrayList<>();

        try {
            Scanner lerInput = new Scanner(System.in);

            System.out.println("digite o nome");
            String nome = lerInput.nextLine();

            for (int i = 0; i <= 9; i++) {
                int arquivo = i;

                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader ler = new BufferedReader(new FileReader("files/nomes/nomescompletos-0" + arquivo + ".txt"));

                            String nomeArquivo = "";

                            int nLinha = 0;

                            while (nomeArquivo != null) {
                                if (nomeArquivo.toLowerCase().contains(nome.toLowerCase()))
                                    System.out.println("Arquivo: nomescompletos-0" + arquivo + ".txt | Linha: " + nLinha +
                                            "| nome: " + nomeArquivo);

                                nomeArquivo = ler.readLine();

                                nLinha++;
                            }

                            ler.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                tds.add(t1);
            }

            for (Thread thread : tds) {
                thread.start();
            }

            for (Thread thread : tds) {
                thread.join();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long fim = System.currentTimeMillis() - start;

        System.out.println("Demorou " + fim / 1000 + "segundos");
    }
}
