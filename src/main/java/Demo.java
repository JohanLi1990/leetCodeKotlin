import java.math.BigDecimal;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Hello from Java");

        var fractional = new BigDecimal(0.625);
        var divisor = new BigDecimal(0.00);
        var ans = fractional.remainder(divisor);
        System.out.println(new BigDecimal(-0.0000000000).compareTo(BigDecimal.ZERO));
    }

    public static String className() {
        return Demo.class.getName();
    }
}
