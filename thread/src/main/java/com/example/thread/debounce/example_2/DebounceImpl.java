package com.example.thread.debounce.example_2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class DebounceImpl{

    private Timer timer;
    private ConcurrentHashMap<String, TimerTask> delayedTaskMap;

    public DebounceImpl() {
        this.timer = new Timer(true);
        this.delayedTaskMap = new ConcurrentHashMap<>();
    }

    public void debounce(final String key, final Debounce debounceCallback, final long delay) {
        if(key == null || key.isEmpty() || key.trim().length() < 1 || delay < 0) return;

        cancelPreviousTasks();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                debounceCallback.execute();
                cancelPreviousTasks();
                delayedTaskMap.clear();
                if(timer != null) timer.cancel();
            }
        };

        scheduleNewTask(key, timerTask, delay);
    }

    private void cancelPreviousTasks() {
        if(delayedTaskMap == null) return;

        if(!delayedTaskMap.isEmpty()) delayedTaskMap
                .forEachEntry(1000, entry -> {
                    entry.getValue().cancel();
                    delayedTaskMap.clear();
                });
    }

    private void scheduleNewTask(String key, TimerTask timerTask, long delay) {
        if (key == null || key.isEmpty() || key.trim().length() < 1 || timerTask == null || delay < 0) return;

        if (delayedTaskMap.containsKey(key)) return;

        timer.schedule(timerTask, delay);
        delayedTaskMap.put(key, timerTask);

    }
}
