package com.chen.mybatis.util.generator;

import com.chen.mybatis.comm.BaseEntity;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * mybatis generator 自定义comment生成器.
 *
 * @author chenzhiying@zbj.com
 * @title
 * @date 2019-01-10 下午2:42
 **/
public class MySQLCommentGenerator extends EmptyCommentGenerator {

    private Properties properties;

    public MySQLCommentGenerator() {
        properties = new Properties();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String author = "chenzhiying@zbj.com";
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setInitializationString("1L");
        topLevelClass.addField(field);

        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("Serializable");
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addSuperInterface(fqjt);
        topLevelClass.addImportedType(imp);

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * " + fullyQualifiedTable.getIntrospectedTableName());
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        Class<BaseEntity> baseEntityClass = BaseEntity.class;

        java.lang.reflect.Field[] declaredFields = baseEntityClass.getDeclaredFields();

        boolean isSet = true;

        for (java.lang.reflect.Field ignored : declaredFields) {

            ignored.setAccessible(true);

            if (ignored.getName().equals(introspectedColumn.getJavaProperty())) {
                isSet = false;
                continue;
            }

        }

        // 获取列注释
        String remarks = introspectedColumn.getRemarks();

        if (isSet == true) {
            field.addJavaDocLine("// "+remarks);
        }

    }
}
