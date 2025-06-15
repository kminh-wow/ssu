package tensor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Objects;

class ScalarImpl implements Scalar, Comparable<Scalar> {
    private BigDecimal value;
    //1. 값을 지정하여 스칼라를 생성할 수 있다.
    ScalarImpl(String val) {      
        try {
            this.value = new BigDecimal(val);
        } catch (NumberFormatException e) {
            throw new TensorException("스칼라 값이 올바른 숫자가 아닙니다: '" + val + "'는 유효한 숫자 형식이 아닙니다.");
        }
    }
    //2. i이상 j미만의 무작위 값을 요소로 하는 스칼라를 생성할 수 있다.
    ScalarImpl(int i, int j) {
        if (i >= j) {
            throw new IllegalArgumentException("스칼라 생성이 불가능합니다: 시작값(" + i + ")은 종료값(" + j + ")보다 작아야 합니다.");
        }
        Random rand = new Random();
        double randomValue = i;
        for (int k = 0; k < 1000; k++) { 
            randomValue = i + (j - i) * rand.nextDouble();
        }
        //BigDecimal range = new BigDecimal(j - i);
        BigDecimal randomDecimal = new BigDecimal(randomValue);
        this.value = randomDecimal.setScale(5, RoundingMode.HALF_UP);
    }
    //12 지정조회
    @Override
    public String getValue() {
        String temp = value.toPlainString();
        return new BigDecimal(temp).toPlainString();
    }
    //12 지정조회회
    @Override
    public void setValue(String val) {
        String temp = new BigDecimal(val).toPlainString();
        this.value = new BigDecimal(temp);
    }
    //14 객체를 콘솔에 출력할 수 있다.
    @Override
    public String toString() {
        String temp = value.toPlainString();
        BigDecimal val = new BigDecimal(temp).setScale(5, RoundingMode.HALF_UP);
        return val.stripTrailingZeros().toPlainString();
    }

    //15 객체의 동등성 판단
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Scalar)) return false;
        Scalar other = (Scalar) obj;
        
        String val1 = this.value.setScale(6, RoundingMode.HALF_UP).toPlainString();
        String val2 = new BigDecimal(other.getValue()).setScale(6, RoundingMode.HALF_UP).toPlainString();
        return val1.equals(val2);
    }
    //16 값의 대소비교
    @Override
    public int compareTo(Scalar val) {
        String val1 = this.value.toPlainString();
        String val2 = val.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        return bd1.compareTo(bd2);
    }
    //17 객체 복제
    @Override
    public Scalar clone() {
        String temp = this.value.toPlainString();
        return new ScalarImpl(new BigDecimal(temp).toPlainString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    //18 스칼라 덧셈
    @Override
    public void add(Scalar other) {
        String val1 = this.value.toPlainString();
        String val2 = other.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        this.value = bd1.add(bd2);
    }
    //19 스칼라 곱셈
    @Override
    public void multiply(Scalar other) {
        String val1 = this.value.toPlainString();
        String val2 = other.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        this.value = bd1.multiply(bd2);
    }
    //24 static 스칼라 덧셈
    static Scalar add(Scalar s1, Scalar s2) {
        String val1 = s1.getValue();
        String val2 = s2.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        BigDecimal result = bd1.add(bd2);
        return new ScalarImpl(result.toPlainString());
    }
    //25 static 스칼라 곱셈
    static Scalar multiply(Scalar s1, Scalar s2) {
        String val1 = s1.getValue();
        String val2 = s2.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        BigDecimal result = bd1.multiply(bd2);
        return new ScalarImpl(result.toPlainString());
    }
}
