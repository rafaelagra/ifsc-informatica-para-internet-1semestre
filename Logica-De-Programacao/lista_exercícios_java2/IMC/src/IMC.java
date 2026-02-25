import java.util.Scanner; //importada para a leitura de dados do usuario via teclado

public class IMC { //declaração da classe principal chamada IMC
    public static void main(String[] args) { //método principal que inicia a execução do programa
        Scanner leia = new Scanner(System.in); //criei um objeto scanner chamado 'leia' para capturar entradas do usuario

        //declaração das variáveis para armazenar peso, altura e valor do IMC
        double peso, alt, vlrIMC;

        //chamei o procedimento 'mensagem' para exibir uma saudação inicial
        mensagem("Olá! bem-vindo ao aplicativo saúde e bem estar! \nEntre com os dados para o cálculo do IMC: ");

        //solicita o peso ao usuário
        System.out.print("\nInforme o peso corporal (em Kg: 72.5 por exemplo): ");
        peso = leia.nextDouble();

        //solicita a altura ao usuário
        System.out.print("Informe a altura (em metros: 1.77 por exemplo): ");
        alt = leia.nextDouble();

        //chama a função que calcula o IMC
        vlrIMC = calculaIMC(peso, alt);

        //exibe os resultados
        System.out.println("\nResultados...:");
        System.out.printf("IMC..........: %.3f\n", vlrIMC); //mostra o valor do IMC com 3 casas decimais
        System.out.printf("classificação: %s \n", interpretaIMC(vlrIMC)); //mostra a classificação do IMC

        mensagem("\nO programa será finalizado!"); //mensagem final
    }
    //procedimento que recebe uma mensagem e a exibe
    public static void mensagem(String texto) {
        System.out.println(texto); //exibe o texto recebido
    }
    //função que calcula o IMC com base no peso e altura
    public static double calculaIMC(double peso, double alt) {
        return peso / (alt * alt); //retorna o resultado do cálculo do IMC
    }
    //função que interpreta o valor do IMC e retorna uma mensagem
    public static String interpretaIMC(double imc) {
        if (imc < 18.5) {
            return "abaixo do peso normal";
        } else if (imc >=18.5 && imc < 25) {
            return "peso normal";
        } else if (imc >= 25 && imc < 30) {
            return "acima do peso";
        } else if (imc >= 30 && imc < 35) {
            return "obesidade grau I";
        } else if (imc >= 35 && imc < 40) {
            return "obesidade grau II";
        } else { //imc >= 40
            return "obesidade grau III";
        }
    }
}