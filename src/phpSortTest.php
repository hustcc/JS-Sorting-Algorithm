<?php

/**
 * Create by wilon <github.com/wilon>
 *
 * 主要参考了 JS 的写法及网上的一些写法。
 *
 * Require: php -v >= 5.4
 * Test:    php phpSortTest.php
 */

function sortTest($func, $total = 5000)
{
    global $arr;
    if (empty($arr)) {
        $arr = range(1, $total);
        echo "Verify sort md5: ", substr(md5(json_encode($arr)), 0, 8), "\r\n";
        shuffle($arr);
    }
    list($m1, $n1) = explode(' ', microtime());
    $res = $func($arr);
    list($m2, $n2) = explode(' ', microtime());
    $time = round(($m2 - $m1) + ($n2 - $n1), 6);
    echo "  $func  {$time}s  " . substr(md5(json_encode($res)), 0, 8) . "\r\n";
}

function bubbleSort($arr)
{
    $len = count($arr);
    for ($i = 0; $i < $len; $i++) {
        for ($j = 0; $j < $len - 1 - $i; $j++) {
            if ($arr[$j] > $arr[$j+1]) {
                $tmp = $arr[$j];
                $arr[$j] = $arr[$j+1];
                $arr[$j+1] = $tmp;
            }
        }
    }
    return $arr;
}

function selectionSort($arr)
{
    $len = count($arr);
    for ($i = 0; $i < $len - 1; $i++) {
        $minIndex = $i;
        for ($j = $i + 1; $j < $len; $j++) {
            if ($arr[$j] < $arr[$minIndex]) {
                $minIndex = $j;
            }
        }
        $temp = $arr[$i];
        $arr[$i] = $arr[$minIndex];
        $arr[$minIndex] = $temp;
    }
    return $arr;
}

function insertionSort($arr)
{
    $len = count($arr);
    for ($i = 1; $i < $len; $i++) {
        $preIndex = $i - 1;
        $current = $arr[$i];
        while($preIndex >= 0 && $arr[$preIndex] > $current) {
            $arr[$preIndex+1] = $arr[$preIndex];
            $preIndex--;
        }
        $arr[$preIndex+1] = $current;
    }
    return $arr;
}

function shellSort($arr)
{
    $len = count($arr);
    $temp = 0;
    $gap = 1;
    while($gap < $len / 3) {
        $gap = $gap * 3 + 1;
    }
    for ($gap; $gap > 0; $gap = floor($gap / 3)) {
        for ($i = $gap; $i < $len; $i++) {
            $temp = $arr[$i];
            for ($j = $i - $gap; $j >= 0 && $arr[$j] > $temp; $j -= $gap) {
                $arr[$j+$gap] = $arr[$j];
            }
            $arr[$j+$gap] = $temp;
        }
    }
    return $arr;
}

function mergeSort($arr)
{
    $len = count($arr);
    if ($len < 2) {
        return $arr;
    }
    $middle = floor($len / 2);
    $left = array_slice($arr, 0, $middle);
    $right = array_slice($arr, $middle);
    return merge(mergeSort($left), mergeSort($right));
}

function merge($left, $right)
{
    $result = [];

    while (count($left) > 0 && count($right) > 0) {
        if ($left[0] <= $right[0]) {
            $result[] = array_shift($left);
        } else {
            $result[] = array_shift($right);
        }
    }

    while (count($left))
        $result[] = array_shift($left);

    while (count($right))
        $result[] = array_shift($right);

    return $result;
}

function quickSort($arr)
{
    if (count($arr) <= 1)
        return $arr;
    $middle = $arr[0];
    $leftArray = array();
    $rightArray = array();

    for ($i = 1; $i < count($arr); $i++) {
        if ($arr[$i] > $middle)
            $rightArray[] = $arr[$i];
        else
            $leftArray[] = $arr[$i];
    }
    $leftArray = quickSort($leftArray);
    $leftArray[] = $middle;

    $rightArray = quickSort($rightArray);
    return array_merge($leftArray, $rightArray);
}


function buildMaxHeap(&$arr)
{
    global $len;
    for ($i = floor($len/2); $i >= 0; $i--) {
        heapify($arr, $i);
    }
}

