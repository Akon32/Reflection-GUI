package reflectiongui.renderers;

import reflectiongui.controllers.DesktopController;

/** Объект, отвечающий за графическое представление рабочего стола. */
public interface DesktopRenderer {

    void showObjectRenderer(ObjectRenderer objectRenderer);

    void hideObjectRenderer(ObjectRenderer objectRenderer);

    void initialize(DesktopController desktopController);
}
