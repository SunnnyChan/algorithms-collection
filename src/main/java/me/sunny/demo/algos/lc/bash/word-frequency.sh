#!/usr/bin/env bash

# 写一个 bash 脚本以统计一个文本文件 words.txt 中每个单词出现的频率。
#
# 为了简单起见，你可以假设：
#
# words.txt只包括小写字母和 ' ' 。
# 每个单词只由小写字母组成。
# 单词间由一个或多个空格字符分隔。
# 示例:
#
# 假设 words.txt 内容如下：
#
# the day is sunny the the
# the sunny is is
# 你的脚本应当输出（以词频降序排列）：
#
# the 4
# is 3
# sunny 2
# day 1
#
# author : sunnychan@gmail.com

awk '{
        for (i = 1; i <= NF; i++){
            arr[$i]++;
        }
    } END {
        for(i in arr) {
            printf "%s %s\n", i, arr[i];
        }
    }
' test-data/words.txt | sort -n -r -k 2
