package org.beelinelibgdx.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public abstract class ModelAndActorVisibilityContract<M extends VisibleModel, A extends VisibleActor> {

    private Collection<M> models;
    private List<A> visibleActors;
    private Group parent;

    public ModelAndActorVisibilityContract(Group parent, Collection<M> models,
                                            List<A> visibleActors) {
        this.parent = parent;
        this.models = models;
        this.visibleActors = visibleActors;
    }

    public void forEachFrame(M model, A actor) {

    }

    public void onDeleteActor(A actor) {

    }

    public abstract A createActor(M model);

    public void refresh() {
        for (M model : models) {
            boolean found = false;
            for (A visibleActor : visibleActors) {
                if (visibleActor.getModel().equals(model)) {
                    found = true;
                    break;
                }
            }
            if (!found && !model.shouldRemoveFromScreen()) {
                A visibleActor = createActor(model);
                visibleActors.add(visibleActor);
                parent.addActor((Actor) visibleActor);
            }
        }

        List<A> toRemove = Lists.newArrayList();
        for (A visibleActor : visibleActors) {
            // should remove if the model is no longer present, or the model is
            // marked as should remove
            boolean containsModel = models.contains(visibleActor.getModel());
            if (!containsModel || (visibleActor.getModel().shouldRemoveFromScreen())) {
                toRemove.add(visibleActor);
            } else {
                forEachFrame((M)visibleActor.getModel(), visibleActor);
            }
        }

        for (A visibleActor : toRemove) {
            onDeleteActor(visibleActor);
            visibleActors.remove(visibleActor);
            visibleActor.remove();
        }
    }

    public List<A> getVisibleActors() {
        return visibleActors;
    }

    public void setModels(Collection<M> models) {
        this.models = models;
    }

}