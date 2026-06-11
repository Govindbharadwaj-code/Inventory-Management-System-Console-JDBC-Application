package com.inventory.bean;

public class Product {
    private int productId;
    private String productName;
    private String category;
    private String supplier;
    private int quantity;
    private double price;
    private int reorderLevel;

    public Product() {}
    public Product(int productId, String productName, String category, String supplier,
                   int quantity, double price, int reorderLevel) {
        this.productId=productId; this.productName=productName;
        this.category=category; this.supplier=supplier;
        this.quantity=quantity; this.price=price; this.reorderLevel=reorderLevel;
    }
    public int    getProductId()    { return productId;    }
    public String getProductName()  { return productName;  }
    public String getCategory()     { return category;     }
    public String getSupplier()     { return supplier;     }
    public int    getQuantity()     { return quantity;     }
    public double getPrice()        { return price;        }
    public int    getReorderLevel() { return reorderLevel; }
    public void setProductId(int v)        { productId=v;    }
    public void setProductName(String v)   { productName=v;  }
    public void setCategory(String v)      { category=v;     }
    public void setSupplier(String v)      { supplier=v;     }
    public void setQuantity(int v)         { quantity=v;     }
    public void setPrice(double v)         { price=v;        }
    public void setReorderLevel(int v)     { reorderLevel=v; }
    @Override
    public String toString() {
        return "Product[id="+productId+", name="+productName+", category="+category+
               ", supplier="+supplier+", qty="+quantity+", price="+price+", reorder="+reorderLevel+"]";
    }
}
