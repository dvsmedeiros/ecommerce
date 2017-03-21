import java.math.BigDecimal;

public class Test {
	
	public static void main(String[] args) {
		
		BigDecimal a = BigDecimal.ZERO;		
		BigDecimal b = new BigDecimal("5");
		BigDecimal c = new BigDecimal("15");
		
		a = a.add(b);		
		System.out.println(a);
		a = a.add(c);
		System.out.println(a);
		
		
		
	
	}
}
