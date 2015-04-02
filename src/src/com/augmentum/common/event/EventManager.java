package com.augmentum.common.event;

import java.util.List;
import java.util.Map;

import org.springframework.core.task.TaskExecutor;

public class EventManager {

    private static Map<String, List<Observer>> events;
    private static TaskExecutor taskExecutor;

    public static void dispatchEvent(String eventName, Map<String, Object> params) {
        if (events == null || events.isEmpty()) {
            return;
        }

        List<Observer> observers = events.get(eventName);
        if (observers == null) {
            return;
        }
        for (Observer observer : observers) {
            if (observer.isAsyn()) {
                EventManagerThread eventManagerThread = new EventManagerThread(observer, params);
                taskExecutor.execute(eventManagerThread);
            } else {
                observer.execute(params);
            }
        }
    }

    public void setEvents(Map<String, List<Observer>> events) {
        EventManager.events = events;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        EventManager.taskExecutor = taskExecutor;
    }

}

class EventManagerThread implements Runnable {

    private Observer observer;
    private Map<String, Object> paras;

    public EventManagerThread(Observer observer, Map<String, Object> paras) {
        this.observer = observer;
        this.paras = paras;
    }

    @Override
    public void run() {
        this.observer.execute(paras);
    }

}
