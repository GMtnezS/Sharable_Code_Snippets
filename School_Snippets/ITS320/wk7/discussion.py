# 1. Basic try and except
print('1. Basic try and except\n')

# 1.a Leveraging basic try/except for handling specific exceptions: 
print('1.a Specific Error Exceptions')

# ZeroDivisionError
try:
    result = 10 / 0
except ZeroDivisionError:
    print("Cannot divide by zero.")
# FileNotFoundError 
try:
    with open("non_existent_file.txt", "r") as file:
        content = file.read()
except FileNotFoundError:
    print("The file you are trying to read does not exist.")
# ValueError
try:
    age = int("abc")
except ValueError:
    print("Please enter a valid integer.")

print('---------------')

# 1.b Leveraging basic try/except for handling multiple specific exceptions (with a tuple!): 
print('1.b Specific Error Exceptions')

# ZeroDivisionError and ValueError and TypeError
try:
    number = 0 # Ideally int(input("Enter a number: ")) but hardcoded for demonstration purposes
    result = 10 / number
except (ZeroDivisionError, ValueError):
    print("Invalid input: enter a non-zero integer.")
try:
    number2 = '0' # Ideally int(input("Enter a number: ")) but hardcoded for demonstration purposes
    result = 10 / number2
except (ZeroDivisionError, ValueError, TypeError):
    print("Invalid input: enter a non-zero integer.")

# FileNotFoundError and PermissionError
try:
    with open("some_file.txt", "r") as file:
        content = file.read()
except (FileNotFoundError, PermissionError) as e:
    print(f"File error: {e}")

# TypeError and ValueError
try:
    num_list = ["10", 5, None]
    result = sum(num_list)
except (TypeError, ValueError) as e:
    print(f"Calculation error: {e}")

print('---------------')

# 1.c Leveraging basic try/except for handling ANY error within single except:
print('1.c ANY Error Exceptions')

try:
    result = int("not_a_number") / 0
except Exception as e:
    print(f"An unexpected error occurred: {e}")

try:
    with open("file.txt", "r") as file:
        content = file.read()
        number = int(content)
except Exception as e:
    print(f"Error processing the file: {e}")

try:
    with open("file.txt", "r") as file:
        content = file.read()
        number = int(content)
except Exception as e:
    print(f"Error processing the file: {e}")

print('_________________________________________\n')

# 2. Handling Multiple Exceptions
print('2. Handling Multiple Exceptions\n')

# 2.a Specifying multiple exceptions in a single except block using a tuple
print('2.a Multiple Exceptions in Tuple')

# TypeError, AttributeError, KeyError
try:
    data = {"key": "value"}
    result = data["nonexistent_key"] + 10  # Will raise KeyError first
except (TypeError, AttributeError, KeyError) as e:
    print(f"An error occurred: {e}")

print('---------------')

# 2.b Using separate except blocks for each exception type
print('2.b Separate Except Blocks')

# TypeError
try:
    result = "10" + 5
except TypeError:
    print("Cannot add string and integer.")

# AttributeError
try:
    result = "string".nonexistent_attribute
except AttributeError:
    print("Attribute does not exist.")

# KeyError
try:
    data = {"key": "value"}
    result = data["missing_key"]
except KeyError:
    print("Key not found in dictionary.")

print('---------------')

# 2.c Assigning the caught exception to a variable for further processing
print('2.c Exception Assignment for Processing')

# TypeError, KeyError
try:
    data = {"key": "value"}
    result = data["nonexistent_key"] + "string" + 5  # Multiple issues here
except (TypeError, KeyError) as e:
    print(f"Handling error: {e}")

print('_________________________________________\n')

# 3. Using else with try and except
print('3. Using else with try and except\n')

# 3.a try with else to execute code only if no exceptions occur
print('3.a Else Block Execution')

try:
    result = 10 / 2
except ZeroDivisionError:
    print("Division by zero error.")
else:
    print("Successful division:", result)

print('---------------')

# 3.b try, except, and else for handling error-free code separately
print('3.b Separate Error-Free Handling with Else')

try:
    names = ["Alice", "Bob"]
    print(names[1])  # Valid index
except IndexError:
    print("Index out of range.")
else:
    print("Access successful:", names[1])

print('---------------')

# 3.c Logging success in else block
print('3.c Logging Success in Else Block')

try:
    items = [1, 2, 3]
    print(items[0])
except IndexError:
    print("List index out of range.")
else:
    print("Item accessed successfully:", items[0])

print('_________________________________________\n')

# 4. The finally Clause
print('4. The finally Clause\n')

# 4.a finally to ensure resource cleanup
print('4.a Resource Cleanup with finally')

try:
    file = open("file.txt", "w")
    file.write("Some data")
except IOError:
    print("File error.")
finally:
    file.close()
    print("File closed.")

print('---------------')

# 4.b Comprehensive handling with try, except, else, and finally
print('4.b Complete Handling with finally')

try:
    file = open("file.txt", "r")
    content = file.read()
except FileNotFoundError:
    print("File not found.")
else:
    print("File read successfully.")
finally:
    file.close()
    print("Ensured file closure.")

print('---------------')

# 4.c Placing cleanup code in finally
print('4.c Cleanup Code in finally')

try:
    number = int("not_a_number")
except ValueError:
    print("Conversion error.")
finally:
    print("Execution finished.")

