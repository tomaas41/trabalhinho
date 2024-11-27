import java.util.Scanner;

public class trabalhinhotrabalhado {
    public static Scanner sc = new Scanner(System.in);
    final static int MAX_AUTONOMIA = 100;

    public static void main(String[] args) {
        String textDescritivo = sc.nextLine();
        int numVeiculos = sc.nextInt();
        int numDias = sc.nextInt();
        int[][] matriz = matrizConstrutora(numVeiculos, numDias);
        int[][] matrizC = carregamentos(matriz);
        info(matriz);
        kilometros(matriz);
        media(matriz);
        veiculosRecarregados(matrizC);
    }

    public static int[][] matrizConstrutora(int numVeiculos, int numDias) {


        int[][] matrizConstrutora = new int[numVeiculos][numDias];


        for (int i = 0; i < numVeiculos; i++) {
            for (int j = 0; j < numDias; j++) {
                matrizConstrutora[i][j] = sc.nextInt();
            }
        }
        return matrizConstrutora;
    }

    public static void printMattriz(int[][] matriz) {

        System.out.printf("\ndia: ");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.printf("%8d ", j);
        }
        System.out.println();

        System.out.printf("----|");
        for (int k = 0; k < matriz[0].length; k++) {
            System.out.printf("--------|");
        }

        System.out.println();
        for (int i = 0; i < matriz.length; i++) {
            System.out.printf("v%-3d:", i);
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%9d", matriz[i][j]);
            }
            System.out.println();
        }

    }

    //A)
    public static void info(int[][] matriz) {
        System.out.println("a) planeamento (km/dia/veiculo)");


        printMattriz(matriz);
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

    //c)
    public static int[][] carregamentos(int[][] matrizConstrutora) {

        System.out.println("c) recargas das baterias");
        int[][] matrizC = new int[matrizConstrutora.length][matrizConstrutora[0].length];
        int autonomia = MAX_AUTONOMIA;
        int nrCarregamentos = 0;


        for (int dia = 0; dia < matrizConstrutora[0].length; dia++) {
            nrCarregamentos = 0;
            for (int veiculo = 0; veiculo < matrizConstrutora.length; veiculo++) {
                nrCarregamentos = 0;
                int distanciaPercorre = matrizConstrutora[veiculo][dia];

                while (distanciaPercorre > 0) {

                    if (distanciaPercorre >= autonomia) {
                        distanciaPercorre -= autonomia;
                        nrCarregamentos++;
                        autonomia = MAX_AUTONOMIA;
                    } else {
                        autonomia -= distanciaPercorre;
                        distanciaPercorre = 0;
                    }

                }
                matrizC[veiculo][dia] = nrCarregamentos;
            }
        }
        printMattriz(matrizC);
        return matrizC;
    }

    //d
    public static void baterias(int[][] array, int numDias, int numVeiculos, int matriz[][]) {
        System.out.printf("d) carga das baterias\ndia :");
        double[][] carga = new double[array.length][array[0].length];
        for (int i = 0; i < numDias; i++) {
            carga[i][0] = 100 - array[i][0];
            if (carga[i][0] < 0)
                carga[i][0] += 100;
        }
        for (int i = 0; i < numVeiculos; i++) {
            System.out.printf("%8d ", i);
        }
        System.out.printf("\n----|");
        for (int i = 0; i < numVeiculos; i++) {
            System.out.printf("--------|");
        }
        for (int i = 0; i < numDias; i++) {
            for (int j = 1; j < numVeiculos; j++) {
                carga[i][j] = carga[i][j - 1] - array[i][j];
                while (carga[i][j] <= 0) {
                    carga[i][j] += 100;
                    System.out.printf("b) recarga das baterias \ndias");

                    int autonomia = MAX_AUTONOMIA;
                    int carregamentos = 0;


                    for (int p = 0; p < numVeiculos; p++) {

                        for (int k = 0; k < numDias; k++) {

                            int kmPercorridos = matriz[p][k];

                            while (kmPercorridos > 0) {

                                if (kmPercorridos > autonomia) {
                                    kmPercorridos = kmPercorridos - autonomia;
                                    carregamentos++;
                                    autonomia = MAX_AUTONOMIA;
                                } else {
                                    autonomia = autonomia - kmPercorridos;
                                    kmPercorridos = 0;
                                }
                            }
                            System.out.println(matriz[carregamentos][0]);
                        }
                        for (int t = 0; t < array.length; t++) {
                            System.out.printf("\nv%-3d:", t);
                            for (int y = 0; y < array[i].length; y++) {
                                System.out.printf("%7.1f%% ", carga[i][j]);
                            }
                            System.out.println();
                        }
                    }
                }
            }
        }
    }

    //e
    public static void media(int[][] matriz) {
        double[][] matrizD = new double[1][matriz[0].length];

        for (int i = 0; i < matriz[0].length; i++) {
            int soma = 0;

            for (int j = 0; j < matriz.length; j++) {

                soma += matriz[i][j];
            }

            double media = (double) soma / matriz.length;
            matrizD[0][i] = media;
        }

        System.out.printf("\ndia: ");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.printf("%8d ", j);
        }
        System.out.println();

        System.out.printf("---|");
        for (int k = 0; k < matriz[0].length; k++) {
            System.out.printf("--------|");
        }

        System.out.print("\nkm : ");

        for (int i = 0; i < matriz[0].length; i++) {
            System.out.printf("%9.1f", matrizD[0][i]);

        }

        System.out.println();

    }

    //g)
    public static void veiculosRecarregados(int[][] matrizC) {

        int recargasConsecutivas = 0;
        int veiculosMaisRecarregado = 0;

        for (int i = 0; i < matrizC.length; i++) {
            recargasConsecutivas = 0;
            for (int j = 0; j < matrizC[0].length; j++) {
                if (matrizC[i][j] != 0) {
                    recargasConsecutivas++;
                }
            }
            if (recargasConsecutivas > veiculosMaisRecarregado) {
                veiculosMaisRecarregado = recargasConsecutivas;
                System.out.printf("veículos com mais dias consecutivas a necessitar de recarga\n" +
                        "<%d> dias consecutivos, veículos : [V%d]", veiculosMaisRecarregado, i);
            }

        }

    }
}

