package tensor;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


class MatrixImpl implements Matrix {
    private List<List<Scalar>> elements;

    MatrixImpl(int m, int n, Scalar val) {
        if (m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid dimensions.");
        elements = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(val.clone());
            }
            elements.add(row);
        }
    }
    MatrixImpl(int i, int j, int m, int n) {
        if (i >= j || m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid parameters.");
        elements = new ArrayList<>();
        for (int row = 0; row < m; row++) {
            List<Scalar> newRow = new ArrayList<>();
            for (int col = 0; col < n; col++) {
                newRow.add(new ScalarImpl(i, j));
            }
            elements.add(newRow);
        }
    }
    
    MatrixImpl(String csvPath) throws IOException {
        elements = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            List<Scalar> row = new ArrayList<>();
            for (String token : tokens) {
                row.add(new ScalarImpl(token.trim()));
            }
            elements.add(row);
        }
        reader.close();
    }



    MatrixImpl(Scalar[][] arr) {
        elements = new ArrayList<>();
        for (Scalar[] row : arr) {
            List<Scalar> newRow = new ArrayList<>();
            for (Scalar s : row) {
                newRow.add(s.clone());
            }
            elements.add(newRow);
        }
    }

    MatrixImpl(int n) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(new ScalarImpl(i == j ? "1" : "0"));
            }
            elements.add(row);
        }
    }

    @Override
    public Scalar getValue(int row, int col) {
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds: (" + row + ", " + col + ")");
        }
        return elements.get(row).get(col).clone();
    }
    @Override
    public void setValue(int row, int col, Scalar val) {
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds: (" + row + ", " + col + ")");
        }
        elements.get(row).set(col, val.clone());
    }
    //여기까지함
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            sb.append("[");
            List<Scalar> row = elements.get(i);
            for (int j = 0; j < row.size(); j++) {
                // 소수점 5자리까지 표시
                BigDecimal val = new BigDecimal(row.get(j).getValue());
                val = val.setScale(5, RoundingMode.HALF_UP);
                sb.append(val.stripTrailingZeros().toPlainString());
                if (j < row.size() - 1) sb.append(", ");
            }
            sb.append("]");
            if (i < elements.size() - 1) sb.append("\n");
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize()) return false;        
        // 소수점 6자리까지 비교
        BigDecimal epsilon = new BigDecimal("0.000001");
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                BigDecimal val1 = new BigDecimal(this.getValue(i, j).getValue());
                BigDecimal val2 = new BigDecimal(other.getValue(i, j).getValue());
                if (val1.subtract(val2).abs().compareTo(epsilon) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int rowSize() {
        return elements.size();
    }
    @Override
    public int colSize() {
        if (elements.isEmpty()) return 0;
        return elements.get(0).size();
    }

    @Override
    public Matrix clone() {
        int rows = this.rowSize();
        int cols = this.colSize();
        Scalar[][] arr = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = this.getValue(i, j).clone();
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public void add(Matrix other) {
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize()) {
            throw new IllegalArgumentException("행렬의 크기가 다릅니다.");
        }
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                this.getValue(i, j).add(other.getValue(i, j));
            }
        }
    }

    @Override
    public void multiply(Matrix other) {
        if (this.colSize() != other.rowSize()) {
            throw new IllegalArgumentException("행렬 곱셈 조건이 맞지 않습니다.");
        }
        int m = this.rowSize();
        int n = this.colSize();
        int l = other.colSize();
        List<List<Scalar>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Scalar> row = new ArrayList<>();
            for (int j = 0; j < l; j++) {
                BigDecimal sum = BigDecimal.ZERO;
                for (int k = 0; k < n; k++) {
                    BigDecimal a = new BigDecimal(this.getValue(i, k).getValue());
                    BigDecimal b = new BigDecimal(other.getValue(k, j).getValue());
                    sum = sum.add(a.multiply(b));
                }
                row.add(new ScalarImpl(sum.toPlainString()));
            }
            result.add(row);
        }
        this.elements = result;
    }

    @Override
    public Vector getRowVector(int row) { //34번
        if (row < 0 || row >= this.rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        Scalar[] arr = new Scalar[this.colSize()];
        for (int j = 0; j < this.colSize(); j++) {
            arr[j] = this.getValue(row, j).clone();
        }
        return new VectorImpl(arr);
    }

    @Override
    public Vector getColVector(int col) { //35번
        if (col < 0 || col >= this.colSize()) {
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        Scalar[] arr = new Scalar[this.rowSize()];
        for (int i = 0; i < this.rowSize(); i++) {
            arr[i] = this.getValue(i, col).clone();
        }
        return new VectorImpl(arr);
    }

    @Override
    public Matrix extractSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd) { //36번
        if (rowStart < 0 || rowEnd > this.rowSize() || colStart < 0 || colEnd > this.colSize() || rowStart >= rowEnd || colStart >= colEnd) {
            throw new IndexOutOfBoundsException("부분 행렬 인덱스가 범위를 벗어났습니다.");
        }
        int rows = rowEnd - rowStart;
        int cols = colEnd - colStart;
        Scalar[][] arr = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = this.getValue(rowStart + i, colStart + j).clone();
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix minorSubMatrix(int row, int col) { //37번
        if (row < 0 || row >= this.rowSize() || col < 0 || col >= this.colSize()) {
            throw new IndexOutOfBoundsException("minor 인덱스가 범위를 벗어났습니다.");
        }
        int rows = this.rowSize() - 1;
        int cols = this.colSize() - 1;
        Scalar[][] arr = new Scalar[rows][cols];
        int r = 0;
        for (int i = 0; i < this.rowSize(); i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < this.colSize(); j++) {
                if (j == col) continue;
                arr[r][c] = this.getValue(i, j).clone();
                c++;
            }
            r++;
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix transposeMatrix() { //38번
        int rows = this.rowSize();
        int cols = this.colSize();
        Scalar[][] arr = new Scalar[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[j][i] = this.getValue(i, j).clone();
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Scalar trace() {
        if (!isSquare()) {
            throw new NotSquareMatrixException("정사각 행렬이 아닙니다.");
        }

        Scalar sumElements = new ScalarImpl("0");

        for (int i =0; i < rowSize(); i++) {
            Scalar diagonalElement = elements.get(i).get(i);
            if (diagonalElement != null) {
                sumElements.add(diagonalElement);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
        return sumElements;
    }

    @Override
    public boolean isSquare() {
        return rowSize() == colSize();
    }

    @Override
    public boolean isUpperTriangular() {
        if (!isSquare()) return false;
        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < i; j++) {
                if (!getValue(i, j).equals(new ScalarImpl("0"))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isLowerTriangular() {
        if (!isSquare()) return false;
        for (int i = 0; i < rowSize(); i++) {
            for (int j = i + 1; j < colSize(); j++) {
                if (!getValue(i, j).equals(new ScalarImpl("0"))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isIdentity() {
        if (!isSquare()) return false;
        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                if (i == j) {
                    if (!getValue(i, j).equals(new ScalarImpl("1"))) {
                        return false;
                    }
                } else {
                    if (!getValue(i, j).equals(new ScalarImpl("0"))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isZero() {
        for (int i = 0; i < rowSize(); i++) {
            for (int j = 0; j < colSize(); j++) {
                if (!getValue(i, j).equals(new ScalarImpl("0"))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Scalar getDeterminant() {
        if (rowSize() != colSize()) {
            throw new IllegalArgumentException("정사각 행렬만 행렬식을 구할 수 있습니다.");
        }
        int n = rowSize();
        if (n == 1) {
            return getValue(0, 0).clone();
        }
        if (n == 2) {
            Scalar a = getValue(0, 0);
            Scalar b = getValue(0, 1);
            Scalar c = getValue(1, 0);
            Scalar d = getValue(1, 1);
            Scalar ad = a.clone();
            ad.multiply(d);
            Scalar bc = b.clone();
            bc.multiply(c);
            Scalar result = ad.clone();
            Scalar negBc = bc.clone();
            negBc.multiply(new ScalarImpl("-1"));
            result.add(negBc);
            return result;
        }
        Scalar determinant = new ScalarImpl("0");
        for (int j = 0; j < n; j++) {
            Scalar cofactor = getValue(0, j).clone();
            Matrix minor = minorSubMatrix(0, j);
            Scalar minorDet = ((MatrixImpl)minor).getDeterminant();
            cofactor.multiply(minorDet);
            if (j % 2 == 0) {
                determinant.add(cofactor);
            } else {
                Scalar negCofactor = cofactor.clone();
                negCofactor.multiply(new ScalarImpl("-1"));
                determinant.add(negCofactor);
            }
        }
        return determinant;
    }

    @Override
    public Matrix getInverseMatrix() {
        int n = rowSize();
        if (n != colSize()) throw new IllegalArgumentException("정사각 행렬만 역행렬을 구할 수 있습니다.");
        Scalar det = getDeterminant();
        if (det.equals(new ScalarImpl("0"))) {
            throw new ArithmeticException("역행렬이 존재하지 않습니다.");
        }
        if (n == 2) {
            Scalar a = getValue(0, 0);
            Scalar b = getValue(0, 1);
            Scalar c = getValue(1, 0);
            Scalar d = getValue(1, 1);
            Scalar[][] inv = new Scalar[2][2];
            inv[0][0] = new ScalarImpl((new BigDecimal(d.getValue()).divide(new BigDecimal(det.getValue()), 10, RoundingMode.HALF_UP)).toPlainString());
            inv[0][1] = new ScalarImpl((new BigDecimal(b.getValue()).multiply(new BigDecimal("-1")).divide(new BigDecimal(det.getValue()), 10, RoundingMode.HALF_UP)).toPlainString());
            inv[1][0] = new ScalarImpl((new BigDecimal(c.getValue()).multiply(new BigDecimal("-1")).divide(new BigDecimal(det.getValue()), 10, RoundingMode.HALF_UP)).toPlainString());
            inv[1][1] = new ScalarImpl((new BigDecimal(a.getValue()).divide(new BigDecimal(det.getValue()), 10, RoundingMode.HALF_UP)).toPlainString());
            return new MatrixImpl(inv);
        }
        Scalar[][] identity = new Scalar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                identity[i][j] = new ScalarImpl(i == j ? "1" : "0");
            }
        }
        Matrix aug = new MatrixImpl(n, 2 * n, new ScalarImpl("0"));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aug.setValue(i, j, getValue(i, j).clone());
                aug.setValue(i, j + n, identity[i][j].clone());
            }
        }
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            BigDecimal maxVal = new BigDecimal(aug.getValue(i, i).getValue()).abs();
            for (int k = i + 1; k < n; k++) {
                BigDecimal val = new BigDecimal(aug.getValue(k, i).getValue()).abs();
                if (val.compareTo(maxVal) > 0) {
                    maxVal = val;
                    maxRow = k;
                }
            }
            if (maxRow != i) {
                for (int j = 0; j < 2 * n; j++) {
                    Scalar temp = aug.getValue(i, j);
                    aug.setValue(i, j, aug.getValue(maxRow, j));
                    aug.setValue(maxRow, j, temp);
                }
            }
            if (aug.getValue(i, i).equals(new ScalarImpl("0"))) {
                throw new ArithmeticException("역행렬이 존재하지 않습니다.");
            }
            Scalar diag = aug.getValue(i, i).clone();
            for (int j = 0; j < 2 * n; j++) {
                Scalar val = aug.getValue(i, j).clone();
                val = new ScalarImpl((new BigDecimal(val.getValue()).divide(new BigDecimal(diag.getValue()), 10, RoundingMode.HALF_UP)).toPlainString());
                aug.setValue(i, j, val);
            }
            for (int k = 0; k < n; k++) {
                if (k == i) continue;
                Scalar factor = aug.getValue(k, i).clone();
                for (int j = 0; j < 2 * n; j++) {
                    Scalar val = aug.getValue(k, j).clone();
                    Scalar sub = aug.getValue(i, j).clone();
                    sub.multiply(factor);
                    val = new ScalarImpl((new BigDecimal(val.getValue()).subtract(new BigDecimal(sub.getValue()), new MathContext(10, RoundingMode.HALF_UP))).toPlainString());
                    aug.setValue(k, j, val);
                }
            }
        }
        Scalar[][] inv = new Scalar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inv[i][j] = aug.getValue(i, j + n).clone();
            }
        }
        return new MatrixImpl(inv);
    }
    //45 두 행의 위치 맞교환
    @Override
    public void rowSwap(int row1, int row2) {
        if (row1 < 0 || row1 >= rowSize() || row2 < 0 || row2 >= rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        if (row1 == row2) return;

        List<Scalar> temp = elements.get(row1);
        elements.set(row1, elements.get(row2));
        elements.set(row2, temp);
    }

    //46 두 열의 위치 맞교환
    @Override
    public void colSwap(int col1, int col2) {
        if (col1 < 0 || col1 >= colSize() || col2 < 0 || col2 >= colSize()){
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        if (col1 == col2) return;

        for (int i = 0; i < rowSize(); i++) {
            List<Scalar> currentRow = elements.get(i);

            Scalar temp = currentRow.get(col1);
            currentRow.set(col1, currentRow.get(col2));
            currentRow.set(col2, temp);
        }
    }

    //47 특정 행에 상수배(스칼라)
    @Override
    public void rowMultiply(int index, Scalar val) {
        if (index < 0 || index >= rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("null을 참조하였습니다.");
        }

        List<Scalar> rowElements = this.elements.get(index);

        for (int i = 0; i < colSize(); i++) {
            Scalar currentElement = rowElements.get(i);
            
            if (currentElement != null) {
                currentElement.multiply(val);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
    }

    //48 특정 열에 상수배(스칼라)
    @Override
    public void colMultiply(int index, Scalar val) {
        if (index < 0 || index >= colSize()) {
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        if (val == null) {
            throw new NullPointerException("null을 참조하였습니다.");
        }
        for (int i = 0; i < rowSize(); i++) {
            Scalar currentElement = elements.get(i).get(index);

            if (currentElement != null) {
                currentElement.multiply(val);
            } else {
                throw new NullPointerException("null을 참조하였습니다.");
            }
        }
    }

     //49
     @Override
     public void rowAddOtherRow(int targetRow, int sourceRow, Scalar val) {
         if (targetRow < 0 || targetRow >= rowSize()) {
             throw new IndexOutOfBoundsException("대상 행 인덱스(" + targetRow + ")가 유효 범위를 벗어났습니다. 전체 행 수: " + rowSize());
         }
         if (sourceRow < 0 || sourceRow >= rowSize()) {
             throw new IndexOutOfBoundsException("소스 행 인덱스(" + sourceRow + ")가 유효 범위를 벗어났습니다. 전체 행 수: " + rowSize());
         }
         if (val == null) {
             throw new NullPointerException("곱할 스칼라 값은 null일 수 없습니다.");
         }
         List<Scalar> targetRowElements = elements.get(targetRow);
         List<Scalar> sourceRowElements = elements.get(sourceRow);
 
         for (int j = 0; j < colSize(); j++) {
             Scalar targetElement = targetRowElements.get(j);
             Scalar sourceElement = sourceRowElements.get(j);
 
             if (targetElement == null || sourceElement == null) {
                 if (sourceElement == null)
                     throw new NullPointerException("소스 행의 요소가 null입니다. at (" + sourceRow + "," + j + ")");
                 if (targetElement == null)
                     throw new NullPointerException("타겟 행의 요소가 null입니다. at (" + targetRow + "," + j + ")");
             }
 
             Scalar term = sourceElement.clone();
             term.multiply(val);
             targetElement.add(term);
         }
     }
 
     //50
     @Override
     public void colAddOtherCol(int targetCol, int sourceCol, Scalar val) {
         if (targetCol < 0 || targetCol >= colSize()) {
             throw new IndexOutOfBoundsException("대상 열 인덱스(" + targetCol + ")가 유효 범위를 벗어났습니다. 전체 열 수: " + colSize());
         }
         if (sourceCol < 0 || sourceCol >= colSize()) {
             throw new IndexOutOfBoundsException("소스 열 인덱스(" + sourceCol + ")가 유효 범위를 벗어났습니다. 전체 열 수: " + colSize());
         }
         if (val == null) {
             throw new NullPointerException("곱할 스칼라 값은 null일 수 없습니다.");
         }
         for (int i = 0; i < rowSize(); i++) {
             List<Scalar> currentRow = this.elements.get(i);
 
             Scalar targetElement = currentRow.get(targetCol);
             Scalar sourceElement = currentRow.get(sourceCol);
 
             if (targetElement == null || sourceElement == null) {
                 if (sourceElement == null) throw new NullPointerException("소스 열의 요소가 null입니다. at ("+i+","+sourceCol+")");
                 if (targetElement == null) throw new NullPointerException("타겟 열의 요소가 null입니다. at ("+i+","+targetCol+")");
             }
 
             Scalar term = sourceElement.clone();
             term.multiply(val);
             targetElement.add(term);
         }
     }
    @Override
    public Matrix getRREF() { //51번
        Matrix copy = this.clone();
        int lead = 0;
        int rowCount = copy.rowSize();
        int colCount = copy.colSize();
        for (int r = 0; r < rowCount; r++) {
            if (lead >= colCount) break;
            int i = r;
            while (i < rowCount && copy.getValue(i, lead).equals(new ScalarImpl("0"))) {
                i++;
            }
            if (i == rowCount) {
                lead++;
                if (lead >= colCount) break;
                r--;
                continue;
            }
            // swap rows i and r
            if (i != r) {
                for (int k = 0; k < colCount; k++) {
                    Scalar temp = copy.getValue(r, k);
                    copy.setValue(r, k, copy.getValue(i, k));
                    copy.setValue(i, k, temp);
                }
            }
            // divide row r by leading value
            Scalar lv = copy.getValue(r, lead).clone();
            for (int k = 0; k < colCount; k++) {
                Scalar val = copy.getValue(r, k).clone();
                if (!lv.equals(new ScalarImpl("0"))) {
                    val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).divide(new java.math.BigDecimal(lv.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                }
                copy.setValue(r, k, val);
            }
            // eliminate other rows
            for (int i2 = 0; i2 < rowCount; i2++) {
                if (i2 != r) {
                    Scalar lv2 = copy.getValue(i2, lead).clone();
                    for (int k = 0; k < colCount; k++) {
                        Scalar val = copy.getValue(i2, k).clone();
                        Scalar sub = copy.getValue(r, k).clone();
                        sub.multiply(lv2);
                        val = new ScalarImpl((new java.math.BigDecimal(val.getValue()).subtract(new java.math.BigDecimal(sub.getValue()), java.math.MathContext.DECIMAL128)).toPlainString());
                        copy.setValue(i2, k, val);
                    }
                }
            }
            lead++;
        }
        return copy;
    }

    @Override
    public boolean isRREF() { //52번
        int prevLead = -1;
        for (int i = 0; i < rowSize(); i++) {
            int lead = -1;
            for (int j = 0; j < colSize(); j++) {
                if (!getValue(i, j).equals(new ScalarImpl("0"))) {
                    if (!getValue(i, j).equals(new ScalarImpl("1"))) return false;
                    lead = j;
                    break;
                }
            }
            if (lead <= prevLead) return false;
            for (int k = 0; k < rowSize(); k++) {
                if (k != i && lead != -1 && !getValue(k, lead).equals(new ScalarImpl("0"))) return false;
            }
            prevLead = lead;
        }
        return true;
    }

    static Matrix add(Matrix m1, Matrix m2) {
        if (m1.rowSize() != m2.rowSize() || m1.colSize() != m2.colSize()) {
            throw new IllegalArgumentException("행렬의 크기가 다릅니다.");
        }
        Scalar[][] arr = new Scalar[m1.rowSize()][m1.colSize()];
        for (int i = 0; i < m1.rowSize(); i++) {
            for (int j = 0; j < m1.colSize(); j++) {
                arr[i][j] = ScalarImpl.add(m1.getValue(i, j), m2.getValue(i, j));
            }
        }
        return new MatrixImpl(arr);
    }

    static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.colSize() != m2.rowSize()) {
            throw new IllegalArgumentException("행렬 곱셈 조건이 맞지 않습니다.");
        }
        int m = m1.rowSize();
        int n = m1.colSize();
        int l = m2.colSize();
        Scalar[][] arr = new Scalar[m][l];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                java.math.BigDecimal sum = java.math.BigDecimal.ZERO;
                for (int k = 0; k < n; k++) {
                    java.math.BigDecimal a = new java.math.BigDecimal(m1.getValue(i, k).getValue());
                    java.math.BigDecimal b = new java.math.BigDecimal(m2.getValue(k, j).getValue());
                    sum = sum.add(a.multiply(b));
                }
                arr[i][j] = new ScalarImpl(sum.toPlainString());
            }
        }
        return new MatrixImpl(arr);
    }

    static Matrix attachHMatrix(Matrix m1, Matrix m2) { //32번
        if (m1.rowSize() != m2.rowSize()) {
            throw new IllegalArgumentException("행 개수가 다릅니다.");
        }
        int rows = m1.rowSize();
        int cols1 = m1.colSize();
        int cols2 = m2.colSize();
        Scalar[][] arr = new Scalar[rows][cols1 + cols2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols1; j++) {
                arr[i][j] = m1.getValue(i, j).clone();
            }
            for (int j = 0; j < cols2; j++) {
                arr[i][cols1 + j] = m2.getValue(i, j).clone();
            }
        }
        return new MatrixImpl(arr);
    }

    static Matrix attachVMatrix(Matrix m1, Matrix m2) { //33번
        if (m1.colSize() != m2.colSize()) {
            throw new IllegalArgumentException("열 개수가 다릅니다.");
        }
        int rows1 = m1.rowSize();
        int rows2 = m2.rowSize();
        int cols = m1.colSize();
        Scalar[][] arr = new Scalar[rows1 + rows2][cols];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = m1.getValue(i, j).clone();
            }
        }
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols; j++) {
                arr[rows1 + i][j] = m2.getValue(i, j).clone();
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix attachHMatrix(Matrix other) {
        return attachHMatrix(this, other);
    }
    @Override
    public Matrix attachVMatrix(Matrix other) {
        return attachVMatrix(this, other);
    }
}
