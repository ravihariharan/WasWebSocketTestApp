package org.omnifaces.showcase.push;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class PushBean implements Serializable {

    private static AtomicLong counter = new AtomicLong();

    private boolean connected;

    @Inject @Push(channel="counter")
    private PushContext push;

    public void toggle() {
        connected = !connected;
    }

    public void increment() {
        long newvalue = counter.incrementAndGet();
        push.send("someEvent");
    }

    public boolean isConnected() {
        return connected;
    }

    public Long getCount() {
        return counter.get();
    }

    public void updateCounterAction() {
        System.out.println("-------updateCounterAction-------");
    }
}