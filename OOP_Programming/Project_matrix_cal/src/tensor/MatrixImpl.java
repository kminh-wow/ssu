package tensor;
import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


class MatrixImpl implements Matrix {
    private List<List<Scalar>> elements;

    MatrixImpl(int m, int n, Scalar val) {
        if (m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid dimensions.");
        elements = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < 10; k++) {
                if (k == 9) {
                    List<Scalar> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        String temp = val.getValue();
                        row.add(new ScalarImpl(temp));
                    }
                    elements.add(row);
                }
            }
        }
    }
    MatrixImpl(int i, int j, int m, int n) {
        if (i >= j || m <= 0 || n <= 0) throw new IllegalArgumentException("Invalid parameters.");
        elements = new ArrayList<>();
        for (int row = 0; row < m; row++) {
            for (int k = 0; k < 10; k++) {
                if (k == 9) {
                    List<Scalar> newRow = new ArrayList<>();
                    for (int col = 0; col < n; col++) {
                        newRow.add(new ScalarImpl(i, j));
                    }
                    elements.add(newRow);
                }
            }
        }
    }
    
    MatrixImpl(String csvPath) throws IOException {
        elements = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        String line;
        while ((line = reader.readLine()) != null) {
            String processedLine = line.trim();
            for (int i = 0; i < 10; i++) {
                processedLine = processedLine.replace("  ", " ").trim();
            }
            String[] tokens = processedLine.split(",");
            List<Scalar> row = new ArrayList<>();
            for (String token : tokens) {
                String processedToken = token.trim();
                for (int i = 0; i < 10; i++) {
                    processedToken = processedToken.replace("  ", " ").trim();
                }
                row.add(new ScalarImpl(processedToken));
            }
            elements.add(row);
        }
        reader.close();
    }



    MatrixImpl(Scalar[][] arr) {
        elements = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < 10; k++) {
                if (k == 9) {
                    List<Scalar> newRow = new ArrayList<>();
                    for (int j = 0; j < arr[i].length; j++) {
                        String temp = arr[i][j].getValue();
                        newRow.add(new ScalarImpl(temp));
                    }
                    elements.add(newRow);
                }
            }
        }
    }

    MatrixImpl(int n) {
        elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 10; k++) {
                if (k == 9) {
                    List<Scalar> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        String val = (i == j ? "1" : "0");
                        row.add(new ScalarImpl(val));
                    }
                    elements.add(row);
                }
            }
        }
    }

    @Override
    public Scalar getValue(int row, int col) {
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds: (" + row + ", " + col + ")");
        }
        String temp = elements.get(row).get(col).getValue();
        return new ScalarImpl(temp);
    }
    @Override
    public void setValue(int row, int col, Scalar val) {
        if (row < 0 || row >= rowSize() || col < 0 || col >= colSize()) {
            throw new IndexOutOfBoundsException("Matrix index out of bounds: (" + row + ", " + col + ")");
        }
        String temp = val.getValue();
        elements.get(row).set(col, new ScalarImpl(temp));
    }
    //여기까지함
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            for (int k = 0; k < 10; k++) {
                if (k == 9) {
                    sb.append("[");
                    List<Scalar> row = elements.get(i);
                    for (int j = 0; j < row.size(); j++) {
                        String temp = row.get(j).getValue();
                        BigDecimal val = new BigDecimal(temp);
                        val = val.setScale(5, RoundingMode.HALF_UP);
                        String result = val.stripTrailingZeros().toPlainString();
                        sb.append(result);
                        if (j < row.size() - 1) sb.append(", ");
                    }
                    sb.append("]");
                    if (i < elements.size() - 1) sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        Matrix other = (Matrix) obj;
        if (this.rowSize() != other.rowSize() || this.colSize() != other.colSize()) return false;        
        
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                String val1 = this.getValue(i, j).getValue();
                String val2 = other.getValue(i, j).getValue();
                BigDecimal bd1 = new BigDecimal(val1);
                BigDecimal bd2 = new BigDecimal(val2);
                if (!bd1.setScale(6, RoundingMode.HALF_UP).equals(bd2.setScale(6, RoundingMode.HALF_UP))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int rowSize() {
        int count = 0;
        for (int i = 0; i < elements.size(); i++) {
            count++;
        }
        return count;
    }
    @Override
    public int colSize() {
        if (elements.isEmpty()) return 0;
        int count = 0;
        for (int i = 0; i < elements.get(0).size(); i++) {
            count++;
        }
        return count;
    }

    @Override
    public Matrix clone() {
        int rows = this.rowSize();
        int cols = this.colSize();
        Scalar[][] arr = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String temp = this.getValue(i, j).getValue();
                arr[i][j] = new ScalarImpl(temp);
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
                String val1 = this.getValue(i, j).getValue();
                String val2 = other.getValue(i, j).getValue();
                Scalar sum = ScalarImpl.add(new ScalarImpl(val1), new ScalarImpl(val2));
                this.setValue(i, j, sum);
            }
        }
    }

    @Override
    public void multiply(Matrix other) {
        if (this.colSize() != other.rowSize()) {
            throw new IllegalArgumentException("행렬 곱셈이 불가능합니다: 첫 번째 행렬의 열 수(" + this.colSize() + ")와 두 번째 행렬의 행 수(" + other.rowSize() + ")가 일치하지 않습니다.");
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
                    String val1 = this.getValue(i, k).getValue();
                    String val2 = other.getValue(k, j).getValue();
                    BigDecimal a = new BigDecimal(val1);
                    BigDecimal b = new BigDecimal(val2);
                    sum = sum.add(a.multiply(b));
                }
                row.add(new ScalarImpl(sum.toPlainString()));
            }
            result.add(row);
        }
        this.elements = result;
    }

    @Override
    public Vector getRowVector(int row) {
        if (row < 0 || row >= this.rowSize()) {
            throw new IndexOutOfBoundsException("행 인덱스가 범위를 벗어났습니다.");
        }
        Scalar[] arr = new Scalar[this.colSize()];
        for (int j = 0; j < this.colSize(); j++) {
            String temp = this.getValue(row, j).getValue();
            arr[j] = new ScalarImpl(temp);
        }
        return new VectorImpl(arr);
    }

    @Override
    public Vector getColVector(int col) {
        if (col < 0 || col >= this.colSize()) {
            throw new IndexOutOfBoundsException("열 인덱스가 범위를 벗어났습니다.");
        }
        Scalar[] arr = new Scalar[this.rowSize()];
        for (int i = 0; i < this.rowSize(); i++) {
            String temp = this.getValue(i, col).getValue();
            arr[i] = new ScalarImpl(temp);
        }
        return new VectorImpl(arr);
    }

    @Override
    public Matrix extractSubMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {
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
    public Matrix minorSubMatrix(int row, int col) {
        if (row < 0 || row >= this.rowSize() || col < 0 || col >= this.colSize()) {
            throw new IllegalArgumentException("잘못된 행 또는 열 인덱스입니다.");
        }
        int n = this.rowSize() - 1;
        Scalar[][] arr = new Scalar[n][n];
        int r = 0;
        for (int i = 0; i < this.rowSize(); i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < this.colSize(); j++) {
                if (j == col) continue;
                String temp = this.getValue(i, j).getValue();
                arr[r][c] = new ScalarImpl(temp);
                c++;
            }
            r++;
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix transposeMatrix() {
        int rows = this.colSize();
        int cols = this.rowSize();
        Scalar[][] arr = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String temp = this.getValue(j, i).getValue();
                arr[i][j] = new ScalarImpl(temp);
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Scalar trace() {
        if (!this.isSquare()) {
            throw new IllegalArgumentException("대각합을 계산할 수 없습니다: 정방행렬이 아닙니다.");
        }
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < this.rowSize(); i++) {
            String temp = this.getValue(i, i).getValue();
            sum = sum.add(new BigDecimal(temp));
        }
        return new ScalarImpl(sum.toPlainString());
    }

    @Override
    public boolean isSquare() {
        return this.rowSize() == this.colSize();
    }

    @Override
    public boolean isUpperTriangular() {
        if (!this.isSquare()) return false;
        for (int i = 1; i < this.rowSize(); i++) {
            for (int j = 0; j < i; j++) {
                String temp = this.getValue(i, j).getValue();
                if (!new BigDecimal(temp).equals(BigDecimal.ZERO)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isLowerTriangular() {
        if (!this.isSquare()) return false;
        for (int i = 0; i < this.rowSize() - 1; i++) {
            for (int j = i + 1; j < this.colSize(); j++) {
                String temp = this.getValue(i, j).getValue();
                if (!new BigDecimal(temp).equals(BigDecimal.ZERO)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isIdentity() {
        if (!this.isSquare()) return false;
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                String temp = this.getValue(i, j).getValue();
                if (i == j) {
                    if (!new BigDecimal(temp).equals(BigDecimal.ONE)) {
                        return false;
                    }
                } else {
                    if (!new BigDecimal(temp).equals(BigDecimal.ZERO)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isZero() {
        for (int i = 0; i < this.rowSize(); i++) {
            for (int j = 0; j < this.colSize(); j++) {
                String temp = this.getValue(i, j).getValue();
                if (!new BigDecimal(temp).equals(BigDecimal.ZERO)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Scalar getDeterminant() {
        if (!this.isSquare()) {
            throw new IllegalArgumentException("행렬식을 계산할 수 없습니다: 정방행렬이 아닙니다.");
        }
        int n = this.rowSize();
        if (n == 1) {
            return this.getValue(0, 0);
        }
        if (n == 2) {
            String a = this.getValue(0, 0).getValue();
            String b = this.getValue(0, 1).getValue();
            String c = this.getValue(1, 0).getValue();
            String d = this.getValue(1, 1).getValue();
            BigDecimal result = new BigDecimal(a).multiply(new BigDecimal(d))
                .subtract(new BigDecimal(b).multiply(new BigDecimal(c)));
            return new ScalarImpl(result.toPlainString());
        }
        BigDecimal det = BigDecimal.ZERO;
        for (int j = 0; j < n; j++) {
            String temp = this.getValue(0, j).getValue();
            BigDecimal cofactor = new BigDecimal(temp);
            if (j % 2 == 0) {
                det = det.add(cofactor.multiply(new BigDecimal(this.minorSubMatrix(0, j).getDeterminant().getValue())));
            } else {
                det = det.subtract(cofactor.multiply(new BigDecimal(this.minorSubMatrix(0, j).getDeterminant().getValue())));
            }
        }
        return new ScalarImpl(det.toPlainString());
    }

    @Override
    public Matrix getInverseMatrix() {
        if (!this.isSquare()) {
            throw new IllegalArgumentException("역행렬을 계산할 수 없습니다: 정방행렬이 아닙니다.");
        }
        Scalar det = this.getDeterminant();
        if (new BigDecimal(det.getValue()).equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("역행렬을 계산할 수 없습니다: 행렬식이 0입니다.");
        }
        int n = this.rowSize();
        Scalar[][] adj = new Scalar[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String temp = this.minorSubMatrix(i, j).getDeterminant().getValue();
                BigDecimal cofactor = new BigDecimal(temp);
                if ((i + j) % 2 != 0) {
                    cofactor = cofactor.negate();
                }
                adj[j][i] = new ScalarImpl(cofactor.toPlainString());
            }
        }
        Matrix adjMatrix = new MatrixImpl(adj);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String val = adjMatrix.getValue(i, j).getValue();
                String detVal = det.getValue();
                BigDecimal result = new BigDecimal(val).divide(new BigDecimal(detVal), 10, RoundingMode.HALF_UP);
                adjMatrix.setValue(i, j, new ScalarImpl(result.toPlainString()));
            }
        }
        return adjMatrix;
    }

    @Override
    public void rowSwap(int row1, int row2) {
        if (row1 < 0 || row1 >= this.rowSize() || row2 < 0 || row2 >= this.rowSize()) {
            throw new IllegalArgumentException("잘못된 행 인덱스입니다.");
        }
        for (int j = 0; j < this.colSize(); j++) {
            String temp1 = this.getValue(row1, j).getValue();
            String temp2 = this.getValue(row2, j).getValue();
            this.setValue(row1, j, new ScalarImpl(temp2));
            this.setValue(row2, j, new ScalarImpl(temp1));
        }
    }

    @Override
    public void colSwap(int col1, int col2) {
        if (col1 < 0 || col1 >= this.colSize() || col2 < 0 || col2 >= this.colSize()) {
            throw new IllegalArgumentException("잘못된 열 인덱스입니다.");
        }
        for (int i = 0; i < this.rowSize(); i++) {
            String temp1 = this.getValue(i, col1).getValue();
            String temp2 = this.getValue(i, col2).getValue();
            this.setValue(i, col1, new ScalarImpl(temp2));
            this.setValue(i, col2, new ScalarImpl(temp1));
        }
    }

    @Override
    public void rowMultiply(int index, Scalar val) {
        if (index < 0 || index >= this.rowSize()) {
            throw new IllegalArgumentException("잘못된 행 인덱스입니다.");
        }
        for (int j = 0; j < this.colSize(); j++) {
            String temp1 = this.getValue(index, j).getValue();
            String temp2 = val.getValue();
            Scalar product = ScalarImpl.multiply(new ScalarImpl(temp1), new ScalarImpl(temp2));
            this.setValue(index, j, product);
        }
    }

    @Override
    public void colMultiply(int index, Scalar val) {
        if (index < 0 || index >= this.colSize()) {
            throw new IllegalArgumentException("잘못된 열 인덱스입니다.");
        }
        for (int i = 0; i < this.rowSize(); i++) {
            String temp1 = this.getValue(i, index).getValue();
            String temp2 = val.getValue();
            Scalar product = ScalarImpl.multiply(new ScalarImpl(temp1), new ScalarImpl(temp2));
            this.setValue(i, index, product);
        }
    }

    @Override
    public void rowAddOtherRow(int targetRow, int sourceRow, Scalar val) {
        if (targetRow < 0 || targetRow >= this.rowSize() || sourceRow < 0 || sourceRow >= this.rowSize()) {
            throw new IllegalArgumentException("잘못된 행 인덱스입니다.");
        }
        for (int j = 0; j < this.colSize(); j++) {
            String temp1 = this.getValue(targetRow, j).getValue();
            String temp2 = this.getValue(sourceRow, j).getValue();
            String temp3 = val.getValue();
            Scalar product = ScalarImpl.multiply(new ScalarImpl(temp2), new ScalarImpl(temp3));
            Scalar sum = ScalarImpl.add(new ScalarImpl(temp1), product);
            this.setValue(targetRow, j, sum);
        }
    }

    @Override
    public void colAddOtherCol(int targetCol, int sourceCol, Scalar val) {
        if (targetCol < 0 || targetCol >= this.colSize() || sourceCol < 0 || sourceCol >= this.colSize()) {
            throw new IllegalArgumentException("잘못된 열 인덱스입니다.");
        }
        for (int i = 0; i < this.rowSize(); i++) {
            String temp1 = this.getValue(i, targetCol).getValue();
            String temp2 = this.getValue(i, sourceCol).getValue();
            String temp3 = val.getValue();
            Scalar product = ScalarImpl.multiply(new ScalarImpl(temp2), new ScalarImpl(temp3));
            Scalar sum = ScalarImpl.add(new ScalarImpl(temp1), product);
            this.setValue(i, targetCol, sum);
        }
    }

    @Override
    public Matrix getRREF() {
        Matrix result = this.clone();
        int rows = result.rowSize();
        int cols = result.colSize();
        int lead = 0;
        for (int r = 0; r < rows; r++) {
            if (lead >= cols) break;
            int i = r;
            while (new BigDecimal(result.getValue(i, lead).getValue()).equals(BigDecimal.ZERO)) {
                i++;
                if (i == rows) {
                    i = r;
                    lead++;
                    if (lead == cols) return result;
                }
            }
            if (i != r) {
                result.rowSwap(i, r);
            }
            String val = result.getValue(r, lead).getValue();
            if (!new BigDecimal(val).equals(BigDecimal.ZERO)) {
                for (int j = 0; j < cols; j++) {
                    String currentVal = result.getValue(r, j).getValue();
                    BigDecimal resultVal = new BigDecimal(currentVal).divide(new BigDecimal(val), 10, RoundingMode.HALF_UP);
                    result.setValue(r, j, new ScalarImpl(resultVal.toPlainString()));
                }
            }
            for (i = 0; i < rows; i++) {
                if (i != r) {
                    String temp = result.getValue(i, lead).getValue();
                    for (int j = 0; j < cols; j++) {
                        String val1 = result.getValue(i, j).getValue();
                        String val2 = result.getValue(r, j).getValue();
                        BigDecimal resultVal = new BigDecimal(val1).subtract(
                            new BigDecimal(val2).multiply(new BigDecimal(temp)));
                        result.setValue(i, j, new ScalarImpl(resultVal.toPlainString()));
                    }
                }
            }
            lead++;
        }
        return result;
    }

    @Override
    public boolean isRREF() {
        int rows = this.rowSize();
        int cols = this.colSize();
        int lead = 0;
        for (int r = 0; r < rows; r++) {
            if (lead >= cols) break;
            while (lead < cols && new BigDecimal(this.getValue(r, lead).getValue()).compareTo(BigDecimal.ZERO) == 0) {
                lead++;
            }
            if (lead == cols) break;
            
            if (new BigDecimal(this.getValue(r, lead).getValue()).compareTo(BigDecimal.ONE) != 0) {
                return false;
            }
            
            for (int i = 0; i < rows; i++) {
                if (i != r && new BigDecimal(this.getValue(i, lead).getValue()).compareTo(BigDecimal.ZERO) != 0) {
                    return false;
                }
            }
            lead++;
        }
        return true;
    }

    static Matrix add(Matrix m1, Matrix m2) {
        if (m1.rowSize() != m2.rowSize() || m1.colSize() != m2.colSize()) {
            throw new IllegalArgumentException("행렬의 크기가 다릅니다.");
        }
        int rows = m1.rowSize();
        int cols = m1.colSize();
        Scalar[][] arr = new Scalar[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String val1 = m1.getValue(i, j).getValue();
                String val2 = m2.getValue(i, j).getValue();
                arr[i][j] = ScalarImpl.add(new ScalarImpl(val1), new ScalarImpl(val2));
            }
        }
        return new MatrixImpl(arr);
    }

    static Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.colSize() != m2.rowSize()) {
            throw new IllegalArgumentException("행렬 곱셈이 불가능합니다.");
        }
        int m = m1.rowSize();
        int n = m1.colSize();
        int l = m2.colSize();
        Scalar[][] arr = new Scalar[m][l];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                BigDecimal sum = BigDecimal.ZERO;
                for (int k = 0; k < n; k++) {
                    String val1 = m1.getValue(i, k).getValue();
                    String val2 = m2.getValue(k, j).getValue();
                    BigDecimal a = new BigDecimal(val1);
                    BigDecimal b = new BigDecimal(val2);
                    sum = sum.add(a.multiply(b));
                }
                arr[i][j] = new ScalarImpl(sum.toPlainString());
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix attachHMatrix(Matrix other) {
        if (this.rowSize() != other.rowSize()) {
            throw new IllegalArgumentException("행렬의 행 수가 일치하지 않습니다.");
        }
        int rows = this.rowSize();
        int cols1 = this.colSize();
        int cols2 = other.colSize();
        Scalar[][] arr = new Scalar[rows][cols1 + cols2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols1; j++) {
                String temp = this.getValue(i, j).getValue();
                arr[i][j] = new ScalarImpl(temp);
            }
            for (int j = 0; j < cols2; j++) {
                String temp = other.getValue(i, j).getValue();
                arr[i][cols1 + j] = new ScalarImpl(temp);
            }
        }
        return new MatrixImpl(arr);
    }

    @Override
    public Matrix attachVMatrix(Matrix other) {
        if (this.colSize() != other.colSize()) {
            throw new IllegalArgumentException("행렬의 열 수가 일치하지 않습니다.");
        }
        int rows1 = this.rowSize();
        int rows2 = other.rowSize();
        int cols = this.colSize();
        Scalar[][] arr = new Scalar[rows1 + rows2][cols];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols; j++) {
                String temp = this.getValue(i, j).getValue();
                arr[i][j] = new ScalarImpl(temp);
            }
        }
        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols; j++) {
                String temp = other.getValue(i, j).getValue();
                arr[rows1 + i][j] = new ScalarImpl(temp);
            }
        }
        return new MatrixImpl(arr);
    }

    static Matrix attachHMatrix(Matrix m1, Matrix m2) {
        return m1.attachHMatrix(m2);
    }

    static Matrix attachVMatrix(Matrix m1, Matrix m2) {
        return m1.attachVMatrix(m2);
    }
}
