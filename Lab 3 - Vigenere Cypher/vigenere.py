romanian_alphabet = "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ"


def perform_cipher(action):
    result = ''

    while True:
        key = input('Secret key (must be at least 7 characters): ')
        key = key.replace(' ', '').upper()

        if set(key).issubset(set(romanian_alphabet)) and len(key) >= 7:
            break
        else:
            print('The key should only contain letters of the Romanian alphabet and have a length of at least 7. Try again.')

    if action == 1:
        operation = 'encrypt'
    elif action == 2:
        operation = 'decrypt'

    while True:
        text = input(f'Enter the text you want to {operation}: ')
        text = text.replace(' ', '').upper()

        if set(text).issubset(set(romanian_alphabet)):
            break
        else:
            print(
                'The text should only contain letters of the Romanian alphabet. Try again.')

    for i, char in enumerate(text):
        shift = romanian_alphabet.find(key[i % len(key)])
        if action == 2:
            shift = -shift
        result += romanian_alphabet[(romanian_alphabet.find(char) +
                                     shift) % len(romanian_alphabet)]

    return result


while True:
    try:
        print('Choose action:\n1) Encryption\n2) Decryption\n3)Exit')
        option = int(input())

        if option == 3:
            exit()
        elif option in [1, 2]:
            if option == 1:
                encryptedText = perform_cipher(option)
                print('The encrypted text is:', end='')
                print(encryptedText)
            elif option == 2:
                decryptedText = perform_cipher(option)
                print('The decrypted text is:', end='')
                print(decryptedText)
        else:
            print('\nInput is invalid.')

    except ValueError:
        print('\nInput is invalid. Try again')
