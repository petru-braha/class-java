## sorting case study: java VS cpp

this module compares sort routines execution time between java and cpp solutions. the two major algorithms involved are: bubble sort and built-in quick sort. no optimization flags are allowed for either language.

30 independent runs of each implementation were analysed. the results (in milliseconds) can be found below.

| n  | bubble cpp  | bubble java | quicks cpp  | quicks java |
|:---|:-----------:|:-----------:|:-----------:|:-----------:|
| 00 | 23692 | 3382 | 15 | 3 |
| 01 | 19979 | 3272 | 14 | 3 |
| 02 | 25029 | 3479 | 5 | 3 |
| 03 | 20034 | 3286 | 15 | 3 |
| 04 | 20114 | 3339 | 15 | 4 |
| 05 | 20017 | 3246 | 15 | 3 |
| 06 | 25928 | 3211 | 5 | 3 |
| 07 | 20010 | 3221 | 17 | 4 |
| 08 | 19958 | 3341 | 6 | 4 |
| 09 | 19931 | 3264 | 17 | 3 |
| 10 | 31296 | 3315 | 6 | 4 |
| 11 | 20073 | 3287 | 16 | 4 |
| 12 | 19915 | 3257 | 6 | 3 |
| 13 | 19950 | 3280 | 5 | 4 |
| 14 | 28225 | 3311 | 5 | 3 |
| 15 | 20055 | 3232 | 14 | 3 |
| 16 | 20029 | 3218 | 14 | 4 |
| 17 | 20038 | 3390 | 17 | 4 |
| 18 | 19934 | 3393 | 16 | 4 |
| 19 | 28920 | 3313 | 12 | 4 |
| 20 | 20136 | 3259 | 14 | 3 |
| 21 | 20007 | 3255 | 21 | 3 |
| 22 | 19968 | 3470 | 15 | 4 |
| 23 | 20214 | 3216 | 14 | 5 |
| 24 | 19925 | 3418 | 6 | 3 |
| 25 | 20061 | 3236 | 6 | 4 |
| 26 | 21570 | 3253 | 16 | 5 |
| 27 | 27211 | 3280 | 6 | 3 |
| 28 | 20092 | 3253 | 17 | 3 |
| 29 | 27641 | 3231 | 14 | 3 |

| statistic | bubble cpp  | bubble java | quicks cpp  | quicks java |
|:----------|:-----------:|:-----------:|:-----------:|:-----------:|
| mean      | 21998.40 | 3296.9333 | 12.133333 | 3.5333333 |
| st-dev    | 3434.355 | 74.122421 | 4.9461468 | 0.6288102 |

we can conclude that in this particular cases of bubble sort implementation java outperforms cpp in milliseconds matter.
