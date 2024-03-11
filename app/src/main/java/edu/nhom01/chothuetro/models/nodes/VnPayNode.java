package edu.nhom01.chothuetro.models.nodes;

import com.google.gson.annotations.SerializedName;

public class VnPayNode {
    private final String keyNode = "PaymentLink";
    @SerializedName("PaymentLink")
    private String valueNode;

    public VnPayNode(String valueNode) { }

    public String getValueNode() { return this.valueNode; }
    public void setValueNode(String valueNode) {
        this.valueNode = valueNode;
    }
}
