package com.echo.rst.menu;

import com.echo.rst.entity.CommonEntity;
import com.echo.rst.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by echo on 16-6-11.
 * 菜单
 */
@Entity
@Table(name = "tbl_menu")
@JsonIgnoreProperties(value={"orders"})
public class Menu extends CommonEntity implements Serializable {

    private static final long serialVersionUID = -613794406409968601L;

    /** 菜名 */
    private String name;
    /** 描述 */
    private String description;
    /** 价格 */
    private Double price;

    @OneToMany(mappedBy = "menu")
    private Set<Order> orders;

    public Menu() {
    }

    public Menu(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
