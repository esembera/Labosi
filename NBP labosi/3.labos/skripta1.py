import json

def parse_entry(entry_lines):
    entry = {}
    current_section = None

    for line in entry_lines:
        key, value = line.split(': ', 1)

        # Check if it's a new section (product or review)
        if key.startswith('product/') or key.startswith('review/'):
            current_section = key.split('/')[0]
            entry[current_section] = {}

        # Add key-value pairs to the current section
        if current_section:
            entry[current_section][key] = value

    return entry

# Read the data from the text file
with open('Automotive.txt', 'r') as file:
    data = file.read()

# Split the data into individual product and review blocks
entries = data.strip().split('\n\n')

# Create a list to store product and review objects
result_list = []

# Iterate through each entry and create product and review objects
for entry in entries:
    lines = entry.split('\n')
    entry_dict = parse_entry(lines)
    result_list.append(entry_dict)

# Save the result list as a JSON file
with open('output.json', 'w') as json_file:
    json.dump(result_list, json_file, indent=2)

print("Conversion completed. JSON file created.")
