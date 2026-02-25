import java.util.Scanner; //permite a leitura dos dados no teclado

public class TimeBasquete2 { //classe principal do programa
    public static void main(String[] args) { //classe principal do programa
        Scanner sc = new Scanner(System.in); //captura entradas do usuário

        System.out.println("Olá,Monte seu time de basquete com características e estatísticas"); //mensagem inicial do programa
        System.out.print("Informe a quantidade de jogadores: ");
        int N = sc.nextInt(); //lê um número inteiro e armazena em N
        sc.nextLine(); //consome a quebra de linha pendente após nextInt()

        //criei dois vetores para armazenar os dados dos jogadores:
        String[] nomes = new String[N];// para nomes (tipo string)
        double[] alturas = new double[N];//para alturas (tipo double)

        //loop para ler os dados de cada jogador
        for (int i = 0; i < N; i++) {
            System.out.println("Jogador " + (i + 1) + ":"); //exibe qual jogador está sendo cadastrado

            //lê o nome do jogador
            System.out.print("Nome: ");
            nomes[i] = sc.nextLine(); //armazena o nome no vetor 'nomes'

            //lê a altura do jogador
            System.out.print("Altura (em metros): ");
            alturas[i] = sc.nextDouble(); //armazena a altura nop vetor 'alturas'
            sc.nextLine(); //consome a quebra de linha pendente após nextDouble()
        }

        //declarei variáveis para estatísticas
        int catA = 0; //contador para a categoria A (altura >= 2.10)
        int catB = 0; //contador para categoria B (1.90 <= altura < 2.10)
        int catC = 0; //contador para categoria C (altura < 1.90)
        double somaAlturas = 0; // para calcular a média das alturas
        double maiorAltura = 0; // para armazenar a maior altura encontrada

        //loop para processar os dados e calcular estatísticas
        for (int i = 0; i < N; i++) {
            somaAlturas += alturas[i]; //adiciona a altura atual á soma total

            //verifica a categoria do jogador com base na altura
            if (alturas[i] > 2.10) {
                catA++; //incrementa contador da categoria A
            } else if (alturas[i] >= 1.90) {
                catB++; //incrementa contador da categoria B
            } else {
                catC++; //incrementa contador da categoria C
            }

            //atualiza a maior altura encontrada até agora
            if (alturas[i] > maiorAltura) {
                maiorAltura = alturas[i];
            }
        }
        //exibe o relatório final
        System.out.println("\n--- RELATÓRIO ---");

        //lista de todos os jogadores com suas respectivas alturas
        System.out.println("jogadores e alturas:");
        for (int i = 0; i < N; i++) {
            System.out.println(nomes[i] + " - " + alturas[i] + "m");
        }
        // calcula a Média das alturas
        double media = somaAlturas / N;
        System.out.printf("Média das alturas: %.2f m%n", media); //exibe a média formatada com duas casas decimais

        //exibe os percentuais de cada categoria
        System.out.printf("percentual categoria A: %.2f%%%n",(catA * 100.0 / N));
        System.out.printf("percentual categoria B: %.2f%%%n", (catB * 100.0 / N));
        System.out.printf("percentual categoria C: %.2f%%%n", (catC * 100.0 / N));

        //lista de jogadores com altura >= 2.00
        System.out.println("\nJogadores com altura >= 2.oom:");
        for (int i = 0; i < N; i++) {
            if (alturas[i] >= 2.00) {
                System.out.println(nomes[i] + " - " + alturas[i] + "m");
            }
        }

        //exibe a maior altura encontrada
        System.out.println("\nMaior altura: " + maiorAltura + "m");

        //exibe os jogadores que possuem maior altura
        System.out.println("Jogador(es) com maior altura:");
        for (int i = 0; i < N; i++) {
            if (alturas[i] == maiorAltura) {
                System.out.println(nomes[i]);
            }
        }

        sc.close();  //fecha o scanner
    }
}