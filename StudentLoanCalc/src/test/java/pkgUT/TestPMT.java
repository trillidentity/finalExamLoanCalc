package pkgUT;


import org.apache.poi.ss.formula.functions.*;
import static org.junit.jupiter.api.Assertions.*;
import app.LoanCalcHelper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

public class TestPMT {

	@Test
	public void TestPMT() {
		
		//	PMT is a standard function included in apache POI.
		//	For a given r (rate), n (number of payments), p (present value), f (future value), t (how compounding is applied)
		//	this function will determine payment
		
		//	This is an example with known values
		//	PMT returns with negative values (this is typical accounting).  
		
		double PMT;
		double r = 0.07 / 12;
		double n = 20 * 12;
		double p = 150000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));		
		double PMTExpected = 1162.95;		
		assertEquals(PMTExpected, PMT, 0.01);
	}
	@Test
	public void testGetMonthlyPayment() {
		double PMT;
		double r = 0.07 / 12;
		int n = 15 * 12;
		double p = 200000;
		double f = 0;
		boolean t = false;
		PMT = Math.abs(FinanceLib.pmt(r, n, p, f, t));

		LoanCalculator loanCalculator = new LoanCalculator();
		loanCalculator.calculateLoan(p, n / 12, r * 12, LocalDate.now(), 0);

		double totalPayment = loanCalculator.getMonthlyPayment();
		double PMTExpected = 1797.66;
		assertEquals(PMTExpected, PMT, 0.01);
		assertEquals(PMTExpected, totalPayment, 0.01);
	}

	@Test
	public void testGetTotalPayment() {
		double r = 0.07 / 12;
		int n = 15 * 12;
		double p = 200000;

		LoanCalculator loanCalculator = new LoanCalculator();
		loanCalculator.calculateLoan(p, n / 12, r * 12, LocalDate.now(), 0);

		double totalPayment = loanCalculator.getTotalPayment();

		double totalExpected =  323577.84;
		assertEquals(totalExpected, totalPayment, 0.01);
	}

}

 

