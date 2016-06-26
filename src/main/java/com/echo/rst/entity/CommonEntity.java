package com.echo.rst.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by echo on 16-6-11.
 * 数据库公共实体
 */
@MappedSuperclass
public abstract class CommonEntity {
    /**
     * ID属性
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonEntity that = (CommonEntity) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
