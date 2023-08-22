#!/bin/bash

# List of words
words=("apple" "banana" "cherry" "grape" "orange")

# Save words to a text file
wordlist_file="my_wordlist.txt"
for word in "${words[@]}"; do
    echo "$word" >> "$wordlist_file"
done

echo "Wordlist created: $wordlist_file"
