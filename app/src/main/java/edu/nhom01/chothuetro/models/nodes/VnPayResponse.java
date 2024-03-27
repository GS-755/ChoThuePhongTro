package edu.nhom01.chothuetro.models.nodes;

import com.google.gson.annotations.SerializedName;

public class VnPayResponse {
    @SerializedName("TerminalID")
    private String terminalId;
    @SerializedName("ClientTransacId")
    private String clientTransactId;
    @SerializedName("ServerTransacId")
    private String serverTransactId;
    @SerializedName("PaymentAmount")
    private double paymentAmount;
    @SerializedName("TransacStatus")
    private int transactionStatus;
    @SerializedName("ReturnText")
    private String returnText;
    @SerializedName("BankCode")
    private String bankCode;

    public VnPayResponse() { }

    @SerializedName("TerminalID")
    public String getTerminalId() { return this.terminalId; }
    @SerializedName("ClientTransacId")
    public String getClientTransactId() { return this.clientTransactId; }
    @SerializedName("ServerTransacId")
    public String getServerTransactId() { return this.serverTransactId; }
    @SerializedName("PaymentAmount")
    public double getPaymentAmount() { return this.paymentAmount; }
    @SerializedName("TransacStatus")
    public int getTransactionStatus() { return this.transactionStatus; }
    @SerializedName("ReturnText")
    public String getReturnText() { return this.returnText; }
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
    public void setReturnText(String returnText) {
        this.returnText = returnText;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
