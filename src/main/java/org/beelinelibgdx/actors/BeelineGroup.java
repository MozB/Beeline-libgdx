package org.beelinelibgdx.actors;

import com.badlogic.gdx.scenes.scene2d.Group;


public abstract class BeelineGroup extends Group implements BeelineRemovable {

    public BeelineGroup() {
        setTransform(false);
    }

    @Override
    public abstract void refresh();

}
