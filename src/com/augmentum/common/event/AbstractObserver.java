package com.augmentum.common.event;

public abstract class AbstractObserver implements Observer {

    protected boolean asyn = false;

    @Override
    public boolean isAsyn() {
        return asyn;
    }

    public void setAsyn(boolean asyn) {
        this.asyn = asyn;
    }

}