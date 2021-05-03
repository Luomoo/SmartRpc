import fun.luomo.SClientApplication;
import fun.luomo.rpc.sms.NormalBusi;
import fun.luomo.rpc.sms.SendSms;
import fun.luomo.rpc.vo.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SClientApplication.class)
class RpcClientApplicationTests {

    @Autowired
    private NormalBusi normalBusi;
    @Autowired
    private SendSms sendSms;

    @Test
    void rpcTest() {
        long start = System.currentTimeMillis();
        normalBusi.business();

        /*发送邮件*/
        UserInfo userInfo = new UserInfo("luomo","13811111111");
        System.out.println("Send mail: "+ sendSms.sendMail(userInfo));
        System.out.println("共耗时："+(System.currentTimeMillis()-start)+"ms");

    }

}