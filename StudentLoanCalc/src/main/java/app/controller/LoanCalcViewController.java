package app.controller;

import app.StudentCalc;
import app.LoanCalcHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgLogic.Payment;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	private LoanCalcHelper loanCalcHelper = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private Label lblTotalPayments;

	@FXML
	private TextField AdditionalPayment;

	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private TableView<Payment> tvResults;
	
	@FXML
	private TableColumn<Payment, Integer> colPaymentNumber;
	
	@FXML
	private Label ratePerMonth;

	private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
	
	//TODO: Account for all the other columns	
	@FXML
	public TableColumn<Payment, String> paymentNbr, dueDate, payment, additionalPayment, interest, principle, balance;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPaymentNumber.setCellValueFactory(new PropertyValueFactory<>("paymentNbr"));
		//TODO: Add a 'setCellValueFactor' entry for each column, mapping to each attribute in Payment
		
		dueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
		payment.setCellValueFactory(new PropertyValueFactory<>("Payment"));
		additionalPayment.setCellValueFactory(new PropertyValueFactory<>("AdditionalPayment"));
		interest.setCellValueFactory(new PropertyValueFactory<>("Interest"));
		principle.setCellValueFactory(new PropertyValueFactory<>("Principle"));
		balance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
		tvResults.setItems(paymentList);
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	public void setCalcHelper(LoanCalcHelper lc) {
		this.loanCalcHelper = lc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {

		//	Examples- how to read data from the form
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());		
		lblTotalPayments.setText("123");		
		LocalDate localDate = PaymentStartDate.getValue();
		
		loanCalcHelper.calculateLoan(dLoanAmount, numberOfYears, annualInterestRate, startDate, additionalPayment);

		Payment[] paymentDetails = loanCalcHelper.getMonthlyPaymentDetails();

		double totalPayments = loanCalcHelper.getTotalPayment();
		lblTotalPayments.setText("" + totalPayments);

		double totalInterest = loanCalcHelper.getTotalInterest();
		lblTotalInterest.setText("" + totalInterest);

		double monthlyRate = loanCalcHelper.getMonthlyRate();
		ratePerMonth.setText(monthlyRate + "%");

		ObservableList<Payment> paymentDetailsList = FXCollections.observableArrayList();
		 for (Payment pd: paymentDetails) {
		 	paymentDetailsList.addAll(pd);
		 }
		 tvResults.setItems(paymentDetailsList);

		loanCalcHelper.reset();
		/*
		 * When this button is clicked, you need to do the following:
		 * 
		 * 1) Clear the table
		 * 2) Clear the results fields (Total Payments, Total Interest)
		 * 3) You're going to create 'n' payments based on the data you give.  You'll calculate and
		 * 		carry forward 'balance', because you're going to have to hand calculate that month's
		 * 		interest.
		 * Payment# - you'll set this, counting from 1 to N
		 * Due Date - based on the given date.  method .plusMonths(1) will calculate date + 1 month.
		 * Payment  - Calculate based on PMT function (which is your minimum payment)
		 * Additional Payment - based on Additional Payment given by user
		 * Interest - Calculate based on 
		 */
	}
	@FXML
	private void btnReset(ActionEvent event) {
		LoanAmount.clear();
		InterestRate.clear();
		NbrOfYears.clear();
		PaymentStartDate.setValue(null);
		tvResults.getItems().clear();
		loanCalcHelper.reset();
		lblTotalPayments.setText("");
		lblTotalInterest.setText("");
		ratePerMonth.setText("");
	}
}
