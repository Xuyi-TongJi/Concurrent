package edu.seu.guardedSuspension.mailBoxes;

import edu.seu.guardedSuspension.GuardedObject;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * 解耦类
 */
public class MailBoxes {
    // 必须使用线程安全对象
    private static final Map<Integer, GuardedObject> boxes = new Hashtable<>();

    private static int id = 1;

    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds(){
        return boxes.keySet();
    }

    public static GuardedObject getGuardedObject(int id){
        return boxes.remove(id);
    }
}
