package com.rst.echo.entity;

import java.io.Serializable;

/**
 * Created by echo on 16-6-11.
 * 菜单
 */
public class Menu extends CommonEntity implements Serializable {

    private static final long serialVersionUID = -613794406409968601L;

    /** 菜名 */
    private String name;
    /** 描述 */
    private String description;
    /** 价格 */
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
