package com.rst.echo.entity;

/**
 * Created by echo on 16-6-11.
 * 数据库公共实体
 */
public abstract class CommonEntity {
    /**
     * ID属性
     */
    Long id;

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
