package com.sbt.javaschool.rnd;

public class Person {

    private final boolean man;
    private final String name;
    private Person spouse = null;

    public Person getSpouse() {
        return spouse;
    }

    public boolean isMan() {
        return man;
    }

    public  Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }


    public boolean marry(Person person) {
        if (this.man == person.isMan() |
                this.spouse == person      ) {
            return false;
        }
        this.divorce();

        this.spouse = person;
        person.marry(this);

        return true;
    }

    public  boolean divorce() {
        if (this.spouse == null) {
            return  false;
        }

        Person p = this.spouse;
        this.spouse = null;
        p.divorce();

        return  true;
    }
}
