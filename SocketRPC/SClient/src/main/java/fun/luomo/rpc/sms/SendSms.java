package fun.luomo.rpc.sms;


import fun.luomo.rpc.vo.UserInfo;

public interface SendSms {
    /*发送短信*/
    boolean sendMail(UserInfo user);

}
