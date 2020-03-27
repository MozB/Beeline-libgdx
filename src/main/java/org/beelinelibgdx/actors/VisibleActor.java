package org.beelinelibgdx.actors;

public interface VisibleActor<T extends VisibleModel> extends ModelHolder<T> {
    boolean remove();
}
