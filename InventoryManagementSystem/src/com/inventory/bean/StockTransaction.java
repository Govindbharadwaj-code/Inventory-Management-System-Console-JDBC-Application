package com.inventory.bean;

public class StockTransaction {
    private int transactionId;
    private int productId;
    private String transactionType;
    private int quantity;
    private String transactionDate;
    private String remarks;

    public StockTransaction() {}
    public StockTransaction(int transactionId, int productId, String transactionType,
                            int quantity, String transactionDate, String remarks) {
        this.transactionId=transactionId; this.productId=productId;
        this.transactionType=transactionType; this.quantity=quantity;
        this.transactionDate=transactionDate; this.remarks=remarks;
    }
    public int    getTransactionId()   { return transactionId;   }
    public int    getProductId()       { return productId;       }
    public String getTransactionType() { return transactionType; }
    public int    getQuantity()        { return quantity;        }
    public String getTransactionDate() { return transactionDate; }
    public String getRemarks()         { return remarks;         }
    public void setTransactionId(int v)     { transactionId=v;   }
    public void setProductId(int v)         { productId=v;       }
    public void setTransactionType(String v){ transactionType=v; }
    public void setQuantity(int v)          { quantity=v;        }
    public void setTransactionDate(String v){ transactionDate=v; }
    public void setRemarks(String v)        { remarks=v;         }
}
