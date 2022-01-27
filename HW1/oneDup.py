"""
file: oneDup.py
description: CSCI 665, Homework 1, Problem 3
language: python3
author: Abhishek Shah, as5553@rit.edu
        Kushal Kale, ksk7657@rit.edu
"""


def oneDup(input_array, low, high):
    if low > high:  # for sorted array LHS number cannot be greater than RHS
        return -1

    mid = (low + high) // 2  # index for the middle element

    # Checking if the middle element is the one repeating
    if input_array[mid] != mid + 1:
        if mid > 0 and input_array[mid] == input_array[mid - 1]:
            return mid

        # If mid element is not in correct position
        # the repeated element should be in left
        return oneDup(input_array, low, mid - 1)

    # If mid is at correct position
    # repeated one should be in right.
    return oneDup(input_array, mid + 1, high)


# reading input from user and storing it in a list
values = []
size = int(input())

for n in range(size + 2):
    numbers = int(input())
    values.append(numbers)

# calling the function and storing the result
index = oneDup(values, 0, len(values))

# if index is not None, return the repeated element
if index:
    print(values[index])
