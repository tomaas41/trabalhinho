import java.util.Scanner;

public class trabalhinhotrabalhado {
    public static Scanner sc = new Scanner(System.in);
    final static int MAX_AUTONOMIA = 100;

    public static void main(String[] args) {
        String textDescritivo = sc.nextLine();
        int numVeiculos = sc.nextInt();
        int numDias = sc.nextInt();
        int[][] matriz = matrizConstrutora(numVeiculos, numDias);
        info(matriz);
        kilometros(matriz);
        int[][] matrizC = carregamentos(matriz);
        baterias(matriz);
        acimaDaMédia(matriz,media(matriz));
        veiculosRecarregados(matrizC);
        diaMaisTardio(matrizC);
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

        System.out.printf("\ndia :");
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
                System.out.printf("%8d ", matriz[i][j]);
            }
            System.out.println();
        }

    }

    //a)
    public static void info(int[][] matriz) {
        System.out.printf("a) planeamento (km/dia/veiculo)");


        printMattriz(matriz);
    }

    //b)
    public static void kilometros(int[][] array) {
        System.out.printf("\nb) total de km a percorrer\n");
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

        System.out.printf("\nc) recargas das baterias");
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

    //d)
    public static void baterias(int[][] array) {
        System.out.printf("\nd) carga das baterias\ndia :");
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
        }
        System.out.println();
    }

    //e)
    public static double[] media(int[][] matriz) {
        System.out.printf("\ne) média de km diários da frota");
        double[] matrizD = new double[matriz[0].length];

        for (int i = 0; i < matriz[0].length; i++) {
            int soma = 0;

            for (int j = 0; j < matriz.length; j++) {

                soma += matriz[j][i];
            }

            double media = (double) soma / matriz.length;
            matrizD[i] = media;
        }

        System.out.printf("\ndia: ");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.printf("%8d ", j);
        }
        System.out.println();

        System.out.printf("----|");
        for (int k = 0; k < matriz[0].length; k++) {
            System.out.printf("--------|");
        }

        System.out.print("\nkm : ");

        for (int i = 0; i < matriz[0].length; i++) {
            System.out.printf("%8.1f ", matrizD[i]);

        }

        System.out.println();
        return matrizD;

    }
    //f)
    public static void acimaDaMédia(int[][] matriz,double[] matrizD) {
        int veiculosAcima=0;
        int[] contagem = new int[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j]>matrizD[i])
                    contagem[i]++;
            }
            if(contagem[i]==matriz[0].length)
                veiculosAcima++;
        }
        System.out.printf("\nf) deslocações sempre acima da média diária\n<%d> veículos : ", veiculosAcima);
        for (int i = 0; i < contagem.length; i++) {
            if (contagem[i]==matriz[0].length)
                System.out.printf("[v%d]", i);
        }
        System.out.println();
    }
    //g)
    public static void veiculosRecarregados(int[][] matrizC) {
        System.out.printf("\ng) veículos com mais dias consecutivas a necessitar de recarga");
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
            }
        }
        System.out.printf("\n<%d> dias consecutivos, veículos : ", veiculosMaisRecarregado);
        for (int i = 0; i < matrizC.length; i++) {
            int contagem = 0;
            for (int j = 0; j < matrizC[0].length; j++) {
               if (matrizC[i][j] > 0) {
                   contagem++;
               }
            }
            if(contagem==veiculosMaisRecarregado){
                System.out.printf("[V%d]", i);
            }
        }
        System.out.println();
    }
    //h)
    public static void diaMaisTardio(int[][] recargas) {
        System.out.printf("\nh) dia mais tardio em que todos os veículos necessitam de recarregar");
        int diaMaisTardio = -1;
        for (int j = 0; j < recargas[0].length; j++) {
            int contagem = 0;
            for (int i = 0; i < recargas.length; i++) {
                if(recargas[i][j]>0)
                    contagem++;
            }
            if(contagem==recargas[0].length)
                diaMaisTardio = j;
        }
        System.out.printf(" <%d>\n", diaMaisTardio);
    }
}

