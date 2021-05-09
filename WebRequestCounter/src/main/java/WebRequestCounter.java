package main.java;

public interface WebRequestCounter {
  void increment();
 long getCurrentCount();
 void flush();
}
