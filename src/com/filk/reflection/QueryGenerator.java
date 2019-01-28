package com.filk.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.StringJoiner;

public class QueryGenerator<T> {
    private final int MAX_FIELD_COUNT = 255;
    private String tableName;
    private String[] fieldNames = new String[MAX_FIELD_COUNT];
    private String[] fieldTypes = new String[MAX_FIELD_COUNT];
    private String[] fieldColumns = new String[MAX_FIELD_COUNT];
    private int fieldCount;

    public QueryGenerator(Class<T> clazz) {
        Table annotation = clazz.getAnnotation(Table.class);

        if (annotation == null) {
            throw new IllegalArgumentException("@Table is missing");
        }

        tableName = annotation.name().isEmpty() ? clazz.getName() : annotation.name();
        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Field declaredField : clazz.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                if (fieldCount > MAX_FIELD_COUNT) {
                    throw new RuntimeException("Class " + clazz.getName() + " has more fields than allowed (" + MAX_FIELD_COUNT + ")");
                }
                fieldNames[fieldCount] = declaredField.getName();
                fieldColumns[fieldCount] = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                fieldTypes[fieldCount] = declaredField.getType().getName();

                fieldCount++;
            }
        }
        fieldNames = Arrays.copyOf(fieldNames, fieldCount);
        fieldColumns = Arrays.copyOf(fieldColumns, fieldCount);
        fieldTypes = Arrays.copyOf(fieldTypes, fieldCount);
    }

    public String selectAll() {
        return "SELECT " + joinAllColumns() + " FROM " + tableName + ";";
    }

    public String insert(T entity) {
        String[] fieldValues = getAllValues(entity);
        StringJoiner values = new StringJoiner(", ");
        for (String fieldValue : fieldValues) {
            values.add(fieldValue);
        }
        return "INSERT INTO " + tableName + "(" + joinAllColumns() + ") VALUES (" + values + ");";
    }

    public String update(T entity, int id) {
        String[] fieldValues = getAllValues(entity);
        StringJoiner updateValues = new StringJoiner(", ");
        for (int i = 0; i < fieldValues.length; i++) {
            if(!fieldNames[i].equals("id")) {
                updateValues.add(fieldColumns[i] + " = " + fieldValues[i]);
            }
        }
        return "UPDATE " + tableName + " SET " + updateValues + " WHERE id = " + id + ";";
    }

    public String getById(int id) {
        String select = selectAll();
        return select.substring(0, select.length()-1) + " WHERE id = " + id + ";";
    }

    public String deleteById(int id) {
        return "DELETE FROM " + tableName + " WHERE id = " + id + ";";
    }

    private String joinAllColumns() {
        StringJoiner allColumns = new StringJoiner(", ");
        for (String fieldColumn : fieldColumns) {
            allColumns.add(fieldColumn);
        }
        return allColumns.toString();
    }

    private String[] getAllValues(T entity) {
        Class<?> clazz = entity.getClass();
        String[] values = new String[fieldCount];

        for (int i = 0; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];
            try {
                Field field = clazz.getDeclaredField(fieldName);
                String fieldType = field.getType().getName();
                field.setAccessible(true);
                if (fieldType.equals("int") || fieldType.equals("long") || fieldType.equals("short") || fieldType.equals("byte") || fieldType.equals("double") || fieldType.equals("float")) {
                    values[i] = field.get(entity).toString();
                } else {
                    values[i] = "'" + field.get(entity) + "'";
                }
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("No such field: " + fieldName);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Can't access field: " + fieldName);
            }
        }
        return values;
    }

    //private String[] getName
}
