package com.heaven.model.ifilm;

/**
 * Created by LeoLu on 2016/12/17.
 */

public class AllChatUnread {
    /**
     * IM : 3
     * unReadTotalCount : 0
     * done : 0
     * undone : 31
     */

    private int IM;
    private int unReadTotalCount;
    private int done;
    private int undone;

    public int getIM() {
        return IM;
    }

    public void setIM(int IM) {
        this.IM = IM;
    }

    public int getUnReadTotalCount() {
        return unReadTotalCount;
    }

    public void setUnReadTotalCount(int unReadTotalCount) {
        this.unReadTotalCount = unReadTotalCount;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getUndone() {
        return undone;
    }

    public void setUndone(int undone) {
        this.undone = undone;
    }
}
