package org.beelinelibgdx.actors;

public interface ModelHolder<M extends VisibleModel> {

    M getModel();

    void refresh();

}
