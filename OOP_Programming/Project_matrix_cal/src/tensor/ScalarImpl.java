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
            throw new TensorException("스칼라 값이 올바른 숫자가 아닙니다: " + val);
        }
    }
    ScalarImpl(int i, int j) {//02번
        if (i >= j) throw new IllegalArgumentException("i must be less than j");
        Random rand = new Random();
        BigDecimal range = new BigDecimal(j - i);
        BigDecimal randomDecimal = new BigDecimal(rand.nextDouble()).multiply(range).add(new BigDecimal(i));
        this.value = randomDecimal.setScale(5, RoundingMode.HALF_UP);
    }
    @Override
    public String getValue() {
        return value.toPlainString();  // 또는 stripTrailingZeros().toPlainString()
    }
    
    @Override
    public void setValue(String val) {
        this.value = new BigDecimal(val);
    }
    @Override
    public String toString() {
        BigDecimal val = value.setScale(5, RoundingMode.HALF_UP);
        return val.stripTrailingZeros().toPlainString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Scalar)) return false;
        Scalar other = (Scalar) obj;
        return this.value.equals(new BigDecimal(other.getValue()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(Scalar other) {
        return this.value.compareTo(new java.math.BigDecimal(other.getValue()));
    }

    @Override
    public Scalar clone() {
        return new ScalarImpl(this.value.toPlainString());
    }

    @Override
    public void add(Scalar other) {
        this.value = this.value.add(new java.math.BigDecimal(other.getValue()));
    }

    @Override
    public void multiply(Scalar other) {
        this.value = this.value.multiply(new java.math.BigDecimal(other.getValue()));
    }
}
