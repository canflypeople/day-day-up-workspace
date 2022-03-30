package com.zmji.year.three.month.three;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zhongmou.ji
 * @date : 2022/3/18 9:01 上午
 **/
public class LeetCode2043 {

    class Bank {

        private ReentrantLock[] locks;
        private long[] balance;

        public Bank(long[] balance) {
            locks = new ReentrantLock[balance.length];
            for (int i = 0; i < balance.length; i++) {
                locks[i] = new ReentrantLock();
            }
            this.balance = new long[balance.length];
            System.arraycopy(balance, 0, this.balance, 0, balance.length);
        }

        // 转账， 从1账户转到2账户
        public boolean transfer(int account1, int account2, long money) {
            // 需要把账户1的账户给锁住
            long balance1 = balance[account1];
            if (balance1 < money) {
                return false;
            }
            balance[account1] = balance[account1] - money;
            balance[account2] = balance[account2] = money;
            return true;
        }

        // 存款
        public boolean deposit(int account, long money) {
            synchronized (locks[account]) {
                balance[account] = balance[account] + money;
            }
            return true;
        }

        // 取款
        public boolean withdraw(int account, long money) {
            synchronized (locks[account]) {
                if (balance[account] < money) {
                    return false;
                }
                balance[account] = balance[account] - money;
            }
            return true;
        }
    }

}
