package br.com.lavacao; // define o pacote onde a classe está
import java.util.Scanner; // importa a classe Scanner da biblioteca java // permite ler dados do teclado

public class Main { // define a classe principal

    public static void main(String[] args) { // ponto de entrada do programa
        Scanner sc = new Scanner(System.in); //criei um objeto scanner para ler entradas do teclado

        System.out.print("Informe a quantidade de atendimentos: "); // solicita a quantidade de atendimentos e valida se é um número positivo ou não.
        int qtdAtendimentos = lerInteiroValidado(sc, 1, Integer.MAX_VALUE); //chama método que lê um número inteiro e valida se está entre 1 e infinito

        //arrays para contar tipos de veículos e serviços (indice 0 = tipo 1, índice 1 = tipo 2 e etc.)
        int[] contVeiculos = new int[3];
        int[] contServicos = new int[3];

        //Variável para acumular o total arrecadado
        double totalArrecadado = 0.0; // guarda a soma de todos os preços

        //loop para processar cada atendimento
        for (int i = 0; i < qtdAtendimentos; i++) { // repete o bloco para cada atendimento. EX:"enquanto i for menor que qtdAtendimentos conte de 1 em 1 "
            System.out.println("\nAtendimento" + (i + 1)); // mostra qual atendimento está sendo processado

            // le o nome do cliente (usei nextLine para permitir espaços)
            System.out.print("nome do cliente: ");
            String nome = sc.nextLine().trim(); //"trim" foi usado para evitar nomes com espaço no começo ou fim.

            //solicita e valida tipo do veículo (1 a 3)
            System.out.println("tipo do veículo (1-Pequeno, 2-Médio, 3-Grande): ");
            int tipoVeiculo = lerInteiroValidado(sc, 1, 3);

            // solicita e valida tipo do serviço (1 a 3)
            System.out.println("Serviço (1-Externa, 2-Externa+Interna, 3-Externa+Interna+Cera): ");
            int tipoServico = lerInteiroValidado(sc, 1, 3);

            //calcula preço usando tabela
            double preco = calculaPreco(tipoVeiculo, tipoServico);

            //atualiza contadores e total
            contVeiculos[tipoVeiculo - 1]++;
            contServicos[tipoServico - 1]++;
            totalArrecadado += preco;

            //exibe resumo do atendimento
            System.out.println("\nResumo do atendimento:");
            System.out.println("Cliente: " + nome);
            System.out.println("Veículo: " + descricaoVeiculo(tipoVeiculo));
            System.out.println("Serviço: " + descricaoServico(tipoServico));
            System.out.printf("Valor a pagar: R$ %.2f%n" ,preco); //printf foi usado para controlar casas decimais
        }
        // exibe resumo final com estatísticas
        imprimirResumo(contVeiculos, contServicos, totalArrecadado, qtdAtendimentos); // chama método que calcula percentuais e mostra quem foi mais atendido
        sc.close(); // fecha o scanner para liberar recursos
    }
    //Método para ler um inteirovalidado entre min e max
    public static int lerInteiroValidado(Scanner sc, int min, int max) { //lê número, trata erro com try/ catch
        int valor;
        while (true) {
            try {
                //tenta converter a entrada para inteiro
                valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    return valor; // valor válido
                } else {
                    System.out.println("Valor inválido! Informe um número entre " + min + " e " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, Digite o número inteiro."); // trata erro caso o usuário digite letras ou símbolos
            }
        }
    }
    public static String descricaoVeiculo(int tipo) {
        switch (tipo) {
            case 1: return "Pequeno (populares)";
            case 2: return "Médio (SUV e Picape)";
            case 3: return "Grande (Van e Micro-ônibus)";
            default: return "Desconhecido";
        }
    }

    public static String descricaoServico(int tipo) {
        switch (tipo) {
            case 1: return "lavação externa";
            case 2: return "lavação externa + interna";
            case 3: return "lavação externa + interna + cera";
            default: return "Desconhecido";

        }
    }
    // calcula preço com base na tabela
    public static double calculaPreco(int veiculo, int servico) {
        // matriz de preços: linhas = tipo de veículo, colunas = tipo de serviço
        double[][] tabela = {
                {50, 70, 90}, // veículo pequeno
                {70, 90, 110}, // veículo médio
                {90, 110, 130} // veículo grande
        };
        return tabela[veiculo - 1][servico - 1];
    }
    // resumo final com percentuais e mais atendidos
    public static void imprimirResumo(int[] contVeiculos, int[] contServicos, double total, int qtd) { //calcula percentuais e mostra estatísticas
        System.out.println("\n=== RESUMO FINAL ===");
        System.out.printf("Total Arrecadado: R$ %.2f%n", total);

        System.out.println("\nPercentual por tipo de veículo:");
        for (int i = 0; i < contVeiculos.length; i++) {
            double perc = (contVeiculos[i] * 100.0) / qtd;
            System.out.printf("%s: %.2f%%%n", descricaoVeiculo(i + 1), perc);
        }

        System.out.println("\nPercentual por tipo de serviço:");
        for (int i = 0; i < contServicos.length; i++) {
            double perc = (contServicos[i] * 100.0) / qtd;
            System.out.printf("%s: %.2f%%%n", descricaoServico(i + 1), perc);
        }

        //identifica o tipo mais atendido
        int maxVeiculo = maxIndex(contVeiculos);
        int maxServico = maxIndex(contServicos);
        System.out.println("\nTipo de veículo mais atendido: " + descricaoVeiculo(maxVeiculo + 1));
        System.out.println("Serviço mais atendido: " + descricaoServico(maxServico + 1));
    }

    // retorna um índice de maior valor em um array
    public static int maxIndex(int[] arr) {
        int max = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[max]) max = i;
        }
        return max;
    }
}