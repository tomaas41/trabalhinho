import java.util.Scanner;

public class trabalhinhotrabalhado {
    //a)

    //b)
    public static void kilometros() {
        int soma = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                soma += array[j][i];
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("total de km a percorrer\nv%d :    %d km\n", i, soma);
        }
    }
    //c)

    //d)
    public static void cargaDasBaterias(){

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String textDescritivo = sc.nextLine();
        int numVeiculos = sc.nextInt();
        int numDias = sc.nextInt();
        matrizI(lerInfo(numVeiculos, numDias));

    }

    public static int [] [] lerInfo(int numVeiculos, int numDias) {

        Scanner sc= new Scanner(System.in);

        int [][] matriz = new int[numVeiculos][numDias];

        for (int i = 0; i < numVeiculos; i++) {
            for (int j = 0; j < numDias; j++) {
                int kmPercorridos = sc.nextInt();
                matriz[i][j] = kmPercorridos;
            }

        }
       return matriz;
    }
    public static void matrizI(int [][] matriz) {
       for (int i = 0; i < matriz.length; i++) {
           for (int j = 0; j < matriz[i].length; j++) {
               System.out.print(matriz[i][j] + " ");
           }
           System.out.println();
       }

    }


}
