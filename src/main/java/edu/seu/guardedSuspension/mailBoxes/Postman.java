package edu.seu.guardedSuspension.mailBoxes;

import edu.seu.guardedSuspension.GuardedObject;

public class Postman extends Thread{
    private int id;
    private String mail;

    public Postman(int id, String mail){
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run(){
        GuardedObject guardedObject = MailBoxes.getGuardedObject(id);
        System.out.println("送信：" + id + mail);
        guardedObject.complete(mail);
        System.out.println("送信完成:" + id + mail);
    }
}
