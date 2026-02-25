import java.util.Scanner; //lê os dados do teclado

//declaração da classe principal
public class LavacaoCarros {

    //matriz de preços: cada linha representa um tipo de veículo, cada coluna um tipo de serviço
    static int[][] precos = {
            {50, 70, 90}, //preços para veículo pequeno
            {70, 90, 110}, //preços para veículo médio
            {90, 110, 130} //preços para veículo grande
    };
    //método que retorna a descrição do tipo de veículo
    public static String getTipoVeiculo(int tipo) {
        switch (tipo) {
            case 1: return "Pequeno";
            case 2: return "Médio";
            case 3: return "Grande";
            default: return "Desconhecido"; //caso o número seja inválido
        }
    }
    //método que retorna a descrição do tipo de serviço
    public static String getTipoServico(int tipo) {
        switch (tipo) {
            case 1: return "Lavação externa";
            case 2: return "Lavação externa + interna";
            case 3: return "Lavação externa + interna + cera";
            default: return "Desconhecido"; //caso o número seja inválido
        }
    }
    //método principal onde o programa começa a executar
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //lê os dados dos usuarios

        //solicita a quantidade de atendimentos
        System.out.print("Digite a quantidade de atendimentos: ");
        int n = sc.nextInt(); //lê o número de atendimentos
        sc.nextLine(); //limpa o buffer do teclado

        //declara vetores para armazenar os dados dos clientes
        String[] nomes = new String[n]; //nomes dos clientes
        int[] tiposVeiculo = new int[n]; //tipo de veículo de cada cliente
        int[] tiposServico = new int[n]; //tipo de serviço de cada cliente
        int[] valores = new int[n]; //valor pago por cada cliente

        //contadores para estatísticas
        int[] contVeiculo = new int[3]; //contador por tipo de veículo
        int[] contServico = new int[3]; //contador por tipo de serviço
        int totalArrecadado = 0; //soma total dos valores pagos

        //loop para entrada de dados de cada atendimento
        for (int i = 0; i< n; i++) {
            System.out.println("\nAtendimento " + (i + 1)); //mostra o número do atendimento

            System.out.print("Nome do cliente: "); //lê o nome do cliente
            nomes[i] = sc.nextLine(); //lê o nome digitado

            //validação do tipo de veículo (1 a 3)
            do {
                System.out.print("Tipo de veículo (1-Pequeno, 2-Médio, 3-Grande): ");
                tiposVeiculo[i] = sc.nextInt();
            } while (tiposVeiculo[i] < 1 || tiposVeiculo[i] > 3); //repete até ser válido

            //validação do tipo de serviço (1 a 3)
            do {
                System.out.print("Tipo de serviço (1-Lavação externa, 2-Lavação externa + interna ou 3-Lavação externa + interna + cera): ");
                tiposServico[i] = sc.nextInt();
            } while (tiposServico[i] < 1 || tiposServico[i] > 3); //repete até ser válido

            //calcula o valor com base na matriz de preços
            valores[i] = precos[tiposVeiculo[i] - 1][tiposServico[i] - 1];

            //atualiza os contadores
            contVeiculo[tiposVeiculo[i] - 1]++;
            contServico[tiposServico[i] - 1]++;
            totalArrecadado += valores[i]; //soma o valor ao total

            sc.nextLine(); //limpa o buffer
        }
        //exibe os dados de cada atendimento
        System.out.println("\nResumo dos atendimentos:");
        for (int i = 0; i < n; i++) {
            System.out.println("Cliente: " + nomes[i]);
            System.out.println("Tipo de veículo: " + getTipoVeiculo(tiposVeiculo[i]));
            System.out.println("Serviço: " + getTipoServico(tiposServico[i]));
            System.out.println("Valor: R$" + valores[i]);
            System.out.println("-------------------------------------------");
        }
        //exibe as estatísticas de atendimento por tipo de veículo
        System.out.println("\nEstatísticas:");
        for (int i = 0; i < 3; i++) {
            System.out.printf("Percentual de veículos tipo %s: %.2f%%\n", getTipoVeiculo(i + 1), (contVeiculo[i] * 100.0 / n));
        }
        //exibe a estatística de atendimento por tipo de serviço
        for (int i = 0; i < 3; i++) {
            System.out.printf("Percentual de serviço %s: %.2f%%\n", getTipoServico(i + 1), (contServico[i] * 100.0 / n));
        }
        //exibe o total arrecadado
        System.out.println("Total arrecadado: R$" + totalArrecadado);

        //identifica os tipos de veículos mais atendidos
        int maxVeiculo = Math.max(contVeiculo[0], Math.max(contVeiculo[1], contVeiculo[2]));
        System.out.print("Tipo(s) de veículo mais atendido(s): ");
        for (int i = 0; i < 3; i++) {
            if (contVeiculo[i] == maxVeiculo) {
                System.out.print(getTipoVeiculo(i + 1) + " ");
            }
        }
        System.out.println();

        //identifica os serviços mais atendidos
        int maxServico = Math.max(contServico[0], Math.max(contServico[1], contServico[2]));
        System.out.print("Serviço(s) mais atendido(s): ");
        for (int i = 0; i < 3; i++) {
            if (contServico[i] == maxServico) {
                System.out.print(getTipoServico(i + 1) + " ");
            }
        }
        System.out.println();

        //filtro por tipo de veículo
        System.out.print("\nDigite o tipo de veículo para filtrar (1, 2 ou 3): ");
        int filtro = sc.nextInt();
        System.out.println("Clientes com veículo tipo " + getTipoVeiculo(filtro) + ":");
        for (int i = 0; i < n; i++) {
            if (tiposVeiculo[i] == filtro) {
                System.out.println("Cliente: " + nomes[i] + ", Serviço: " + getTipoServico(tiposServico[i]) + ", Valor: R$" + valores[i]);
            }
        }
        sc.close(); //fecha o scanner
    }
}