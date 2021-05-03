package fun.luomo.rpc.sms;

import fun.luomo.rpc.vo.UserInfo;

public class SendSmsImpl implements SendSms {

    @Override
    public boolean sendMail(UserInfo user) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已发送短信息给：" + user.getUserName() + "到【" + user.getPhone() + "】");
        return true;
    }
}