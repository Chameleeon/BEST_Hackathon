package org.unibl.etf.search;

public class ChatDto {
    public String image;
    public String product;

    public ChatDto() {}

    public ChatDto(String image, String product) {
        this.image = image;
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
