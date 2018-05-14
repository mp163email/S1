package timer;


public abstract class Timer {


    private static long idGenerator = 0;


    private long timerId;


    private long createTime;


    protected long delayTime;


    private long triggerTime;


    private int sumExecuteNum;


    private int remainExecuteNum;


    private InTimerCallBack timerCallBackInterface;


    public Timer (long delayTime, int sumExecuteNum, InTimerCallBack timerCallBack) {
        this.setTimerId(++idGenerator);
        this.delayTime = delayTime;
        this.sumExecuteNum = sumExecuteNum;
        this.remainExecuteNum = sumExecuteNum;
        this.timerCallBackInterface = timerCallBack;
    }


    public final void trigger () throws Exception {
        timerCallBackInterface.callBack(this);
    }


    public final void closeTimer () {
        this.remainExecuteNum = 0;
    }


    public long getTriggerTime () {
        this.triggerTime = this.createTime + this.delayTime;
        return triggerTime;
    }

    public long getTimerId() {
        return timerId;
    }

    public void setTimerId(long timerId) {
        this.timerId = timerId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public int getSumExecuteNum() {
        return sumExecuteNum;
    }

    public void setSumExecuteNum(int sumExecuteNum) {
        this.sumExecuteNum = sumExecuteNum;
    }

    public int getRemainExecuteNum() {
        return remainExecuteNum;
    }

    public void setRemainExecuteNum(int remainExecuteNum) {
        this.remainExecuteNum = remainExecuteNum;
    }

    public InTimerCallBack getTimerCallBackInterface() {
        return timerCallBackInterface;
    }

    public void setTimerCallBackInterface(InTimerCallBack timerCallBackInterface) {
        this.timerCallBackInterface = timerCallBackInterface;
    }
}
