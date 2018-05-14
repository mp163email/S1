package timer;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ImTimerCallBackPing implements InTimerCallBack {

    @Override
    public void callBack(Timer timer) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date()) + " Hello, I`m a Ping!!!!  PingTimer总共需执行" + timer.getSumExecuteNum() + " 还剩" + timer.getRemainExecuteNum() + "次");
    }
}
