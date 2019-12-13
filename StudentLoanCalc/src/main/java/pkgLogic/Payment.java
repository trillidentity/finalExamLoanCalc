package pkgLogic;

import java.time.LocalDate;

public class Payment {
	
	//TODO: I've accounted for PaymentNbr, you need to add all the other fields for the class
	private int PaymentNbr;
	private LocalDate DueDate;
	private double Payment;
	private double AdditionalPayment;
	private double InterestPayment;
	private double Principle;
	private double EndingBalance;
	


	public Payment (double beginningBalance, 
	int paymentNbr,
	LocalDate dueDate,
	Loan loan) 
	
	{
		
		this.PaymentNbr = paymentNbr;
		this.DueDate  =dueDate;
		//before was this.Payment = this.getPMT();
		this.Payment = (beginningBalance>loan.getPMT()) ? loan.getPMT(): (beginningBalance + (beginningBalance*(loan.getInterestRate()/12)));
		this.AdditionalPayment = loan.getAddtionalPayment();
		InterestPayment = beginningBalance*(loan.getInterestRate()/12);
		Principle = loan.getPMT()+ loan.getAddtionalPayment()-InterestPayment;
		EndingBalance = beginningBalance - Principle;
		
	}


	
	//TODO: Fix the constructor, add the fields you've added.
	/*public Payment(int paymentNbr) {
		super();
		PaymentNbr = paymentNbr;
	} */



	public void setPaymentNbr(int paymentNbr) {
		PaymentNbr = paymentNbr;
	}
	
	//TODO: Add getters and setters for new fields.	
	
	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public LocalDate getDueDate() {
		return DueDate;
	}

	public double getPayment() {
		return Payment;
	}

	
	public double getAdditionalPayment() {
		return AdditionalPayment;
	}
	
	public double getInterestPayment() {
		return InterestPayment;
	}
	
	public double getPrinciple() {
		return Principle;
	}
	
	public double getEndingBalance() {
		return EndingBalance;
	}
	
	public double getTotalPayment() {
		return this.getPayment()+ this.AdditionalPayment;
	}
}
