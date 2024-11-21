import java.util.Scanner;

public class trabalhinhotrabalhado {
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
