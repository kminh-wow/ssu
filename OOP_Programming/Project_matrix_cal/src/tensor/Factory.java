package tensor;

import java.io.IOException;

public class Factory {

    // Scalar 생성
    public static Scalar createScalar(String val) {
        return new ScalarImpl(val);
    }

    public static Scalar createScalar(int i, int j) {
        // int 값을 String으로 변환하여 전달
        return new ScalarImpl(String.valueOf(i + (int)(Math.random() * (j - i + 1))));
    }

    // Vector 생성
    public static Vector createVector(int n, Scalar val) {
        return new VectorImpl(n, val);
    }
    
    public static Vector createVector(int i, int j, int n) {
        // 각 요소에 대해 랜덤값 생성
        Scalar[] arr = new Scalar[n];
        for (int k = 0; k < n; k++) {
            arr[k] = createScalar(i, j);
        }
        return new VectorImpl(arr);
    }

    public static Vector createVector(Scalar[] arr) {
        return new VectorImpl(arr);
    }

    // Matrix 생성
    public static Matrix createMatrix(int m, int n, Scalar val) {
        return new MatrixImpl(m, n, val);
    }

    public static Matrix createMatrix(int i, int j, int m, int n) {
        // 각 요소에 대해 랜덤값 생성
        Scalar[][] arr = new Scalar[m][n];
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                arr[k][l] = createScalar(i, j);
            }
        }
        return new MatrixImpl(arr);
    }

    public static Matrix createMatrix(String csvPath) throws IOException {
        return new MatrixImpl(csvPath);
    }

    public static Matrix createMatrix(Scalar[][] arr) {
        return new MatrixImpl(arr);
    }

    public static Matrix createIdentityMatrix(int size) {
        return new MatrixImpl(size);
    }
}