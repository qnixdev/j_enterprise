package com.task_management_system.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

public abstract class AbstractRepository {
    @Autowired
    protected DataSource dataSource;

    protected java.util.Date getSimpleData(java.sql.Date date) {
        if (null != date) {
            return new java.util.Date(date.getTime());
        }

        return null;
    }

    protected java.sql.Date getSqlData(java.util.Date date) {
        if (null != date) {
            return new java.sql.Date(date.getTime());
        }

        return null;
    }
}