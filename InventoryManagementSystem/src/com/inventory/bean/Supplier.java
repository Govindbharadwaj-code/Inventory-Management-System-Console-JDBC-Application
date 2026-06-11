package com.inventory.bean;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String contactPerson;
    private String phone;
    private String email;
    private String city;

    public Supplier() {}
    public Supplier(int supplierId, String supplierName, String contactPerson,
                    String phone, String email, String city) {
        this.supplierId=supplierId; this.supplierName=supplierName;
        this.contactPerson=contactPerson; this.phone=phone;
        this.email=email; this.city=city;
    }
    public int    getSupplierId()    { return supplierId;    }
    public String getSupplierName()  { return supplierName;  }
    public String getContactPerson() { return contactPerson; }
    public String getPhone()         { return phone;         }
    public String getEmail()         { return email;         }
    public String getCity()          { return city;          }
    public void setSupplierId(int v)          { supplierId=v;    }
    public void setSupplierName(String v)     { supplierName=v;  }
    public void setContactPerson(String v)    { contactPerson=v; }
    public void setPhone(String v)            { phone=v;         }
    public void setEmail(String v)            { email=v;         }
    public void setCity(String v)             { city=v;          }
}