print('_________________________________________\n')

# 5. Raising Exceptions
print('5. Raising Exceptions\n')

# 5.a Manually trigger exceptions
print('5.a Manually Raising Exceptions')

def check_positive(value):
    if value < 0:
        raise ValueError("Negative value not allowed.")
try:
    check_positive(-5)
except ValueError as e:
    print(e)

print('---------------')

# 5.b Raising exceptions with custom messages
print('5.b Custom Message on Exception')

try:
    raise RuntimeError("Custom runtime error occurred.")
except RuntimeError as e:
    print(e)

print('---------------')

# 5.c Exception chaining with raise from
print('5.c Exception Chaining with from')

try:
    try:
        open("missing_file.txt")
    except FileNotFoundError as e:
        raise RuntimeError("Could not open the file") from e
except RuntimeError as e:
    print(e)

print('_________________________________________\n')

# 6. Custom Exception Classes
print('6. Custom Exception Classes\n')

# 6.a Defining custom exception classes
print('6.a Define Custom Exception Class')

class CustomError(Exception):
    pass

try:
    raise CustomError("A custom error occurred.")
except CustomError as e:
    print(e)

print('---------------')

# 6.b Custom exception with attributes
print('6.b Custom Exception with Attributes')

class ValidationError(Exception):
    def __init__(self, message, field):
        super().__init__(message)
        self.field = field

try:
    raise ValidationError("Invalid data", "username")
except ValidationError as e:
    print(f"{e} on field: {e.field}")

print('---------------')

# 6.c Custom exceptions for meaningful handling
print('6.c Meaningful Custom Exceptions')

class AuthenticationError(Exception):
    pass

try:
    raise AuthenticationError("Authentication failed.")
except AuthenticationError as e:
    print(e)

print('_________________________________________\n')

# 7. Enriching Exceptions with Notes
print('7. Enriching Exceptions with Notes\n')

# 7.a Using add_note() to add context
print('7.a Using add_note()')

try:
    raise TypeError("Invalid type encountered")
except TypeError as e:
    e.add_note("Occurred in data processing stage")
    print(e)

print('---------------')

# 7.b Adding multiple notes for traceability
print('7.b Multiple Notes for Traceability')

try:
    raise FileNotFoundError("File missing")
except FileNotFoundError as e:
    e.add_note("Checked in main directory")
    e.add_note("Checked in backup directory")
    print(e)

print('---------------')

# 7.c Using add_note() when logging or re-raising
print('7.c Notes with Re-raising Exceptions')

try:
    try:
        raise TimeoutError("Connection timed out")
    except TimeoutError as e:
        e.add_note("Attempted reconnection 3 times")
        raise
except TimeoutError as e:
    print(e)

print('_________________________________________\n')

# Here are my sample outputs: 

# 1. Basic try and except

# 1.a Specific Error Exceptions
# Cannot divide by zero.
# The file you are trying to read does not exist.
# Please enter a valid integer.
# ---------------
# 1.b Specific Error Exceptions
# Invalid input: enter a non-zero integer.
# Invalid input: enter a non-zero integer.
# File error: [Errno 2] No such file or directory: 'some_file.txt'
# Calculation error: unsupported operand type(s) for +: 'int' and 'str'
# ---------------
# 1.c ANY Error Exceptions
# An unexpected error occurred: invalid literal for int() with base 10: 'not_a_number'
# Error processing the file: invalid literal for int() with base 10: 'Some data'
# Error processing the file: invalid literal for int() with base 10: 'Some data'
# _________________________________________

# 2. Handling Multiple Exceptions

# 2.a Multiple Exceptions in Tuple
# An error occurred: 'nonexistent_key'
# ---------------
# 2.b Separate Except Blocks
# Cannot add string and integer.
# Attribute does not exist.
# Key not found in dictionary.
# ---------------
# 2.c Exception Assignment for Processing
# Handling error: 'nonexistent_key'
# _________________________________________

# 3. Using else with try and except

# 3.a Else Block Execution
# Successful division: 5.0
# ---------------
# 3.b Separate Error-Free Handling with Else
# Bob
# Access successful: Bob
# ---------------
# 3.c Logging Success in Else Block
# 1
# Item accessed successfully: 1
# _________________________________________

# 4. The finally Clause

# 4.a Resource Cleanup with finally
# File closed.
# ---------------
# 4.b Complete Handling with finally
# File read successfully.
# Ensured file closure.
# ---------------
# 4.c Cleanup Code in finally
# Conversion error.
# Execution finished.
# _________________________________________

# 5. Raising Exceptions

# 5.a Manually Raising Exceptions
# Negative value not allowed.
# ---------------
# 5.b Custom Message on Exception
# Custom runtime error occurred.
# ---------------
# 5.c Exception Chaining with from
# Could not open the file
# _________________________________________

# 6. Custom Exception Classes

# 6.a Define Custom Exception Class
# A custom error occurred.
# ---------------
# 6.b Custom Exception with Attributes
# Invalid data on field: username
# ---------------
# 6.c Meaningful Custom Exceptions
# Authentication failed.
# _________________________________________

# 7. Enriching Exceptions with Notes

# 7.a Using add_note()
# Invalid type encountered
# ---------------
# 7.b Multiple Notes for Traceability
# File missing
# ---------------
# 7.c Notes with Re-raising Exceptions
# Connection timed out
# _________________________________________