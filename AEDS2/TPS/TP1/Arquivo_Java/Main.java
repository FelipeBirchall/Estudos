package Arquivo_Java;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Lê um inteiro 'n' que representa a quantidade de números que serão lidos
        int n = scanner.nextInt();

        // Tenta abrir um arquivo de acesso aleatório para leitura e escrita
        try (RandomAccessFile file = new RandomAccessFile("arquivo.txt", "rw")) {

            // Loop para ler 'n' números double da entrada e escrevê-los no arquivo
            for (int i = 0; i < n; i++) {
                file.writeDouble(scanner.nextDouble());
            }

            // Move o ponteiro do arquivo para o final do arquivo
            file.seek(file.length());

            // Loop para ler os números do arquivo de trás para frente
            while (file.getFilePointer() > 0) {
                // Move o ponteiro 8 bytes para trás (tamanho de um double)
                file.seek(file.getFilePointer() - 8); 
                
                // Lê o valor double do arquivo
                double valor = file.readDouble();
                
                // Verifica se o valor é um número inteiro (sem parte decimal)
                if(valor == (int) valor) {
                    // Se for inteiro, imprime como int
                    System.out.println((int)valor);
                } else {
                    // Caso contrário, imprime como double
                    System.out.println(valor);
                }
                
                // Move o ponteiro 8 bytes para trás novamente para continuar a leitura
                file.seek(file.getFilePointer() - 8);
            }
        } catch (IOException e) {
            // Captura e imprime qualquer exceção de I/O que possa ocorrer
            System.out.println("ERRO: " + e.getMessage());
        }

        // Fecha o scanner para liberar recursos
        scanner.close(); 
    }
}