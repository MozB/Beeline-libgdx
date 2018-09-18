package org.beelinelibgdx.actions;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class TypingAction extends TemporalAction {

    private Label label;
    private String text;

    public TypingAction(Label label, String text) {
        this(label, text, text.length() / 50f);
    }

    public TypingAction(Label label, String text, float duration) {
        super(duration);
        this.label = label;
        this.text = text;
    }

    @Override
    protected void update(float percent) {
        int bodyLength = text.length();
        int bodyEnd = (int) (bodyLength * percent);
        label.setText(text.substring(0, bodyEnd));
    }

}

