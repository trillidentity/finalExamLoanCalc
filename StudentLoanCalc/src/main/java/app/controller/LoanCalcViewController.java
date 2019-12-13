package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgLogic.Loan;
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
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;

	
	@FXML
	private TableView<Payment> tvResults;
	
	@FXML
	private TableColumn<Payment, Integer> colPaymentNumber;
	
	@FXML
	private TableColumn<Payment, LocalDate> colDueDate;
	
	@FXML
	private TableColumn<Payment, Double> colPayment;
	
	@FXML 
	private TableColumn<Payment, Double> colAdditionalPayment;
	
	@FXML
	private TableColumn<Payment, Double> colInterest;
	
	@FXML
	private TableColumn<Payment, Double> colPrinciple;
	
	@FXML
	private TableColumn<Payment, Double> colBalance;
	
	private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPaymentNumber.setCellValueFactory(new PropertyValueFactory<>("paymentNbr"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
		colAdditionalPayment.setCellValueFactory(new PropertyValueFactory<>("additionalPayment"));
		colInterest.setCellValueFactory(new PropertyValueFactory<>("interest"));
		colPrinciple.setCellValueFactory(new PropertyValueFactory<>("principle"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
		
		tvResults.setItems(paymentList);
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	
	
	
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		tvResults.getItems().clear();
		lblTotalPayments.setText("");
		lblTotalInterest.setText("");
		
		Loan loan = new Loan(PaymentStartDate.getValue(), Double.parseDouble(LoanAmount.getText()),
				Double.parseDouble(InterestRate.getText()), Double.parseDouble(AdditionalPayment.getText()),
				Integer.parseInt(NbrOfYears.getText())*12);
		
		paymentList.addAll(loan.getLoanPayments());
		tvResults.setItems(paymentList);
		
		lblTotalPayments.setText(Double.toString(loan.getTotalPayments()));
		lblTotalInterest.setText(Double.toString(loan.getTotalInterest()));
			 

		
		//	Examples- how to read data from the form
		/*double dLoanAmount = Double.parseDouble(LoanAmount.getText());		
		lblTotalPayments.setText("240");
		lblTotalInterest.setText("29,000");
		LocalDate localDate = PaymentStartDate.getValue();
		*/
		
		
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
	
}