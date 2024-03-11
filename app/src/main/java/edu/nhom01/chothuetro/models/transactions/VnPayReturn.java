package edu.nhom01.chothuetro.models.transactions;

import com.google.gson.annotations.SerializedName;

public class VnPayReturn {
    @SerializedName("TerminalID")
    private String terminalId;
    @SerializedName("ClientTransacID")
    private String clientTransactId;
    @SerializedName("ServerTransacID")
    private String serverTransactId;
    @SerializedName("PaymentAmount")
    private double paymentAmount;
    @SerializedName("TransacStatus")
    private int transactionStatus;
    @SerializedName("ReturnText")
    private String message;
    @SerializedName("BankCode")
    private String bankCode;

    public VnPayReturn() { }

    @SerializedName("TerminalID")
    public String getTerminalId() { return this.terminalId; }
    @SerializedName("ClientTransacID")
    public String getClientTransactId() { return this.clientTransactId; }
    @SerializedName("ServerTransacID")
    public String getServerTransactId() { return this.serverTransactId; }
    @SerializedName("PaymentAmount")
    public double getPaymentAmount() { return this.paymentAmount; }
    @SerializedName("TransacStatus")
    public int getTransactionStatus() { return this.transactionStatus; }
    @SerializedName("ReturnText")
    public String getMessage() { return this.message; }
    @SerializedName("BankCode")
    public String getBankCode() { return this.bankCode; }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
    public void setClientTransactId(String clientTransactId) {
        this.clientTransactId = clientTransactId;
    }
    public void setServerTransactId(String serverTransactId) {
        this.serverTransactId = serverTransactId;
    }
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
