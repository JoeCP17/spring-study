package com.example.disign.observer.subject;


import com.example.disign.observer.observer.Observer;

public interface Subject {
    void registerObserver(Observer observer);

    void removeObjectObserver(Observer observer);

    void notifyObserver(); // 주제가 변경되었을때 모든 옵저버에게 변경 내용을 알리려는 메서드
}