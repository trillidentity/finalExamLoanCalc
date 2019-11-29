package pkgLogic;

public class Payment {
	
	//TODO: I've accounted for PaymentNbr, you need to add all the other fields for the class
	private int PaymentNbr;

	
	//TODO: Fix the constructor, add the fields you've added.
	public Payment(int paymentNbr) {
		super();
		PaymentNbr = paymentNbr;
	}

	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public void setPaymentNbr(int paymentNbr) {
		PaymentNbr = paymentNbr;
	}
	
	//TODO: Add getters and setters for new fields.	
}
