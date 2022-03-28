import numpy as np
import pandas as pd


def count_words(data: pd.DataFrame):
    res: list[int] = []
    for entry in data["text"]:
        res.append(len(entry.split(" ")))
    data["sentenceLenght"] = res
    return data


def main():
    data = pd.read_csv("data.csv", quotechar="'")
    data = count_words(data)

    # Write to file
    with open("mod_data.csv", mode="w", encoding="utf-8") as dst:
        data.to_csv(dst, quotechar="'")


if __name__ == "__main__":
    main()
