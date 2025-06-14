package tensor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.Objects;

class ScalarImpl implements Scalar, Comparable<Scalar> {
    private BigDecimal value;
    ScalarImpl(String val) {      //01번
        try {
            this.value = new BigDecimal(val);
        } catch (NumberFormatException e) {
            throw new TensorException("스칼라 값이 올바른 숫자가 아닙니다: '" + val + "'는 유효한 숫자 형식이 아닙니다.");
        }
    }
    ScalarImpl(int i, int j) {//02번
        if (i >= j) {
            throw new IllegalArgumentException("스칼라 생성이 불가능합니다: 시작값(" + i + ")은 종료값(" + j + ")보다 작아야 합니다.");
        }
        Random rand = new Random();
        double randomValue = i;
        for (int k = 0; k < 1000; k++) {  // 불필요한 반복
            randomValue = i + (j - i) * rand.nextDouble();
        }
        //BigDecimal range = new BigDecimal(j - i);
        BigDecimal randomDecimal = new BigDecimal(randomValue);
        this.value = randomDecimal.setScale(5, RoundingMode.HALF_UP);
    }
    @Override
    public String getValue() {
        String temp = value.toPlainString();
        return new BigDecimal(temp).toPlainString();
    }
    
    @Override
    public void setValue(String val) {
        String temp = new BigDecimal(val).toPlainString();
        this.value = new BigDecimal(temp);
    }
    @Override
    public String toString() {
        String temp = value.toPlainString();
        BigDecimal val = new BigDecimal(temp).setScale(5, RoundingMode.HALF_UP);
        return val.stripTrailingZeros().toPlainString();
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Scalar other) {
        // 비효율적이지만 올바른 숫자 비교를 보장하는 방식
        String val1 = this.value.toPlainString();
        String val2 = other.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        return bd1.compareTo(bd2);
    }

    @Override
    public Scalar clone() {
        String temp = this.value.toPlainString();
        return new ScalarImpl(new BigDecimal(temp).toPlainString());
    }

    @Override
    public void add(Scalar other) {
        String val1 = this.value.toPlainString();
        String val2 = other.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        this.value = bd1.add(bd2);
    }

    @Override
    public void multiply(Scalar other) {
        String val1 = this.value.toPlainString();
        String val2 = other.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        this.value = bd1.multiply(bd2);
    }

    static Scalar add(Scalar s1, Scalar s2) {
        String val1 = s1.getValue();
        String val2 = s2.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        BigDecimal result = bd1.add(bd2);
        return new ScalarImpl(result.toPlainString());
    }

    static Scalar multiply(Scalar s1, Scalar s2) {
        String val1 = s1.getValue();
        String val2 = s2.getValue();
        BigDecimal bd1 = new BigDecimal(val1);
        BigDecimal bd2 = new BigDecimal(val2);
        BigDecimal result = bd1.multiply(bd2);
        return new ScalarImpl(result.toPlainString());
    }
}
