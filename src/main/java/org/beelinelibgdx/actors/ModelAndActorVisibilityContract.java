package org.beelinelibgdx.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public abstract class ModelAndActorVisibilityContract<M extends VisibleModel, E extends VisibleActor> {

    private Collection<M> models;
    private List<E> visibleActors;
    private Group parent;

    public ModelAndActorVisibilityContract(Group parent, Collection<M> models,
                                            List<E> visibleActors) {
        this.parent = parent;
        this.models = models;
        this.visibleActors = visibleActors;
    }

    public abstract void forEachEntity(E entity);

    public abstract void onDeleteEntity(E entity);

    public abstract E createVisibleActor(M model);

    public void refresh() {
        for (M model : models) {
            boolean found = false;
            for (E visibleActor : visibleActors) {
                if (visibleActor.getModel().equals(model)) {
                    found = true;
                    break;
                }
            }
            if (!found && !model.shouldRemoveFromScreen()) {
                E visibleActor = createVisibleActor(model);
                visibleActors.add(visibleActor);
                parent.addActor((Actor) visibleActor);
            }
        }

        List<E> toRemove = Lists.newArrayList();
        for (E visibleActor : visibleActors) {
            // should remove if the model is no longer present, or the model is
            // marked as should remove
            boolean containsModel = models.contains(visibleActor.getModel());
            if (!containsModel || (visibleActor.getModel().shouldRemoveFromScreen())) {
                toRemove.add(visibleActor);
            } else {
                forEachEntity(visibleActor);
            }
        }

        for (E visibleActor : toRemove) {
            onDeleteEntity(visibleActor);
            visibleActors.remove(visibleActor);
            visibleActor.remove();
        }
    }

    public List<E> getVisibleActors() {
        return visibleActors;
    }

    public void setModels(Collection<M> models) {
        this.models = models;
    }

}