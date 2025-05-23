package TPS.TP1.Algebra_Booleana;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.nextLine();

        while (!entrada.equals("0")) {
            entrada = substituir(entrada); // Substitui variáveis e operadores
            System.out.println(solucao(entrada, 0)); // Resolve a expressão lógica e imprime o resultado
            entrada = scanner.nextLine(); 
        }

        scanner.close();
    }

    static String substituir(String entrada) {
        entrada = entrada.replaceAll(" ", ""); // Remove espaços da entrada
        
        int quantidade = Integer.parseInt(entrada.substring(0, 1)); // Obtém a quantidade de variáveis
        
        int[] valor = new int[quantidade];
        
        // Armazena os valores das variáveis A, B, C...
        for (int i = 0; i < quantidade; i++){
            valor[i] = Integer.parseInt(entrada.substring(i + 1, i + 2));
        }

        entrada = entrada.substring(quantidade + 1); // Remove os números iniciais da string
        
        // Substitui as variáveis pelos valores correspondentes
        if (quantidade > 0){
            entrada = entrada.replace("A", String.valueOf(valor[0]));
        }
        if (quantidade > 1){
            entrada = entrada.replace("B", String.valueOf(valor[1]));
        }
        if (quantidade > 2){
            entrada = entrada.replace("C", String.valueOf(valor[2]));
        } 

        // Substitui operadores textuais pelos símbolos matemáticos
        entrada = entrada.replace("and", "*");
        entrada = entrada.replace("or", "+");
        entrada = entrada.replace("not", "!");
        
        return entrada;
    }
    
    static String solucao(String equacao, int i) {
        int iOperador = identificarOperador(equacao, i);
        
        while (iOperador != -1) { // Resolve operadores recursivamente
            equacao = solucao(equacao, iOperador);
            iOperador = identificarOperador(equacao, i);
        }

        // Aplica as operações lógicas
        if (equacao.charAt(i) == '*') {
            equacao = solucaoAnd(equacao, i);
        } else if (equacao.charAt(i) == '+') {
            equacao = solucaoOr(equacao, i);
        } else if (equacao.charAt(i) == '!') {
            equacao = solucaoNot(equacao, i);
        }

        return equacao;
    }

    static int identificarOperador(String equacao, int i) {
        for (int j = i + 1; j < equacao.length(); j++) {
            if (equacao.charAt(j) == '*' || equacao.charAt(j) == '+' || equacao.charAt(j) == '!') {
                return j; // Retorna a posição do operador encontrado
            }
            if (equacao.charAt(j) == ')') {
                return -1; // Indica fim da expressão
            }
        }
        return -1;
    }
    
    static String solucaoAnd(String equacao, int i) {
        int j = fimEquacao(equacao);
        String resultado = equacao.substring(0, i);
        
        boolean equacao0 = false;
        String equacaoInterna = equacao.substring(i + 2, j);
        
        String[] termos = equacaoInterna.split(",");

        // Se algum termo for 0, o resultado será 0
        for (String termo : termos) {
            if (termo.equals("0")) {
                resultado += "0";
                equacao0 = true;
                break;
            }
        }

        if (!equacao0) {
            resultado += "1"; // Se nenhum termo for 0, o resultado é 1
        }

        resultado += equacao.substring(j + 1);
        
        return resultado;
    }
    
    static String solucaoOr(String equacao, int i) {
        int j = fimEquacao(equacao);
        String resultado = equacao.substring(0, i);
        
        boolean equacao1 = false;
        String equacaoInterna = equacao.substring(i + 2, j);
        
        String[] termos = equacaoInterna.split(",");

        // Se algum termo for 1, o resultado será 1
        for (String termo : termos) {
            if (termo.equals("1")) {
                resultado += "1";
                equacao1 = true;
                break;
            }
        }

        if (!equacao1) {
            resultado += "0"; // Se nenhum termo for 1, o resultado é 0
        }

        resultado += equacao.substring(j + 1);
        return resultado;
    }

    static String solucaoNot(String equacao, int i) {
        int j = fimEquacao(equacao);
        String resultado = equacao.substring(0, i);
        
        String equacaoInterna = equacao.substring(i + 2, j);

        // Inverte o valor lógico
        resultado += equacaoInterna.equals("1") ? "0" : "1";
        resultado += equacao.substring(j + 1);
        
        return resultado;
    }

    static int fimEquacao(String str) {
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == ')'){
                return i; // Retorna a posição do parêntese fechando
            }
        }
        return -1;
    }
}

