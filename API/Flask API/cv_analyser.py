from pyresparser import ResumeParser
import numpy as np
import os
import tempfile

def get_skills(input):
    output = dict()
    for resume_entry in input:
        with tempfile.NamedTemporaryFile(suffix=".pdf", delete=False) as temp_file:
            temp_file.write(resume_entry.read())
            temp_file_name = temp_file.name
        data = ResumeParser(temp_file_name).get_extracted_data()
        output[data["email"]] = data["skills"]
        os.unlink(temp_file_name)
    return output

def levenshtein_distance(str1, str2):
    len_str1 = len(str1) + 1
    len_str2 = len(str2) + 1

    d = np.zeros((len_str1, len_str2))

    for i in range(len_str1):
        d[i][0] = i
    for j in range(len_str2):
        d[0][j] = j

    for i in range(1, len_str1):
        for j in range(1, len_str2):
            cost = 0 if str1[i-1] == str2[j-1] else 1
            d[i][j] = min(d[i-1][j] + 1,     
                          d[i][j-1] + 1,     
                          d[i-1][j-1] + cost)

    return d[-1][-1]


def skill_similarity_name_score(skill_name1:str, skill_name2:str):
    lev_distance = levenshtein_distance(skill_name1.lower(), skill_name2.lower())

    max_len = max(len(skill_name1), len(skill_name2))
    if max_len == 0:
        return 1.0  
    similarity_score = 1 - (lev_distance / max_len)

    return similarity_score


def skills_match_score(wanted_skills:list,actual_skills:list):
    total_score = 0
    count = 0

    for wanted_skill in wanted_skills:
        for actual_skill in actual_skills:
            similarity = skill_similarity_name_score(wanted_skill, actual_skill)
            total_score += similarity
            count += 1

    if count == 0:
        return 0

    return total_score / count
    