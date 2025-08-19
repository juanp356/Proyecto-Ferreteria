package com.example.ferreteria.service;

import com.example.ferreteria.Model.Proceeds;
import com.example.ferreteria.controllers.viewVending;

import java.math.BigDecimal;

public class CartItem {

    public Long proceedsId;
    public String name;
    public int qty;
    public int unitPriceCents;
    public Proceeds product;
    public String formattedSubtotal;
    public String formattedUnitPrice;

    public int getSubtotalCents(){ return unitPriceCents * qty; }
    public BigDecimal getSubtotalMoney(){ return new BigDecimal(getSubtotalCents()).movePointLeft(2); }

    public String getSubtotalFormatted() {
        return viewVending.COP_FORMATTER.format(getSubtotalCents());
    }

    public String getPriceFormatted() {
        return viewVending.COP_FORMATTER.format(product.getPrice());
    }

    public void setFormattedSubtotal(String s){ this.formattedSubtotal = s; }
    public void setFormattedUnitPrice(String s){ this.formattedUnitPrice = s; }

}
