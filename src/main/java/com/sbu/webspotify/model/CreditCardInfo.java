package com.sbu.webspotify.model;

public class CreditCardInfo {

    private String creditCardNumber;
    private String cardHolderName;
    private String billingAddress;
    private String ccv;
    private String expirationDate;

    public CreditCardInfo() {
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCCV() {
        return this.ccv;
    }

    public void setCCV(String ccv) {
        this.ccv = ccv;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean getIsValid() {
        int sum = 0;
        boolean alternate = false;
        for (int i = this.creditCardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
