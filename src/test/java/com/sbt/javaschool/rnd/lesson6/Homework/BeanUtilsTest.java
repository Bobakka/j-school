package com.sbt.javaschool.rnd.lesson6.Homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilsTest {

    private static class TestClass {
        private Integer test_integer = 1;
        private String test_string = "test";
        private Double test_double = 1.0d;
        private Long test_Long = 1L;

        private Integer getTest() {

            return 0;
        }

        public Integer getTest_integer() {
            return test_integer;
        }

        public void setTest_integer(Integer test_integer) {
            this.test_integer = test_integer;
        }

        public String getTest_string() {
            return test_string;
        }

        public void setTest_string(String test_string) {
            this.test_string = test_string;
        }

        public Double getTest_double() {
            return test_double;
        }

        public void setTest_double(Double test_double) {
            this.test_double = test_double;
        }

        public Long getTest_Long() {
            return test_Long;
        }

        public void setTest_Long(Long test_Long) {
            this.test_Long = test_Long;
        }
    }

    @Test
    void assign() {
        TestClass t1 = new TestClass();
        TestClass t2 = new TestClass();
        t2.setTest_integer(5);
        t2.setTest_double(5.0d);
        t2.setTest_Long(5L);
        t2.setTest_string("5");

        BeanUtils.assign(t1, t2);

        assertEquals(t2.getTest_string(), t1.getTest_string());
        assertEquals(t2.getTest_double(), t1.getTest_double());
        assertEquals(t2.getTest_Long(), t1.getTest_Long());
        assertEquals(t2.getTest_integer(), t1.getTest_integer());

    }
}