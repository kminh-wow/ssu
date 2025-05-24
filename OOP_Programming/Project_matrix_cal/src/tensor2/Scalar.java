package tensor2;

public interface Scalar {
    // 값 관련 메서드
    abstract void setValue(String value);
    abstract String getValue();
    
    // 연산 메서드
    abstract void add(Scalar other);
    abstract void multiply(Scalar other);
    
    // 비교 메서드
    abstract boolean equals(Object obj);
    abstract int compareTo(Scalar other);
    
    // 복제 메서드
    abstract Scalar clone();
} 