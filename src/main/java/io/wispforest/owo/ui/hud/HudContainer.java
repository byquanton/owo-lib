package io.wispforest.owo.ui.hud;

import io.wispforest.owo.ui.container.VerticalFlowLayout;
import io.wispforest.owo.ui.core.Component;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Size;
import io.wispforest.owo.ui.core.Sizing;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Very simple extension of {@link VerticalFlowLayout} that
 * does not allow children to be layout-positioned, used by {@link Hud}
 */
public class HudContainer extends VerticalFlowLayout {

    protected HudContainer(Sizing horizontalSizing, Sizing verticalSizing) {
        super(horizontalSizing, verticalSizing);
    }

    @Override
    protected void mountChild(@Nullable Component child, Size space, Consumer<Component> layoutFunc) {
        if (child == null) return;

        if (child.positioning().get().type == Positioning.Type.LAYOUT) {
            throw new IllegalStateException("owo-ui HUD components must be explicitly positioned");
        } else {
            super.mountChild(child, space, layoutFunc);
        }
    }
}