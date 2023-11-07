import secrets
import re
import random

def generate_random_key():
    return ''.join(secrets.choice('01') for _ in range(56))

def generate_random_i():
    return random.randint(1, 16)

def input_key():
    while True:
        permuted_key = input('K+ (56 bits): ')
        if re.match(r'^[01]{56}$', permuted_key):
            return permuted_key
        else:
            print('The permuted K+ must have 56 bits and be in binary format (only 0s and 1s). Try again.')

def input_i():
    while True:
        block_nr = int(input('i (1-16): '))
        if block_nr>=1 and block_nr<=16:
            return block_nr
        else:
            print('i must be a number between 1-16. Try again.')

def left_shift_bits(input_string, shift_amount):
    shifted_string = input_string[shift_amount:] + input_string[:shift_amount]
    return shifted_string

def perform_left_shift(c0,d0,i):
    new_c = c0
    new_d = d0
    for a in range(1,i+1):
        if a in [1,2,9,16]:
            new_c = left_shift_bits(new_c, 1)
            new_d = left_shift_bits(new_d, 1)
        else:
            new_c = left_shift_bits(new_c, 2)
            new_d = left_shift_bits(new_d, 2)
        print(f"C{a}: {new_c}\nD{a}: {new_d}")


while True:
    try:
        print('Choose action:\n1. Manual input\n2. Random permuted key\n3. Exit')
        option = int(input('- '))
        if option == 1 or option == 2:
            if option == 1:
                key = input_key()
            elif option == 2:
                key = generate_random_key()
                print(f"Randomly generated K+: {key}")
            c0 = key[:28]
            d0 = key[-28:]  
            print(f"C0: {c0}")
            print(f"D0: {d0}")
            print('For which i would you like to find Ci and Di?\n1. Manual input\n2. Random i\n3. Exit')
            option2 = int(input('- '))
            if option2 == 1 or option2 == 2:
                if option2 == 1:
                    i = input_i()
                elif option2 == 2:
                    i = generate_random_i()
                    print(f"Randomly generated i: {i}")
                perform_left_shift(c0,d0,i)
            elif option == 3:
                exit()

        elif option == 3:
            exit()
        else:
            print('\nInput is invalid.')

    except ValueError:
        print('\nInput is invalid.')