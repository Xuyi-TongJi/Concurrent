package edu.seu.guardedSuspension.mailBoxes;

import edu.seu.guardedSuspension.GuardedObject;

public class People extends Thread{
    @Override
    public void run(){
        // 收信
        GuardedObject guardedObject = MailBoxes.createGuardedObject();
        System.out.println("收信");
        Object mail = guardedObject.get(5000);
        System.out.println("已经收到信" + guardedObject.getId() + mail);
    }
}
