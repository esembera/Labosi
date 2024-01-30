import json
import datetime

with open('Automotive.txt', 'r') as file:
    lines = file.read()

data_list = []

chunks = lines.split("\n\n")

print("Broj dokumenata: ", len(chunks), "\n")

for chunk in chunks:
    data_dict = {}
    for line in chunk.split("\n"):
        if line.isspace():
            continue

        if(len(line.strip().split(": ")) == 1):
            key = line.strip().split(": ")[0]
            value = ""
        else:
            key, value = line.strip().split(": ", 1)
        
        keys = key.split("/")
        current_dict = data_dict
        for k in keys[:-1]:
            current_dict = current_dict.setdefault(k, {})
            
        if keys[-1] == 'price':
            if value == "unknown":
                value = None
            else:
                value = float(value)
        
        if keys[-1] == 'score':
            value = float(value)
        
        if keys[-1] == 'time':
            value = datetime.datetime.fromtimestamp(int(value)).isoformat()
        current_dict[keys[-1]] = value
    data_list.append(data_dict)

json_data = json.dumps(data_list, indent=4)

with open('automotive.json', 'w') as json_file:
    json_file.write(json_data)
