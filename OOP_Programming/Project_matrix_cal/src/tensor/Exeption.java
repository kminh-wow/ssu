package tensor;

public class TensorException extends RuntimeException {
    public TensorException(String msg) { super(msg); }
}

public class SizeMismatchException extends TensorException {
    public SizeMismatchException(String msg) { super(msg); }
}

public class NotSquareMatrixException extends TensorException {
    public NotSquareMatrixException(String msg) { super(msg); }
}
