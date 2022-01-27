"""
file: match.py
description: CSCI 665, Homework 1, Problem 4
language: python3
author: Abhishek Shah, as5553@rit.edu
        Kushal Kale, ksk7657@rit.edu
"""


def match(askers, responders):
    asker_pair = [-1 for _ in range(len(askers))]
    responder_pair = [-1 for _ in range(len(responders))]

    # to store list of free askers
    free = [None]
    # to add the askers into the list of free askers
    free.extend([i for i in range(len(askers))])

    # to get an unpaired asker
    free_asker = free.pop()
    # to count the responders asked by each asker
    count = [0 for _ in askers]

    while free_asker is not None:
        if len(askers[free_asker]) > 0:
            # get the first unasked responder for the free asker
            res = askers[free_asker][count[free_asker]]
            count[free_asker] += 1
            # if responder is unpaired
            if responder_pair[res] == -1:
                asker_pair[free_asker] = res
                responder_pair[res] = free_asker
                free_asker = free.pop()
            # if responder is free but prefers the free asker to its current pair
            elif responders[res][responder_pair[res]] > responders[res][free_asker]:
                reject = responder_pair[res]
                free.append(reject)
                asker_pair[reject] = -1
                asker_pair[free_asker] = res
                responder_pair[res] = free_asker
                free_asker = free.pop()
        else:
            free_asker = free.pop()
    return asker_pair


def main():
    n = int(input())
    # to store preference list
    askers_pref = []
    # to store preference list
    responders_pref = []
    # taking input from user
    for i in range(n):
        askers_pref.append([int(i) for i in input().split(" ")])
    for i in range(n):
        responders_pref.append([int(i) for i in input().split(" ")])

    # inverse of responders_pref
    inverse_responders = [[0 for _ in range(len(responders_pref[0]))] for _ in range(len(responders_pref))]
    for i in range(len(responders_pref)):
        for j in responders_pref[i]:
            inverse_responders[i][responders_pref[i][j]] = j

    # deep copy askers_pref into copy_askers
    copy_askers = [[] for _ in range(len(askers_pref))]
    for i in range(len(askers_pref)):
        for j in askers_pref[i]:
            copy_askers[i].append(j)

    match_1 = match(copy_askers, inverse_responders)

    # inverse of askers_pref
    inverse_askers = [[0 for _ in range(len(askers_pref[0]))] for _ in range(len(askers_pref))]
    for i in range(len(askers_pref)):
        for j in askers_pref[i]:
            inverse_askers[i][askers_pref[i][j]] = j

    match_2 = match(responders_pref, inverse_askers)

    # to invert the list of matching for group 2
    inverse_match2 = [0 for _ in range(len(askers_pref))]
    for i in range(len(match_2)):
        inverse_match2[match_2[i]] = i

    if match_1 == inverse_match2:
        print("NO")
    else:
        print("YES")


if __name__ == '__main__':
    main()
