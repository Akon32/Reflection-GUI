package reflectiongui.demo;


import reflectiongui.annotations.GlobalGroups;
import reflectiongui.annotations.Groups;
import reflectiongui.annotations.RenderObjectBy;
import reflectiongui.annotations.Title;
import reflectiongui.controllers.PropertyController;
import reflectiongui.grouping.GroupManager;
import reflectiongui.renderers.standard.PlainObjectRenderer;

import java.util.Collection;

@RenderObjectBy(PlainObjectRenderer.class)
public class GroupDemo {
    @GlobalGroups({"a", "b"})
    @Groups("b")
    String strAB;
    @GlobalGroups("a")
    @Groups("b")
    String strB;

    void fillGlobals(@Title("groupName") String groupName) {
        Collection<PropertyController> group = GroupManager.getInstance().lookupGlobal(groupName);
        for (PropertyController a : group) {
            a.getRenderer().setValue("global group " + groupName);
            a.updateObject();
        }
    }

    void fillLocals(@Title("groupName") String groupName) {
        Collection<PropertyController> group = GroupManager.getInstance().lookup(this, groupName);
        for (PropertyController a : group) {
            a.getRenderer().setValue("local group " + groupName);
            a.updateObject();
        }
    }

}
