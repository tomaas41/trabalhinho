import java.util.Scanner;
public class trabalhinhotrabalhado {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String textDescritivo = sc.nextLine();
        int numVeiculos = sc.nextInt();
        int numDias = sc.nextInt();
        int[][] matriz = lerInfo(numVeiculos, numDias);
        kilometros(matriz);
        baterias(matriz);
    }

    //a)
    public static int[][] lerInfo(int numVeiculos, int numDias) {
        System.out.println();
        System.out.printf("a) planeamento (km/dia/veículo)\ndia :");

        int[][] matriz = new int[numVeiculos][numDias];
        for (int i = 0; i < numVeiculos; i++) {
            System.out.printf("%8d", i);
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
            System.out.printf("v%-4d:", i);
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%8d", matriz[i][j]);
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
            System.out.printf("v%-3d:%8d km\n", i, soma[i]);
        }
    }

    //d)
    public static void baterias(int[][] array) {
        System.out.printf("d) carga das baterias\ndia :");
        double[][] carga = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            carga[i][0] = 100 - array[i][0];
            if (carga[i][0] < 0)
                carga[i][0] += 100;
        }
        for (int i = 0; i < array[0].length; i++) {
            System.out.printf("%8d ", i);
        }
        System.out.printf("\n----|");
       for (int i = 0; i < array[0].length; i++) {
           System.out.printf("--------|");
       }
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                carga[i][j] = carga[i][j - 1] - array[i][j];
                while(carga[i][j] <= 0){
                    carga[i][j] += 100;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("\nv%-3d:", i);
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%7.1f%% ",carga[i][j]);
            }
            System.out.println();
        }
    }


}

