# Advent of Code 2022 - Day 06
This is my solution for [Advent of Code 2022 - Day 07](https://adventofcode.com/2022/day/7). This problem involves parsing input from a text file which represents Linux-style commands to navigate a directory tree. We then need to solve a few problems which involve calculating the folder sizes, and finding the smallest possible folder we could delete in order to fit an update onto our system.

My solution uses recursion throughout. I'm sure there is probably a data structure I could have used to do this more easily, but I enjoyed creating a "linked-list" like structure of my own to represent the directory tree.
