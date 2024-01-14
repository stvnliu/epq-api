package me.imsonmia.epqapi.config;

import org.hibernate.dialect.MariaDBDialect;

/**
 * MySQLCustomDialect
 */
public class MySQLCustomDialect extends MariaDBDialect {
    // https://stackoverflow.com/questions/42430786/how-to-set-collation-for-table-attribute-as-utf8-bin-in-either-annotation-or-app#54993738
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci";
    }
}