import java.util.Scanner;
public class trabalhinhotrabalhado {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String textDescritivo = sc.nextLine();
        int numVeiculos = sc.nextInt();
        int numDias = sc.nextInt();
        int[][] matriz = lerInfo(numVeiculos, numDias);
        kilometros(matriz);
    }
    //a)
    public static int[][] lerInfo(int numVeiculos, int numDias) {
        System.out.println();
        System.out.printf("a) planeamento (km/dia/veículo)\ndia :");

        int[][] matriz = new int[numVeiculos][numDias];
        for (int i = 0; i < numVeiculos; i++) {
            System.out.printf("%8d",i);
            for (int j = 0; j < numDias; j++) {
                int kmPercorridos = sc.nextInt();
                matriz[i][j] = kmPercorridos;
            }
        }
        System.out.printf("----|");
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("--------|");
        }
        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("v%-4d:",i);
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%8d",matriz[i][j]);
            }
            System.out.println();
        }
        return matriz;
    }
    //b)
    public static void kilometros(int[][] array) {
        System.out.printf("b) total de km a percorrer\n");
        int soma[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                soma[i] += array[i][j];
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("v%-3d:%8d km\n",i,soma[i]);
        }
    }
}
    //c)

    //d)

