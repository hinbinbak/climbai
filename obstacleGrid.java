package 动态规划;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("all")
public class Solution {
    public static void main(String[] args) {
//        System.out.println(new Solution().fib1(4));
//        System.out.println(new Solution().climbStairs(3));
//        int[][] obstacleGrid = {{0, 0}, {1, 1}, {0, 0}};
//        new Solution().uniquePathsWithObstacles(obstacleGrid);
//        new Solution().integerBreak(10);
//        int[] weight = {1, 3, 4};
//        int[] value = {15, 20, 30};
//        int bagsize = 4;
//        new Solution().testCompletePack(weight, value, bagsize);
        int[] coins = {1, 2, 5};
        new Solution().change(5, coins);
    }

    /*
        leetcode:509 斐波那契数
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        //确定dp数组以及下标的含义
        int[] dp = new int[n + 1];
        //dp数组初始化
        dp[0] = 0;
        dp[1] = 1;
        //确认遍历顺序
        for (int i = 2; i <= n; i++) {
            //确定递推公式
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    /*
        leetcode:70 爬楼梯
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        //1.确定dp数组以及下标的含义 dp[i]表示爬到第i层有dp[i]种方法
        int[] dp = new int[n + 1];
        //3.dp数组初始化
        dp[1] = 1;
        dp[2] = 2;
        //4.确认遍历顺序
        for (int i = 3; i <= n; i++) {
            //2.确定递推公式
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*
        leetcode:746 使用最小花费爬楼梯
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        //1.确认dp数组以及下标的含义 dp[i]表示爬到第i层的最低消耗
        int[] dp = new int[n + 1];
        //3.dp数组的初始化
        dp[0] = 0;
        dp[1] = 0;
        //4.确认遍历顺序
        for (int i = 2; i <= n; i++) {
            //2.确认递推公式
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    /*
        leetcode:62 不同路径
     */
    public int uniquePaths(int m, int n) {
        //1.确认dp数组以及下标的含义 dp[i][j]表示到(i,j)位置的路径总数
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //3.dp数组的初始化
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
        leetcode:63 不同路径II
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        //如果在起点或终点出现了障碍，直接返回0
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        //1.确认dp数组以及下标的含义 dp[i][j]表示到(i,j)位置的路径总数
        int[][] dp = new int[m][n];
        //3.dp数组的初始化
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }
        //4.确认遍历顺序
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    //2.确认递推公式
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[m - 1][n - 1];
    }

    /*
        leetcode:343 整数拆分
     */
    public int integerBreak(int n) {
        //1.确认dp数组以及下标的含义 dp[i]表示拆分i得到的最大乘积
        int[] dp = new int[n + 1];
        //3.dp数组的初始化
        dp[2] = 1;
        //4.确认遍历顺序
        for (int i = 3; i <= n; i++) {
            //j<i-1是为了避免出现dp[0]和dp[1]
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /*
        leetcode:96 不同的二叉搜索树
     */
    public int numTrees(int n) {
        //1.确认dp数组以及下标的含义 dp[i]表示i个节点不同二叉搜索树的种类
        int[] dp = new int[n + 1];
        //3.dp数组的初始化(空树算一种形态)
        dp[0] = 1;
        //4.确认遍历顺序
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) { //j表示树根的节点编号
                //左子树节点个数j-1,右子树节点个数i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /*
        01背包问题
     */
    public void testweightbagproblem(int[] weight, int[] value, int bagSize) {
        int m = weight.length, n = bagSize;
        //1.确认dp数组以及下标的含义 dp[i][j]表示从下标为[0-i]的物品里任取，放入容量为j的背包里，价值总和最大
        int[][] dp = new int[m][n + 1];
        //3.对dp数组第一列初始化，背包容量为0时，能获得的价值为0
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        //3.对dp数组第一行初始化，weight[0]>j时为值为0，否则值为value[0]
        for (int j = weight[0]; j <= n; j++) {
            dp[0][j] = value[0];
        }
        //4.确认遍历顺序
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                if (weight[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        //5.打印dp数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        01背包 滚动数组
     */
    public void testweightbagproblem2(int[] weight, int[] value, int bagSize) {
        int m = weight.length, n = bagSize;
        //1.确认dp数组以及下标的含义 dp[j]表示背包容量为j时能获得的最大价值
        int[] dp = new int[n + 1];
        //3.初始化dp数组,其余下标默认为0
        dp[0] = 0;
        //4.确认遍历顺序
        for (int i = 0; i < m; i++) {
            for (int j = n; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
            //打印dp数组
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }
    }

    /*
        leetcode:416 分割等和子集
        nums[i]，重量是nums[i]，价值也是nums[i]，背包体积是sum/2。
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2; //此时sum就相当于背包问题中的背包容量
        //1.确认dp数组以及下标的含义 dp[j]表示背包容量为j时能获得的最大子集和
        int[] dp = new int[sum + 1];
        //3.初始化dp数组
        dp[0] = 0;
        //4.确认遍历顺序
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[sum] == sum;
    }

    /*
        leetcode:1049 最后一块石头的重量
        将石头分为两堆，重量相减值最小
     */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int target = sum;
        sum /= 2;
        //将问题转化为如何使容量为sum的背包的价值总和最大
        int[] dp = new int[sum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return target - dp[sum] - dp[sum];
    }

    /*
        leetcode:494 目标和
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        if ((target + sum) % 2 != 0) {
            return 0;
        }
        int size = (target + sum) / 2;
        if (size < 0) {
            size = -size;
        }
        //填满j（包括j）这么大容积的包，有dp[j]种方法
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    /*
        完全背包
     */
    public void testCompletePack(int[] weight, int[] value, int bagSize) {
        int m = weight.length, n = bagSize;
        int[] dp = new int[n + 1];
        for (int i = 0; i < m; i++) {
            //完全背包与01背包的区别就是遍历的顺序，完全背包每个物品可以多次选中，要用正序
            for (int j = weight[i]; j <= bagSize; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
    }

    /*
        leetcode:518 零钱兑换II
     */
    public int change(int amount, int[] coins) {
        //dp[j]表示凑成金额j的货币组合数为dp[j]
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /*
        leetcode:377 组合总和IV
     */
    public int combinationSum4(int[] nums, int target) {
        //dp[j]表示凑成总数j有几种方案
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

    /*
        leetcode:322 零钱兑换
        计算并返回可以凑成总金额所需的 最少的硬币个数
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        //dp[j]表示凑足总额为j所需要的钱币的最少个数
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = max;
        }
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != max) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    /*
        leetcode:279 完全平方数
     */
    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        //dp[j]表示值为j的最少数量
        int[] dp = new int[n + 1];
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }

    /*
        leetcode:139 单词划分
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //dp[i]表示可拆分成一个或多个字典中出现过的单词
        boolean[] dp = new boolean[s.length() + 1];
        HashSet<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) { //先遍历背包，再遍历物品
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

}
