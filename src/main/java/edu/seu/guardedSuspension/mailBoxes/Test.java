package edu.seu.guardedSuspension.mailBoxes;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Thread.sleep(1000);
        for (Integer id : MailBoxes.getIds()){
            new Postman(id, "内容" + id).start();
        }
    }
}
