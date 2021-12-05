package com.bsli.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    private Integer productID;

    @XmlElement(name="productName")
    private String prodName;
  
    private BigDecimal price;
    private Integer unit;
    private String ProductDesc;

    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + prodName + '\'' +
                ", ProductDesc='" + ProductDesc + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                '}';
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
