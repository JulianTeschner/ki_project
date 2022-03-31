import numpy as np
import pandas as pd
from typing import List


def count_words(data: pd.DataFrame) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len(entry.split(" ")))
    data["sentence_lenght"] = res
    return data


def count_sentiment_words(
    data: pd.DataFrame, words: List[str], title: str
) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len([word for word in entry.split(" ") if word.lower() in words]))
    data[title] = res
    return data


def count_punctuation(
    data: pd.DataFrame, punctuation_list: List[str], title: str
) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len([char for char in entry if char.lower() in punctuation_list]))
    data[title] = res
    return data


def count_negation(
    data: pd.DataFrame, negation: List[str]
) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len([word for word in entry.split(" ") if word.lower() in negation]))
    data["Negation"] = res
    return data


def start_sentence(
    data: pd.DataFrame, start: List[str], title:str
) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len([word for word in entry.split(" ", 1) if word in start]))
    data[title] = res
    return data


def count_uppercase_words(
    data: pd.DataFrame, exception: List[str]
) -> pd.DataFrame:
    res: list[int] = []
    for entry in data["text"]:
        res.append(len([word for word in entry.split(" ") if word.isupper() and word not in exception]))
    data["upper_case"] = res
    return data
    

def main():
    data = pd.read_csv("data.csv", quotechar="'")

    positive_words: list[str] = []
    negative_words: list[str] = []

    with open("positive.txt", "r") as f:
        positive_words = f.read().splitlines()

    with open("negative.txt", "r") as f:
        negative_words = f.read().splitlines()

    data = count_words(data)

    data = count_sentiment_words(data, positive_words, "positive_words")
    data = count_sentiment_words(data, negative_words, "negative_words")

    data = count_punctuation(data, [".", ",", "!", "?"], "punctuation_sum")
    data = count_punctuation(data, ["."], "punctuation_dot")
    data = count_punctuation(data, [","], "punctuation_comma")
    data = count_punctuation(data, ["!"], "punctuation_exclamation")
    data = count_punctuation(data, ["?"], "punctuation_question")
    
    data = count_negation(data, ["not", "no"])
    
    data = start_sentence(data, ["I"], "start_with_I")
    
    data = count_uppercase_words(data, ["I", "A"])

    print(data)
    # Write to file
    with open("mod_data.csv", mode="w", encoding="utf-8") as dst:
        data.to_csv(dst, quotechar="'")


if __name__ == "__main__":
    main()
