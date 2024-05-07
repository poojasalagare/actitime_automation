package com.practice;

public class Hondacity1 implements Honda{

    public  void engine()
    {
        System.out.println("engine->used implemented keyword..");
    }

    public static void main(String[] args)
    {
        Hondacity1 hondacity1=new Hondacity1();
        hondacity1.engine();
    }
}
