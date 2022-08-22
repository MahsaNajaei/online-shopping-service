package com.example.onlineshoppingservice.model.domain;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Provider provider;
    @Column(name = "commentable", columnDefinition = "boolean default true")
    private boolean isCommentable;
    @Column(name = "votable", columnDefinition = "boolean default true")
    private boolean isVotable;
    @Column(columnDefinition = "boolean default true")
    private boolean publiclyReviewable;
    @Column(columnDefinition = "boolean default true")
    private boolean publiclyRepresentable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public boolean isCommentable() {
        return isCommentable;
    }

    public void setCommentable(boolean commentable) {
        isCommentable = commentable;
    }

    public boolean isVotable() {
        return isVotable;
    }

    public void setVotable(boolean votable) {
        isVotable = votable;
    }

    public boolean isPubliclyReviewable() {
        return publiclyReviewable;
    }

    public void setPubliclyReviewable(boolean publiclyReviewable) {
        this.publiclyReviewable = publiclyReviewable;
    }

    public boolean isPubliclyRepresentable() {
        return publiclyRepresentable;
    }

    public void setPubliclyRepresentable(boolean publiclyRepresentable) {
        this.publiclyRepresentable = publiclyRepresentable;
    }
}
