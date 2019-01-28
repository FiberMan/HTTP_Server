package com.filk.reflection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryGeneratorTest {
    @Test
    public void selectAll() {
        QueryGenerator<Person> queryGenerator = new QueryGenerator<>(Person.class);
        String getAllSql = queryGenerator.selectAll();
        String expectedSql = "SELECT id, person_name, salary FROM persons;";
        assertEquals(expectedSql, getAllSql);
    }

    @Test
    public void insert() {
        QueryGenerator<Person> queryGenerator = new QueryGenerator<>(Person.class);
        Person person = new Person("Tom", 1000);
        String insertSql = queryGenerator.insert(person);
        String expectedSql = "INSERT INTO persons(id, person_name, salary) VALUES (1, 'Tom', 1000.0);";
        assertEquals(expectedSql, insertSql);
    }

    @Test
    public void update() {
        QueryGenerator<Person> queryGenerator = new QueryGenerator<>(Person.class);
        Person person = new Person("Tom", 1000);
        String updateSql = queryGenerator.update(person, 99);
        String expectedSql = "UPDATE persons SET person_name = 'Tom', salary = 1000.0 WHERE id = 99;";
        assertEquals(expectedSql, updateSql);
    }

    @Test
    public void getById() {
        QueryGenerator<Person> queryGenerator = new QueryGenerator<>(Person.class);
        String selectSQL = queryGenerator.getById( 99);
        String expectedSql = "SELECT id, person_name, salary FROM persons WHERE id = 99;";
        assertEquals(expectedSql, selectSQL);
    }

    @Test
    public void deleteById() {
        QueryGenerator<Person> queryGenerator = new QueryGenerator<>(Person.class);
        String deleteSQL = queryGenerator.deleteById( 99);
        String expectedSql = "DELETE FROM persons WHERE id = 99;";
        assertEquals(expectedSql, deleteSQL);
    }
}
