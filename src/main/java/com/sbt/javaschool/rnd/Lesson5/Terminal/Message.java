package com.sbt.javaschool.rnd.Lesson5.Terminal;

public class Message {
    public Cmd getCommand() {
        return command;
    }

    public void setCommand(Cmd command) {
        this.command = command;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public enum Cmd {
        getBalance,
        getMoney,
        putMoney
    }
    public enum Direction {
        server,
        terminal
    }
    private Integer money;
    private Cmd command;
    private Integer from;
    private String errorString;
    private Direction direction;
}
