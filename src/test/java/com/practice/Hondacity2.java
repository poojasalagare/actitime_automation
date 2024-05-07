package com.practice;

public class Hondacity2 {

    public static void main(String[] arg)
    {

      Honda honda=new Honda() {
          @Override
          public void engine() {
              System.out.println("engine->anonymous class");
          }
      };

      honda.engine();
      }

}
