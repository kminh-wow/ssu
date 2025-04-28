package tensor;

import java.math.BigDecimal;
import java.util.Random;

class ScalarImpl implements Scalar {
    private BigDecimal value;

    ScalarImpl(String val) {
        this.value = new BigDecimal(val);
    }

    ScalarImpl(int i, int j) {
        if (i >= j) throw new IllegalArgumentException("i must be less than j");
        Random rand = new Random();
        int randomValue = rand.nextInt(j - i) + i;
        this.value = new BigDecimal(randomValue);
    }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }
    public String toString() { return value.stripTrailingZeros().toPlainString(); }
    public boolean equals(Object obj) {
        if (!(obj instanceof Scalar)) return false;
        return value.compareTo(((Scalar) obj).getValue()) == 0;
    }
    public Scalar clone() { return new ScalarImpl(value.toPlainString()); }
    public int compareTo(Scalar o) { return value.compareTo(o.getValue()); }
    public void add(Scalar other) { value = value.add(other.getValue()); }
    public void multiply(Scalar other) { value = value.multiply(other.getValue()); }
}
