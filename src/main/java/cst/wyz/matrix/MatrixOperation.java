package cst.wyz.matrix;

/**
 * @author jerrywang
 * @version 1.0 simple operation of matrix
 *
 */
public class MatrixOperation {

    private enum Status{
        ADD,MULTIPLY,AandM,REVERSE,ILLEGAL,MOD
    }

    public static void printMatrix(double[][] m , String...strings ) {
        System.out.println(strings[0]);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static double mod(double[][] m) {
        double result = 0.0;
        if (!check(m).equals(Status.MOD)) {
            System.out.println("Illegal input !");
            return 0;
        }

        if (m.length == 1) {
            result = m[0][0];
        }
        if (m.length == 2) {
            result = m[0][0] * m[1][1] - m[0][1] * m[1][0];
        }
        if (m.length > 2) {
            double[][] iteration = new double[m.length-1][m.length-1];
            for (int i = 0; i < m.length; i++) {
                double init = m[0][i];
                for (int j = 1; j < m.length; j++) {
                    for (int k = 0; k < m.length; k++) {
                        if (k == i) {
                            continue;
                        }
                        if (k > i) {
                            iteration[j-1][k-1] = m[j][k];
                        }
                        else {
                            iteration[j-1][k] = m[j][k];
                        }
                    }
                }
                result += init * Math.pow(-1, 0+i) * mod(iteration);
            }
        }

        return result;
    }

    private static Status check(double[][]... matrixs) {
        if (matrixs.length == 1) {
            if (matrixs[0].length == matrixs[0][0].length ) {
                return Status.MOD;
            }
            return Status.ILLEGAL;
        }
        if (matrixs[0].length == matrixs[0][0].length &&
                matrixs[1].length == matrixs[1][0].length &&
                matrixs[0].length == matrixs[1].length) {
            return Status.AandM;
        }
        if (matrixs[0].length == matrixs[1].length && matrixs[0][0].length == matrixs[1][0].length) {
            return Status.ADD;
        }
        if (matrixs[0][0].length == matrixs[1].length) {
            return Status.MULTIPLY;
        }
        return Status.ILLEGAL;

    }

    public static double[][] add(double[][] m1, double [][] m2){
        if (!check(m1, m2) .equals(Status.ADD) || !check(m1, m2).equals(Status.AandM)) {
            System.out.println("Illegal input !");
            return null;
        }

        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    public double[][] substract(double[][] m1, double[][] m2){
        if (!check(m1, m2) .equals(Status.ADD) && !check(m1, m2).equals(Status.AandM)) {
            System.out.println("Illegal input !");
            return null;
        }

        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] m1, double[][] m2){
        if(!check(m1, m2).equals(Status.MULTIPLY) && !check(m1, m2).equals(Status.AandM) ) {
            System.out.println("Illegal input !");
            return null;
        }

        double[][] result = new double[m1.length][m2[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = 0.0;
                for (int k = 0; k < m1[0].length; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] divide(double[][] m , double dividend){
        if (Math.abs((dividend - 0)) < Double.MIN_VALUE) {
            System.out.println("Wrong input !");
            System.exit(0);
        }
        double[][] result = new double[m.length][m[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = m[i][j]/dividend;
            }
        }
        return result;
    }

    public static double[][] reverse_with(double[][] m){
        int len = m.length;
        double[][] adjugate = new double[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                double[][] temp = new double[len-1][len-1];
                for (int k = 0; k < len; k++) {
                    if (k == i) {
                        continue;
                    }
                    for (int l = 0; l < len; l++) {
                        if (l == j) {
                            continue;
                        }
                        temp[k < i ? k : (k-1)][l < j ? l : (l-1)] = m[k][l];
                    }
                }
                adjugate[j][i] = mod(temp) * Math.pow(-1, i+j);
            }
        }

        return divide(adjugate, mod(m));
    }

    public static double[][] reverse(double[][] m){
        if (!check(m).equals(Status.MOD) && (mod(m) - 0) < Double.MIN_VALUE ) {
            System.out.println("Illegal input !");
            return null;
        }
        int len = m.length;

        double[][] result = new double[len][len];

        /*
         * LR分解求矩阵逆
         * 1. 求L矩阵(下三角单位矩阵)，R矩阵(上三角矩阵)。
         * 	a.初始化 L 矩阵对角线元素为 1
         * 	b.求 R 矩阵第一行
         * 	c.依次求出 L R 矩阵的其余元素，L矩阵的对应列元素要在 R矩阵的对应行元素求出后计算
         * 2. 求L矩阵逆Lreverse，R矩阵逆Rreverse
         * 3. 求Rreverse * Lreverse
         *
         */
        double[][] L = new double[len][len];
        double[][] R = new double[len][len];
        double[][] Lreverse = new double[len][len];
        double[][] Rreverse = new double[len][len];

        for (int i = 0; i < len; i++) {
            L[i][i] = 1.0;
            R[0][i] = m[0][i];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += L[j][k] * R [k][i];
                }
                L[j][i] = (m[j][i] - sum)/R[i][i];
            }
            if (i != 0) {
                for (int j = i; j < R.length; j++) {
                    double sum = 0.0;
                    for (int k = 0; k < i; k++) {
                        sum += L[i][k] * R[k][j];
                    }
                    R[i][j] = m[i][j] - sum;
                }
            }
            for (int j = 0; j < len; j++) {
                if (i > j) {
                    R[i][j] = 0.0;
                    Rreverse[i][j] = 0.0;
                }
                if (j > i) {
                    L[i][j] = 0.0;
                    Lreverse[i][j] = 0.0;
                }
            }
        }
        MatrixOperation.printMatrix(L, "L matrix : ");
        MatrixOperation.printMatrix(R, "R matrix : ");


        for (int i = 0; i < len; i++) {
            Lreverse[i][i] = 1.0/L[i][i];
            Rreverse[i][i] = 1.0/R[i][i];
        }
        for (int delta = 1; delta < len; delta++) {
            for (int i = 0; i < len - delta + 1; i++) {
                double sum = 0.0;
                for (int j = i+1; j < len; j++) {
                    sum += R[i][j] * Rreverse[j][i+delta];
                }
                Rreverse[i][i+delta] = -sum / R[i][i];
            }
        }
        for (int i = 0; i < len -1 ; i++) {
            for (int j = 1; j < len; j++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += L[j][k] * Lreverse[k][i];
                }
                L[j][i] = -sum;
            }
        }
        MatrixOperation.printMatrix(Lreverse, "L reverse matrix : ");
        MatrixOperation.printMatrix(Rreverse, "R reverse matrix : ");

        result = multiply(Rreverse, Lreverse);
        MatrixOperation.printMatrix(result, "A reverse matrix : ");

        return result;

    }

}
