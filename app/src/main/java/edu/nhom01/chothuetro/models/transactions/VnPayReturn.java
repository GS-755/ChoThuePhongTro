package edu.nhom01.chothuetro.models.transactions;

public class VnPayReturn {
    /*
        public string TerminalID { get => this.terminalID; set => this.terminalID = value; }
        public string ClientTransacID { get => this.clientTransacID; set => this.clientTransacID = value; }
        public string ServerTransacID { get => this.serverTransacID; set => this.serverTransacID = value; }
        public double PaymentAmount { get => this.paymentAmount; set => this.paymentAmount = value; }
        public int TransacStatus { get => this.transacStatus; set => this.transacStatus = value; }
        public string ReturnText { get => this.returnText; set => this.returnText = value; }
        public string BankCode { get => this.bankCode; set => this.bankCode = value; }
    */
    private String terminalId;
    private String clientTransactId;
    private String serverTransactId;
    private double paymentAmount;
    private int transactionStatus;
    private String message;
    private String bankCode;

    public VnPayReturn() { }

    public String getTerminalId() { return this.terminalId; }
    public String getClientTransactId() { return this.clientTransactId; }
    public String getServerTransactId() { return this.serverTransactId; }
    public double getPaymentAmount() { return this.paymentAmount; }
    public int getTransactionStatus() { return this.transactionStatus; }
    public String getMessage() { return this.message; }
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
