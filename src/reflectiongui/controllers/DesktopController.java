package reflectiongui.controllers;

import reflectiongui.renderers.DesktopRenderer;

/**
 * Контроллер рабочего стола (группы отображаемых объектов).
 */
public class DesktopController {
    private DesktopRenderer desktopRenderer;

    public DesktopController() {
    }

    public void addObject(Object object) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void removeObject(Object object) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Завершить приложение.
     */
    public void shutdownApplication() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public DesktopRenderer getDesktopRenderer() {
        return desktopRenderer;
    }

    public void setDesktopRenderer(DesktopRenderer desktopRenderer) {
        this.desktopRenderer = desktopRenderer;
    }
}
