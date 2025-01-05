package com.mahin.game.demo.game;

public class SuperContraGame implements GamingConsole{
    @Override
    public void up() {
        System.out.println("Super Contra up");
    }

    @Override
    public void down() {
        System.out.println("Super Contra down");
    }

    @Override
    public void left() {
        System.out.println("Super Contra left");
    }

    @Override
    public void right() {
        System.out.println("Super Contra right");
    }
}
