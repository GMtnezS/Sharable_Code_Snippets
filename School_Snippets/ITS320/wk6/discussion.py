import time

# 📋 List Operations! # what discovering that I can paste emojies in python comments look like! :D 
print("📋 List Operations") # what discovering that I can paste emojies in python strings look like! :D 

# Insertion
my_list = list(range(1_000_000))

# Measure appending to the end
start_time = time.time()
my_list.append(1_000_001)
end_time = time.time()
append_time = (end_time - start_time) * 1000
print(f"Appending an element to a list of 1 million items: {append_time:.5f} ms")

# Measure inserting at the beginning
start_time = time.time()
my_list.insert(0, 0)
end_time = time.time()
insert_beginning_time = (end_time - start_time) * 1000
print(f"Inserting an element at the beginning of a list: {insert_beginning_time:.5f} ms")

# Deletion
# Measure removing from the beginning
start_time = time.time()
del my_list[0]
end_time = time.time()
remove_first_time = (end_time - start_time) * 1000
print(f"Removing the first element of a list: {remove_first_time:.5f} ms")

# Measure removing from the end
start_time = time.time()
del my_list[-1]
end_time = time.time()
remove_last_time = (end_time - start_time) * 1000
print(f"Removing the last element of a list: {remove_last_time:.5f} ms")

# Lookup
# Measure accessing an element by index
start_time = time.time()
_ = my_list[len(my_list) // 2]  # Access the middle element
end_time = time.time()
lookup_middle_time = (end_time - start_time) * 1000
print(f"Accessing an element in the middle of the list: {lookup_middle_time:.5f} ms")


print('__________________________________________________')


# 🔑  Dictionary Operations # what discovering that I can paste emojies in python comments look like! :D 
print("🔑  Dictionary Operations") # what discovering that I can paste emojies in python strings look like! :D 

# Dictionary Operations
my_dict = {i: i for i in range(1_000_000)}

# Insertion
# Measure adding a new key-value pair 
# Notice that dictionaries don't have a set order, so we only need to measure it once
start_time = time.time()
my_dict[1_000_001] = 1_000_001
end_time = time.time()
dict_insert_time = (end_time - start_time) * 1000
print(f"Adding an entry to a dictionary with 1 million keys: {dict_insert_time:.5f} ms")

# Deletion
# Measure removing a key-value pair
start_time = time.time()
del my_dict[1_000_001]
end_time = time.time()
dict_delete_time = (end_time - start_time) * 1000
print(f"Removing a key-value pair from the dictionary: {dict_delete_time:.5f} ms")

# Lookup
# Measure retrieving a value by key
start_time = time.time()
_ = my_dict[500_000]  # Access a value by key in the middle
end_time = time.time()
dict_lookup_time = (end_time - start_time) * 1000
print(f"Looking up a value in the dictionary: {dict_lookup_time:.5f} ms")

print('__________________________________________________')

# Notice that the results will vary wildly based on resources available to the script
# Even within the same computer and terminal, results will likely always vary and flunctuate. 
# It's about noticing the pattern and estimating range of performance rather than focusing on an specific time value. 

# One of my Sample Outputs:
# 📋 List Operations
# Appending an element to a list of 1 million items: 5.97000 ms
# Inserting an element at the beginning of a list: 5.54609 ms
# Removing the first element of a list: 2.10166 ms
# Removing the last element of a list: 0.00095 ms
# Accessing an element in the middle of the list: 0.01907 ms
# __________________________________________________
# 🔑  Dictionary Operations
# Adding an entry to a dictionary with 1 million keys: 0.00191 ms
# Removing a key-value pair from the dictionary: 0.00095 ms
# Looking up a value in the dictionary: 0.00310 ms

# Notice how INSERTIONS are the heaviest actions you can perform on an list.
# This output was specially critical too! 
# The average results on most executions was around 2ms, but this one reached up to 5ms!

# Surprisingly, the most consistently efficient process in the list was Removing the last element
# I knew it was a fast process, but I was shocked to see it consistently performing better than the Lookup itself

# There was even an execution in which we got almost 0ms for deleting the last item in list! 
# whereas lookup was still around 0.009, and the deletion for the dictionary was around 0.004

# Removing the last element of a list: 0.00000 ms
# VS
# Accessing an element in the middle of the list: 0.00906 ms
# Removing a key-value pair from the dictionary: 0.00405 ms

# Impressive! 

# The results for dictionary was pretty predictable and straightforward.
# However, they were not significant and important! 
# They were consistently low and never reached 1ms for ANY operation. 

# These consistently high-performing results are what makes dictionaries amazing data structures to work with. 