function heapify(&$arr, $i)
{
    global $len;
    $left = 2 * $i + 1;
    $right = 2 * $i + 2;
    $largest = $i;

    if ($left < $len && $arr[$left] > $arr[$largest]) {
        $largest = $left;
    }

    if ($right < $len && $arr[$right] > $arr[$largest]) {
        $largest = $right;
    }

    if ($largest != $i) {
        swap($arr, $i, $largest);
        heapify($arr, $largest);
    }
}

function swap(&$arr, $i, $j)
{
    $temp = $arr[$i];
    $arr[$i] = $arr[$j];
    $arr[$j] = $temp;
}

function heapSort($arr) {
    global $len;
    $len = count($arr);
    buildMaxHeap($arr);
    for ($i = count($arr) - 1; $i > 0; $i--) {
        swap($arr, 0, $i);
        $len--;
        heapify($arr, 0);
    }
    return $arr;
}

function countingSort($arr, $maxValue = null)
{
    if ($maxValue === null) {
        $maxValue = max($arr);
    }
    for ($m = 0; $m < $maxValue + 1; $m++) {
        $bucket[] = null;
    }

    $arrLen = count($arr);
    for ($i = 0; $i < $arrLen; $i++) {
        if (!array_key_exists($arr[$i], $bucket)) {
            $bucket[$arr[$i]] = 0;
        }
        $bucket[$arr[$i]]++;
    }

    $sortedIndex = 0;
    foreach ($bucket as $key => $len) {
        if ($len !== null) $arr[$sortedIndex++] = $key;
    }

    return $arr;
}

function bucketSort($arr, $bucketSize = 5)
{
    if (count($arr) === 0) {
      return $arr;
    }

    $minValue = $arr[0];
    $maxValue = $arr[0];
    for ($i = 1; $i < count($arr); $i++) {
      if ($arr[$i] < $minValue) {
          $minValue = $arr[$i];
      } else if ($arr[$i] > $maxValue) {
          $maxValue = $arr[$i];
      }
    }

    $bucketCount = floor(($maxValue - $minValue) / $bucketSize) + 1;
    $buckets = array();
    for ($i = 0; $i < count($buckets); $i++) {
        $buckets[$i] = [];
    }

    for ($i = 0; $i < count($arr); $i++) {
        $buckets[floor(($arr[$i] - $minValue) / $bucketSize)][] = $arr[$i];
    }

    $arr = array();
    for ($i = 0; $i < count($buckets); $i++) {
        $bucketTmp = $buckets[$i];
        sort($bucketTmp);
        for ($j = 0; $j < count($bucketTmp); $j++) {
            $arr[] = $bucketTmp[$j];
        }
    }

    return $arr;
}

function radixSort($arr, $maxDigit = null)
{
    if ($maxDigit === null) {
        $maxDigit = max($arr);
    }
    $counter = [];
    for ($i = 0; $i < $maxDigit; $i++) {
        for ($j = 0; $j < count($arr); $j++) {
            preg_match_all('/\d/', (string) $arr[$j], $matches);
            $numArr = $matches[0];
            $lenTmp = count($numArr);
            $bucket = array_key_exists($lenTmp - $i - 1, $numArr)
                ? intval($numArr[$lenTmp - $i - 1])
                : 0;
            if (!array_key_exists($bucket, $counter)) {
                $counter[$bucket] = [];
            }
            $counter[$bucket][] = $arr[$j];
        }
        $pos = 0;
        for ($j = 0; $j < count($counter); $j++) {
            $value = null;
            if ($counter[$j] !== null) {
                while (($value = array_shift($counter[$j])) !== null) {
                    $arr[$pos++] = $value;
                }
          }
        }
    }

    return $arr;
}

$total = 2000;

sortTest('bubbleSort', $total);
sortTest('selectionSort', $total);
sortTest('insertionSort', $total);
sortTest('shellSort', $total);
sortTest('mergeSort', $total);
sortTest('quickSort', $total);
sortTest('heapSort', $total);
sortTest('countingSort', $total);
sortTest('bucketSort', $total);
sortTest('radixSort', $total);
