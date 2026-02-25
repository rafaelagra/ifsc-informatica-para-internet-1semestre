import java.util.Scanner; // classe scanner lê os dados do teclado

public class TimeBasquete {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // cria um objeto scanner para entrada de dados

        System.out.print("Digite a quantidade de jogadores: ");
        int n = sc.nextInt(); // lê a quantidade de jogadores (N)

        // arrays para armazenar os dados dos jogadores
        String[] nomes = new String[n]; //armazena os nomes
        double[] alturas = new double[n]; //armazena as alturas
        String[] categorias = new String[n]; //armazena as categorias

        //variáveis para cálculos finais
        double somaAlturas = 0; //para calcular a média
        double maiorAltura = 0; //para encontrar a maior altura
        String nomeMaiorAltura = ""; //nome do jogador mais alto
        int contA = 0, contB = 0, contC = 0; // contadores para categorias

        sc.nextLine(); // limpa o buffer do teclado

        //loop para ler os dados de cada jogador
        for (int i = 0; i < n; i++) {
            System.out.println("\nJogador " + (i + 1) + ":");

            System.out.print("Nome: ");
            nomes[i] = sc.nextLine(); //lê o nome do jogador

            System.out.print("Altura (em metros): ");
            alturas[i] = sc.nextDouble(); //lê altura do jogador
            sc.nextLine(); //limpa o buffer

            //determina a categoria com base na altura
            if (alturas[i] >= 2.10) {
                categorias[i] = "A";
                contA++;
            } else if (alturas[i] >= 1.90) {
                categorias[i] = "B";
                contB++;
            } else {
                categorias[i] = "C";
                contC++;
            }
            //soma as alturas para calcular a média depois
            somaAlturas += alturas[i];

            //verifica se esta é a maior altura até agora
            if (alturas[i] > maiorAltura) {
                maiorAltura = alturas[i]; // atualiza maior altura
                nomeMaiorAltura = nomes[i]; // atualiza nome do jogador mais alto
            }
        }
        //calcula a média das alturas do time
        double mediaAlturas = somaAlturas / n;

        //calcula os percentuais de cada categoria
        double percA = (contA * 100.0) / n;
        double percB = (contB * 100.0) / n;
        double percC = (contC * 100.0) / n;

        //exibe os resultados finais.
        System.out.println("\n==== RESULTADOS ====");
        System.out.printf("Média das alturas: %.2f m%n", mediaAlturas); //%.2f formata com 2 casas decimais
        System.out.println("Maior Altura: " + maiorAltura + " m (Jogador: " + nomeMaiorAltura + ")");
        System.out.printf("Percentual categoria A: %.2f%%%n", percA);
        System.out.printf("Percentual categoria B: %.2f%%%n", percB);
        System.out.printf("Percentual categoria C: %.2f%%%n", percC);

        sc.close(); // fecha o scanner
    }
}