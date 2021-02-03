package com.example.x_smartcity_4.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/2  0:26
 */
public class GetCommitById {

    /**
     *             "num": 30,
     *             "commit": "回家",
     *             "commitTime": "2020-10-18 20:51:09",
     *             "reviewer": "abc"
     */
    private String num,commit,commitTime,reviewer;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
