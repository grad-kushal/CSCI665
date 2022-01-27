"""
file: sortingTest.py
description: CSCI 665, Homework 1, Problem 5
language: python3
author: Abhishek Shah, as5553@rit.edu
        Kushal Kale, ksk7657@rit.edu
"""
import numpy
import copy
from time import time

mu, sigma = 0.5, 0.0001  # mean and standard deviation


# Merge Sort O(nlogn)
def merge_sort(input_array, left_index, right_index):
    if left_index >= right_index:
        return
    middle = (left_index + right_index) // 2
    merge_sort(input_array, left_index, middle)
    merge_sort(input_array, middle + 1, right_index)
    merge(input_array, left_index, right_index, middle)


def merge(input_array, left_index, right_index, middle):
    # Making copies of both arrays to merge
    left_copy = input_array[left_index:middle + 1]
    right_copy = input_array[middle + 1:right_index + 1]

    # Initial values for variables that we use to keep
    # track of where we are in each array
    left_copy_index = 0
    right_copy_index = 0
    sorted_index = left_index

    # Go through both copies until we run out of elements in one
    while left_copy_index < len(left_copy) and right_copy_index < len(right_copy):

        # If left_copy has the smaller element, put it in the sorted
        # part and then move forward in left_copy (by increasing the pointer)
        if left_copy[left_copy_index] <= right_copy[right_copy_index]:
            input_array[sorted_index] = left_copy[left_copy_index]
            left_copy_index = left_copy_index + 1
        # Opposite from above
        else:
            input_array[sorted_index] = right_copy[right_copy_index]
            right_copy_index = right_copy_index + 1

        # Regardless of where we got our element
        # move forward in the sorted part
        sorted_index = sorted_index + 1

    # If we ran out of elements either in left_copy or right_copy
    # add the remaining elements
    while left_copy_index < len(left_copy):
        input_array[sorted_index] = left_copy[left_copy_index]
        left_copy_index = left_copy_index + 1
        sorted_index = sorted_index + 1

    while right_copy_index < len(right_copy):
        input_array[sorted_index] = right_copy[right_copy_index]
        right_copy_index = right_copy_index + 1
        sorted_index = sorted_index + 1

# Bucket Sort O(n)
def bucket_sort(input_array):
    # maximum value & length of the input list to determine
    # which value goes into which bucket
    largest = max(input_array)
    length = len(input_array)
    size = largest / length

    # Create empty buckets of length equal to the input list
    buckets = [[] for _ in range(length)]

    # Put list elements into different buckets based on their indexes
    for i in range(length):
        j = int(input_array[i] / size)
        if j != length:
            buckets[j].append(input_array[i])
        else:
            buckets[length - 1].append(input_array[i])

    # Sort elements within the buckets using Insertion sort
    for i in range(length):
        insertionSort(buckets[i])


# Insertion sort for sorting individual bucket lists
def insertionSort(bucket_list):
    # We start from 1 since the first element is trivially sorted
    for index in range(1, len(bucket_list)):
        currentValue = bucket_list[index]
        currentPosition = index

        # If we haven't reached the beginning & there is an element
        # in our sorted array larger than the one we're trying to insert
        # move that element to the right
        while currentPosition >= 0 and bucket_list[currentPosition - 1] > currentValue:
            bucket_list[currentPosition] = bucket_list[currentPosition - 1]
            currentPosition = currentPosition - 1

        # We either reached the beginning of array or we found
        # an element of the sorted array that is smaller than the element
        # we're trying to insert at index currentPosition
        # Either way, insert the element at currentPosition
        bucket_list[currentPosition] = currentValue

# Insertion sort O(n2)
def insertion_sort(input_list):
    # We start from 1 since the first element is trivially sorted
    for index in range(1, len(input_list)):
        currentValue = input_list[index]
        currentPosition = index

        # If we haven't reached the beginning & there is an element
        # in our sorted array larger than the one we're trying to insert
        # move that element to the right
        while currentPosition > 0 and input_list[currentPosition - 1] > currentValue:
            input_list[currentPosition] = input_list[currentPosition - 1]
            currentPosition = currentPosition - 1

        # We either reached the beginning of array or we found
        # an element of the sorted array that is smaller than the element
        # we're trying to insert at index currentPosition
        # Either way, insert the element at currentPosition
        input_list[currentPosition] = currentValue


# arrays for passing as argument to the function
array = []
array_merge = []
array_insertion = []
array_bucket = []

# random floating point numbers with a uniform distribution
# range [0, 1)
for _ in range(10000):
    array.append(numpy.random.uniform(0, 1))

# Comment uniform one to use the gaussian one

# random floating point numbers with a Gaussian (normal)
# distribution with µ = 0.5 and σ = 0.0001
# for _ in range(1000):
#     array.append(numpy.random.normal(mu, sigma))

# keeping same arrays for analysis
array_merge = copy.deepcopy(array)
array_insertion = copy.deepcopy(array)
array_bucket = copy.deepcopy(array)


def test():
    print()
    # calling merge sort
    start_merge = time()
    merge_sort(array_merge, 0, len(array_merge) - 1)
    elapsed = (time() - start_merge)
    if elapsed > float(180.0):
        print("Merge Sort took more than 3 minutes for n = {}".format(len(array_merge)))
    else:
        print(f"Time taken by Merge Sort: ", elapsed, "for n = {}".format(len(array_merge)))

    # calling insertion sort
    start_ins = time()
    insertion_sort(array_insertion)
    elapsed = (time() - start_ins)
    if elapsed > float(180):
        print("Insertion Sort took more than 3 minutes for n = {}".format(len(array_insertion)))
    else:
        print(f"Time taken by Insertion Sort: ", elapsed, "for n = {}".format(len(array_insertion)))

    # calling bucket sort
    start_buc = time()
    bucket_sort(array_bucket)
    elapsed = (time() - start_buc)
    if elapsed > float(180):
        print("Bucket Sort took more than 3 minutes for n = {}".format(len(array_bucket)))
    else:
        print(f"Time taken by Bucket Sort: ", elapsed, "for n = {}".format(len(array_bucket)))

if __name__ == '__main__':
    test()