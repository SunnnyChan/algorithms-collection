#!/usr/bin/env bash

# 给定一个文件 file.txt，转置它的内容。
#
# 你可以假设每行列数相同，并且每个字段由 ' ' 分隔.
#
# 示例:
#
# 假设 file.txt 文件内容如下：
#
# name age
# alice 21
# ryan 30
# 应当输出：
#
# name alice ryan
# age 21 30
#
# author : sunnychan@gmail.com


# 注意点
# 行末不要输出空格

awk '{
        for(i = 1; i <= NF; i++) {
            arr[NR, i] = $i;
        }
    } END {
        for (i = 1; i <= NF; i++) {
            for (j = 1; j <= FNR; j++) {
                printf "%s", arr[j, i];
                if (j != FNR) {
                    printf " ";
                }
            }
            printf "\n";
        }

    }' test-data/transpose-file.txt
