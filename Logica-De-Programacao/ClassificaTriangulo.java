import java.util.Scanner; // serve para ler dados digitados pelo usuário

public class ClassificaTriangulo { // classe principal do programa
    public static void main(String[] args) { // método principal
        Scanner input = new Scanner(System.in); //lê valores digitados no teclado
        
        // leitura dos lados do triângulo
        System.out.print("Digite o primeiro lado: ");
        double lado1 = input.nextDouble(); //le o número digitado e armazena na variável
        
        System.out.print("Digite o segundo lado: ");
        double lado2 = input.nextDouble(); //le o número digitado e armazena na variável
        
        System.out.print("Digite o terceiro lado: ");
        double lado3 = input.nextDouble(); //le o número digitado e armazena na variável
        
        input.close(); // fecha o scanner para liberar memória
        
        // verificando se é triângulo
        if (lado1 + lado2 > lado3 &&
            lado1 + lado3 > lado2 &&
            lado2 + lado3 > lado1) {
                
                System.out.println("Os lados formam um triângulo.");
       } else {
           System.out.println("Os lados NÃO formam um triângulo.");
       }
       // Classificação do triângulo
       if (lado1 == lado2 && lado2 == lado3) {
           System.out.println("O triângulo é equilátero.");
       } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3)
   {         
        System.out.println("O triângulo é isóceles");
   } else {
       System.out.println("O triângulo é escaleno.");
   }
        
    }
}